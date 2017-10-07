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
 * 规则date-effective属性测试
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class DateEffectiveTest {
	
	private static final Logger logger = LoggerFactory.getLogger(DateEffectiveTest.class);
	
	/**
	 * 规则文件路径
	 */
	private static final String FILE_PATH = "rules/proDateEffective.drl";
	
	/**
	 * drools日期格式键值
	 */
	private static final String DROOLS_DATE_FORMAT_KEY = "drools.dateformat";
	
	/**
	 * drools日期格式value值
	 */
	private static final String DROOLS_DATE_FORMAT_VALUE = "yyyy-MM-dd";
	
	/**
	 * date-effective属性测试
	 * <pre>
	 *     只有当前系统时间大于date-effective指定的时间时，
	 *     规则才会执行
	 * </pre>
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		/* 
		 * 设置Drools date-effective属性的值格式
		 * 注: 修改格式必须在添加规则文件之前,否则不会生效
		 */
		System.setProperty(DROOLS_DATE_FORMAT_KEY, DROOLS_DATE_FORMAT_VALUE);
		StatefulKnowledgeSession statefulKnowledgeSession = DroolsFactory
				.getStatefulKnowledgeSession(FILE_PATH, DateEffectiveTest.class, ResourceType.DRL);
		statefulKnowledgeSession.fireAllRules();
		statefulKnowledgeSession.dispose();
		logger.info("End...");
	}

}
