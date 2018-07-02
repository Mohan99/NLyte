package com.gcs.nlyte.web.persistance.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gcs.nlyte.web.persistance.dao.api.NlyteDaoSupport;
import com.gcs.nlyte.web.persistance.dao.entity.NlyteCustomerDataStg;
import com.gcs.nlyte.web.persistance.dao.entity.NlyteCustomerDataTransStg;
import com.gcs.nlyte.web.persistance.dao.entity.NlyteUsers;
import com.gcs.nlyte.web.persistance.dao.interfaces.INlyteUsersDao;
import com.gcs.nlyte.web.util.Constants;
@Repository("nlyteUsersDao")
public class NlyteUsersDao extends NlyteDaoSupport<NlyteUsers, Long> implements INlyteUsersDao{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7271759047485644931L;

	public NlyteUsers login(String userID, String password){
		Map<String,Object> m = new HashMap<String,Object>();
		m.put("loginId",userID);
		m.put("passwordTx", password);
		List<NlyteUsers> l = getSetResult(m, Constants.ONLY_ACTIVE, true);
		if(l!=null){
			}else{
				
			}
		return l!=null && l.size()>0?l.get(0):null;
	}
	
	public boolean createUser(NlyteUsers nlyteusers){
		boolean flag=false;
			try{
				insertDto(nlyteusers,1);
		//save(nlyteusers);
		
		return true;
	}
		catch(Exception e){
			e.printStackTrace();
			return flag;
		}
	}
	public List<NlyteUsers> getUserList(){
		
		List<NlyteUsers> nlyteUsers =findAllUsers();
		
		return nlyteUsers;
	}
	
public List<NlyteUsers> getDeletedUserList(){
		
		List<NlyteUsers> nlyteUsers =findDeletedUsers();
		
		return nlyteUsers;
	}
	@Override
	public boolean updateUser(NlyteUsers nlyteusers) {
		boolean flag = false;
		try {
			

			updateDto(nlyteusers, 1);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return flag;
		}
	}
				public boolean deleteUser(NlyteUsers nlyteusers){
				
				boolean flag=false;
				try{
					deleteDto(nlyteusers,1);
			//save(nlyteusers);
			
			return true;
			}
			catch(Exception e){
				e.printStackTrace();
				return flag;
	}
	}
	
	public List<NlyteUsers> getUserData(int userId) {
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("NLYTE_USER_ID", userId);
		List<NlyteUsers> l = getSetResult(m, Constants.ONLY_ACTIVE, true);
		return l ;
		
	}
	
}
