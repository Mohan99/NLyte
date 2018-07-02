package com.gcs.nlyte.web.persistance.dao.interfaces;

import com.gcs.nlyte.web.persistance.dao.api.INlyteDaoSupport;
import com.gcs.nlyte.web.persistance.dao.entity.NltyeCustomerDataProcess;
import com.gcs.nlyte.web.persistance.dao.entity.NlyteMasterDataTransStg;

public interface INltyeCustomerDataProcess extends INlyteDaoSupport<NltyeCustomerDataProcess,Long>{

	public boolean nlyteCustProcessIns(NltyeCustomerDataProcess process);
	public boolean nlyteUpdateStatus(String cmsId);
	
}
