package com.gcs.nlyte.web.bean;

import com.gcs.nlyte.web.persistance.dao.entity.NlyteCustomerDataTransStg;
import com.gcs.nlyte.web.persistance.dao.entity.NlyteMasterDataTransStg;

public class NlyteCustMastDataBean {
	private NlyteCustomerDataTransStg nCustDataTransStg;	
	public NlyteCustomerDataTransStg getnCustDataTransStg() {
		return nCustDataTransStg;
	}
	public void setnCustDataTransStg(NlyteCustomerDataTransStg nCustDataTransStg) {
		this.nCustDataTransStg = nCustDataTransStg;
	}
	public NlyteMasterDataTransStg getnMasterDataTrans() {
		return nMasterDataTrans;
	}
	public void setnMasterDataTrans(NlyteMasterDataTransStg nMasterDataTrans) {
		this.nMasterDataTrans = nMasterDataTrans;
	}
	private NlyteMasterDataTransStg nMasterDataTrans;

}
