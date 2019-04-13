package com.example.phoebe.youtiao.service.impl;

import com.example.phoebe.youtiao.api.ClockService;
import com.example.phoebe.youtiao.api.result.ClockResult;
import com.example.phoebe.youtiao.api.result.ListClockResult;
import com.example.phoebe.youtiao.api.vo.clock.*;
import com.example.phoebe.youtiao.commmon.ModelResult;
import com.example.phoebe.youtiao.commmon.SHErrorCode;
import com.example.phoebe.youtiao.commmon.util.BeanUtil;
import com.example.phoebe.youtiao.commmon.util.UUIDUtil;
import com.example.phoebe.youtiao.controller.WebSocketServer;
import com.example.phoebe.youtiao.dao.api.ClockDao;
import com.example.phoebe.youtiao.dao.entity.ClockEntity;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class ClockServiceImpl implements ClockService {

    @Autowired
    ClockDao clockDao;

    @Override
    public ModelResult addClock(AddClockVo vo) {
        ClockEntity addEntity = BeanUtil.copy(vo, ClockEntity.class);
        addEntity.setId(UUIDUtil.getUUID());
        if(!clockDao.addClock(addEntity)){
            log.warn("ClockServiceImpl.addClock vo:{} addEntity:{}", vo, addEntity);
            return new ModelResult(SHErrorCode.ADD_FAIL);
        }
        return new ModelResult(SHErrorCode.SUCCESS);
    }

    @Override
    public ModelResult updateClock(UpdateClockVo vo) {
        ClockEntity existEntity = clockDao.getClockById(vo.getId());
        if(null == existEntity){
            log.warn("ClockServiceImpl.updateClock vo:{}", vo);
            return new ModelResult(SHErrorCode.NO_DATA);
        }
        ClockEntity updateEntity = BeanUtil.copy(vo, ClockEntity.class);
        updateEntity.setId(vo.getId());

        if(!clockDao.updateClock(updateEntity)){
            log.warn("ClockServiceImpl.updateClock vo:{}  updateEntity:{}", vo, updateEntity);
            return new ModelResult(SHErrorCode.UPDATE_FAIL);
        }
        return new ModelResult(SHErrorCode.SUCCESS);


    }

    @Override
    public ModelResult deleteClock(DeleteClockVo vo) {

        if(!clockDao.deleteClockById(vo.getId())){
            log.warn("ClockServiceImpl.deleteClock  vo:{}", vo);
            return new ModelResult(SHErrorCode.DEL_FAIL);
        }
        return new ModelResult(SHErrorCode.SUCCESS);
    }

    @Override
    public ModelResult<List<ListClockResult>> listClock(ListClockVo vo) {
        List<ClockEntity> clockList = clockDao.listClock(vo.getAid());
        List<ListClockResult> results = Lists.newArrayList();
        if(CollectionUtils.isNotEmpty(clockList)){
            for (ClockEntity clockEntity : clockList) {
                ListClockResult result = BeanUtil.copy(clockEntity, ListClockResult.class);
                result.setTime(clockEntity.getTime().getTime());
                results.add(result);
            }
        }

        return new ModelResult<>(SHErrorCode.SUCCESS, results);
    }

    @Override
    public ModelResult<ClockResult> queryClockById(QueryClockByIdVo vo) {
        ClockEntity clock = clockDao.getClockById(vo.getId());
        if(null == clock){
            return new ModelResult<>(SHErrorCode.NO_DATA);
        }
        ClockResult result = BeanUtil.copy(clock, ClockResult.class);
        result.setTime(clock.getTime().getTime());
        return new ModelResult<>(SHErrorCode.SUCCESS, result);
    }

//    @Scheduled(cron = "0/2 * * * * ?")
    @Override
    public void searchClock2Call() {
        List<ClockEntity> clockList = clockDao.listClockByStatus(0);
        try {
            for (ClockEntity clock : clockList) {
                long time = clock.getTime().getTime();
                long now = new Date().getTime();
                if(time < now){
                    if(clock.getCycle() == 1){
                        time += (24 * 60 * 60 * 1000);
                    }else if(clock.getCycle() == 3){
                        time += (24 * 60 * 60 * 1000);
                    }else {
                        // 还有月份没有实现
                        continue;
                    }
                    clock.setTime(new Date(time));
                    clockDao.updateClock(clock);
                    WebSocketServer.sendInfo(clock.getId(), clock.getAid());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
