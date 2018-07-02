package com.gcs.nlyte.web.persistance.dao.api;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import com.gcs.nlyte.web.bean.NltyeCustDataProcessBean;
import com.gcs.nlyte.web.bean.NltyeCustDataSelectProcessBean;
import com.gcs.nlyte.web.bean.NlyteCustMastDataBean;
import com.gcs.nlyte.web.bean.NlyteProcessedCountBean;
import com.gcs.nlyte.web.persistance.dao.entity.NltyeCustomerDataProcess;
import com.gcs.nlyte.web.persistance.dao.entity.NlyteCustomerDataStg;
import com.gcs.nlyte.web.persistance.dao.entity.NlyteCustomerDataTransStg;
import com.gcs.nlyte.web.persistance.dao.entity.NlyteMasterDataTransStg;
import com.gcs.nlyte.web.util.Constants;
import com.gcs.nlyte.web.util.ParametarizedData;

public abstract class NlyteDaoSupport<T, ID extends Serializable> implements INlyteDaoSupport<T, ID>, Serializable {

	private static final long serialVersionUID = 88766925821795497L;
	private Class<T> clazz;
	protected boolean isActive = true;

	public NlyteDaoSupport() {
		this.clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@PersistenceContext
	EntityManager entityManager;

	public EntityManager getEntityManager() {
		return this.entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public EntityManager getSession() {
		return this.entityManager;
	}

	public void setClazz(Class<T> clazzToSet) {
		this.clazz = clazzToSet;
	}

	public T findOne(Long id) {
		return this.entityManager.find(this.clazz, id);
	}

	public List<T> findAllUsers() {
		return this.entityManager.createQuery("from  NlyteUsers where activeYn=true").getResultList();
	}

	public List<T> findDeletedUsers() {
		return this.entityManager.createQuery("from  NlyteUsers where activeYn=false").getResultList();
	}

	public List<T> findAll() {
		return this.entityManager.createQuery("from " + this.clazz.getName()).getResultList();
	}

	public List<T> findAllCustStgList() {
		return this.entityManager.createQuery("from NlyteCustomerDataStg order by cmsId desc").getResultList();
	}

	public List<T> findAllMasterData(int nmtId) {
		return this.entityManager.createQuery("from NlyteMasterDataTransStg where nmtId=" + nmtId).getResultList();
	}

	public NlyteCustomerDataStg findCustDtStgById(String cmsId) {
		int cmsIntVal = Integer.parseInt(cmsId);
		List<NlyteCustomerDataStg> nlyteCustDataStg = this.entityManager
				.createQuery("from NlyteCustomerDataStg where cmsId=" + cmsId).getResultList();
		return nlyteCustDataStg.get(0);
	}

	public boolean deleteCustdataById(int cmsId) {
		Query processDelQuery = entityManager
				.createQuery("delete from NltyeCustomerDataProcess where nlyteCustomerDataTransStg in "
						+ "(select nctId from NlyteCustomerDataTransStg where nlyteCustomerDataStg=" + cmsId + ")");
		Query transDelQuery = entityManager
				.createQuery("delete from NlyteCustomerDataTransStg where nlyteCustomerDataStg=" + cmsId);

		Query stgDelQuery = entityManager.createQuery("delete from NlyteCustomerDataStg where cmsId=" + cmsId);
		processDelQuery.executeUpdate();
		transDelQuery.executeUpdate();
		stgDelQuery.executeUpdate();
		return true;
	}

	public List<NlyteCustMastDataBean> findProcessedData(String cmsId) {

		List<Object[]> object = this.entityManager.createQuery(
				"from NlyteCustomerDataTransStg c, NlyteMasterDataTransStg m where c.nmsId is not null and m.nmtId=c.nmsId and c.nlyteCustomerDataStg="
						+ Integer.parseInt(cmsId))
				.getResultList();

		List<NlyteCustMastDataBean> nltyeCustMastDatasBean = new ArrayList<NlyteCustMastDataBean>();

		for (Object[] obj : object) {

			NlyteCustMastDataBean bean = new NlyteCustMastDataBean();

			NlyteCustomerDataTransStg nlyteCustomerDataTransStg = (NlyteCustomerDataTransStg) obj[0];
			NlyteMasterDataTransStg nlyteMasterDataTransStg = (NlyteMasterDataTransStg) obj[1];

			bean.setnCustDataTransStg(nlyteCustomerDataTransStg);
			bean.setnMasterDataTrans(nlyteMasterDataTransStg);
			nltyeCustMastDatasBean.add(bean);

		}
		return nltyeCustMastDatasBean;

	}

	public List<T> findUnProcessedData(String cmsId) {

		return this.entityManager
				.createQuery("from NlyteCustomerDataTransStg where nmsId is null and nlyteCustomerDataStg="
						+ Integer.parseInt(cmsId))
				.getResultList();
	}

	public void save(T entity) {
		this.entityManager.persist(entity);
		// em.getTransaction().commit();
		// this.entityManager.getTransaction().commit();
	}

	public void update(T entity) {
		this.entityManager.merge(entity);
	}

	public void delete(T entity) {
		this.entityManager.remove(entity);
	}

	public void deleteById(Long entityId) {
		T entity = this.findOne(entityId);
		this.delete(entity);
	}

	/**** DAO Intelligence START ****/

	protected String activeYnQueryString(String className, List<String> list, String rootName, boolean isStart,
			boolean isOnlyForRoot, boolean value) {
		String returnValue = "";
		if (list == null)
			list = new ArrayList<String>();
		if (!list.contains(className))
			list.add(className);
		if (!isOnlyForRoot) {
			try {
				Class c = Class.forName(className);
				Field[] f = c.getDeclaredFields();
				for (int i = 0; i < f.length; i++) {
					String typeName = f[i].getType().getName();
					if (typeName.startsWith("com.gcs.nlyte.web") && !list.contains(typeName)) {
						String rootValue = (rootName != null && !rootName.equals("") ? (rootName + ".") : "")
								+ f[i].getName();
						returnValue = " AND (" + rootValue + " is null OR " + rootValue + ".activeYn = "
								+ (value ? "true" : "false") + ")";
						returnValue = returnValue + activeYnQueryString(typeName, list, rootValue, false, false, value);
					}
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		returnValue = (isStart ? ("activeYn =" + (value ? "true" : "false")) : "") + returnValue;
		return returnValue;
	}

	protected String buildQuery(String whereClause, short operation, boolean isOnlyForRoot) {
		String tableName = clazz.getName();
		tableName = tableName.substring(tableName.lastIndexOf('.') + 1);
		boolean activeYn = false;
		if (operation == Constants.ONLY_ACTIVE)
			activeYn = true;
		else if (operation == Constants.ONLY_IN_ACTIVE)
			activeYn = false;

		String activeYnStr = (operation < Constants.ALL
				? activeYnQueryString(clazz.getName(), null, null, true, isOnlyForRoot, activeYn)
				: "");
		activeYnStr = activeYnStr != null && !activeYnStr.trim().equals("") ? (" where " + activeYnStr) : "";
		whereClause = (activeYnStr.equals("") && whereClause != null && !whereClause.trim().equals("")
				? (" where " + whereClause)
				: whereClause != null && !whereClause.trim().equals("") ? (" AND " + whereClause) : "");
		whereClause = whereClause != null && !whereClause.trim().equals("") ? whereClause : "";
		tableName = "FROM " + tableName + " " + activeYnStr + " " + whereClause;
		if (orderByField != null) {
			tableName = tableName + " order by " + orderByField + " " + (isDescending ? "desc" : "asc");
		}

		return tableName;
	}

	protected String orderByField;
	protected boolean isDescending;
	protected int maxRecords = 100;
	protected int startRecord = 0;

	protected Query getQueryObject(Map<String, Object> map, short operation, boolean isOnlyForRoot) {
		String whereClause = "";
		if (map != null) {
			Iterator<String> itr = map.keySet().iterator();
			boolean isFirst = true;
			while (itr.hasNext()) {
				String key = itr.next();
				String refName = key + "ref";
				refName = refName.replaceAll("\\.", "");
				String operator = Constants.EQUAL;
				if (map.get(key) instanceof ParametarizedData) {
					ParametarizedData pd = (ParametarizedData) map.get(key);
					operator = pd.getOperator();
				}
				whereClause = whereClause + (!isFirst ? " AND " : "") + key + " " + operator + " :" + refName;
				isFirst = false;
			}
		}
		String qryStr = buildQuery(whereClause, operation, isOnlyForRoot);

		Query query = this.entityManager.createQuery(qryStr);
		if (map != null) {
			Iterator<String> itr = map.keySet().iterator();
			while (itr.hasNext()) {
				String key = itr.next();
				String refName = key + "ref";
				refName = refName.replaceAll("\\.", "");
				Object obj = null;
				if (map.get(key) instanceof ParametarizedData) {
					ParametarizedData pd = (ParametarizedData) map.get(key);
					obj = pd.getParameterObject();
				} else
					obj = map.get(key);
				// if(obj instanceof List)query.setParameterList(refName,(List)obj);
				// else
				query.setParameter(refName, obj);
			}
		}
		query.setFirstResult(startRecord);
		query.setMaxResults(maxRecords);
		return query;
	}

	private static String getClassName(Object obj, boolean isPackageRequired) {
		String className = obj.getClass().getName();
		if (isPackageRequired)
			return className;
		else
			return className.substring(className.lastIndexOf('.') + 1);
	}

	protected T getUniqueObject(Map<String, Object> map, short operation, boolean isOnlyForRoot) {
		return (T) getQueryObject(map, operation, isOnlyForRoot).getSingleResult();
	}

	protected List<T> getSetResult(Map<String, Object> map, short operation, boolean isOnlyForRoot) {
		Query query = getQueryObject(map, operation, isOnlyForRoot);

		List list = query.getResultList();
		return list;
	}

	protected void doTransaction(Object object, Integer userId, int type) {
		// Transaction tx = null;Byte(b)
		try {
			setAuditData(object, userId, type);
			// LogT logt = new LogT();
			String tableName = getTableName(this.entityManager, this.clazz);
			// logt.setLogTypeTx(type==Constants.INSERT?"I":type==Constants.DELETE?"D":"U");
			// logt.setLogTableTx(tableName);
			if (type != Constants.INSERT) {
				// entityManager.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(arg0)
				// Query q = this.entityManager.createQuery("FROM "+getClassName(object,false) +
				// " ");
			}
			switch (type) {
			case Constants.INSERT:
				this.entityManager.persist(object);
				break;
			case Constants.UPDATE:
				this.entityManager.merge(object);
				break;
			case Constants.DELETE:
				this.entityManager.remove(object);
				break;
			}

			// TODO -- need to get old values and new values and should configurae it
			// logt.setUpdatedBy(userId);
			// logt.setUpdatedDt(new Date());
			// session.save(logt);

			// TODO - need to save the LOG_T
			// tx.commit();
		} catch (Exception ex) {
			// if(tx!=null)tx.rollback();
			// TODO - raise exception
			ex.printStackTrace();
		}
	}

	/**
	 * Returns the table name for a given entity type in the {@link EntityManager}.
	 * 
	 * @param em
	 * @param entityClass
	 * @return
	 */
	public static <T> String getTableName(EntityManager em, Class<T> entityClass) {
		/*
		 * Check if the specified class is present in the metamodel. Throws
		 * IllegalArgumentException if not.
		 */
		Metamodel meta = em.getMetamodel();
		EntityType<T> entityType = meta.entity(entityClass);
		Table t = entityClass.getAnnotation(Table.class);
		String tableName = (t == null) ? entityType.getName().toUpperCase() : t.name();
		return tableName;
	}

	private void setAuditData(Object obj, Integer userId, int type) {

	}

	private void setAuditValue(String methodName, Object obj, Class c, Class[] classArgs, Object args) {
		try {
			Method method = c.getMethod(methodName, classArgs);
			method.invoke(obj, args);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void insertDto(Object obj, Integer userId) {
		doTransaction(obj, userId, Constants.INSERT);
		// this.entityManager.getTransaction().commit();
	}

	public void updateDto(Object obj, Integer userId) {
		doTransaction(obj, userId, Constants.UPDATE);
	}

	public void deleteDto(Object obj, Integer userId) {
		doTransaction(obj, userId, Constants.DELETE);
	}

	/**** DAO Intelligence STOP ****/
	/*
	 * public int getLatestInsertedRecord(int id){ String result="-1"; result =
	 * this.entityManager.createQuery("SELECT SCOPE_IDENTITY()"); return result;
	 * //i=result; //return this.entityManager.createQuery( "IDENT_CURRENT (" +
	 * this.clazz.getName() +")"); }
	 */
	public int create(Object obj) {
		int i = -1;
		NlyteCustomerDataStg nlytecustdtStg = (NlyteCustomerDataStg) obj;
		String tableName = getTableName(this.entityManager, this.clazz);

		String className = obj.getClass().getName();
		if ("NLYTE_MASTER_DATA_STG".equalsIgnoreCase(tableName)) {

			i = nlytecustdtStg.getCmsId();
		}

		return i;
	}

	public boolean updateCustStg(int id) {
		entityManager.createQuery("update NlyteCustomerDataStg set status='Y' , processStatus='N' where cmsId =" + id);
		return true;
	}

	public boolean updateCustProcessed(int nctId, int nmtId, int cmsId) {
		Query q = entityManager.createQuery("update NlyteCustomerDataTransStg set nmsId=" + nmtId
				+ ",nmsValue=(select modelTx from NlyteMasterDataTransStg where nmtId=" + nmtId + "),  "
				+ "matchPercentage=(select perentage from NltyeCustomerDataProcess where nlyteCustomerDataTransStg="
				+ nctId + " and nlyteMasterDataTransStg=" + nmtId + ") where nctId =" + nctId);
		q.executeUpdate();
		Query q1 = entityManager
				.createQuery("update NltyeCustomerDataProcess set status=1 where nlyteCustomerDataTransStg =" + nctId);
		q1.executeUpdate();
		List<Object[]> object = entityManager
				.createQuery("FROM NlyteCustomerDataStg a, NltyeCustomerDataProcess b,NlyteCustomerDataTransStg c"
						+ " where a.cmsId =c.nlyteCustomerDataStg and c.nctId=b.nlyteCustomerDataTransStg and b.status=0  and a.cmsId="
						+ cmsId)
				.getResultList();
		if (object.isEmpty()) {
			Query q2 = entityManager
					.createQuery("update NlyteCustomerDataStg set processStatus='Y' where  cmsId =" + cmsId);
			q2.executeUpdate();
		}
		return true;
	}

	public List<T> findAllCustProcessed() {
		return this.entityManager.createQuery("from NlyteCustomerDataStg where status='Y' and processStatus='Y' ")
				.getResultList();
	}

	public List<T> findAllCustProcessCompleted() {
		return this.entityManager.createQuery("from NlyteCustomerDataStg where status='Y' order by cmsId desc")
				.getResultList();

	}

	public List<T> getStgMasterRecord() {
		return this.entityManager.createQuery("from NlyteMasterDataStg order by nmsId desc").setMaxResults(1)
				.getResultList();
	}

	public List<NlyteProcessedCountBean> findAllCustProcessedCount() {
		List<Long> procCount = new ArrayList<Long>();
		List<NlyteProcessedCountBean> nlyteProcessedCountBean = new ArrayList<NlyteProcessedCountBean>();
		List<Object[]> object = this.entityManager
				.createQuery("select c.cmsId as custStgId,(select count(1) from  NlyteCustomerDataTransStg where \r\n"
						+ "nlyteCustomerDataStg=c.cmsId and nmsId is not null) \r\n"
						+ " as matchCount, (select count(1) from  NlyteCustomerDataTransStg where \r\n"
						+ "nlyteCustomerDataStg=c.cmsId and nmsId is null) \r\n"
						+ " as unMatchCount,(SELECT  count(distinct b.nctId )  FROM NltyeCustomerDataProcess a,\r\n"
						+ " NlyteCustomerDataTransStg b \r\n"
						+ " where b.nctId=a.nlyteCustomerDataTransStg and b.nlyteCustomerDataStg=c.cmsId and a.status=0) as multiMatchedCount FROM\r\n"
						+ "  NlyteCustomerDataStg c where c.status='Y' order by c.cmsId desc")
				.getResultList();
		for (Object[] obj : object) {
			NlyteProcessedCountBean bean = new NlyteProcessedCountBean();
			// NlyteProcessedCountBean bean=(NlyteProcessedCountBean)obj[0];
			bean.setCustStgId((Integer) obj[0]);
			bean.setMatchCount((Long) obj[1]);
			bean.setUnMatchCount((Long) obj[2]);
			bean.setMultiMatchedCount((Long) obj[3]);
			nlyteProcessedCountBean.add(bean);
		}
		return nlyteProcessedCountBean;
	}

	public List<T> findAllCustReview() {
		return this.entityManager.createQuery("from NlyteCustomerDataStg where status='Y' and processStatus='N' ")
				.getResultList();
	}

	public List<NltyeCustDataProcessBean> findCustProcessedAll(String cmsId) {
		List<Object[]> object = this.entityManager
				.createQuery("from NlyteCustomerDataTransStg a, NlyteCustomerDataStg b,NltyeCustomerDataProcess c,"
						+ " NlyteMasterDataTransStg d where c.nlyteMasterDataTransStg=d.nmtId and c.nlyteCustomerDataTransStg=a.nctId and c.status=0 and b.cmsId=a.nlyteCustomerDataStg and b.cmsId="
						+ cmsId + " order by c.nlyteCustomerDataTransStg")
				.getResultList();

		List<NltyeCustDataProcessBean> nltyeCustDataProcessBean = new ArrayList<NltyeCustDataProcessBean>();

		for (Object[] obj : object) {
			NltyeCustDataProcessBean bean = new NltyeCustDataProcessBean();
			NlyteCustomerDataTransStg nlyteCustomerDataTransStg = (NlyteCustomerDataTransStg) obj[0];
			NlyteCustomerDataStg nlyteCustomerDataStg = (NlyteCustomerDataStg) obj[1];
			NltyeCustomerDataProcess nltyeCustomerDataProcess = (NltyeCustomerDataProcess) obj[2];
			NlyteMasterDataTransStg nlyteMasterDataTransStg = (NlyteMasterDataTransStg) obj[3];

			bean.setnCustDataTransStg(nlyteCustomerDataTransStg);
			bean.setnCustDataStg(nlyteCustomerDataStg);
			bean.setnCustDataProcess(nltyeCustomerDataProcess);
			bean.setnMasterDataTrans(nlyteMasterDataTransStg);
			nltyeCustDataProcessBean.add(bean);
		}
		return nltyeCustDataProcessBean;
	}

	public int getCustomerDataProcessedListCount() {
		List count = this.entityManager
				.createQuery(" from NlyteCustomerDataTransStg where NMS_ID!=0  and NMS_ID is not null").getResultList();
		return count != null ? count.size() : 0;
	}

	public List<NltyeCustDataProcessBean> findCustProcessed(String cmsId) {

		List<Object[]> object = this.entityManager
				.createQuery("from NlyteCustomerDataTransStg a, NlyteCustomerDataStg b,NltyeCustomerDataProcess c,"
						+ " NlyteMasterDataTransStg d where c.nlyteMasterDataTransStg=d.nmtId and c.nlyteCustomerDataTransStg=a.nctId and c.status=0 and"
						+ " b.cmsId=a.nlyteCustomerDataStg and b.cmsId=" + cmsId
						+ " order by c.nlyteCustomerDataTransStg")
				.getResultList();

		List<NltyeCustDataProcessBean> nltyeCustDataProcessBean = new ArrayList<NltyeCustDataProcessBean>();

		int i = 0;
		int temp = 0;
		for (Object[] obj : object) {
			i++;
			NltyeCustDataProcessBean bean = new NltyeCustDataProcessBean();
			NlyteCustomerDataTransStg nlyteCustomerDataTransStg = (NlyteCustomerDataTransStg) obj[0];
			NlyteCustomerDataStg nlyteCustomerDataStg = (NlyteCustomerDataStg) obj[1];
			NltyeCustomerDataProcess nltyeCustomerDataProcess = (NltyeCustomerDataProcess) obj[2];
			NlyteMasterDataTransStg nlyteMasterDataTransStg = (NlyteMasterDataTransStg) obj[3];
			// Removing Duplicate objects from NlyteCustomerDataTransStg
			/*
			 * System.out.println("i ="+i);
			 * System.out.println("nlyteCustomerDataTransStg.getNctId() ="
			 * +nlyteCustomerDataTransStg.getNctId()); System.out.println("temp ="+temp);
			 */
			if (temp != nlyteCustomerDataTransStg.getNctId()) {
				bean.setnCustDataTransStg(nlyteCustomerDataTransStg);
				bean.setnCustDataStg(nlyteCustomerDataStg);
				bean.setnCustDataProcess(nltyeCustomerDataProcess);
				bean.setnMasterDataTrans(nlyteMasterDataTransStg);
				nltyeCustDataProcessBean.add(bean);
			}
			temp = nlyteCustomerDataTransStg.getNctId();
		}
		return nltyeCustDataProcessBean;
	}

	public List<NltyeCustDataSelectProcessBean> findCustProcessedByNctId(String processNctId) {

		List<Object[]> object = this.entityManager.createQuery(
				"from NltyeCustomerDataProcess a,NlyteMasterDataTransStg b where b.nmtId=a.nlyteMasterDataTransStg and a.nlyteCustomerDataTransStg= "
						+ processNctId)
				.getResultList();

		List<NltyeCustDataSelectProcessBean> nltyeCustDataSelectList = new ArrayList<NltyeCustDataSelectProcessBean>();

		for (Object[] obj : object) {
			NltyeCustDataSelectProcessBean bean = new NltyeCustDataSelectProcessBean();
			NltyeCustomerDataProcess nltyeCustomerDataProcess = (NltyeCustomerDataProcess) obj[0];
			NlyteMasterDataTransStg nlyteMasterDataTransStg = (NlyteMasterDataTransStg) obj[1];

			bean.setnCustDataProcess(nltyeCustomerDataProcess);
			bean.setnMasterDataTrans(nlyteMasterDataTransStg);
			nltyeCustDataSelectList.add(bean);

		}
		return nltyeCustDataSelectList;
	}

	public boolean nlyteUpdateProcessStatus(String cmsId) {
		int id = Integer.parseInt(cmsId);
		Query query = entityManager.createQuery("update NlyteCustomerDataStg set processStatus='N' where cmsId=" + id);
		query.executeUpdate();
		return true;
	}

	public List<T> getCustUnprocessList(String cmsId) {
		int cmsIntVal = Integer.parseInt(cmsId);
		return this.entityManager
				.createQuery("from NlyteCustomerDataTransStg c where nmsId is null and 0 = (select count(*)  from "
						+ "NltyeCustomerDataProcess where status=0 and c.nctId = nlyteCustomerDataTransStg) and nlyteCustomerDataStg="
						+ cmsIntVal)
				.getResultList();
	}

	public List<NltyeCustDataProcessBean> getCustUnprocessListContains(String cmsId) {
		int cmsIntVal = Integer.parseInt(cmsId);
		List<Object[]> object = this.entityManager.createQuery(
				"from NlyteCustomerDataTransStg c,NlyteMasterDataTransStg m, NlyteCustomerDataStg stg where (m.materialNmTx like concat(c.manufacturerTx,' ',c.modelTx,'%') or m.modelTx like concat(c.manufacturerTx,' ',c.modelTx,'%') or m.materialNmTx like concat('%',c.modelTx) or m.modelTx like concat(c.modelTx,'%')) and c.nmsId is null and stg.cmsId=c.nlyteCustomerDataStg and (stg.status is null or stg.status='N') and c.nlyteCustomerDataStg="
						+ cmsIntVal)
				.getResultList();

		List<NltyeCustDataProcessBean> nltyeCustDataProcessBean = new ArrayList<NltyeCustDataProcessBean>();
		for (Object[] obj : object) {
			NltyeCustDataProcessBean bean = new NltyeCustDataProcessBean();
			NlyteCustomerDataTransStg nlyteCustomerDataTransStg = (NlyteCustomerDataTransStg) obj[0];
			NlyteMasterDataTransStg nlyteMasterDataTransStg = (NlyteMasterDataTransStg) obj[1];

			bean.setnCustDataTransStg(nlyteCustomerDataTransStg);
			bean.setnMasterDataTrans(nlyteMasterDataTransStg);
			nltyeCustDataProcessBean.add(bean);
		}
		return nltyeCustDataProcessBean;
	}

	public List<T> getCustNeedsToProcess(String cmsId) {
		int cmsIntVal = Integer.parseInt(cmsId);
		return this.entityManager.createQuery("from NlyteCustomerDataTransStg where nlyteCustomerDataStg=" + cmsIntVal)
				.getResultList();
	}

	public List<NltyeCustDataProcessBean> getCustNeedsToProcessEqui(String cmsId) {
		int cmsIntVal = Integer.parseInt(cmsId);
		/*
		 * return this.entityManager
		 * .createQuery("from NlyteCustomerDataTransStg where nlyteCustomerDataStg=" +
		 * cmsIntVal) .getResultList();
		 */

		List<Object[]> object = this.entityManager.createQuery(
				"from NlyteCustomerDataTransStg c,NlyteMasterDataTransStg m where (concat(c.manufacturerTx,' ',c.modelTx)=m.materialNmTx or concat(c.manufacturerTx,' ',c.modelTx)=m.modelTx or c.modelTx=m.materialNmTx or c.modelTx=m.modelTx) and c.nmsId is null and c.nlyteCustomerDataStg="
						+ cmsIntVal)
				.getResultList();

		List<NltyeCustDataProcessBean> nltyeCustDataProcessBean = new ArrayList<NltyeCustDataProcessBean>();
		for (Object[] obj : object) {
			NltyeCustDataProcessBean bean = new NltyeCustDataProcessBean();
			NlyteCustomerDataTransStg nlyteCustomerDataTransStg = (NlyteCustomerDataTransStg) obj[0];
			NlyteMasterDataTransStg nlyteMasterDataTransStg = (NlyteMasterDataTransStg) obj[1];

			bean.setnCustDataTransStg(nlyteCustomerDataTransStg);
			bean.setnMasterDataTrans(nlyteMasterDataTransStg);
			nltyeCustDataProcessBean.add(bean);
		}
		return nltyeCustDataProcessBean;
	}

	public List<NltyeCustDataProcessBean> findMultipleMatchedData(String cmsId) {

		List<Object[]> object = this.entityManager
				.createQuery("from NlyteCustomerDataTransStg a, NlyteCustomerDataStg b,NltyeCustomerDataProcess c,"
						+ " NlyteMasterDataTransStg d where c.nlyteMasterDataTransStg=d.nmtId and c.nlyteCustomerDataTransStg=a.nctId and c.status=0 and b.cmsId=a.nlyteCustomerDataStg and b.cmsId="
						+ cmsId)
				.getResultList();

		List<NltyeCustDataProcessBean> nltyeCustDataProcessBean = new ArrayList<NltyeCustDataProcessBean>();

		for (Object[] obj : object) {
			NltyeCustDataProcessBean bean = new NltyeCustDataProcessBean();
			NlyteCustomerDataTransStg nlyteCustomerDataTransStg = (NlyteCustomerDataTransStg) obj[0];
			NlyteCustomerDataStg nlyteCustomerDataStg = (NlyteCustomerDataStg) obj[1];
			NltyeCustomerDataProcess nltyeCustomerDataProcess = (NltyeCustomerDataProcess) obj[2];
			NlyteMasterDataTransStg nlyteMasterDataTransStg = (NlyteMasterDataTransStg) obj[3];

			bean.setnCustDataTransStg(nlyteCustomerDataTransStg);
			bean.setnCustDataStg(nlyteCustomerDataStg);
			bean.setnCustDataProcess(nltyeCustomerDataProcess);
			bean.setnMasterDataTrans(nlyteMasterDataTransStg);
			nltyeCustDataProcessBean.add(bean);
		}
		return nltyeCustDataProcessBean;
	}

	public int getMatchedDataMaster(String materialName, String model, String manufacture) {
		List<NlyteMasterDataTransStg> count = this.entityManager
				.createQuery("FROM NlyteMasterDataTransStg where materialNmTx='" + materialName + "'and modelTx='"
						+ model + "'and manufacturerTx='" + manufacture + "'")
				.getResultList();
		int retVal = 0;
		if (count.size() > 0) {
			retVal = count.get(0).getNmtId();
		}
		return retVal;
	}

	public Long getMaterialCont() {
		return (long) this.entityManager.createQuery("from NlyteMasterDataTransStg").getResultList().size();
	}

}
