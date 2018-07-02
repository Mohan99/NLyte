package com.gcs.nlyte.web.persistance.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gcs.nlyte.web.bean.NltyeCustDataProcessBean;
import com.gcs.nlyte.web.bean.NltyeCustDataSelectProcessBean;
import com.gcs.nlyte.web.bean.NlyteCustMastDataBean;
import com.gcs.nlyte.web.persistance.dao.api.NlyteDaoSupport;
import com.gcs.nlyte.web.persistance.dao.entity.NltyeCustomerDataProcess;
import com.gcs.nlyte.web.persistance.dao.entity.NlyteCustomerDataStg;
import com.gcs.nlyte.web.persistance.dao.entity.NlyteCustomerDataTransStg;
import com.gcs.nlyte.web.persistance.dao.entity.NlyteMasterDataTransStg;
import com.gcs.nlyte.web.persistance.dao.entity.NlyteUsers;
import com.gcs.nlyte.web.persistance.dao.interfaces.INlyteCustomerDataTransStgDao;
import com.gcs.nlyte.web.util.Constants;

@Repository("nlyteCustomerDataTransStgDao")
public class NlyteCustomerDataTransStgDao extends NlyteDaoSupport<NlyteCustomerDataTransStg, Long>
		implements INlyteCustomerDataTransStgDao {
	public boolean nlyteCustomerImportEachCell(List<NlyteCustomerDataTransStg> nlyteCustDtTransStgLst) {
		boolean flag = false;
		try {
			for (NlyteCustomerDataTransStg nlyteCustomerDataTransStg : nlyteCustDtTransStgLst) {
				insertDto(nlyteCustomerDataTransStg, 1);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return flag;
		}
	}

	public List<NlyteCustomerDataTransStg> getnlyteCustomerList() {

		List<NlyteCustomerDataTransStg> nlyteCustDtTrnsStg = findAll();

		return nlyteCustDtTrnsStg;
	}
	
	public List<NlyteCustMastDataBean> getCustomerDataTransStgProcessedList(String cmsId) {
		List<NlyteCustMastDataBean> nlyteCustMastDataBean = findProcessedData(cmsId);
		return nlyteCustMastDataBean;
	}
	
	public List<NlyteCustomerDataTransStg> getCustomerDataTransStgUnProcessedList(String cmsId) {
		List<NlyteCustomerDataTransStg> nlyteCustDtTrnsStg = findUnProcessedData(cmsId);
		return nlyteCustDtTrnsStg;
	}

	public List<NlyteCustomerDataTransStg> getCustomerDataTransStgList(String cmsId) {
		List<NlyteCustomerDataTransStg> nlyteCustomerDataTransStg =getCustNeedsToProcess(cmsId);
		return nlyteCustomerDataTransStg;
	}
	
	public List<NltyeCustDataProcessBean> getCustNeedsToProcessEqual(String cmsId) {
		List<NltyeCustDataProcessBean> nlyteCustomerDataTransStg =getCustNeedsToProcessEqui(cmsId);
		return nlyteCustomerDataTransStg;
	}
	
	

	@Override
	public boolean nlyteCustomerUpdate(NlyteCustomerDataTransStg nlyteCustDtStg) {
		boolean flag = false;
		try {
			updateDto(nlyteCustDtStg, 1);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return flag;
		}
	}

	@Override
	public List<NltyeCustDataProcessBean> getCustomerDataProcessedList(String cmsId) {

		List<NltyeCustDataProcessBean> nlyteCustomerDataTransStg = findCustProcessed(cmsId);
		return nlyteCustomerDataTransStg;
	}

	public List<NltyeCustDataSelectProcessBean> getCustomerDataProcessedListByNctId(String processNctId) {

		List<NltyeCustDataSelectProcessBean> nlyteCustomerDataprocess = findCustProcessedByNctId(processNctId);
		return nlyteCustomerDataprocess;
	}

	@Override
	public List<NltyeCustDataProcessBean> findCustProcessedAllList(String cmsId) {
		List<NltyeCustDataProcessBean> nlyteCustomerDataTransStg = findCustProcessedAll(cmsId);
		return nlyteCustomerDataTransStg;
	}
	
	
	public int getCustomerDataProcessedCount() {
		int  count = getCustomerDataProcessedListCount();
		return count;
	}

	@Override
	public List<NlyteCustomerDataTransStg> getCustomerDataTransUnmatchedList(String cmsId) {
		List<NlyteCustomerDataTransStg> nlyteCustomerDataTransStg = getCustUnprocessList(cmsId);
		return nlyteCustomerDataTransStg;
	}
	
	@Override
	public List<NltyeCustDataProcessBean> getCustomerDataTransUnmatchedListContain(String cmsId) {
		List<NltyeCustDataProcessBean> nlyteCustomerDataTransStg = getCustUnprocessListContains(cmsId);
		return nlyteCustomerDataTransStg;
	}

	public List<NltyeCustDataProcessBean> getCustomerDataTransStgMultiMatchedList(String cmsId) {
		List<NltyeCustDataProcessBean> nlyteCustDtTrnsStg = findMultipleMatchedData(cmsId);
		return nlyteCustDtTrnsStg;
}
}
