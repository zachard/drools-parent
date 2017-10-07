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

package com.zachard.drools.hello.query;

import java.util.ArrayList;
import java.util.List;

import org.drools.builder.ResourceType;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.rule.QueryResults;
import org.drools.runtime.rule.QueryResultsRow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zachard.drools.hello.model.Customer;
import com.zachard.drools.hello.model.Order;
import com.zachard.drools.hello.util.DroolsFactory;

/**
 * 规则执行查询测试类
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class QueryTest {
	
	private static final Logger logger = LoggerFactory.getLogger(QueryTest.class);
	
	/**
	 * 规则文件路径
	 */
	private static final String FILE_PATH = "rules/query.drl";
	
	/**
	 * 无参数查询名称
	 */
	private static final String NO_ARGS_QUERY = "queryCustomerByAgeAndOrder";
	
	/**
	 * 带参数查询名称
	 */
	private static final String QUERY_WITH_ARGS = "queryCustomerByAge";
	
	/**
	 * 规则文件查询测试方法
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		StatefulKnowledgeSession statefulKnowledgeSession = DroolsFactory
				.getStatefulKnowledgeSession(FILE_PATH, QueryTest.class, ResourceType.DRL);
		
		// 向Drools的Working Memory中插入Fact对象
		statefulKnowledgeSession.insert(getCustomer("张三", 26, 6));
		statefulKnowledgeSession.insert(getCustomer("李四", 31, 8));
		statefulKnowledgeSession.insert(getCustomer("王五", 32, 4));
		statefulKnowledgeSession.insert(getCustomer("赵六", 35, 10));
		
		// 情况1: 执行无条件查询
		//QueryResults queryResults = statefulKnowledgeSession.getQueryResults(NO_ARGS_QUERY);
		//logger.info("年龄大于30且订单超过5个的姓名为: ");
		
		/* 
		 * 情况2: 执行带条件查询
		 * 查询参数为不定参数
		 */
		QueryResults queryResults = statefulKnowledgeSession.getQueryResults(QUERY_WITH_ARGS, 30);
		logger.info("年龄大于30的姓名为: ");
		
		// 遍历结果
		for (QueryResultsRow queryResult : queryResults) {
			Customer customer = (Customer) queryResult.get("customer");
			logger.info(customer.getName());
		}
		
		statefulKnowledgeSession.dispose();
	}
	
	/**
	 * 获取顾客对象
	 * 
	 * @param name    顾客姓名
	 * @param age     顾客年龄
	 * @param size    顾客订单数量
	 * @return        生成后的顾客
	 */
	private static Customer getCustomer(String name, int age, int size) {
		Customer customer = new Customer();
		customer.setName(name);
		customer.setAge(age);
		List<Order> orders = new ArrayList<>();
		
		for (int i = 0; i < size; i++) {
			orders.add(new Order());
		}
		
		customer.setOrders(orders);
		
		return customer;
	}

}
