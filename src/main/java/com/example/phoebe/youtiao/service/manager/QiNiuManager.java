package com.example.phoebe.youtiao.service.manager;


import com.example.phoebe.youtiao.commmon.util.UUIDUtil;
import com.github.pagehelper.util.StringUtil;
import com.google.gson.Gson;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.util.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Service
public class QiNiuManager {
    String urlHead = "http://pq92yp4cm.bkt.clouddn.com/";

    String accessKey = "X5a0ocJYENSVIRR4PnXP8OaExYFLP9LG3SMXutiC";
    String secretKey = "pVBazOXZzT8ELao-Ws_HtZ_FVAKhtFg4K-nDVR2K";
    String bucket = "hugh_picture_zone";

    public String connectPathAndUrl(String path){
        return String.format("%s/%s", urlHead, path);
    }

    public boolean localUpload() {
        Configuration cfg = new Configuration(Zone.zone2());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        //如果是Windows情况下，格式是 D:\\qiniu\\test.png
        String localFilePath = "C:\\Users\\LKB\\Desktop\\05-nginx组成.png";
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(localFilePath, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
            return false;
        }
        return true;
    }

    public String uploadByStream(MultipartFile file, String qinNiuName, String type) {
        if(null == qinNiuName) {
            if (type.equals("T")) {
                qinNiuName = String.format("%s_%s-%s", "TE", String.valueOf(UUIDUtil.getUUID()), file.getOriginalFilename());
            }
            if (type.equals("F")) {
                qinNiuName = String.format("%s_%s-%s", "FE", String.valueOf(UUIDUtil.getUUID()), file.getOriginalFilename());
            }
        }
        Configuration cfg = new Configuration(Zone.zone2());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);

        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = qinNiuName;
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(file.getInputStream(), key, upToken, null, null);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return key;
    }

    public String changeQiNiuFileName(String tPath){
        if(StringUtil.isEmpty(tPath) || !tPath.startsWith("TE_")){
            return null;
        }
        Configuration cfg = new Configuration(Zone.zone0());
//...其他参数参考类注释

        String fromBucket = bucket;
        String fromKey = tPath;
        String toBucket = bucket;
        String[] originFileName = tPath.split("TE_");
        String qinNiuName = String.format("%s_%s", "FE", originFileName[1]);
        if(null == qinNiuName){
            return null;
        }
        String toKey = qinNiuName;
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.move(fromBucket, fromKey, toBucket, toKey);
        } catch (QiniuException ex) {
            //如果遇到异常，说明移动失败
            System.err.println(ex.code());
            System.err.println(ex.response.toString());
            return null;
        }
        return qinNiuName;
    }

    public boolean deleteFile(String FPath){
        if(StringUtil.isEmpty(FPath) || !FPath.startsWith("FE_")){
            return false;
        }
        Configuration cfg = new Configuration(Zone.zone0());
        String key = FPath;
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete(bucket, key);
        } catch (QiniuException ex) {
            //如果遇到异常，说明删除失败
            System.err.println(ex.code());
            System.err.println(ex.response.toString());
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        QiNiuManager manager = new QiNiuManager();

        String[] aa = "aa_bb".split("aa_");
        manager.localUpload();
    }
}
