package com.ideal.oms.service;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ideal.oms.entity.ComputerRoom;
import com.ideal.oms.repository.ComputerRoomRepository;

@Service
public class ComputerRoomService {
    @Resource
    private ComputerRoomRepository computerRoomRepository;

    public List<ComputerRoom> findAll(){
    	return computerRoomRepository.findAll();
    }

   
}
