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

package com.zachard.drools.hello.rules;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * Drools入门测试类
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class DroolsHello {
	
	/**
	 * Drools Hello 测试
	 * 
	 * @param args  入口函数参数
	 */
	public static void main(String[] args) {
		try {
			KieServices kieServices = KieServices.Factory.get();
			KieContainer kieContainer = kieServices.getKieClasspathContainer();
			KieSession kieSession = kieContainer.newKieSession("ksession-rules");
			
			Message message = new Message();
			message.setMessage("Hello World");
			message.setStatus(Message.HELLO);
			kieSession.insert(message);
			kieSession.fireAllRules();
			kieSession.dispose();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static class Message {
		
		/**
		 * <code>HELLO</code> 状态码
		 */
		public static final int HELLO = 0;
		
		/**
		 * <code>GOODBYE</code> 状态码
		 */
		public static final int GOODBYE = 1;
		
		/**
		 * 提示信息
		 */
		private String message;
		
		/**
		 * 状态码
		 */
		private int status;
		
		/**
		 * 返回提示信息
		 * 
		 * @return 信息
		 */
		public String getMessage() {
			return message;
		}
		
		/**
		 * 设置提示信息
		 * 
		 * @param 被设置的信息
		 */
		public void setMessage(String message) {
			this.message = message;
		}
		
		/** 
		 * 获取状态
		 * 
		 * @return 状态
		 */
		public int getStatus() {
			return status;
		}
		
		/**
		 * 设置状态
		 * 
		 * @param 被设置的状态
		 */
		public void setStatus(int status) {
			this.status = status;
		}
		
	}

}
