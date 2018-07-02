package com.gcs.nlyte.web.servcie;

import java.util.List;

import com.gcs.nlyte.web.bean.NltyeCustDataProcessBean;
import com.gcs.nlyte.web.bean.NltyeCustDataSelectProcessBean;
import com.gcs.nlyte.web.bean.NlyteCustMastDataBean;
import com.gcs.nlyte.web.bean.NlyteProcessedCountBean;
import com.gcs.nlyte.web.persistance.dao.entity.NltyeCustomerDataProcess;

//import org.springframework.stereotype.Service;

import com.gcs.nlyte.web.persistance.dao.entity.NlyteCustomerDataStg;
import com.gcs.nlyte.web.persistance.dao.entity.NlyteCustomerDataTransStg;
import com.gcs.nlyte.web.persistance.dao.entity.NlyteMasterDataStg;
import com.gcs.nlyte.web.persistance.dao.entity.NlyteMasterDataTransStg;
import com.gcs.nlyte.web.persistance.dao.entity.NlyteUsers;

public interface INlyteService {

	public NlyteUsers login(String userID, String password);

	public boolean createUser(NlyteUsers nlyteusers);

	public boolean editUser(NlyteUsers nlyteusers);

	public List<NlyteUsers> getUserList();

	public List<NlyteUsers> getDeletedUserList();

	public boolean nlyteCustomerImportBulk(NlyteCustomerDataStg nlyteCustDtStg);

	public List<NlyteCustomerDataStg> getCustomerDtStgImportList();
	
	public boolean deleteCustRcrd(int cmsId); 

	public List<NlyteCustomerDataStg> getCustToBeProcessList();

	// public int getNlyteCustPrimaryKey(NlyteCustomerDataStg nlyteCustDtStg);
	public boolean nlyteCustomerImportEachCell(List<NlyteCustomerDataTransStg> nlyteCustDtTransStgLst);

	public List<NlyteCustomerDataTransStg> getnlyteCustomerList();

	public List<NlyteMasterDataStg> getMasterData();
	public List<NlyteMasterDataStg> getMasterDataInOredr();

	public void uploadExcelMasterData(NlyteMasterDataStg stg);

	public boolean nlyteImportCall(NlyteMasterDataStg nlyteMastDtStg);

	public List<NlyteMasterDataStg> getMasterDtStgImportList();

	public NlyteCustomerDataStg getCustomerPrimaryDataByUUID(String uuid);

	public NlyteMasterDataStg getMasterPrimaryDataByUUID(String uuid);

	public boolean nlyteMasterImportEachCell(List<NlyteMasterDataTransStg> nlyteMastDtTransStgLst);

	public boolean nlyteMasterImportUpdateCell(List<NlyteMasterDataTransStg> nlyteMastDtTransStgLst);

	public List<NlyteMasterDataTransStg> getnlyteMasterList();

	public List<NlyteCustomerDataTransStg> getCustomerDataTransStgList(String cmsId);

	public List<NlyteCustomerDataTransStg> getCustomerDataTransUnmatchedList(String cmsId);

	public List<NltyeCustDataProcessBean> getCustomerDataTransUnmatchedListContain(String cmsId);

	public List<NltyeCustDataProcessBean> getCustomerDataProcessedList(String cmsId);

	public List<NltyeCustDataProcessBean> findCustProcessedAllList(String cmsId);

	public int getCustomerDataProcessedListCount();

	public List<NltyeCustDataSelectProcessBean> getCustomerDataProcessedListById(String processNctId);

	public boolean nlyteCustomerUpdate(NlyteCustomerDataTransStg nlyteCustTransDtStg);

	public boolean nlyteCustProcessIns(NltyeCustomerDataProcess process);

	public boolean nlyteUpdateStatus(String cmsId);

	public boolean updateCustStgData(int id);

	public boolean nlyteCustStgUpdate(NlyteCustomerDataStg prevListdata);

	public List<NlyteMasterDataTransStg> getMasterDataTransStgList(int nmsId);

	public List<NlyteMasterDataTransStg> getMasterDataTransStgNmtList(int nctId);

	public List<NlyteMasterDataTransStg> getMasterDataTransStgById(int nmtId);

	public List<NlyteCustomerDataStg> getCustomerDtStgProcessedList();

	public boolean updateUser(NlyteUsers nlyteusers);

	public boolean deleteUser(NlyteUsers nlyteusers);

	public List<NlyteUsers> getUserData(int userId);

	public boolean updateCustProcessedByIds(int keays, int values, int cmsId);

	public List<NlyteCustomerDataStg> getCustomerDtStgReviewList();

	List<NlyteCustomerDataStg> getCustomerDtStgProcessCompletedList();
	public List<NlyteProcessedCountBean> getCustomerDtStgProcessedCount();

	public List<NlyteCustMastDataBean> getCustomerDataTransStgProcessedList(String cmsId);

	public List<NlyteCustomerDataTransStg> getCustomerDataTransStgUnProcessedList(String cmsId);

	public NlyteCustomerDataStg getCustomerDtStgById(String cmsId);

	public List<NltyeCustDataProcessBean> getCustomerDataTransStgMultiMatchedList(String cmsId);

	public List<NltyeCustDataProcessBean> getCustomerDataTransStgListEqual(String cmsId);
	
	public int matchMasterData(String materialName,String model,String manufacture);
	
	public Long getMasterCount();

}
