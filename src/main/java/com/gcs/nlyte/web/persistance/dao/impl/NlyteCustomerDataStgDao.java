package com.gcs.nlyte.web.persistance.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gcs.nlyte.web.bean.NlyteProcessedCountBean;
import com.gcs.nlyte.web.persistance.dao.api.NlyteDaoSupport;
import com.gcs.nlyte.web.persistance.dao.entity.NlyteCustomerDataStg;
import com.gcs.nlyte.web.persistance.dao.entity.NlyteCustomerDataTransStg;
import com.gcs.nlyte.web.persistance.dao.interfaces.INlyteCustomerDataStgDao;
import com.gcs.nlyte.web.util.Constants;

@Repository("nlyteCustomerDataStgDao")
public class NlyteCustomerDataStgDao extends NlyteDaoSupport<NlyteCustomerDataStg, Long>
		implements INlyteCustomerDataStgDao {

	public boolean nlyteCustomerImportBulk(NlyteCustomerDataStg nlyteCustDtStg) {
		boolean flag = false;
		try {
			insertDto(nlyteCustDtStg, 1);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return flag;
		}
	}

	public boolean nlyteCustomerUpdate(NlyteCustomerDataStg nlyteCustDtStg) {
		boolean flag = false;
		try {
			updateDto(nlyteCustDtStg, 1);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return flag;
		}
	}

	public List<NlyteCustomerDataStg> getCustomerDtStgImportList() {

		List<NlyteCustomerDataStg> nlyteCustDtStg = findAllCustStgList();

		return nlyteCustDtStg;
	}

	public NlyteCustomerDataStg getCustomerPrimaryDataByUUID(String uuid) {
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("uniqueId", uuid);
		List<NlyteCustomerDataStg> l = getSetResult(m, Constants.ONLY_ACTIVE, true);
		return l != null && l.size() > 0 ? l.get(0) : null;
	}

	@Override
	public boolean updateCustStgData(int cmsId) {
		if (updateCustStg(cmsId)) {
			return true;
		
		}
		else
			return false;
	}

	@Override
	public boolean nlyteCustStgUpdate(NlyteCustomerDataStg prevListdata) {
		boolean flag = false;
		try {

			updateDto(prevListdata, 1);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return flag;
		}
	}

	@Override
	public List<NlyteCustomerDataStg> getCustomerDtStgProcessedList() {
		List<NlyteCustomerDataStg> nlyteCustDtStg = findAllCustProcessed();
		return nlyteCustDtStg;
	}
	
	@Override
	public List<NlyteCustomerDataStg> getCustomerDtStgProcessCompletedList() {
		List<NlyteCustomerDataStg> nlyteCustDtStg = findAllCustProcessCompleted();
		return nlyteCustDtStg;
	}
	
	@Override
	public List<NlyteProcessedCountBean> getCustomerDtStgProcessCount() {
		List<NlyteProcessedCountBean> procCount = findAllCustProcessedCount();
		return procCount;
	}

	@Override
	public List<NlyteCustomerDataStg> getCustToBeProcessList() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean updateCustProcessedByIds(int nctIds, int values,int cmsId) {
		boolean flag = updateCustProcessed(nctIds,values,cmsId);
		return flag;
	}

	@Override
	public List<NlyteCustomerDataStg> getCustomerDtStgReviewList() {
		List<NlyteCustomerDataStg> nlyteCustDtStg = findAllCustReview();
		return nlyteCustDtStg;
	}

	@Override
	public NlyteCustomerDataStg getCustomerDtStgById(String cmsId) {
		NlyteCustomerDataStg nlyteCustDtStg=findCustDtStgById(cmsId);
		return nlyteCustDtStg;
	}
	
	@Override
	public boolean deleteCustData(int cmsId) {
		return deleteCustdataById(cmsId);
	}

}
