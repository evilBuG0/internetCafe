package com.ideal.oms.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ideal.oms.framework.entity.AutoModel;

/**
 * Created by dell on 15-5-19.
 */
@Entity
@Table(name="computer")
public class Computer extends AutoModel {
    private String computerName;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="computer_room_id")
    private ComputerRoom computerRoom;

    private Integer seatRow;
    private Integer seatColumn;
    private Integer computerState;
    
    private Integer delFlag;

    public Computer(){
    }
    public Computer(Long id){
        setId(id);
    }
    
   
    public String getComputerName() {
		return computerName;
	}
	public void setComputerName(String computerName) {
		this.computerName = computerName;
	}
	public ComputerRoom getComputerRoom() {
		return computerRoom;
	}
	public void setComputerRoom(ComputerRoom computerRoom) {
		this.computerRoom = computerRoom;
	}
	public Integer getSeatRow() {
		return seatRow;
	}
	public void setSeatRow(Integer seatRow) {
		this.seatRow = seatRow;
	}
	public Integer getSeatColumn() {
		return seatColumn;
	}
	public void setSeatColumn(Integer seatColumn) {
		this.seatColumn = seatColumn;
	}
	public Integer getComputerState() {
		return computerState;
	}
	public void setComputerState(Integer computerState) {
		this.computerState = computerState;
	}
	public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }
}
