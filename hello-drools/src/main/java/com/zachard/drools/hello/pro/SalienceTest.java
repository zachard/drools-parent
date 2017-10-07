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

package com.zachard.drools.hello.pro;

import org.drools.builder.ResourceType;
import org.drools.runtime.StatefulKnowledgeSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zachard.drools.hello.util.DroolsFactory;

/**
 * 规则salience属性测试执行类
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class SalienceTest {
	
	private static final Logger logger = LoggerFactory.getLogger(SalienceTest.class);
	
	/**
	 * 规则文件路径
	 */
	private static final String FILE_PATH = "rules/proSalience.drl";
	
	/**
	 * 未设置salience属性规则随机执行测试
	 * <pre>
	 *     执行多次测试,觉得规则是倒序执行
	 * </pre>
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		StatefulKnowledgeSession statefulKnowledgeSession = DroolsFactory
				.getStatefulKnowledgeSession(FILE_PATH, SalienceTest.class, ResourceType.DRL);
		statefulKnowledgeSession.fireAllRules();
		statefulKnowledgeSession.dispose();
		logger.info("End...");
	}

}
