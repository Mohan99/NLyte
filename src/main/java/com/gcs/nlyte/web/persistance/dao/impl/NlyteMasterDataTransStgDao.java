package com.gcs.nlyte.web.persistance.dao.impl;

import org.springframework.stereotype.Repository;

import com.gcs.nlyte.web.persistance.dao.api.NlyteDaoSupport;
import com.gcs.nlyte.web.persistance.dao.entity.NlyteCustomerDataStg;
import com.gcs.nlyte.web.persistance.dao.entity.NlyteMasterDataStg;
import com.gcs.nlyte.web.persistance.dao.entity.NlyteMasterDataTransStg;
import com.gcs.nlyte.web.persistance.dao.interfaces.INlyteMasterDataTransStgDao;
import com.gcs.nlyte.web.util.Constants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("nlyteMasterDataTransStgDao")
public class NlyteMasterDataTransStgDao extends NlyteDaoSupport<NlyteMasterDataTransStg, Long>
		implements INlyteMasterDataTransStgDao {
	/*
	 * public void uploadExcelMasterData(NlyteMasterDataStg stg) { insertDto(stg,1);
	 * //save(stg); }
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public boolean nlyteMasterImportEachCell(List<NlyteMasterDataTransStg> nlyteMastDtTransStgLst) {
		boolean flag = false;
		try {
			for (NlyteMasterDataTransStg nlyteMasterDataTransStg : nlyteMastDtTransStgLst) {
				insertDto(nlyteMasterDataTransStg, 1);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return flag;
		}
	}

	public List<NlyteMasterDataTransStg> getnlyteMasterList() {
		List<NlyteMasterDataTransStg> nlyteMastDtTrnsStg = findAll();
		return nlyteMastDtTrnsStg;
	}

	@Override
	public List<NlyteMasterDataTransStg> getMasterDataTransStgList(int nmsId) {
		/*
		 * Map<String, Object> m = new HashMap<String, Object>(); m.put("nms_id",
		 * nmsId); List<NlyteMasterDataTransStg> nlyteMasterDataTransStg =
		 * getSetResult(m, Constants.ONLY_ACTIVE, true);
		 */
		List<NlyteMasterDataTransStg> nlyteMasterDataTransStg = findAll();
		return nlyteMasterDataTransStg;
	}

	@Override
	public List<NlyteMasterDataTransStg> getMasterDataTransStgNmtList(int nmtId) {
		/*
		 * Map<String, Object> m = new HashMap<String, Object>(); m.put("nmt_id",
		 * nmtId); List<NlyteMasterDataTransStg> nlyteMasterDataTransStg =
		 * getSetResult(m, Constants.ONLY_ACTIVE, true);
		 */
		List<NlyteMasterDataTransStg> nlyteMasterDataTransStg = findAllMasterData(nmtId);
		return nlyteMasterDataTransStg;
	}

	@Override
	public boolean nlyteMasterImportUpdateCell(List<NlyteMasterDataTransStg> nlyteMastDtTransStgLst) {

		boolean flag = false;
		try {
			for (NlyteMasterDataTransStg nlyteMasterDataTransStg : nlyteMastDtTransStgLst) {

				updateDto(nlyteMasterDataTransStg, 1);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return flag;
		}
	}

	@Override
	public int getMatchedMasterData(String materialName, String model, String manufacture) {

		return getMatchedDataMaster(materialName, model, manufacture);
	}

	@Override
	public Long getMastCount() {
		return getMaterialCont();
	}
}
