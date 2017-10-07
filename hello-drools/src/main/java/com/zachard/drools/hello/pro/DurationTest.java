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
 * 规则属性duration执行测试类
 * <pre>
 *     duration属性表示在指定时间之后,规则将在另一线程中执行
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class DurationTest {
	
	private static final Logger logger = LoggerFactory.getLogger(DurationTest.class);
	
	/**
	 * 规则文件路径
	 */
	private static final String FILE_PATH = "rules/proDuration.drl";
	
	/**
	 * 规则属性duration测试方法
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		StatefulKnowledgeSession statefulKnowledgeSession = DroolsFactory
				.getStatefulKnowledgeSession(FILE_PATH, DurationTest.class, ResourceType.DRL);
		logger.error("触发规则之前的时间戳: " + System.currentTimeMillis());
		statefulKnowledgeSession.fireAllRules();
		statefulKnowledgeSession.dispose();
		
		//主线程休眠5s，等待子线程结束
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			logger.error("线程异常中断", e);
		}
		
		//查看main方法中的线程id
		logger.error("main方法的线程ID为: " + Thread.currentThread().getId());
	}

}
