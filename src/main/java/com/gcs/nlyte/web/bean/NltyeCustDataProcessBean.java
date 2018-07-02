package com.gcs.nlyte.web.bean;

import com.gcs.nlyte.web.persistance.dao.entity.NltyeCustomerDataProcess;
import com.gcs.nlyte.web.persistance.dao.entity.NlyteCustomerDataStg;
import com.gcs.nlyte.web.persistance.dao.entity.NlyteCustomerDataTransStg;
import com.gcs.nlyte.web.persistance.dao.entity.NlyteMasterDataTransStg;

public class NltyeCustDataProcessBean {
	private NlyteCustomerDataTransStg nCustDataTransStg;
	private NlyteCustomerDataStg nCustDataStg;
	private NltyeCustomerDataProcess nCustDataProcess;
	private NlyteMasterDataTransStg nMasterDataTrans;
	
	public NlyteMasterDataTransStg getnMasterDataTrans() {
		return nMasterDataTrans;
	}
	public void setnMasterDataTrans(NlyteMasterDataTransStg nMasterDataTrans) {
		this.nMasterDataTrans = nMasterDataTrans;
	}
	public NlyteCustomerDataTransStg getnCustDataTransStg() {
		return nCustDataTransStg;
	}
	public void setnCustDataTransStg(NlyteCustomerDataTransStg nCustDataTransStg) {
		this.nCustDataTransStg = nCustDataTransStg;
	}
	public NlyteCustomerDataStg getnCustDataStg() {
		return nCustDataStg;
	}
	public void setnCustDataStg(NlyteCustomerDataStg nCustDataStg) {
		this.nCustDataStg = nCustDataStg;
	}
	public NltyeCustomerDataProcess getnCustDataProcess() {
		return nCustDataProcess;
	}
	public void setnCustDataProcess(NltyeCustomerDataProcess nCustDataProcess) {
		this.nCustDataProcess = nCustDataProcess;
	}
	
	

}
