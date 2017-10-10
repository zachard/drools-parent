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

package com.zachard.drools.common.util;

import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zachard.drools.common.exception.DroolsException;

/**
 * 对Drools规则进行校验的工具类
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class DroolsValidateUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(DroolsValidateUtil.class);
	
	/**
	 * 校验规则文件
	 * 
	 * @param filePath   规则文件路径
	 * @param clazz      规则文件相对类路径
	 * @param type       规则文件类型
	 */
	@SuppressWarnings("rawtypes")
	public static void validateDroolsFile(String filePath, Class clazz, ResourceType type) {
		logger.info("校验的规则文件路径为: " + filePath);
		
		// 1. 创建KnowledgeBuilder对象并获取KnowledgePackage对象
		KnowledgeBuilder kBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		kBuilder.add(ResourceFactory.newClassPathResource(filePath, clazz), type);
		
		// 先判断DRL规则文件是否有错误
		if (kBuilder.hasErrors()) {
			StringBuilder msgBuilder = new StringBuilder();
			KnowledgeBuilderErrors kBuilderErrors = kBuilder.getErrors();
			kBuilderErrors.forEach(kBuilderError -> {
				logger.info("规则文件中存在错误,错误信息如下: ");
				logger.error(kBuilderError.getMessage());
				msgBuilder.append(kBuilderError.getMessage()).append("\n");
			});
			
			throw new DroolsException(msgBuilder.toString());
		}
	}
}
