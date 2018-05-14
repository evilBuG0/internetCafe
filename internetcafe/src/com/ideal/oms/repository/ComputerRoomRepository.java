package com.ideal.oms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ideal.oms.entity.ComputerRoom;
import com.ideal.oms.framework.orm.JpaRepository;


@Repository
public interface ComputerRoomRepository extends JpaRepository<ComputerRoom, Long> {
    @Query("SELECT o FROM ComputerRoom o WHERE o.delFlag = 0")
    public List<ComputerRoom> findAll();
    
    
    
    
    
}
