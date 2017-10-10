/*
 *  Copyright 2015-2017 zachard, Inc.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.zachard.drools.hello.summary.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zachard.drools.hello.summary.util.DbHelper;

/**
 * 用于操纵数据库用户表的DAO类
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class UserDao {
	
	/**
	 * 数据库操作工具类, 对JDBC进行了简单的封装
	 */
	private static DbHelper dbHelper = new DbHelper();
	
	/**
	 * 根据用户id查询用户信息SQL
	 */
	private static final String QUERY_USER_BY_ID = " SELECT u_id, u_name, u_level, u_point, t_flag FROM t_user WHERE u_id = ? ";
	
	/**
	 * 更新用户积分SQL
	 */
	private static final String UPDATE_USER_POINT_BY_ID = " UPDATE t_user SET u_point = ? WHERE u_id = ? ";
	
	/**
	 * 更新用户等级SQL
	 */
	private static final String UPDATE_USER_LEVEL_BY_ID = " UPDATE t_user SET u_level = ? WHERE u_id = ? ";
	
	/**
	 * 更新用户购买次数SQL
	 */
	private static final String UPDATE_USER_FLAG_BY_ID = " UPDATE t_user SET t_flag = t_flag + 1 WHERE u_id = ? ";
	
	/**
	 * 根据用户id查询用户信息
	 * 
	 * @param userId    用户ID
	 * @return          用户信息
	 */
	public Map<String, Object> getUserById(Integer userId) {
		List<Object> params = new ArrayList<>();
		params.add(userId);
		
		return dbHelper.queryObject(QUERY_USER_BY_ID, params);
	}
	
	/**
	 * 根据用户ID更新用户积分
	 * 
	 * @param userId    用户ID
	 * @param point     用户积分
	 * @return          是否更新成功
	 */
	public boolean updatePointById(Integer userId, Float point) {
		List<Object> params = new ArrayList<>();
		params.add(point);
		params.add(userId);
		
		return dbHelper.updateObject(UPDATE_USER_POINT_BY_ID, params) > 0;
	}
	
	/**
	 * 根据用户ID更新用户等级信息
	 * 
	 * @param userId    用户ID
	 * @param level     用户等级
	 * @return          是否更新成功
	 */
	public boolean updateLevelById(Integer userId, String level) {
		List<Object> params = new ArrayList<>();
		params.add(level);
		params.add(userId);
		
		return dbHelper.updateObject(UPDATE_USER_LEVEL_BY_ID, params) > 0;
	}
	
	/**
	 * 根据用户ID更新用户购买次数
	 * 
	 * @param userId    用户ID
	 * @return          是否更新成功
	 */
	public boolean updateFlagById(Integer userId) {
		List<Object> params = new ArrayList<>();
		params.add(userId);
		
		return dbHelper.updateObject(UPDATE_USER_FLAG_BY_ID, params) > 0;
	}
}
