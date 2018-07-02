package com.gcs.nlyte.web.persistance.dao.api;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

public interface INlyteDaoSupport<T, ID extends Serializable> {
	public EntityManager getSession();
	public T findOne( Long id );
	public List< T > findAll();
	public void save( T entity );
	public void update( T entity );
	public void delete( T entity );
	public void deleteById( Long entityId );
	public void insertDto(Object obj,Integer userId);
	public void updateDto(Object obj,Integer userId);
	public void deleteDto(Object obj,Integer userId);
	public List< T > findAllCustProcessed();
	public boolean nlyteUpdateProcessStatus(String cmsId);

}
