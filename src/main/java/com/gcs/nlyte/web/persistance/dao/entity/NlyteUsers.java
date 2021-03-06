package com.gcs.nlyte.web.persistance.dao.entity;
// Generated 28 Oct, 2017 6:23:45 PM by Hibernate Tools 5.2.5.Final

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;

/**
 * NlyteUsers generated by hbm2java
 */
public class NlyteUsers implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4947480127532409265L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer nlyteUserId;
	private String userDisplayNameTx;
	private String userFirstNameTx;
	private String userLastNameTx;
	private String userMiddleNameTx;
	private boolean adminYn;
	private String passwordTx;
	private String loginId;
	private String mobileTx;
	private String emailId;
	private boolean activeYn;
	private Date createdDt;
	private Date updatedDt;

	public NlyteUsers() {
	}

	public NlyteUsers(boolean adminYn, String passwordTx, String loginId, boolean activeYn, Date createdDt,
			Date updatedDt) {
		this.adminYn = adminYn;
		this.passwordTx = passwordTx;
		this.loginId = loginId;
		this.activeYn = activeYn;
		this.createdDt = createdDt;
		this.updatedDt = updatedDt;
	}

	public NlyteUsers(String userDisplayNameTx, String userFirstNameTx, String userLastNameTx, String userMiddleNameTx,
			boolean adminYn, String passwordTx, String loginId, String mobileTx, String emailId, boolean activeYn,
			Date createdDt, Date updatedDt) {
		this.userDisplayNameTx = userDisplayNameTx;
		this.userFirstNameTx = userFirstNameTx;
		this.userLastNameTx = userLastNameTx;
		this.userMiddleNameTx = userMiddleNameTx;
		this.adminYn = adminYn;
		this.passwordTx = passwordTx;
		this.loginId = loginId;
		this.mobileTx = mobileTx;
		this.emailId = emailId;
		this.activeYn = activeYn;
		this.createdDt = createdDt;
		this.updatedDt = updatedDt;
	}

	public Integer getNlyteUserId() {
		return this.nlyteUserId;
	}

	public void setNlyteUserId(Integer nlyteUserId) {
		this.nlyteUserId = nlyteUserId;
	}

	public String getUserDisplayNameTx() {
		return this.userDisplayNameTx;
	}

	public void setUserDisplayNameTx(String userDisplayNameTx) {
		this.userDisplayNameTx = userDisplayNameTx;
	}

	public String getUserFirstNameTx() {
		return this.userFirstNameTx;
	}

	public void setUserFirstNameTx(String userFirstNameTx) {
		this.userFirstNameTx = userFirstNameTx;
	}

	public String getUserLastNameTx() {
		return this.userLastNameTx;
	}

	public void setUserLastNameTx(String userLastNameTx) {
		this.userLastNameTx = userLastNameTx;
	}

	public String getUserMiddleNameTx() {
		return this.userMiddleNameTx;
	}

	public void setUserMiddleNameTx(String userMiddleNameTx) {
		this.userMiddleNameTx = userMiddleNameTx;
	}

	public boolean isAdminYn() {
		return this.adminYn;
	}

	public void setAdminYn(boolean adminYn) {
		this.adminYn = adminYn;
	}

	public String getPasswordTx() {
		return this.passwordTx;
	}

	public void setPasswordTx(String passwordTx) {
		this.passwordTx = passwordTx;
	}

	public String getLoginId() {
		return this.loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getMobileTx() {
		return this.mobileTx;
	}

	public void setMobileTx(String mobileTx) {
		this.mobileTx = mobileTx;
	}

	public String getEmailId() {
		return this.emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public boolean isActiveYn() {
		return this.activeYn;
	}

	public void setActiveYn(boolean activeYn) {
		this.activeYn = activeYn;
	}

	public Date getCreatedDt() {
		return this.createdDt;
	}

	public void setCreatedDt(Date createdDt) {
		this.createdDt = createdDt;
	}

	public Date getUpdatedDt() {
		return this.updatedDt;
	}

	public void setUpdatedDt(Date updatedDt) {
		this.updatedDt = updatedDt;
	}

}
