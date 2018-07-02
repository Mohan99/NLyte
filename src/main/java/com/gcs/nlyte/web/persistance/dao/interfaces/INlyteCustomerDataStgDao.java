package com.gcs.nlyte.web.persistance.dao.interfaces;

import java.util.List;

import com.gcs.nlyte.web.bean.NltyeCustDataProcessBean;
import com.gcs.nlyte.web.bean.NlyteProcessedCountBean;
import com.gcs.nlyte.web.persistance.dao.api.INlyteDaoSupport;
import com.gcs.nlyte.web.persistance.dao.entity.NlyteCustomerDataStg;

public interface INlyteCustomerDataStgDao extends INlyteDaoSupport<NlyteCustomerDataStg,Long> {
	public boolean nlyteCustomerImportBulk(NlyteCustomerDataStg nlyteCustDtStg);
    public List<NlyteCustomerDataStg> getCustomerDtStgImportList();
    //public int getNlyteCustPrimaryKey(NlyteCustomerDataStg nlyteCustDtStg);
    public NlyteCustomerDataStg getCustomerPrimaryDataByUUID(String uId);
    public boolean updateCustStgData(int cmsId) ;
	public boolean nlyteCustStgUpdate(NlyteCustomerDataStg prevListdata);
	public List<NlyteCustomerDataStg> getCustomerDtStgProcessedList();
    public List<NlyteCustomerDataStg> getCustToBeProcessList();
    public boolean updateCustProcessedByIds(int keays, int values,int cmsId);
	public List<NlyteCustomerDataStg> getCustomerDtStgReviewList();
	public List<NlyteCustomerDataStg> getCustomerDtStgProcessCompletedList();
	public List<NlyteProcessedCountBean> getCustomerDtStgProcessCount();
	public NlyteCustomerDataStg getCustomerDtStgById(String cmsId);
	public boolean deleteCustData(int cmsId);
}
