package com.ideal.oms.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ideal.oms.entity.security.User;
import com.ideal.oms.framework.entity.AutoModel;

/**
 * Created by dell on 15-5-19.
 */
@Entity
@Table(name="sign_in_temp")
public class SignInTemp extends AutoModel {
    private String serialNum;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="student_id")
    private Student student;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="create_user")
    private User createUser;
    private String signState;
    private String explainInfo;
    private Integer delFlag;
    
    public SignInTemp(){
    }
    public SignInTemp(Long id){
        setId(id);
    }
	public String getSerialNum() {
		return serialNum;
	}
	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public String getSignState() {
		return signState;
	}
	public void setSignState(String signState) {
		this.signState = signState;
	}
	public Integer getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	public User getCreateUser() {
		return createUser;
	}
	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}
	public String getExplainInfo() {
		return explainInfo;
	}
	public void setExplainInfo(String explainInfo) {
		this.explainInfo = explainInfo;
	}
	
    
    
}
