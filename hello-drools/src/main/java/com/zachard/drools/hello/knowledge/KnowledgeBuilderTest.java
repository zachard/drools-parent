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

import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.definition.KnowledgePackage;
import org.drools.io.ResourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * {@link KnowledgeBuilder} 测试示例
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class KnowledgeBuilderTest {
	
	/**
	 * 日志记录器
	 */
	private static final Logger logger = LoggerFactory.getLogger(KnowledgeBuilderTest.class);
	
	/**
	 * {@link KnowledgeBuilder} 编译资源文件测试类
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		KnowledgeBuilder kBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		
		// ResourceFactory提供了一个便利的工厂实现了期望的IO操作
		kBuilder.add(ResourceFactory.newClassPathResource("rules/builder.drl", KnowledgeBuilderTest.class), 
				ResourceType.DRL);
		
		//遍历错误信息并打印出来
		if (kBuilder.hasErrors()) {
			logger.error("规则文件中存在错误, 错误信息如下: ");
			KnowledgeBuilderErrors kBuilderErrors = kBuilder.getErrors();
			kBuilderErrors.forEach(kBuilderError -> {
				logger.info(kBuilderError.toString());
			});
		}
		
		Collection<KnowledgePackage> kPackages = kBuilder.getKnowledgePackages();
		kPackages.forEach(kPackage -> {
			logger.info(kPackage.getName());
		});
	}

}
