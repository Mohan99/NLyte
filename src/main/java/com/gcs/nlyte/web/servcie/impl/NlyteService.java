package com.gcs.nlyte.web.servcie.impl;

import java.util.List;

import javax.annotation.Resource;
//import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gcs.nlyte.web.bean.NltyeCustDataProcessBean;
import com.gcs.nlyte.web.bean.NltyeCustDataSelectProcessBean;
import com.gcs.nlyte.web.bean.NlyteCustMastDataBean;
import com.gcs.nlyte.web.bean.NlyteProcessedCountBean;
import com.gcs.nlyte.web.persistance.dao.entity.NltyeCustomerDataProcess;
import com.gcs.nlyte.web.persistance.dao.entity.NlyteCustomerDataStg;
import com.gcs.nlyte.web.persistance.dao.entity.NlyteCustomerDataTransStg;
import com.gcs.nlyte.web.persistance.dao.entity.NlyteMasterDataStg;
import com.gcs.nlyte.web.persistance.dao.entity.NlyteMasterDataTransStg;
import com.gcs.nlyte.web.persistance.dao.entity.NlyteUsers;
import com.gcs.nlyte.web.persistance.dao.interfaces.INltyeCustomerDataProcess;
import com.gcs.nlyte.web.persistance.dao.interfaces.INlyteCustomerDataStgDao;
import com.gcs.nlyte.web.persistance.dao.interfaces.INlyteCustomerDataTransStgDao;
import com.gcs.nlyte.web.persistance.dao.interfaces.INlyteMasterDataStgDao;
import com.gcs.nlyte.web.persistance.dao.interfaces.INlyteMasterDataTransStgDao;
import com.gcs.nlyte.web.persistance.dao.interfaces.INlyteUsersDao;
import com.gcs.nlyte.web.servcie.INlyteService;
import com.gcs.nlyte.web.util.Constants;

@Service("nlyteService")
public class NlyteService implements INlyteService {
	// @Resource(name="")
	// private DaoClassName variableName;
	@Resource(name = "nlyteCustomerDataStgDao")
	private INlyteCustomerDataStgDao nlyteCustomerDataStgDao;

	@Resource(name = "nlyteCustomerDataTransStgDao")
	private INlyteCustomerDataTransStgDao nlyteCustomerDataTransStgDao;

	@Resource(name = "nlyteMasterDataStgDao")
	private INlyteMasterDataStgDao nlyteMasterDataStgDao;

	@Resource(name = "nlyteMasterDataTransStgDao")
	private INlyteMasterDataTransStgDao nlyteMasterDataTransStgDao;
	
	@Resource(name = "nltyeCustomerDataProcess")
	private INltyeCustomerDataProcess nltyeCustomerDataProcess;

	// @Resource(name="nlyteUsersDao")
	@Autowired
	private INlyteUsersDao nlyteUsersDao;

	@Autowired
	private INlyteCustomerDataStgDao nlyteCustDtStgObj;

	@Autowired
	private INlyteCustomerDataTransStgDao nlyteCustDtStgTrnsStg;

	@Autowired
	private INlyteMasterDataStgDao nlyteMasterDtStgObj;

	@Autowired
	private INltyeCustomerDataProcess nltyeCustDataProcessObj;
	
	@Autowired
	private INlyteMasterDataTransStgDao nlyteMasterDataTransStg;

	public List<NlyteMasterDataStg> getMasterData() {
		return nlyteMasterDtStgObj.getAllRecords();
	}
	
	public List<NlyteMasterDataStg> getMasterDataInOredr() {
		return nlyteMasterDtStgObj.getRecentStgRecord();
	}

	public NlyteUsers login(String userID, String password) {
		return nlyteUsersDao.login(userID, password);
	}

	@Transactional(readOnly = false)
	public boolean createUser(NlyteUsers nlyteusers) {
		return nlyteUsersDao.createUser(nlyteusers);
	}

	public boolean editUser(NlyteUsers nlyteusers) {
		return nlyteUsersDao.createUser(nlyteusers);
	}

	public List<NlyteUsers> getUserList() {
		return nlyteUsersDao.getUserList();

	}
	
	public List<NlyteUsers> getDeletedUserList() {
		return nlyteUsersDao.getDeletedUserList();

	}

	public void uploadExcelMasterData(NlyteMasterDataStg stg) {
		nlyteMasterDtStgObj.save(stg);
	}

	public List<NlyteCustomerDataStg> getCustomerDtStgImportList() {
		return nlyteCustDtStgObj.getCustomerDtStgImportList();
	}
	
	@Transactional(readOnly = false)
	public boolean deleteCustRcrd(int cmsId) {
		return nlyteCustDtStgObj.deleteCustData(cmsId);
	}

	public List<NlyteCustomerDataTransStg> getNlyteCustomerDataTransStgList(String cmsId) {
		return nlyteCustDtStgTrnsStg.getCustomerDataTransStgList(cmsId);

	}

	// Added for Customer import Bulk
	@Transactional(readOnly = false)
	public boolean nlyteCustomerImportBulk(NlyteCustomerDataStg nlyteCustDtStg) {
		return nlyteCustDtStgObj.nlyteCustomerImportBulk(nlyteCustDtStg);
	}

	@Transactional(readOnly = false)
	public boolean nlyteCustomerUpdate(NlyteCustomerDataTransStg nlyteCustDtStg) {
		return nlyteCustDtStgTrnsStg.nlyteCustomerUpdate(nlyteCustDtStg);
	}
	
	@Transactional(readOnly = false)
	public boolean nlyteCustProcessIns(NltyeCustomerDataProcess process) {
		return nltyeCustDataProcessObj.nlyteCustProcessIns(process);
	}
	
	@Transactional(readOnly = false)
	public boolean nlyteUpdateStatus(String cmsId) {
		return nltyeCustDataProcessObj.nlyteUpdateStatus(cmsId);
	}

	public List<NlyteCustomerDataTransStg> getnlyteCustomerList() {
		return nlyteCustDtStgTrnsStg.getnlyteCustomerList();

	}

	@Transactional(readOnly = false)
	public boolean nlyteCustomerImportEachCell(List<NlyteCustomerDataTransStg> nlyteCustDtTransStgLst) {
		return nlyteCustDtStgTrnsStg.nlyteCustomerImportEachCell(nlyteCustDtTransStgLst);
	}

	// Added for Customer import Bulk
	@Transactional(readOnly = false)
	public boolean nlyteImportCall(NlyteMasterDataStg nlyteMastDtStg) {
		return nlyteMasterDtStgObj.nlyteImportCall(nlyteMastDtStg);
	}

	public List<NlyteMasterDataStg> getMasterDtStgImportList() {
		return nlyteMasterDtStgObj.getMasterDtStgImportList();

	}

	@Transactional(readOnly = false)
	public NlyteCustomerDataStg getCustomerPrimaryDataByUUID(String uuid) {
		return nlyteCustDtStgObj.getCustomerPrimaryDataByUUID(uuid);
	}

	@Transactional(readOnly = false)
	public NlyteMasterDataStg getMasterPrimaryDataByUUID(String uuid) {
		return nlyteMasterDtStgObj.getMasterPrimaryDataByUUID(uuid);
	}

	@Transactional(readOnly = false)
	public boolean nlyteMasterImportEachCell(List<NlyteMasterDataTransStg> nlyteMastDtTransStgLst) {
		return nlyteMasterDataTransStg.nlyteMasterImportEachCell(nlyteMastDtTransStgLst);
	}

	@Transactional(readOnly = false)
	public boolean nlyteMasterImportUpdateCell(List<NlyteMasterDataTransStg> nlyteMastDtTransStgLst) {
		return nlyteMasterDataTransStg.nlyteMasterImportUpdateCell(nlyteMastDtTransStgLst);
	}

	public List<NlyteMasterDataTransStg> getnlyteMasterList() {
		return nlyteMasterDataTransStg.getnlyteMasterList();

	}

	public List<NlyteCustomerDataTransStg> getCustomerDataTransStgList(String cmsId) {
		return nlyteCustDtStgTrnsStg.getCustomerDataTransStgList(cmsId);

	}
	
	public List<NltyeCustDataProcessBean> getCustomerDataTransStgListEqual(String cmsId) {
		return nlyteCustDtStgTrnsStg.getCustNeedsToProcessEqual(cmsId);

	}
	
	public List<NlyteCustMastDataBean> getCustomerDataTransStgProcessedList(String cmsId) {
		return nlyteCustDtStgTrnsStg.getCustomerDataTransStgProcessedList(cmsId);

	}
	
	public List<NlyteCustomerDataTransStg> getCustomerDataTransStgUnProcessedList(String cmsId) {
		return nlyteCustDtStgTrnsStg.getCustomerDataTransStgUnProcessedList(cmsId);

	}
	
	public List<NltyeCustDataProcessBean> getCustomerDataProcessedList(String cmsId) {
		return nlyteCustDtStgTrnsStg.getCustomerDataProcessedList(cmsId);

	}
	public List<NltyeCustDataProcessBean> findCustProcessedAllList(String cmsId) {
		return nlyteCustDtStgTrnsStg.findCustProcessedAllList(cmsId);

	}
	
	public int getCustomerDataProcessedListCount() {
		return nlyteCustDtStgTrnsStg.getCustomerDataProcessedListCount();

	}
	
	public List<NltyeCustDataSelectProcessBean> getCustomerDataProcessedListById(String processNctId) {
		return nlyteCustDtStgTrnsStg.getCustomerDataProcessedListByNctId(processNctId);

	}

	public List<NlyteMasterDataTransStg> getMasterDataTransStgList(int nmsId) {
		return nlyteMasterDataTransStg.getMasterDataTransStgList(nmsId);

	}
	
	public List<NlyteMasterDataTransStg> getMasterDataTransStgNmtList(int nctId) {
		return nlyteMasterDataTransStg.getMasterDataTransStgNmtList(nctId);

	}
	
	public List<NlyteMasterDataTransStg> getMasterDataTransStgById(int nmtId) {
		return nlyteMasterDataTransStg.getMasterDataTransStgNmtList(nmtId);

	}

	@Override
	public boolean updateCustStgData(int id) {
		return nlyteCustDtStgObj.updateCustStgData(id);

	}

	@Transactional(readOnly = false)
	public boolean nlyteCustStgUpdate(NlyteCustomerDataStg prevListdata) {
		return nlyteCustDtStgObj.nlyteCustStgUpdate(prevListdata);
	}

	@Override
	public List<NlyteCustomerDataStg> getCustomerDtStgProcessedList() {
		return nlyteCustDtStgObj.getCustomerDtStgProcessedList();
	}
	
	@Override
	public List<NlyteCustomerDataStg> getCustomerDtStgProcessCompletedList() {
		return nlyteCustDtStgObj.getCustomerDtStgProcessCompletedList();
	}
	
	@Override
	public List<NlyteProcessedCountBean> getCustomerDtStgProcessedCount() {
		return nlyteCustDtStgObj.getCustomerDtStgProcessCount();
	}

	@Override
	public List<NlyteUsers> getUserData(int userId) {
		return nlyteUsersDao.getUserData(userId);
	}

	public boolean deleteUser(NlyteUsers nlyteusers) {
		return nlyteUsersDao.deleteUser(nlyteusers);
	}
	
	@Transactional(readOnly=false)
	public boolean updateUser(NlyteUsers nlyteusers) {
	return nlyteUsersDao.updateUser(nlyteusers);
	}
	public List<NlyteCustomerDataStg> getCustToBeProcessList() {
		return nlyteCustDtStgObj.getCustToBeProcessList();

	}
	@Transactional(readOnly=false)
	public boolean updateCustProcessedByIds(int nctIds, int values,int cmsId) {
		return nlyteCustDtStgObj.updateCustProcessedByIds(nctIds,values,cmsId);

	}

	@Override
	public List<NlyteCustomerDataStg> getCustomerDtStgReviewList() {
		return nlyteCustDtStgObj.getCustomerDtStgReviewList();
	}

	@Override
	public List<NlyteCustomerDataTransStg> getCustomerDataTransUnmatchedList(String cmsId) {
		return nlyteCustDtStgTrnsStg.getCustomerDataTransUnmatchedList(cmsId);

	}
	
	@Override
	public List<NltyeCustDataProcessBean> getCustomerDataTransUnmatchedListContain(String cmsId) {
		return nlyteCustDtStgTrnsStg.getCustomerDataTransUnmatchedListContain(cmsId);

	}

	@Override
	public NlyteCustomerDataStg getCustomerDtStgById(String cmsId) {
		return nlyteCustDtStgObj.getCustomerDtStgById(cmsId);
	}
	public List<NltyeCustDataProcessBean> getCustomerDataTransStgMultiMatchedList(String cmsId) {
		return nlyteCustDtStgTrnsStg.getCustomerDataTransStgMultiMatchedList(cmsId);

		}

	@Override
	public int matchMasterData(String materialName, String model, String manufacture) {
		// TODO Auto-generated method stub
		return nlyteMasterDataTransStg.getMatchedMasterData(materialName,model,manufacture);
	}

	@Override
	public Long getMasterCount() {
		
		return nlyteMasterDataTransStg.getMastCount();
	}
	

}
