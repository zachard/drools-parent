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
 * drools规则属性agenda-group测试类
 * <pre>
 *     agenda-group用于将规则进行分组,只有获得焦点的组的规则才会执行
 *     多个规则可以被添加至同一个组
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class AgendaGroupTest {
	
	private static final Logger logger = LoggerFactory.getLogger(AgendaGroupTest.class);
	
	/**
	 * 规则文件路径
	 */
	private static final String FILE_PATH = "rules/proAgendaGroup.drl";
	
	/**
	 * 获得焦点的agenda-group分组名称
	 */
	private static final String FOCUS_AGENDA_GROUP_NAME = "group.01";
	
	/**
	 * 规则属性agenda-group测试方法入口
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		StatefulKnowledgeSession statefulKnowledgeSession = DroolsFactory
				.getStatefulKnowledgeSession(FILE_PATH, AgendaGroupTest.class, ResourceType.DRL);
		
		// 指定获得焦点的agenda-group并触发它的规则执行
		statefulKnowledgeSession.getAgenda().getAgendaGroup(FOCUS_AGENDA_GROUP_NAME).setFocus();
		statefulKnowledgeSession.fireAllRules();
		statefulKnowledgeSession.dispose();
		logger.info("End...");
	}

}
