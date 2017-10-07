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

package com.zachard.drools.hello.function;

import org.drools.builder.ResourceType;
import org.drools.runtime.StatefulKnowledgeSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zachard.drools.hello.model.Customer;
import com.zachard.drools.hello.util.DroolsFactory;

/**
 * 规则函数测试类
 * <pre>
 *     函数的可见范围是当前函数所在的文件
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class FunctionTest {
	
	private static final Logger logger = LoggerFactory.getLogger(FunctionTest.class);
	
	/**
	 * 规则文件路径
	 */
	private static final String FILE_PATH = "rules/function1.drl";
	
	/**
	 * 规则函数测试方法
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		StatefulKnowledgeSession statefulKnowledgeSession = DroolsFactory
				.getStatefulKnowledgeSession(FILE_PATH, FunctionTest.class, ResourceType.DRL);
		statefulKnowledgeSession.insert(new Customer());
		statefulKnowledgeSession.fireAllRules();
		statefulKnowledgeSession.dispose();
		logger.info("End...");
	}

}
