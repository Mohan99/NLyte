package com.gcs.nlyte.web.action;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.ws.rs.core.Request;

import org.apache.struts2.ServletActionContext;

import com.gcs.nlyte.web.persistance.dao.entity.NlyteCustomerDataStg;
import com.gcs.nlyte.web.persistance.dao.entity.NlyteUsers;
import com.gcs.nlyte.web.util.MD5Util;

public class NlyteUserAction extends NLyteAction {
	private static final long serialVersionUID = 1L;
	private static org.apache.log4j.Logger logger =
	           org.apache.log4j.Logger.getLogger(NlyteUserAction.class);

	/*
	 * @Autowired private INlyteService nlyteService;
	 */

	private String userId;
	private String userFirstName;
	private String userLastName;
	private String userMiddleName;
	private String Password;
	private Integer nlyteUserId;
	private String curPassword;
	private MD5Util util;
	private String cnfPassword;

	private String uId;

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}

	public String getCnfPassword() {
		return cnfPassword;
	}

	public void setCnfPassword(String cnfPassword) {
		this.cnfPassword = cnfPassword;
	}

	public String getCurPassword() {
		return curPassword;
	}

	public void setCurPassword(String curPassword) {
		this.curPassword = curPassword;
	}

	public Integer getNlyteUserId() {
		return nlyteUserId;
	}

	public void setNlyteUserId(Integer nlyteUserId) {
		this.nlyteUserId = nlyteUserId;
	}

	// List<NlyteUsers> nlyteUsers =null;
	private List<NlyteUsers> nlyteUsers = new ArrayList<NlyteUsers>();

	public NlyteUsers getNlyteEditUser() {
		return nlyteEditUser;
	}

	public void setNlyteEditUser(NlyteUsers nlyteEditUser) {
		this.nlyteEditUser = nlyteEditUser;
	}

	private NlyteUsers nlyteEditUser = new NlyteUsers();

	public List<NlyteUsers> getNlyteUsers() {
		return nlyteUsers;
	}

	public void setNlyteUsers(List<NlyteUsers> nlyteUsers) {
		this.nlyteUsers = nlyteUsers;
	}

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

	private String NlyteUser;

	public String getNlyteUser() {
		return NlyteUser;
	}

	public void setNlyteUser(String nlyteUser) {
		NlyteUser = nlyteUser;
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
		return "success";
	}

	public String nlyteCreateUser() {

		return "nlyteUserCreate";
	}

	public String nlyteCreateUserCall() {
		logger.info("nlyteCreateUserCall() : NlyteUserAction");
		boolean flag = Boolean.FALSE;
		boolean userExists = false;
		try {
			util = new MD5Util();
			NlyteUsers nlyteuser = new NlyteUsers();
			nlyteuser.setLoginId(getUserId());
			nlyteuser.setUserFirstNameTx(getUserFirstName());
			nlyteuser.setUserLastNameTx(getUserLastName());
			nlyteuser.setUserMiddleNameTx(getUserMiddleName());
			nlyteuser.setUserDisplayNameTx(userFirstName + ", " + userLastName);
			nlyteuser.setPasswordTx(util.encryptPassword(getPassword()));
			nlyteuser.setMobileTx(getMobileNo());
			nlyteuser.setEmailId(getEmailId());
			nlyteuser.setActiveYn(true);
			nlyteuser.setCreatedDt(new Date());
			nlyteuser.setUpdatedDt(new Date());

			List<NlyteUsers> usersList = this.nlyteService.getUserList();
			for (NlyteUsers user : usersList) {
				if (user.getLoginId().equals(getUserId())) {
					userExists = true;
					setuId(user.getLoginId());
					System.out.println("uId..." + uId);
					// HttpServletRequest request=ServletActionContext.getRequest();
					// request.setAttribute("msg", "User Id "+getUserId()+" already exists.");
					return "error";
				}

			}
			flag = this.nlyteService.createUser(nlyteuser);
			List<NlyteUsers> users = this.nlyteService.getUserList();
			setNlyteUsers(users);
			if (flag) {
				return "success";
			} else {
				return "error";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			StringWriter errors = new StringWriter();
			logger.error("Exception Message: " + errors.toString());
			return "error";
		}

	}

	public String nlyteDisplayUserList() {
		List<NlyteUsers> users = this.nlyteService.getUserList();
		setNlyteUsers(users);

		return "success";
	}

	public String nlyteDeletedUserList() {
		List<NlyteUsers> users = this.nlyteService.getDeletedUserList();
		setNlyteUsers(users);
		return "success";
	}

	public String nlyteDisplayEditUser() {
		try {
			List<NlyteUsers> users = this.nlyteService.getUserList();
			for (int i = 0; i < users.size(); i++) {
				NlyteUsers nlyteUser = ((List<NlyteUsers>) users).get(i);
				if (nlyteUser.getLoginId().equals(getUserId())) {
					setNlyteEditUser(nlyteUser);
					break;
				}
			}

			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			StringWriter errors = new StringWriter();
			logger.error("Exception Message: " + errors.toString());
			return "error";
		}
	}

	public String nlyteUpdateUser() {
		logger.info("nlyteUpdateUser() : NlyteUserAction");
		try {
			NlyteUsers nlyteUsersObj = new NlyteUsers();
			int userId = getNlyteUserId();
			NlyteUsers nlyteuser = new NlyteUsers();
			List<NlyteUsers> users = this.nlyteService.getUserList();
			List<NlyteUsers> nlyteUsers = this.nlyteService.getUserData(userId);

			for (NlyteUsers prevListdata : nlyteUsers) {
				prevListdata.setLoginId(getUserId());
				prevListdata.setUserFirstNameTx(getUserFirstName());
				prevListdata.setUserLastNameTx(getUserLastName());
				prevListdata.setUserMiddleNameTx(getUserMiddleName());
				prevListdata.setUserDisplayNameTx(userFirstName + ", " + userLastName);
				prevListdata.setMobileTx(getMobileNo());
				prevListdata.setEmailId(getEmailId());
				nlyteUsersObj = prevListdata;
				boolean b = this.nlyteService.updateUser(nlyteUsersObj);
			}
			users = this.nlyteService.getUserList();
			setNlyteUsers(users);

			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			StringWriter errors = new StringWriter();
			logger.error("Exception Message: " + errors.toString());
			return "error";
		}
	}

	public String nlyteDeleteUser() {
		logger.info("nlyteDeleteUser() : NlyteUserAction");
		try {
			NlyteUsers nlyteUsersObj = new NlyteUsers();
			int userId = getNlyteUserId();
			NlyteUsers nlyteuser = new NlyteUsers();
			List<NlyteUsers> users = this.nlyteService.getUserList();
			List<NlyteUsers> nlyteUsers = this.nlyteService.getUserData(userId);

			for (NlyteUsers prevListdata : nlyteUsers) {
				prevListdata.setActiveYn(false);
				nlyteUsersObj = prevListdata;
				boolean b = this.nlyteService.updateUser(nlyteUsersObj);
			}
			users = this.nlyteService.getUserList();
			setNlyteUsers(users);

			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			StringWriter errors = new StringWriter();
			logger.error("Exception Message: " + errors.toString());
			return "error";
		}
	}

	public String nlyteActivateUser() {
		logger.info("nlyteActivateUser() : NlyteUserAction");
		try {
			NlyteUsers nlyteUsersObj = new NlyteUsers();
			int userId = getNlyteUserId();
			// NlyteUsers nlyteuser = new NlyteUsers();
			// List<NlyteUsers> users = this.nlyteService.getUserList();
			List<NlyteUsers> nlyteUsers = this.nlyteService.getDeletedUserList();

			for (NlyteUsers prevListdata : nlyteUsers) {
				if (prevListdata.getNlyteUserId() == userId) {
					prevListdata.setActiveYn(true);
					nlyteUsersObj = prevListdata;
					boolean b = this.nlyteService.updateUser(prevListdata);
				}
			}
			List<NlyteUsers> users = this.nlyteService.getDeletedUserList();
			setNlyteUsers(users);

			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			StringWriter errors = new StringWriter();
			logger.error("Exception Message: " + errors.toString());
			return "error";
		}
	}

	public String changePwd() {
		logger.info("changePwd() : NlyteUserAction");
		NlyteUsers users = (NlyteUsers) session.get("USER_OBJ");
		setCurPassword(users.getPasswordTx());
		setUserId(users.getNlyteUserId().toString());
		return "success";
	}

	public String changePassword() {
		logger.info("changePassword() : NlyteUserAction");
		util = new MD5Util();
		try {
			NlyteUsers users = (NlyteUsers) session.get("USER_OBJ");
			NlyteUsers nlyteUsersObj = new NlyteUsers();

			int userId = users.getNlyteUserId();
			List<NlyteUsers> nlyteUsers = this.nlyteService.getUserData(userId);

			for (NlyteUsers prevListdata : nlyteUsers) {
				prevListdata.setPasswordTx(util.encryptPassword(getCnfpwd()));
				nlyteUsersObj = prevListdata;
				boolean b = this.nlyteService.updateUser(nlyteUsersObj);
			}
		} catch (Exception e) {
			e.printStackTrace();
			StringWriter errors = new StringWriter();
			logger.error("Exception Message: " + errors.toString());
			return "error";
		}
		return "success";
	}

	private String newpwd;

	public String getNewpwd() {
		return newpwd;
	}

	public void setNewpwd(String newpwd) {
		this.newpwd = newpwd;
	}

	private String cnfpwd;

	public String getCnfpwd() {
		return cnfpwd;
	}

	public void setCnfpwd(String cnfpwd) {
		this.cnfpwd = cnfpwd;
	}

}
