package com.example.phoebe.youtiao.controller;


import com.example.phoebe.youtiao.api.PictureService;
import com.example.phoebe.youtiao.api.result.FPathResult;
import com.example.phoebe.youtiao.api.result.TPathResult;
import com.example.phoebe.youtiao.commmon.ModelResult;
import com.example.phoebe.youtiao.commmon.SHErrorCode;
import com.example.phoebe.youtiao.controller.arg.DownLoadArg;
import com.example.phoebe.youtiao.controller.arg.GetFPathArg;
import com.example.phoebe.youtiao.controller.arg.GetTPathArg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;


@Slf4j
@RestController
@RequestMapping("/file")
@CrossOrigin
public class PictureController {

    @Autowired
    PictureService pictureService;

    @RequestMapping(value = "getTPath", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ModelResult<TPathResult> getTPath(@RequestParam("type") Integer type, @RequestParam("file") MultipartFile file) {
        GetTPathArg arg = new GetTPathArg();
        arg.setType(type);
        arg.setFile(file);
        if (arg.isWrongParams()) {
            log.warn("");
            return new ModelResult<>(SHErrorCode.PARAMS_ERROR);
        }
        return pictureService.getTPath(arg);
    }

    @RequestMapping(value = "getFPath", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ModelResult<FPathResult> getFPath(@RequestBody GetFPathArg arg) {
        if (arg.isWrongParams()) {
            log.warn("");
            return new ModelResult<>(SHErrorCode.PARAMS_ERROR);
        }
        return pictureService.getFPath(arg);
    }

    @RequestMapping(value = "download", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public void downLoad(@RequestBody DownLoadArg arg, HttpServletResponse httpServletResponse) throws IOException {
        if (arg.isWrongParams()) {
            return;
        }
        byte[] file = pictureService.downLoad(arg);
        String[] filenames = arg.getPath().split("-");
        String filename = filenames[1];
        OutputStream bos = httpServletResponse.getOutputStream();
        filename = URLEncoder.encode(filename, "UTF-8");
        httpServletResponse.setContentType("application/octet-stream");
        httpServletResponse.setHeader("Content-disposition", "attachment; filename=" + filename);
        bos.write(file);
        bos.flush();
        bos.close();
    }
}
