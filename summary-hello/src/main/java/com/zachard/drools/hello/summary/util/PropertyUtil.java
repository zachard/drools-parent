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

package com.zachard.drools.hello.summary.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 读取properties属性文件的工具类
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class PropertyUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(PropertyUtil.class);
	
	/**
	 * 获取properties文件中的属性值
	 * 
	 * @param fileName    properties文件属性名
	 * @return            Properties对象
	 */
	public static Properties getPropertiesByFile(String fileName) {
		Properties properties = new Properties();
		InputStream input = PropertyUtil.class.getClassLoader().getResourceAsStream(fileName);
		
		try {
			properties.load(input);
		} catch (IOException e) {
			logger.error("加载属性文件失败", e);
		}
		
		return properties;
	}

}
