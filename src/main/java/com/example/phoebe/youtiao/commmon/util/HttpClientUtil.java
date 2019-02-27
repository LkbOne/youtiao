package com.example.phoebe.youtiao.commmon.util;

import com.example.phoebe.youtiao.commmon.exception.InlineException;
import com.example.phoebe.youtiao.commmon.thread.NamedThreadFactory;
import com.ning.http.client.*;
import com.ning.http.client.AsyncHttpClient.BoundRequestBuilder;
import com.ning.http.client.cookie.Cookie;
import com.ning.http.client.providers.netty.NettyAsyncHttpProviderConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class HttpClientUtil {
    private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
    private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);
    private static int DEFAULT_POOL_SIZE = Runtime.getRuntime().availableProcessors() + 1;
    public static ExecutorService executor = new ThreadPoolExecutor(DEFAULT_POOL_SIZE, 1024, 10 * 1000L,
            TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(100), new NamedThreadFactory("HTTP_ASYNC_POOL"),
            new ThreadPoolExecutor.CallerRunsPolicy());
    private static int httpTimeout = 60000;
    private static int httpConnectTimeout = 5000;
    private static int maxConnectionsPerHost = 200;
    private static int pooledConnectionIdleTimeout = 3000;
    private static boolean http_tcpNoDelay;
    private static boolean http_keepAlive;
    private static AsyncHttpClient client;

    static {
        //init
        com.ning.http.client.AsyncHttpClientConfig.Builder builder = new AsyncHttpClientConfig.Builder();
        if (httpConnectTimeout > 0) {
            builder = builder.setConnectTimeout(httpConnectTimeout);
        }
        if (httpTimeout > 0) {
            builder = builder.setRequestTimeout(httpTimeout);
        }
        builder.setFollowRedirect(false).setCompressionEnforced(false);
        if (http_tcpNoDelay) {
            // 开启tcpNoDelay
            NettyAsyncHttpProviderConfig providerConfig = new NettyAsyncHttpProviderConfig();
            providerConfig.addProperty("tcpNoDelay", true);
            builder.setAsyncHttpClientProviderConfig(providerConfig);
        }
        if (http_keepAlive) {
            builder.setAllowPoolingConnections(true);
            builder.setMaxConnectionsPerHost(maxConnectionsPerHost);
            builder.setPooledConnectionIdleTimeout(pooledConnectionIdleTimeout);
        } else {
            builder.setAllowPoolingConnections(false);
        }
        client = new AsyncHttpClient(builder.build());
    }

    public static Response doPut(String url, String body, Map<String, String> headers, List<Cookie> cookies) {
        try {
            BoundRequestBuilder builder = client.preparePut(url);
            return urlWithBody(builder, body, headers, cookies).get(httpTimeout, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            throw InlineException.wrap(e);
        }
    }

    public static Response doGet(String url) {
        try {
            ListenableFuture<Response> future = doAsnyGet(url, null, null);
            Response response = future.get(httpTimeout, TimeUnit.MILLISECONDS);
            return response;
        } catch (Exception e) {
            throw InlineException.wrap(e);
        }
    }

    public static Response doDelete(String url, Map<String, String> headers) {
        try {
            ListenableFuture<Response> future = doDelete(url, headers, null);
            Response response = future.get(httpTimeout, TimeUnit.MILLISECONDS);
            return response;
        } catch (Exception e) {
            throw InlineException.wrap(e);
        }
    }

    public static Response doPost(String url, String body, Map<String, String> headers) {
        try {
            ListenableFuture<Response> future = doAsynPost(url, body, headers, null);
            Response response = future.get(httpTimeout, TimeUnit.MILLISECONDS);
            return response;
        } catch (Exception e) {
            throw InlineException.wrap(e);
        }
    }

    public static Response doGet(String url, Map<String, String> headers) {
        try {
            ListenableFuture<Response> future = doAsnyGet(url, headers, null);
            Response response = future.get(httpTimeout, TimeUnit.MILLISECONDS);
            return response;
        } catch (Exception e) {
            throw InlineException.wrap(e);
        }
    }

    public static Response doGet(String url, int timeoutSecond) {
        try {
            ListenableFuture<Response> future = doAsnyGet(url, null, null);
            Response response = future.get(timeoutSecond, TimeUnit.SECONDS);
            return response;
        } catch (Exception e) {
            throw InlineException.wrap(e);
        }
    }

    public static ListenableFuture<Response> doAsnyGet(String url, Map<String, String> headers, List<Cookie> cookies) {
        try {
            BoundRequestBuilder builder = client.prepareGet(url);
            return url(builder, null, headers, cookies);
        } catch (Exception e) {
            throw InlineException.wrap(e);
        }
    }

    public static ListenableFuture<Response> doDelete(String url, Map<String, String> headers, List<Cookie> cookies) {
        try {
            BoundRequestBuilder builder = client.prepareDelete(url);
            return url(builder, null, headers, cookies);
        } catch (Exception e) {
            throw InlineException.wrap(e);
        }
    }

    public static Response doPost(String url, Map<String, String> params) {
        try {
            ListenableFuture<Response> response = doAsynPost(url, params, null, null);
            return response.get(httpTimeout, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            throw InlineException.wrap(e);
        }
    }

    public static Response doPost(String url, Map<String, String> params, Map<String, String> headers,
            List<Cookie> cookies) {
        try {
            ListenableFuture<Response> future = doAsynPost(url, params, headers, cookies);
            return future.get(httpTimeout, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            throw InlineException.wrap(e);
        }
    }

    public static void doAsynPost(String url, Map<String, String> params, Map<String, String> headers,
            List<Cookie> cookies, final int timeoutSecond, final ResponseHandler handler) {
        try {
            final ListenableFuture<Response> future = doAsynPost(url, params, headers, cookies);
            future.addListener(new Runnable() {
                @Override
                public void run() {
                    Response response = null;
                    try {
                        response = future.get(timeoutSecond, TimeUnit.SECONDS);
                        if (response == null) {
                            logger.error("cannot connected to http server");
                            handler.handle(null);
                            return;
                        }
                        handler.handle(response);
                    } catch (Exception e) {
                        logger.error("ResponseHandler.handle error", e);
                    }

                }
            }, executor);
        } catch (Exception e) {
            throw InlineException.wrap(e);
        }
    }

    public static void doAsynPost(String url, String body, Map<String, String> headers, List<Cookie> cookies,
            final int timeoutSecond, final ResponseHandler handler) {
        try {
            final ListenableFuture<Response> future = doAsynPost(url, body, headers, cookies);
            future.addListener(new Runnable() {
                @Override
                public void run() {
                    Response response = null;
                    try {
                        response = future.get(timeoutSecond, TimeUnit.SECONDS);
                        if (response == null) {
                            logger.error("cannot connected to http server");
                            handler.handle(null);
                            return;
                        }
                        handler.handle(response);
                    } catch (Exception e) {
                        logger.error("ResponseHandler.handle error", e);
                    }

                }
            }, executor);
        } catch (Exception e) {
            throw InlineException.wrap(e);
        }
    }

    public static ListenableFuture<Response> doAsynPost(String url, Map<String, String> params,
            Map<String, String> headers, List<Cookie> cookies) {
        try {
            BoundRequestBuilder builder = client.preparePost(url);
            return url(builder, params, headers, cookies);
        } catch (Exception e) {
            throw InlineException.wrap(e);
        }
    }

    public static ListenableFuture<Response> doAsynPost(String url, String body, Map<String, String> headers,
            List<Cookie> cookies) {
        try {
            BoundRequestBuilder builder = client.preparePost(url);
            return urlWithBody(builder, body, headers, cookies);
        } catch (Exception e) {
            throw InlineException.wrap(e);
        }
    }

    private static ListenableFuture<Response> url(BoundRequestBuilder builder, Map<String, String> params,
            Map<String, String> headers, List<Cookie> cookies) throws IOException {
        if (headers != null) {
            FluentCaseInsensitiveStringsMap headerMap = new FluentCaseInsensitiveStringsMap();
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                headerMap.add(entry.getKey(), entry.getValue());
            }
            builder = builder.setHeaders(headerMap);
        }
        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                builder = builder.addFormParam(entry.getKey(), entry.getValue());
            }
        }
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                builder = builder.addCookie(cookie);
            }
        }
        return builder.execute();
    }

    private static ListenableFuture<Response> urlWithBody(BoundRequestBuilder builder, String body,
            Map<String, String> headers, List<Cookie> cookies) throws IOException {
        if (headers != null) {
            FluentCaseInsensitiveStringsMap headerMap = new FluentCaseInsensitiveStringsMap();
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                headerMap.add(entry.getKey(), entry.getValue());
            }
            builder = builder.setHeaders(headerMap);
        }
        if (body != null) {
            builder.setBody(body.getBytes(DEFAULT_CHARSET));
        }
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                builder = builder.addCookie(cookie);
            }
        }
        return builder.execute();
    }

    public static interface ResponseHandler {
        public void handle(Response response) throws Exception;
    }

}
