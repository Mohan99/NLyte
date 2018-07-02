package com.gcs.nlyte.web.persistance.dao.interfaces;

import java.util.List;

import com.gcs.nlyte.web.persistance.dao.api.INlyteDaoSupport;
import com.gcs.nlyte.web.persistance.dao.entity.NlyteCustomerDataStg;
import com.gcs.nlyte.web.persistance.dao.entity.NlyteMasterDataStg;

public interface INlyteMasterDataStgDao extends INlyteDaoSupport<NlyteMasterDataStg,Long>{
	public boolean nlyteImportCall(NlyteMasterDataStg nlyteMastDtStg);
	public List<NlyteMasterDataStg> getMasterDtStgImportList();
	public List<NlyteMasterDataStg> getAllRecords();
	public NlyteMasterDataStg getMasterPrimaryDataByUUID(String uId);
	public List<NlyteMasterDataStg> getRecentStgRecord();

}
