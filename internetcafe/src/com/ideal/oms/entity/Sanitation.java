package com.ideal.oms.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ideal.oms.entity.security.User;
import com.ideal.oms.framework.entity.AutoModel;

/**
 * Created by dell on 15-5-19.
 */
@Entity
@Table(name = "sanitation")
public class Sanitation extends AutoModel {
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "computer_room_id")
	private ComputerRoom computerRoom;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "classs_id")
	private Classs classsId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lessons_id")
	private Lessons lessonsId;

	private Integer sanitationState;

	private Date createDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "create_user")
	private User createUser;

	private String remark;
	private Integer delFlag;

	public Sanitation() {
	}

	public Sanitation(Long id) {
		setId(id);
	}

	public ComputerRoom getComputerRoom() {
		return computerRoom;
	}

	public void setComputerRoom(ComputerRoom computerRoom) {
		this.computerRoom = computerRoom;
	}

	public Classs getClasssId() {
		return classsId;
	}

	public void setClasssId(Classs classsId) {
		this.classsId = classsId;
	}

	public Lessons getLessonsId() {
		return lessonsId;
	}

	public void setLessonsId(Lessons lessonsId) {
		this.lessonsId = lessonsId;
	}

	public Integer getSanitationState() {
		return sanitationState;
	}

	public void setSanitationState(Integer sanitationState) {
		this.sanitationState = sanitationState;
	}

	public Date getCreateDate() {
		return createDate;
	}

	@Column(name = "create_date")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
}
