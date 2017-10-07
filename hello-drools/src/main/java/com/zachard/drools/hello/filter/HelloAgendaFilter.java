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

package com.zachard.drools.hello.filter;

import org.apache.commons.lang3.StringUtils;
import org.drools.runtime.rule.Activation;
import org.drools.runtime.rule.AgendaFilter;

/**
 * 根据规则名称过滤需要执行的规则
 * <pre>
 *     {@link AgendaFilter}提供了{@link #accept(Activation)}方法决定规则是否执行
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class HelloAgendaFilter implements AgendaFilter {
	
	/**
	 * 规则前缀
	 */
	private String prefix;
	
	/**
	 * 过滤器构造器
	 * 
	 * @param prefix   指定前缀
	 */
	public HelloAgendaFilter(String prefix) {
		this.prefix = prefix;
	}

	/**
	 * 决定activation是否需要执行
	 * <pre>
	 *     当规则以{@link HelloAgendaFilter#prefix}开头时,则规则需要执行
	 * </pre>
	 * 
	 * @param activation    请求被触发的activation对象
	 * @return     <code>true</code>, 规则需要执行, 否则,返回<code>false</code>
	 */
	@Override
	public boolean accept(Activation activation) {
		String ruleName = activation.getRule().getName();
		
		if (StringUtils.isNotEmpty(ruleName) && ruleName.startsWith(prefix)) {
			return true;
		}
		
		return false;
	}

}
