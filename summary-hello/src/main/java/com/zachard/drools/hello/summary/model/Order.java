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
 * description...
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class Order implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7023263092221934368L;

	/**
	 * 订单编号
	 */
	private Integer orderId;
	
	/**
	 * 用户ID
	 */
	private Integer userId;
	
	/**
	 * 商品数量
	 */
	private Integer orderCount;
	
	/**
	 * 订单总额
	 */
	private Float orderAmount;

	/**
	 * 订单积分
	 */
	private Float orderPoint;

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(Integer orderCount) {
		this.orderCount = orderCount;
	}

	public Float getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(Float orderAmount) {
		this.orderAmount = orderAmount;
	}

	public Float getOrderPoint() {
		return orderPoint;
	}

	public void setOrderPoint(Float orderPoint) {
		this.orderPoint = orderPoint;
	}
	
}
