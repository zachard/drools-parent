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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseConfiguration;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.command.Command;
import org.drools.command.CommandFactory;
import org.drools.definition.KnowledgePackage;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.StatelessKnowledgeSession;

/**
 * {@link StatelessKnowledgeSession}类与{@link StatefulKnowledgeSession}类似,
 * 用于接收数据、执行规则,它对{@link StatefulKnowledgeSession}做了包装,使得
 * {@link StatelessKnowledgeSession}对象不再需要调用{@link StatefulKnowledgeSession#dispose()}方法释放资源
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class StatelessKnowledgeSessionTest {
	
	/**
	 * {@link StatelessKnowledgeSession}完整流程测试
	 * 
	 * @param agrs
	 */
	@SuppressWarnings("rawtypes")
	public static void main(String[] agrs) {
        // 1. 创建KnowledgeBuilder对象并获取KnowledgePackage对象
	    KnowledgeBuilder kBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		kBuilder.add(ResourceFactory.newClassPathResource("smaple.drl", KnowledgeBaseTest.class), ResourceType.DRL);
		Collection<KnowledgePackage> kPackages = kBuilder.getKnowledgePackages();
		
		// 2. 创建KnowledgeBase对象并为其添加KnowledgePackage对象
		KnowledgeBaseConfiguration kBaseConfiguration = KnowledgeBaseFactory.newKnowledgeBaseConfiguration();
		kBaseConfiguration.setProperty("org.drools.sequential", "true");
		KnowledgeBase kBase = KnowledgeBaseFactory.newKnowledgeBase(kBaseConfiguration);
		kBase.addKnowledgePackages(kPackages);
		
		// 3. 创建StatelessKnowledgeSession对象,并传入fact对象,触发相应规则
		StatelessKnowledgeSession statelessKnowledgeSession = kBase.newStatelessKnowledgeSession();
//		List<Object> factList = new ArrayList<>();
//		factList.add(new Object());
//		factList.add(new Object());
//		statelessKnowledgeSession.execute(factList);
		
		/* 
		 * 假设需要插入的是一个ArrayList对象,而不是将ArrayList中的元素遍历插入
		 * 处理ArrayList既存在fact对象,又存在global对象的情况
		 */
		List<Command> list = new ArrayList<Command>();
		list.add(CommandFactory.newInsert(new Object()));
		list.add(CommandFactory.newInsert(new Object()));
		list.add(CommandFactory.newSetGlobal("key1", new Object()));
		list.add(CommandFactory.newSetGlobal("key2", new Object()));
		// 注: 查看execute方法实现,可以发现这个方法不被支持
		statelessKnowledgeSession.execute(CommandFactory.newBatchExecution(list));
	}

}
