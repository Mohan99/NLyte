package com.gcs.nlyte.web.bean;

import com.gcs.nlyte.web.persistance.dao.entity.NltyeCustomerDataProcess;
import com.gcs.nlyte.web.persistance.dao.entity.NlyteCustomerDataStg;
import com.gcs.nlyte.web.persistance.dao.entity.NlyteCustomerDataTransStg;
import com.gcs.nlyte.web.persistance.dao.entity.NlyteMasterDataTransStg;

public class NltyeCustDataSelectProcessBean {
	private NltyeCustomerDataProcess nCustDataProcess;
	private NlyteMasterDataTransStg nMasterDataTrans;
	public NltyeCustomerDataProcess getnCustDataProcess() {
		return nCustDataProcess;
	}
	public void setnCustDataProcess(NltyeCustomerDataProcess nCustDataProcess) {
		this.nCustDataProcess = nCustDataProcess;
	}
	public NlyteMasterDataTransStg getnMasterDataTrans() {
		return nMasterDataTrans;
	}
	public void setnMasterDataTrans(NlyteMasterDataTransStg nMasterDataTrans) {
		this.nMasterDataTrans = nMasterDataTrans;
	}
	

	

}
