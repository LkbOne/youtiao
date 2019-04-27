package com.example.phoebe.youtiao.api;

import com.example.phoebe.youtiao.api.result.ClockResult;
import com.example.phoebe.youtiao.api.vo.clock.*;
import com.example.phoebe.youtiao.commmon.ModelResult;

public interface ClockService {
    ModelResult addClock(AddClockVo vo);
    ModelResult updateClock(UpdateClockVo vo);
    ModelResult deleteClock(DeleteClockVo vo);
    ModelResult listClock(ListClockVo vo);
    ModelResult<ClockResult> queryClockById(QueryClockByIdVo vo);

    void searchClock2Call();
}
