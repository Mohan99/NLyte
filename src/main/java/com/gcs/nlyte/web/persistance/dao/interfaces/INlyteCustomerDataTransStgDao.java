package com.gcs.nlyte.web.persistance.dao.interfaces;

import java.util.List;

import com.gcs.nlyte.web.bean.NltyeCustDataProcessBean;
import com.gcs.nlyte.web.bean.NltyeCustDataSelectProcessBean;
import com.gcs.nlyte.web.bean.NlyteCustMastDataBean;
import com.gcs.nlyte.web.persistance.dao.api.INlyteDaoSupport;
import com.gcs.nlyte.web.persistance.dao.entity.NltyeCustomerDataProcess;
import com.gcs.nlyte.web.persistance.dao.entity.NlyteCustomerDataStg;
import com.gcs.nlyte.web.persistance.dao.entity.NlyteCustomerDataTransStg;
import com.gcs.nlyte.web.persistance.dao.entity.NlyteMasterDataTransStg;

public interface INlyteCustomerDataTransStgDao extends INlyteDaoSupport<NlyteCustomerDataTransStg,Long>{
	public boolean nlyteCustomerImportEachCell(List<NlyteCustomerDataTransStg> nlyteCustDtTransStgLst);
	public List<NlyteCustomerDataTransStg> getnlyteCustomerList();
	public List<NlyteCustomerDataTransStg> getCustomerDataTransStgList(String cmsId);
	public List<NltyeCustDataProcessBean> getCustomerDataProcessedList(String cmsId);
	public List<NltyeCustDataSelectProcessBean> getCustomerDataProcessedListByNctId(String processNctId);
	public boolean nlyteCustomerUpdate(NlyteCustomerDataTransStg nlyteCustDtStg);
	public List<NltyeCustDataProcessBean> findCustProcessedAllList(String cmsId);
	public int getCustomerDataProcessedListCount();
	public List<NlyteCustMastDataBean> getCustomerDataTransStgProcessedList(String cmsId) ;
	public List<NlyteCustomerDataTransStg> getCustomerDataTransStgUnProcessedList(String cmsId);
	public List<NlyteCustomerDataTransStg> getCustomerDataTransUnmatchedList(String cmsId);
	public List<NltyeCustDataProcessBean> getCustomerDataTransUnmatchedListContain(String cmsId);
	public List<NltyeCustDataProcessBean> getCustomerDataTransStgMultiMatchedList(String cmsId);
	
	public List<NltyeCustDataProcessBean> getCustNeedsToProcessEqual(String cmsId);
}
