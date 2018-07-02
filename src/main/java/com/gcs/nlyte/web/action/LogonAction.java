package com.gcs.nlyte.web.action;

import java.io.StringWriter;
import java.util.List;
import java.util.logging.Logger;

import org.apache.struts2.dispatcher.SessionMap;

import com.gcs.nlyte.web.persistance.dao.entity.NlyteCustomerDataStg;
import com.gcs.nlyte.web.persistance.dao.entity.NlyteMasterDataStg;
import com.gcs.nlyte.web.persistance.dao.entity.NlyteUsers;
import com.gcs.nlyte.web.util.MD5Util;
import com.opensymphony.xwork2.ActionContext;

public class LogonAction extends NLyteAction {
	private static final long serialVersionUID = 1L;
	private static org.apache.log4j.Logger logger =
	           org.apache.log4j.Logger.getLogger(LogonAction.class);

	private String userName;
	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String execute() {
		MD5Util util = new MD5Util();
		try {
			logger.info("execute() : LogonAction");
			NlyteUsers user = this.nlyteService.login(userName, util.encryptPassword(password));
			// NlyteUsers user = this.nlyteService.login(userName, password);
			if (user != null) {
				if (this.session.containsKey("USER_OBJ"))
					session.remove("USER_OBJ");
				// SessionMap session = (SessionMap)ActionContext.getContext().getSession();
				// session.invalidate();
				session.put("USER_OBJ", user);
				dashboardCount();
				return "success";
			} else
				return "error";

			// return "admin".equals(userName) &&
			// "admin".equals(password)?"dashboard":"error";
			// return "success";
		} catch (Exception ex) {
			ex.printStackTrace();
			StringWriter errors = new StringWriter();
			logger.error("Exception Message: " + errors.toString());
			return "error";
		}

	}

	public String nlyteLogOut() {
		try {
			logger.info("nlyteLogOut() : LogonAction");
			System.out.println("in logout action method");
			if (this.session.containsKey("USER_OBJ"))
				session.remove("USER_OBJ");
			session.put("USER_OBJ", null);
			return "success";
		}

		catch (Exception e) {
			e.printStackTrace();

			StringWriter errors = new StringWriter();
			logger.error("Exception Message: " + errors.toString());
			return "error";
		}

	}

	public String dashboardCount() {
		try {
			//logger.info("dashboardCount() : LogonAction");
			int processedCount = this.nlyteService.getCustomerDataProcessedListCount();
			List<NlyteCustomerDataStg> nlyteCustDtStg = this.nlyteService.getCustomerDtStgImportList();
			Long mastCount = this.nlyteService.getMasterCount();

			/*session.put("MaterialMasterCount", (list != null ? list.size() : 0));*/
			session.put("MaterialMasterCount", mastCount);
			session.put("MaterialLookupCount", (nlyteCustDtStg != null ? nlyteCustDtStg.size() : 0));
			session.put("ProcessedDataCount", (processedCount));
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			StringWriter errors = new StringWriter();
			logger.error("Exception Message: " + errors.toString());
			return "error";
		}

	}

}
