package com.campusdual.viajerasapp.model.core.service;


import java.sql.Timestamp;
import java.util.*;

import com.campusdual.viajerasapp.model.core.dao.ClientActivityDao;
import com.campusdual.viajerasapp.model.core.dao.ClientActivityMultipleDelDao;
import com.campusdual.viajerasapp.model.core.dao.ClientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.campusdual.viajerasapp.api.core.service.IUserService;
import com.campusdual.viajerasapp.model.core.dao.UserDao;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;


@Lazy
@Service("UserService")
public class UserService implements IUserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private ClientDao clientDao;

	@Autowired
	private ClientActivityDao clientActivityDao;

	@Autowired
	private ClientActivityMultipleDelDao clientActivityMultipleDelDao;

	@Autowired
	private DefaultOntimizeDaoHelper daoHelper;

	public void loginQuery(Map<?, ?> key, List<?> attr) {
	}

	//Sample to permission
	//@Secured({ PermissionsProviderSecured.SECURED })
	public EntityResult userQuery(Map<String, Object> keyMap, List<String> attrList) {
		return this.daoHelper.query(userDao, keyMap, attrList);
	}

	public EntityResult userInsert(Map<String, Object> attrMap) {

		return this.daoHelper.insert(userDao, attrMap);
	}

	public EntityResult userUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap) {
		return this.daoHelper.update(userDao, attrMap, keyMap);
	}

	public EntityResult userDelete(Map<String, Object> keyMap) {
		return this.daoHelper.delete(this.userDao, keyMap);
	}

	@Override
	public EntityResult myUserQuery(Map<String, Object> keyMap, List<String> attrList) {
		keyMap.put(ClientDao.EMAILREGISTER, getUser());
		return this.daoHelper.query(clientDao, keyMap, attrList);
	}

	@Override
	public EntityResult myUserUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap) {

		EntityResult userUpdate = null;

		Object pass = attrMap.remove(UserDao.PASSWORD);

		keyMap.put(ClientDao.EMAILREGISTER, getUser());

		if (attrMap.size() > 0){ //datos que se deben gardar en client
			userUpdate = this.daoHelper.update(clientDao, attrMap, keyMap);
		}
		if (pass != null){ //datos que se deben gardar en tuser
			Map<String, Object> userAttr = new HashMap<>();
			userAttr.put(UserDao.PASSWORD, pass);
			userUpdate = this.daoHelper.update(userDao, userAttr, keyMap);
		}
		return userUpdate;
	}

	@Override
	public EntityResult hostQuery(Map<String, Object> keyMap, List<String> attrList) {
		
		return this.daoHelper.query(clientDao, keyMap, attrList, ClientDao.QUERY_HOSTCLIENT );
	}

	//------------CLIENT ENTITIES------------------------------
	@Override
	public EntityResult clientQuery(Map<String, Object> keyMap, List<String> attrList) {
		return this.daoHelper.query(clientDao, keyMap, attrList);
	}
	@Override
	public EntityResult clientInsert(Map<String, Object> attrMap) {
		return this.daoHelper.insert(clientDao, attrMap);
	}
	@Override
	public EntityResult clientUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap) {
		return this.daoHelper.update(clientDao, attrMap, keyMap);
	}
	@Override
	public EntityResult clientDelete(Map<String, Object> keyMap) {
		return this.daoHelper.delete(this.clientDao, keyMap);
	}



	//--------activities queries----------


	@Override
	public EntityResult activity_clientQuery(Map<String, Object> keyMap, List<String> attrList) {

		return this.daoHelper.query(clientDao, keyMap, attrList, ClientDao.QUERY_ACTIVITIESCLIENT);
	}

@Override
	public EntityResult activity_clientInsert(Map<String, Object> attrMap) {

		return this.daoHelper.insert(clientActivityDao, attrMap);
	}


	@Override
	public EntityResult activity_clientDelete(Map<String, Object> keyMap) {

		return this.daoHelper.delete(clientActivityDao, keyMap);
	}

	@Override
	public EntityResult activity_clientMultipleDelDelete(Map<String, Object> keyMap) {

		return this.daoHelper.delete(clientActivityMultipleDelDao, keyMap);
	}




	//---------------

	public String getUser(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth.getName();
	}

	public String getRole(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth.getAuthorities().toArray()[0].toString();
	}



}
