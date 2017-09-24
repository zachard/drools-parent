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

/**
 * {@link KnowledgeBase} 测试类, 用于收集应用当值知识(knowledge)定义的知识库对象
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class KnowledgeBaseTest {
	
	public static void main(String[] args) {
		// 方式一: 直接通过 KnowledgeBaseFactory 创建 KnowledgeBase 对象
		//KnowledgeBase kBase = KnowledgeBaseFactory.newKnowledgeBase();
		
		// 方式二: 通过 KnowledgeBaseConfiguration 对象创建 KnowledgeBase 对象
		KnowledgeBaseConfiguration kBaseConfiguration = KnowledgeBaseFactory.newKnowledgeBaseConfiguration();
		kBaseConfiguration.setProperty("org.drools.sequential", "true");
		KnowledgeBase kBase = KnowledgeBaseFactory.newKnowledgeBase(kBaseConfiguration);
		
		// 方式三: 将需要配置的属性保存到 Properties 对象之中
//		Properties properties = new Properties();
//		properties.setProperty("org.drools.sequential", "true");
//		KnowledgeBaseConfiguration kBaseConfiguration = KnowledgeBaseFactory
//				.newKnowledgeBaseConfiguration(properties, null);
//		KnowledgeBase kBase = KnowledgeBaseFactory.newKnowledgeBase(kBaseConfiguration);
		
		KnowledgeBuilder kBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		kBuilder.add(ResourceFactory.newClassPathResource("smaple.drl", KnowledgeBaseTest.class), ResourceType.DRL);
		Collection<KnowledgePackage> kPackages = kBuilder.getKnowledgePackages();
		
		//将生成的 KnowledgePackage 添加到 KnowledgeBase 之中
		kBase.addKnowledgePackages(kPackages);
	}

}
