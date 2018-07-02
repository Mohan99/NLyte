package com.gcs.nlyte.web.action;


import java.io.StringWriter;

import com.gcs.nlyte.web.persistance.dao.entity.NlyteUsers;

public class NlyteEditAction extends NLyteAction{
	private static final long serialVersionUID = 1L;
	private static org.apache.log4j.Logger logger =
	           org.apache.log4j.Logger.getLogger(NlyteEditAction.class);
	
	/*@Autowired
	private INlyteService nlyteService;*/
	
	private String userId;
	private String userFirstName;
	private String userLastName;
	private String userMiddleName;
	private String Password;
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}

	
	private String mobileNo;
	private String emailId;
	private Boolean userActive;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserFirstName() {
		return userFirstName;
	}
	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}
	public String getUserLastName() {
		return userLastName;
	}
	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}
	public String getUserMiddleName() {
		return userMiddleName;
	}
	public void setUserMiddleName(String userMiddleName) {
		this.userMiddleName = userMiddleName;
	}
	
	
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public Boolean getUserActive() {
		return userActive;
	}
	public void setUserActive(Boolean userActive) {
		this.userActive = userActive;
	}
	
	@Override
	public String execute() {
		logger.info("execute() : NlyteEditAction");
		boolean flag=Boolean.FALSE;
		try {
			NlyteUsers nlyteuser = new NlyteUsers();
			nlyteuser.setLoginId(getUserId());
			nlyteuser.setUserFirstNameTx(getUserFirstName());
			nlyteuser.setUserLastNameTx(getUserLastName());
			nlyteuser.setUserMiddleNameTx(getUserMiddleName());
			nlyteuser.setPasswordTx(getPassword());
			nlyteuser.setMobileTx(getMobileNo());
			nlyteuser.setEmailId(getEmailId());
			nlyteuser.setActiveYn(userActive);
			flag =  this.nlyteService.createUser(nlyteuser);			
			if(flag){
				return "success";
			}
			else {return "error";}
					}catch(Exception ex) {
			ex.printStackTrace();
			StringWriter errors = new StringWriter();
			logger.error("Exception Message: " + errors.toString());
			return "error";
		}
		
		
}
	public String nlyteEditUser(){
		
		return"nlyteUserEdit";
	}
}
