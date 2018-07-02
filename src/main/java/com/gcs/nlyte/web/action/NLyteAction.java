package com.gcs.nlyte.web.action;

import java.io.StringWriter;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.gcs.nlyte.web.persistance.dao.entity.NlyteUsers;
import com.gcs.nlyte.web.servcie.INlyteService;
import com.opensymphony.xwork2.ActionSupport;

public class NLyteAction extends ActionSupport implements SessionAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8971657642499926338L;
	private static org.apache.log4j.Logger logger =
	           org.apache.log4j.Logger.getLogger(NLyteAction.class);
	protected Map<String, Object> session;
	protected NlyteUsers user;
	@Autowired
	@Qualifier("nlyteService")
	protected INlyteService nlyteService;
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		try {
			logger.info("setSession() : NLyteAction");
		this.session=arg0;
		if(this.session!=null && this.session.containsKey("USER_OBJ"))this.user=(NlyteUsers)session.get("USER_OBJ");
		}catch(Exception e) {
			e.printStackTrace();
			StringWriter errors = new StringWriter();
			logger.error("Exception Message: " + errors.toString());
		}
	}

}
