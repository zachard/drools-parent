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

package com.zachard.drools.hello.model;

/**
 * DRL规则文件需要的实体类
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class Customer {

	/**
	 * 顾客姓名
	 */
	private String name;
	
	/**
	 * 顾客年龄
	 */
	private int age;

	/**
	 * 获取顾客姓名
	 * 
	 * @return the name   顾客姓名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置顾客姓名
	 * 
	 * @param name 顾客姓名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取顾客年龄
	 * 
	 * @return 年龄
	 */
	public int getAge() {
		return age;
	}

	/**
	 * 设置顾客年龄
	 * 
	 * @param age 顾客年龄
	 */
	public void setAge(int age) {
		this.age = age;
	}

}
