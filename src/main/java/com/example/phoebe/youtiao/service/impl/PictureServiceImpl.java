package com.example.phoebe.youtiao.service.impl;


import com.example.phoebe.youtiao.api.PictureService;
import com.example.phoebe.youtiao.api.result.FPathResult;
import com.example.phoebe.youtiao.api.result.TPathResult;
import com.example.phoebe.youtiao.commmon.ModelResult;
import com.example.phoebe.youtiao.commmon.SHErrorCode;
import com.example.phoebe.youtiao.commmon.enums.FileTypeEnum;
import com.example.phoebe.youtiao.controller.arg.DownLoadArg;
import com.example.phoebe.youtiao.controller.arg.GetFPathArg;
import com.example.phoebe.youtiao.controller.arg.GetTPathArg;
import com.example.phoebe.youtiao.service.manager.QiNiuManager;
import com.google.common.io.ByteStreams;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class PictureServiceImpl implements PictureService {

    @Autowired
    QiNiuManager qiNiuManager;

    @Override
    public ModelResult<TPathResult> getTPath(GetTPathArg arg) {
        String tPath = null;
        if (arg.getType().equals(FileTypeEnum.picture.getType())) {
            tPath = qiNiuManager.uploadByStream(arg.getFile(), null,"T");
            if (StringUtils.isEmpty(tPath)) {
                return new ModelResult<>(SHErrorCode.TRANSFER_TPATH_FAIL);
            }
        }
        if (arg.getType().equals(FileTypeEnum.file.getType())) {

        }
        TPathResult result = new TPathResult();
        result.setTPath(tPath);
        return new ModelResult<>(SHErrorCode.SUCCESS, result);
    }

    @Override
    public ModelResult<FPathResult> getFPath(GetFPathArg arg) {
        String tPath = null;
        String fPath = qiNiuManager.changeQiNiuFileName(arg.getTPath());
        if(StringUtils.isEmpty(fPath)){
            return new ModelResult<>(SHErrorCode.TRANSFER_FPATH_FAIL);
        }
        FPathResult result = new FPathResult();
        result.setFPath(tPath);
        return new ModelResult<>(SHErrorCode.SUCCESS, result);
    }

    @Override
    public byte[] downLoad(DownLoadArg arg){
        byte[] file = null;
        HttpEntity httpEntity = getHttpEntity(qiNiuManager.connectPathAndUrl(arg.getPath()));
        try {
            file = ByteStreams.toByteArray(httpEntity.getContent());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public static HttpEntity getHttpEntity(String url) {
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);
//            httpGet.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
//            httpGet.addHeader("Content-Type", "text/html;charset=utf-8");
//            httpGet.addHeader("accept-language", "zh-CN,zh;q=0.9,zh-TW;q=0.8");
//            httpGet.addHeader("accept-encoding", "gzip, deflate, br");
            HttpResponse response = httpClient.execute(httpGet);
            return response.getEntity();
        } catch (Exception e) {
            log.error("Error: getHttpEntity! url:{}", url, e);
            return null;
        }finally {

        }
    }

}
