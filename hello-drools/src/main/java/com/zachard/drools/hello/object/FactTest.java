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

package com.zachard.drools.hello.object;

import java.util.Date;

import org.drools.KnowledgeBase;
import org.drools.builder.ResourceType;
import org.drools.definition.type.FactType;
import org.drools.runtime.StatefulKnowledgeSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zachard.drools.hello.util.DroolsFactory;

/**
 * 通过declare在规则文件中定义一个对象并使用
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class FactTest {
	
	private static final Logger logger = LoggerFactory.getLogger(FactTest.class);
	
	/**
	 * 规则文件路径
	 */
	private static final String FILE_PATH = "rules/fact.drl";
	
	/**
	 * 测试在规则文件中declare一个类型并插入对象到Working Memory中方法
	 * 
	 * @param args
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		StatefulKnowledgeSession statefulKnowledgeSession = DroolsFactory
				.getStatefulKnowledgeSession(FILE_PATH, FactTest.class, ResourceType.DRL);
		
		/*
		 * FactType来获取drl文件中声明的类型,并将它们insert到Working Memory之中
		 */
		KnowledgeBase kBase = statefulKnowledgeSession.getKnowledgeBase();
		FactType addressType = kBase.getFactType("com.fact", "Address");
		Object address = addressType.newInstance();
		addressType.set(address, "city", "上海");
		addressType.set(address, "addressName", "浦东新区");
		FactType personType = kBase.getFactType("com.fact", "Person");
		Object person = personType.newInstance();
		personType.set(person, "name", "zachard");
		personType.set(person, "birthday", new Date());
		personType.set(person, "address", address);
		statefulKnowledgeSession.insert(person);
		
		statefulKnowledgeSession.fireAllRules();
		statefulKnowledgeSession.dispose();
		logger.info("End...");
	}

}
