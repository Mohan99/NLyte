package com.gcs.nlyte.web.persistance.dao.interfaces;

import java.util.List;

import com.gcs.nlyte.web.persistance.dao.api.INlyteDaoSupport;
import com.gcs.nlyte.web.persistance.dao.entity.NlyteCustomerDataStg;
import com.gcs.nlyte.web.persistance.dao.entity.NlyteUsers;

public interface INlyteUsersDao extends INlyteDaoSupport<NlyteUsers,Long>{
	public NlyteUsers login(String userID, String password);
	public boolean createUser(NlyteUsers nlyteusers);
//	public boolean editUser(NlyteUsers nlyteusers);
	public List<NlyteUsers> getUserList();
	public List<NlyteUsers> getDeletedUserList();
	public boolean updateUser(NlyteUsers nlyteusers);
	public boolean deleteUser(NlyteUsers nlyteusers);
	public List<NlyteUsers> getUserData(int userId);
}
