package com.example.phoebe.youtiao.api;


import com.example.phoebe.youtiao.api.result.FPathResult;
import com.example.phoebe.youtiao.api.result.TPathResult;
import com.example.phoebe.youtiao.commmon.ModelResult;
import com.example.phoebe.youtiao.controller.arg.DownLoadArg;
import com.example.phoebe.youtiao.controller.arg.GetFPathArg;
import com.example.phoebe.youtiao.controller.arg.GetTPathArg;

public interface PictureService {
    ModelResult<TPathResult> getTPath(GetTPathArg arg);
    ModelResult<FPathResult> getFPath(GetFPathArg arg);
    byte[] downLoad(DownLoadArg arg);
}
