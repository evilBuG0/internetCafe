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
@Table(name = "lessons")
public class Lessons extends AutoModel {
	private String lessonsName;

	private Date createDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "create_user")
	private User createUser;

	private Integer delFlag;

	public Lessons() {
	}

	public Lessons(Long id) {
		setId(id);
	}

	public String getLessonsName() {
		return lessonsName;
	}

	public void setLessonsName(String lessonsName) {
		this.lessonsName = lessonsName;
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

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
}
