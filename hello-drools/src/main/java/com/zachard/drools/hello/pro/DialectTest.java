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
 * 规则属性dialect指定语言测试
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class DialectTest {
	
	private static final Logger logger = LoggerFactory.getLogger(DialectTest.class);
	
	/**
	 * 规则文件路径
	 */
	private static final String FILE_PATH = "rules/proDialect.drl";
	
	/**
	 * drools属性dialect测试方法
	 * <pre>
	 *     dialect属性值为: mvel或java,默认为java
	 * </pre>
	 * @param args
	 */
	public static void main(String[] args) {
		StatefulKnowledgeSession statefulKnowledgeSession = DroolsFactory
				.getStatefulKnowledgeSession(FILE_PATH, DialectTest.class, ResourceType.DRL);
		statefulKnowledgeSession.fireAllRules();
		statefulKnowledgeSession.dispose();
		logger.info("End...");
	}

}
