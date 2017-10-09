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

package com.zachard.drools.hello.summary.model;

import java.io.Serializable;

/**
 * 用户实体类
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9024362841823156670L;

	/**
	 * 用户ID
	 */
	private Integer userId;
	
	/**
	 * 用户姓名
	 */
	private String userName;
	
	/**
	 * 用户等级
	 */
	private String userLevel;
	
	/**
	 * 用户积分
	 */
	private Float userPoint;
	
	/**
	 * 购买次数
	 */
	private Integer flag;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}

	public float getUserPoint() {
		return userPoint;
	}

	public void setUserPoint(float userPoint) {
		this.userPoint = userPoint;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}
	
}
