package com.gcs.nlyte.web.action;

import java.io.StringWriter;

import com.opensymphony.xwork2.ActionSupport;

public class NlyteLoginAction extends ActionSupport {
	private static org.apache.log4j.Logger logger =
	           org.apache.log4j.Logger.getLogger(NlyteLoginAction.class);
	@Override
	public String execute() {
		logger.info("execute() : NlyteLoginAction");
		try {
			return "nlyteLogin";
		} catch (Exception e) {
			e.printStackTrace();
			StringWriter errors = new StringWriter();
			logger.error("Exception Message: " + errors.toString());
			return "error";
		}
	}
}
