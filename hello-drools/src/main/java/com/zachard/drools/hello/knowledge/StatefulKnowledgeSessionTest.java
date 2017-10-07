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

package com.zachard.drools.hello.knowledge;

import java.util.Collection;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseConfiguration;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.definition.KnowledgePackage;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;

/**
 * {@link StatefulKnowledgeSession}是最常用于与引擎交互的方式,它允许程序与引擎建立
 * 可重复利用的会话，并在会话之间的调用保持状态
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class StatefulKnowledgeSessionTest {
	
	/**
	 * {@link StatefulKnowledgeSession}处理规则完整流程测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// 1. 创建KnowledgeBuilder对象并获取KnowledgePackage对象
		KnowledgeBuilder kBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		kBuilder.add(ResourceFactory.newClassPathResource("smaple.drl", KnowledgeBaseTest.class), ResourceType.DRL);
		Collection<KnowledgePackage> kPackages = kBuilder.getKnowledgePackages();
		
		// 2. 创建KnowledgeBase对象并为其添加KnowledgePackage对象
		KnowledgeBaseConfiguration kBaseConfiguration = KnowledgeBaseFactory.newKnowledgeBaseConfiguration();
		kBaseConfiguration.setProperty("org.drools.sequential", "true");
		KnowledgeBase kBase = KnowledgeBaseFactory.newKnowledgeBase(kBaseConfiguration);
		kBase.addKnowledgePackages(kPackages);
		
		// 3. 创建StatefulKnowledgeSession对象,并为其传入属性,触发规则
		StatefulKnowledgeSession statefulKnowledgeSession = kBase.newStatefulKnowledgeSession();
		// setGlobal()方法设置规则中传出数据的接收对象
		statefulKnowledgeSession.setGlobal("globalTest", new Object());
		// insert()方法为规则传入数据
		statefulKnowledgeSession.insert(new Object());
		statefulKnowledgeSession.fireAllRules();
		statefulKnowledgeSession.dispose();
	}

}
