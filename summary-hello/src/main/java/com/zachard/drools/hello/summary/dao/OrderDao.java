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

import com.zachard.drools.hello.summary.util.DbHelper;

/**
 * 用于操纵数据库订单表的DAO类
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class OrderDao {
	
	/**
	 * 数据库操纵工具类
	 */
	private DbHelper dbHelper = new DbHelper();
	
	/**
	 * 插入订单SQL
	 */
	private static final String INSERT_ORDER = " INSERT INTO t_order(u_id, o_count, o_amount, o_point) VALUES (?, ?, ?, ?) ";
	
	/**
	 * 向数据库中新增订单
	 * 
	 * @param userId        订单所属用户ID
	 * @param orderCount    商品数量
	 * @param orderAmount   商品总额
	 * @param orderPoint    订单积分
	 * @return              是否插入成功
	 */
	public boolean insertOrder(Integer userId, Integer orderCount, Float orderAmount, Float orderPoint) {
		List<Object> params = new ArrayList<>();
		params.add(userId);
		params.add(orderCount);
		params.add(orderAmount);
		params.add(orderPoint);
		
		return dbHelper.updateObject(INSERT_ORDER, params) > 0;
	}

}
