package com.gcs.nlyte.web.persistance.dao.interfaces;

import java.util.List;

import com.gcs.nlyte.web.persistance.dao.api.INlyteDaoSupport;
import com.gcs.nlyte.web.persistance.dao.entity.NlyteCustomerDataStg;
import com.gcs.nlyte.web.persistance.dao.entity.NlyteMasterDataStg;
import com.gcs.nlyte.web.persistance.dao.entity.NlyteMasterDataTransStg;

public interface INlyteMasterDataTransStgDao extends INlyteDaoSupport<NlyteMasterDataTransStg,Long>{
	//public void uploadExcelMasterData(NlyteMasterDataStg stg) ;
	public boolean nlyteMasterImportEachCell(List<NlyteMasterDataTransStg> nlyteMastDtTransStgLst);
	public List<NlyteMasterDataTransStg> getnlyteMasterList();
	public List<NlyteMasterDataTransStg> getMasterDataTransStgList(int nmsId);
	public List<NlyteMasterDataTransStg> getMasterDataTransStgNmtList(int nmtId);
	public boolean nlyteMasterImportUpdateCell(List<NlyteMasterDataTransStg> nlyteMastDtTransStgLst);
	public int getMatchedMasterData(String materialName,String model,String manufacture);
	public Long getMastCount();
}
