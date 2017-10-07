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
import org.drools.runtime.rule.AgendaFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zachard.drools.hello.filter.HelloAgendaFilter;
import com.zachard.drools.hello.util.DroolsFactory;

/**
 * 规则属性auto-focus测试类
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class AutoFocusTest {
	
	private static final Logger logger = LoggerFactory.getLogger(AutoFocusTest.class);
	
	/**
	 * 规则文件路径
	 */
	private static final String FILE_PATH = "rules/proAutoFocus.drl";
	
	/**
	 * 需要执行规则的名称前缀
	 */
	private static final String RULE_PREFIX = "rule";
	
	/**
	 * 规则属性auto-focus测试方法
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		StatefulKnowledgeSession statefulKnowledgeSession = DroolsFactory
				.getStatefulKnowledgeSession(FILE_PATH, AutoFocusTest.class, ResourceType.DRL);
		
		/*
		 *  情况1: 添加AgendaFilter,即使agenda-group未设置auto-focus属性,只要满足filter
		 *  条件就会执行
		 */
		AgendaFilter filter = new HelloAgendaFilter(RULE_PREFIX);
		statefulKnowledgeSession.fireAllRules(filter);
		
		/*
		 * 情况2: 不添加AgendaFilter,只有agenda-group设置了auto-focus属性为true时,规则才会被触发执行
		 * agenda-group属性相同的规则,只要有一个规则设置了auto-focus属性为true,其他未设置的规则同样执行
		 */
		//statefulKnowledgeSession.fireAllRules();
		statefulKnowledgeSession.dispose();
		logger.info("End...");
	}

}
