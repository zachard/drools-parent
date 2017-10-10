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

package com.zachard.drools.common.exception;

/**
 * Drools规则异常
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class DroolsException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8113603853327714514L;

	/**
	 * 错误码
	 */
	private String code;
	
	/**
	 * 错误信息
	 */
	private String message;
	
	/**
	 * 无参构造函数
	 */
	public DroolsException() {
		
	}
	
	/**
	 * 根据错误信息构造异常对象
	 * 
	 * @param code    错误码
	 */
	public DroolsException(String message) {
		this.message = message;
	}
	
	/**
	 * 带参数构造函数
	 * 
	 * @param code        错误码
	 * @param message     错误信息
	 */
	public DroolsException(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
