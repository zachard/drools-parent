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

package com.zachard.drools.hello.util;

import java.util.Collection;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.definition.KnowledgePackage;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Drools工厂类
 * <pre>
 *     主要用于创建Drools相关的对象
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class DroolsFactory {
	
	private static final Logger logger = LoggerFactory.getLogger(DroolsFactory.class);
	
	/**
	 * 编译规则文件并创建{@link StatefulKnowledgeSession}对象
	 * 
	 * @param filePath   规则文件路径
	 * @param clazz      规则文件相对类路径
	 * @param type       规则文件类型
	 * @return           Session对象
	 */
	@SuppressWarnings("rawtypes")
	public static StatefulKnowledgeSession getStatefulKnowledgeSession(String filePath, Class clazz, ResourceType type) {
		logger.info("--规则文件路径为: " + filePath);
		
		// 1. 创建KnowledgeBuilder对象并获取KnowledgePackage对象
		KnowledgeBuilder kBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		kBuilder.add(ResourceFactory.newClassPathResource(filePath, clazz), type);
		
		// 先判断DRL规则文件是否有错误
		if (kBuilder.hasErrors()) {
			KnowledgeBuilderErrors kBuilderErrors = kBuilder.getErrors();
			kBuilderErrors.forEach(kBuilderError -> {
				logger.info("规则文件中存在错误,错误信息如下: ");
				logger.error(kBuilderError.getMessage());
			});
			
			return null;
		}
		
		Collection<KnowledgePackage> kPackages = kBuilder.getKnowledgePackages();
		
		// 2. 创建KnowledgeBase对象并为其添加KnowledgePackage对象
		KnowledgeBase kBase = KnowledgeBaseFactory.newKnowledgeBase();
		kBase.addKnowledgePackages(kPackages);
		
		// 3. 创建StatefulKnowledgeSession对象,并为其传入属性,触发规则
		StatefulKnowledgeSession statefulKnowledgeSession = kBase.newStatefulKnowledgeSession();
		statefulKnowledgeSession.fireAllRules();
		statefulKnowledgeSession.dispose();
		
		return statefulKnowledgeSession;
	}

}
