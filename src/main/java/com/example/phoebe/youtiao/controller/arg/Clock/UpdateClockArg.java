package com.example.phoebe.youtiao.controller.arg.Clock;

import com.example.phoebe.youtiao.controller.arg.BaseArg;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

@Data
public class UpdateClockArg extends BaseArg {

    @ApiModelProperty(name = "id", notes = "提醒闹钟id")
    String id;
    @ApiModelProperty(name = "time", notes = "提醒时间")
    Date time;
    @ApiModelProperty(name = "status", notes = "开启状态  0 开启， 1 关闭")
    Integer status;
    @ApiModelProperty(name = "cycle", notes = "周期  1 每天  2 只在工作日  3 一周  5 一个月")
    Integer cycle;
    @ApiModelProperty(name = "name", notes = "闹钟名字")
    String name;
    @ApiModelProperty(name = "description", notes = "形容")
    String description;

    public boolean isWrongParams(){
        if(StringUtils.isEmpty(id)){
            return true;
        }
        return false;
    }
}
