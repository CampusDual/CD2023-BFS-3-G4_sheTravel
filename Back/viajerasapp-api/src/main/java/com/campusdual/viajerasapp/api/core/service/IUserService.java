package com.campusdual.viajerasapp.api.core.service;


import java.util.List;
import java.util.Map;

import com.ontimize.jee.common.dto.EntityResult;


public interface IUserService {

	public EntityResult userQuery(Map<String, Object> keyMap, List<String> attrList);
	public EntityResult userInsert(Map<String, Object> attrMap);
	public EntityResult userUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap);
	public EntityResult userDelete(Map<String, Object> keyMap);

	public EntityResult myUserQuery(Map<String, Object> keyMap, List<String> attrList);

	public EntityResult myUserUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap);

	public EntityResult hostQuery(Map<String, Object> keyMap, List<String> attrList) ;

	public EntityResult clientQuery(Map<String, Object> keyMap, List<String> attrList);
	public EntityResult clientInsert(Map<String, Object> attrMap);
	public EntityResult clientUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap);
	public EntityResult clientDelete(Map<String, Object> keyMap);

	public EntityResult activity_clientQuery(Map<String, Object> keyMap, List<String> attrList);
	public EntityResult activity_clientInsert(Map<String, Object> attrMap);

	public EntityResult activity_clientDelete(Map<String, Object> keyMap);

	public EntityResult activity_clientMultipleDelDelete(Map<String, Object> keyMap);





}
