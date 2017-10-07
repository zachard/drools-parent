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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 规则文件函数工具类
 * <pre>
 *     定义规则文件所需的相关函数
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class FunctionTool {
	
	private static final Logger logger = LoggerFactory.getLogger(FunctionTool.class);
	
	/**
	 * 将名字输出
	 * 
	 * @param name    需要输出的名字
	 */
	public static void printName(String name) {
		logger.info("名字为: " + name);
	}

}
