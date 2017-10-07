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

package com.zachard.drools.hello.rule;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.zachard.drools.hello.rules.DroolsHello.Message;

/**
 * The description...
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class DroolsHelloTest {

	@Test
	public void testHello() {
		try {
			KieServices kieServices = KieServices.Factory.get();
			KieContainer kieContainer = kieServices.getKieClasspathContainer();
			KieSession kieSession = kieContainer.newKieSession("ksession-rules");
			
			Message message = new Message();
			message.setMessage("Hello World");
			message.setStatus(Message.HELLO);
			kieSession.insert(message);
			kieSession.fireAllRules();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
