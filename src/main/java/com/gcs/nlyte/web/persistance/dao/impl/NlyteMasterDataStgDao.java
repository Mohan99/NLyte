package com.gcs.nlyte.web.persistance.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gcs.nlyte.web.persistance.dao.api.NlyteDaoSupport;
import com.gcs.nlyte.web.persistance.dao.entity.NlyteCustomerDataStg;
import com.gcs.nlyte.web.persistance.dao.entity.NlyteMasterDataStg;
import com.gcs.nlyte.web.persistance.dao.interfaces.INlyteMasterDataStgDao;
import com.gcs.nlyte.web.util.Constants;

@Repository("nlyteMasterDataStgDao")
public class NlyteMasterDataStgDao extends NlyteDaoSupport<NlyteMasterDataStg, Long> implements INlyteMasterDataStgDao{
	/*public boolean nlyteImportCall(NlyteMasterDataStg nlyteMastDtStg) {
		boolean flag=false;
		try{
			insertDto(nlyteMastDtStg,1);		
	return true;
}
	catch(Exception e){
		e.printStackTrace();
		return flag;
	}
	}*/
	
	public boolean nlyteImportCall(NlyteMasterDataStg nlyteMastDtStg){
		boolean flag=false;
			try{
				insertDto(nlyteMastDtStg,1);		
		return true;
	}
		catch(Exception e){
			e.printStackTrace();
			return flag;
		}
	}
	
public List<NlyteMasterDataStg> getMasterDtStgImportList(){
		
		List<NlyteMasterDataStg> nlyteMastDtStg =findAll();
		
		return nlyteMastDtStg;
	}
	public List<NlyteMasterDataStg> getAllRecords(){
		return this.getSetResult(null, Constants.ONLY_ACTIVE, true);
	}
	
	public List<NlyteMasterDataStg> getRecentStgRecord(){
		return this.getStgMasterRecord();
	}
	public NlyteMasterDataStg getMasterPrimaryDataByUUID(String uuid) {
		Map<String, Object> m1 = new HashMap<String,Object>();
		m1.put("uniqueId", uuid);
	 	List<NlyteMasterDataStg> l =getSetResult(m1, Constants.ONLY_ACTIVE, true);
	 	return l!=null && l.size()>0?l.get(0):null;
	}
	
}
