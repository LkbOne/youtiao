package com.example.phoebe.youtiao.commmon.util;


import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Http工具类
 * Created by hugh on 2016/9/22.
 */
@Slf4j
public class HttpUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HttpUtil.class);
	
	private static int SocketTimeout = 45000; //45秒
	private static int ConnectTimeout = 45000; //45秒
	private static Boolean SetTimeOut = true;

	/**
	 * 微信头像域名
	 */
	private final static String weChatHeadUrl = "https://wx.qlogo.cn";
	
	private static CloseableHttpClient getHttpClient() {
		RegistryBuilder<ConnectionSocketFactory> registryBuilder = RegistryBuilder.<ConnectionSocketFactory>create();
		ConnectionSocketFactory plainSF = new PlainConnectionSocketFactory();
		registryBuilder.register("http", plainSF);
		//指定信任密钥存储对象和连接套接字工厂
		try {
			KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
			//信任任何链接
			TrustStrategy anyTrustStrategy = new TrustStrategy() {
				@Override
				public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
					return true;
				}
			};
			SSLContext sslContext = SSLContexts.custom().useTLS().loadTrustMaterial(trustStore, anyTrustStrategy)
				.build();
			LayeredConnectionSocketFactory sslSF = new SSLConnectionSocketFactory(sslContext,
				SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			registryBuilder.register("https", sslSF);
		} catch (KeyStoreException | KeyManagementException | NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		Registry<ConnectionSocketFactory> registry = registryBuilder.build();
		//设置连接管理器
		PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(registry);
		
		//构建客户端
		return HttpClientBuilder.create().setConnectionManager(connManager).build();
	}
	
	/**
	 * get
	 *
	 * @param url     请求的url (不能带?)
	 * @param queries 请求的参数，在浏览器？后面的数据，没有可以传null
	 * @return
	 * @throws IOException
	 */
	public static String get(String url, Map<String, String> queries) throws IOException {
		String responseBody = "";
		//CloseableHttpClient httpClient=HttpClients.createDefault();
		//支持https
		CloseableHttpClient httpClient = getHttpClient();
		
		StringBuilder sb = new StringBuilder(url);
		
		if (queries != null && queries.keySet().size() > 0) {
			boolean firstFlag = !url.contains("?");
			
			Iterator iterator = queries.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry entry = (Map.Entry<String, String>) iterator.next();
				if (firstFlag) {
					sb.append("?" + entry.getKey() + "=" + entry.getValue());
					firstFlag = false;
				} else {
					sb.append("&" + entry.getKey() + "=" + entry.getValue());
				}
			}
		}
		
		HttpGet httpGet = new HttpGet(sb.toString());
		if (SetTimeOut) {
			RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(SocketTimeout)
				.setConnectTimeout(ConnectTimeout).build();//设置请求和传输超时时间
			httpGet.setConfig(requestConfig);
		}
		try {
			LOGGER.debug("Executing request:[{}]", httpGet.getRequestLine());
			//请求数据
			CloseableHttpResponse response = httpClient.execute(httpGet);
			LOGGER.debug(response.getStatusLine().toString());
			
			int status = response.getStatusLine().getStatusCode();
			if (status == HttpStatus.SC_OK) {
				HttpEntity entity = response.getEntity();
				// do something useful with the response body
				// and ensure it is fully consumed
				responseBody = EntityUtils.toString(entity, Consts.UTF_8);
				//EntityUtils.consume(entity);
			} else {
				LOGGER.debug("http return status error: {}", status);
				throw new ClientProtocolException("Unexpected response status: " + status);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			httpClient.close();
		}
		return responseBody;
	}
	
	/**
	 * post
	 *
	 * @param url     请求的url (不能带?)
	 * @param queries 请求的参数，在浏览器?后面的数据，没有可以传null
	 * @param params  post form 提交的参数
	 * @return
	 * @throws IOException
	 */
	public static String post(String url, Map<String, Object> queries, Map<String, Object> params) throws IOException {
		return post(url, queries, params, null, null);
	}
	
	/**
	 * 可以制定请求的格式和接受的格式
	 *
	 * @param url
	 * @param queries
	 * @param params
	 * @param requestType
	 * @param acceptType
	 * @return
	 * @throws IOException
	 */
	public static String post(String url, Map<String, Object> queries, Map<String, Object> params, String requestType, String acceptType) throws IOException {
		String responseBody = "";
		//支持https
		CloseableHttpClient httpClient = getHttpClient();
		String realUrl = joinParamters(url, queries);
		
		//指定url,和http方式
		HttpPost httpPost = new HttpPost(realUrl);
		if (SetTimeOut) {
			RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(SocketTimeout)
				.setConnectTimeout(ConnectTimeout).build();//设置请求和传输超时时间
			httpPost.setConfig(requestConfig);
		}
		//添加参数
		httpPost.setEntity(new StringEntity(GsonUtil.getGson().toJson(params), Consts.UTF_8));
		//设置请求和接受的格式
		if (requestType != null) {
			httpPost.setHeader("Content-Type", "application/json");
		}
		if (acceptType != null) {
			httpPost.setHeader("Accept", "application/json");
		}
		httpPost.setHeader("cookie", "FSAuthXC=");
		
		//请求数据
		CloseableHttpResponse response = httpClient.execute(httpPost);
		try {
			LOGGER.debug(response.getStatusLine().toString());
			
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = response.getEntity();
				responseBody = EntityUtils.toString(entity, "UTF-8");
			} else {
				LOGGER.error("http return status error: {}", response.getStatusLine().getStatusCode());
			}
		} catch (Exception e) {
			LOGGER.error("occur exception:[{}]", e);
		} finally {
			response.close();
		}
		
		return responseBody;
	}
	
	/**
	 * 获取认证cookies的请求
	 *
	 * @param url
	 * @param queries
	 * @param params
	 * @param requestType
	 * @param acceptType
	 * @return
	 * @throws IOException
	 */
	public static Map postAndDealCookies(String url, Map<String, Object> queries, Map<String, Object> params, String requestType, String acceptType) throws IOException {
		String responseBody = "";
		//支持https
		CloseableHttpClient httpClient = getHttpClient();
		String realUrl = joinParamters(url, queries);
		
		//指定url,和http方式
		HttpPost httpPost = new HttpPost(realUrl);
		
		if (SetTimeOut) {
			RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(SocketTimeout)
				.setConnectTimeout(ConnectTimeout).build();//设置请求和传输超时时间
			httpPost.setConfig(requestConfig);
		}
		//添加参数
		httpPost.setEntity(new StringEntity(GsonUtil.getGson().toJson(params), Consts.UTF_8));
		//设置请求和接受的格式
		if (requestType != null) {
			httpPost.setHeader("Content-Type", "application/json");
		}
		if (acceptType != null) {
			httpPost.setHeader("Accept", "application/json");
		}
		
		//请求数据
		CloseableHttpResponse response = httpClient.execute(httpPost);
		Map map = getFSAuthXCFromResponse(response);
		try {
			LOGGER.debug(response.getStatusLine().toString());
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = response.getEntity();
				responseBody = EntityUtils.toString(entity, "UTF-8");
				map.put("responseBody", responseBody);
			} else {
				LOGGER.error("http return status error: {}", response.getStatusLine().getStatusCode());
			}
		} catch (Exception e) {
			LOGGER.error("occur exception:[{}]", e);
		} finally {
			response.close();
		}
		
		return map;
	}
	
	private static Map getFSAuthXCFromResponse(CloseableHttpResponse response) {
		Header[] cookies = response.getHeaders("set-cookie");
		Map map = new HashMap();
		for (Header header : cookies) {
			String cookie = header.getValue();
			int i1 = cookie.indexOf("=");
			int i2 = cookie.indexOf(";");
			if (i1 != -1 && i2 != -1) {
				String value = cookie.substring(i1 + 1, i2);
				String key = cookie.substring(0, i1);
				if (key.equals("FSAuthXC")) {
					map.put(key, value);
					break;
				}
			}
		}
		return map;
	}
	
	public static Map postWithCookies(String cookies, String url, Map<String, Object> queries, Map<String, Object> params, String requestType, String acceptType) throws IOException {
		String responseBody = "";
		//支持https
		CloseableHttpClient httpClient = getHttpClient();
		String realUrl = joinParamters(url, queries);
		
		//指定url,和http方式
		HttpPost httpPost = new HttpPost(realUrl);
		if (SetTimeOut) {
			RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(SocketTimeout)
				.setConnectTimeout(ConnectTimeout).build();//设置请求和传输超时时间
			httpPost.setConfig(requestConfig);
		}
		//添加参数
		httpPost.setEntity(new StringEntity(GsonUtil.getGson().toJson(params), Consts.UTF_8));
		//设置请求和接受的格式
		if (requestType != null) {
			httpPost.setHeader("Content-Type", "application/json");
		}
		if (acceptType != null) {
			httpPost.setHeader("Accept", "application/json");
		}
		httpPost.setHeader("cookie", cookies);
		
		//请求数据
		CloseableHttpResponse response = httpClient.execute(httpPost);
		Map map = getFSAuthXCFromResponse(response);
		try {
			LOGGER.debug(response.getStatusLine().toString());
			
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = response.getEntity();
				responseBody = EntityUtils.toString(entity, "UTF-8");
				map.put("responseBody", responseBody);
			} else {
				LOGGER.error("http return status error: {}", response.getStatusLine().getStatusCode());
			}
		} catch (Exception e) {
			LOGGER.error("occur exception:[{}]", e);
		} finally {
			response.close();
		}
		
		return map;
	}
	
	/**
	 * post
	 *
	 * @param url    请求的url (不能带?)
	 * @param params post form 提交的参数
	 * @return
	 * @throws IOException
	 */
	public static String post(String url, Map<String, String> params) throws IOException {
		String responseBody = "";
		//支持https
		CloseableHttpClient httpClient = getHttpClient();
		
		StringBuilder sb = new StringBuilder(url);
		
		//指定url,和http方式
		HttpPost httpPost = new HttpPost(sb.toString());
		if (SetTimeOut) {
			RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(SocketTimeout)
				.setConnectTimeout(ConnectTimeout).build();//设置请求和传输超时时间
			httpPost.setConfig(requestConfig);
		}
		//添加参数
		httpPost.setEntity(new StringEntity(GsonUtil.getGson().toJson(params), Consts.UTF_8));
		
		//请求数据
		CloseableHttpResponse response = httpClient.execute(httpPost);
		try {
			LOGGER.debug(response.getStatusLine().toString());
			
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = response.getEntity();
				responseBody = EntityUtils.toString(entity, "UTF-8");
			} else {
				LOGGER.error("http return status error: {}", response.getStatusLine().getStatusCode());
			}
		} catch (Exception e) {
			LOGGER.error("occur exception:[{}]", e);
		} finally {
			response.close();
		}
		
		return responseBody;
	}
	
	public static String post(String url, Map<String, Object> queries, String params) throws IOException {
		String responseBody = "";
		//支持https
		CloseableHttpClient httpClient = getHttpClient();
		String realUrl = joinParamters(url, queries);
		
		//指定url,和http方式
		HttpPost httpPost = new HttpPost(realUrl);
		if (SetTimeOut) {
			RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(SocketTimeout)
				.setConnectTimeout(ConnectTimeout).build();//设置请求和传输超时时间
			httpPost.setConfig(requestConfig);
		}
		//添加参数
		httpPost.setEntity(new StringEntity(params, Consts.UTF_8));
		
		//请求数据
		CloseableHttpResponse response = httpClient.execute(httpPost);
		try {
			LOGGER.debug(response.getStatusLine().toString());
			
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = response.getEntity();
				responseBody = EntityUtils.toString(entity, "UTF-8");
			} else {
				LOGGER.error("http return status error: {}", response.getStatusLine().getStatusCode());
			}
		} catch (Exception e) {
			LOGGER.error("occur exception:[{}]", e);
		} finally {
			response.close();
		}
		
		return responseBody;
	}
	
	/**
	 * 使用okhttp 方式post请求
	 *
	 * @param url
	 * @param content
	 * @return
	 * @throws IOException
	 */
	public static String postReqResponse(String url, String content) throws IOException {
		OkHttpClient client = new OkHttpClient();
		
		//请求类型为JSON
		MediaType mediaType = MediaType.parse("application/json");
		
		RequestBody body = RequestBody.create(mediaType, content);
		Request.Builder builder = new Request.Builder();
		
		Request request = builder.url(url)
			.post(body)
			.addHeader("content-type", "application/json")
			.build();
		Response response = client.newCall(request).execute();
		if (response.code() != 200) {
			return null;
		}
		
		return response.body().string();
	}
	
	/**
	 * 使用okhttp 方式 get 请求
	 *
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static String getReqResponse(String url) throws IOException {
		OkHttpClient client = new OkHttpClient();
		
		//请求类型为JSON
		Request.Builder builder = new Request.Builder();
		
		Request request = builder.url(url).addHeader("content-type", "application/json").build();
		Response response = client.newCall(request).execute();
		if (response.code() != 200) {
			return null;
		}
		
		return response.body().string();
	}


	public static byte[] getImageFromNetByUrl(String path) {
		Boolean isWeChatHeadUrl = path.contains(HttpUtil.weChatHeadUrl);
		if (isWeChatHeadUrl) {
			path = path.replace("https:", "http:");
		}
		byte[] firstResult = getByteDataByUrl(path);
		/**
		 * 若是微信头像需要再次尝试
		 */
		if (firstResult == null) {
			log.error("Error: getImageFromNetByUrl firstResult is null!, path:{}", path);
			if (isWeChatHeadUrl) {
				path = path.replace("wx.qlogo.cn","thirdwx.qlogo.cn");
				byte[] secondResult = getByteDataByUrl(path);
				// 第二次尝试失败
				if (secondResult == null) {
					log.error("Error: getImageFromNetByUrl secondResult is null!, path:{}", path);
					return null;
				} else {
					return secondResult;
				}
			} else {
				return null;
			}
		} else {
			return firstResult;
		}
	}

	public static byte[] getByteDataByUrl (String path) {
		InputStream inputStream = null;
		ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream();
		try {
			URL url = new URL(path);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			inputStream = conn.getInputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = inputStream.read(buffer)) != -1) {
				byteOutStream.write(buffer, 0, len);
			}
			inputStream.close();
			return byteOutStream.toByteArray();
		} catch (Exception e) {
			log.error("Error: getImageFromNetByUrl! url:{}", path, e);
		} finally {
			try {
				byteOutStream.close();
			} catch (Exception e) {
				log.error("Error: getImageFromNetByUrl! url:{}", path, e);
			}
		}
		return null;
	}
	
	public static String joinParamters(String url, Map<String, Object> queries) {
		StringBuilder sb = new StringBuilder(url);
		if (queries != null && queries.keySet().size() > 0) {
			boolean firstFlag = !url.contains("?");
			Iterator iterator = queries.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry entry = (Map.Entry<String, Object>) iterator.next();
				if (firstFlag) {
					sb.append("?" + entry.getKey() + "=" + entry.getValue());
					firstFlag = false;
				} else {
					sb.append("&" + entry.getKey() + "=" + entry.getValue());
				}
			}
		}
		return sb.toString();
	}
}
