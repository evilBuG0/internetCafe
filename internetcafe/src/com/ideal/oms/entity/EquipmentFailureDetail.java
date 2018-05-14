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
@Table(name="equipment_failure_detail")
public class EquipmentFailureDetail extends AutoModel {
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="equipment_failure")
    private EquipmentFailure equipmentFailure;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="computer_id")
    private Computer computer;

    private Integer failureType;
    private String failureDescribe;
    
    private Integer delFlag;

    public EquipmentFailureDetail(){
    }
    public EquipmentFailureDetail(Long id){
        setId(id);
    }

    

    public EquipmentFailure getEquipmentFailure() {
		return equipmentFailure;
	}
	public void setEquipmentFailure(EquipmentFailure equipmentFailure) {
		this.equipmentFailure = equipmentFailure;
	}
	public Computer getComputer() {
		return computer;
	}
	public void setComputer(Computer computer) {
		this.computer = computer;
	}
	public Integer getFailureType() {
		return failureType;
	}
	public void setFailureType(Integer failureType) {
		this.failureType = failureType;
	}
	public String getFailureDescribe() {
		return failureDescribe;
	}
	public void setFailureDescribe(String failureDescribe) {
		this.failureDescribe = failureDescribe;
	}
	public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }
}
