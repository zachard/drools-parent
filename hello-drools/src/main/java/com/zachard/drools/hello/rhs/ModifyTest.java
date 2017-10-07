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

package com.zachard.drools.hello.rhs;

import org.drools.builder.ResourceType;
import org.drools.runtime.StatefulKnowledgeSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zachard.drools.hello.model.Customer;
import com.zachard.drools.hello.util.DroolsFactory;

/**
 * 规则结果部分modify方法测试类
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class ModifyTest {
	
	private static final Logger logger = LoggerFactory.getLogger(ModifyTest.class);
	
	/**
	 * 规则文件路径
	 */
	private static final String FILE_PATH = "rules/rhsModify.drl";
	
	/**
	 * modify方法测试方法入口
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		StatefulKnowledgeSession statefulKnowledgeSession = DroolsFactory
				.getStatefulKnowledgeSession(FILE_PATH, ModifyTest.class, ResourceType.DRL);
		Customer customer = new Customer();
		customer.setId("zachard");
		customer.setName("zachard");
		customer.setAge(18);
		statefulKnowledgeSession.insert(customer);
		statefulKnowledgeSession.fireAllRules();
		statefulKnowledgeSession.dispose();
		logger.info("End...");
	}

}
