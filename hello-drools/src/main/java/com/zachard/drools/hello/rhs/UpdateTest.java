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
import org.drools.runtime.rule.QueryResults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zachard.drools.hello.util.DroolsFactory;

/**
 * 规则结果部分update方法执行测试类
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class UpdateTest {
	
	private static final Logger logger = LoggerFactory.getLogger(UpdateTest.class);
	
	/**
	 * 规则文件路径
	 */
	private static final String FILE_PATH = "rules/rhsUpdate.drl";
	
	/**
	 * 结果部分update方法测试
	 * 
	 * @param args   函数入口参数
	 */
	public static void main(String[] args) {
		StatefulKnowledgeSession statefulKnowledgeSession = DroolsFactory
				.getStatefulKnowledgeSession(FILE_PATH, UpdateTest.class, ResourceType.DRL);
		QueryResults results = statefulKnowledgeSession.getQueryResults("query fact count");
		logger.info("Working Memeroy中 Customer 对象个数为: " + results.size());
		logger.info("Ending...");
	}

}
