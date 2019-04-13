package com.example.phoebe.youtiao.dao.api;

import com.example.phoebe.youtiao.dao.entity.ClockEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClockDao {
    boolean addClock(ClockEntity entity);
    boolean updateClock(ClockEntity entity);
    boolean deleteClockById(@Param("id") String id);
    List<ClockEntity> listClock(@Param("uid") String uid);
    List<ClockEntity> listClockByStatus(@Param("status") Integer status);
    ClockEntity getClockById(@Param("id") String id);

}
