package com.gcs.nlyte.web.persistance.dao.impl;

import org.springframework.stereotype.Repository;
import com.gcs.nlyte.web.persistance.dao.api.NlyteDaoSupport;
import com.gcs.nlyte.web.persistance.dao.entity.NltyeCustomerDataProcess;
import com.gcs.nlyte.web.persistance.dao.interfaces.INltyeCustomerDataProcess;

@Repository("nltyeCustomerDataProcess")
public class NlyteCustDataProcessDao extends NlyteDaoSupport<NltyeCustomerDataProcess, Long>
		implements INltyeCustomerDataProcess {

	public boolean nlyteCustProcessIns(NltyeCustomerDataProcess process) {
		boolean flag = false;
		try {
			insertDto(process, 1);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return flag;
		}
	}

	public boolean nlyteUpdateStatus(String cmsId) {
		try {
			boolean retv = nlyteUpdateProcessStatus(cmsId);
			return retv;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

}
