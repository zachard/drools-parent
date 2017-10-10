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

package com.zachard.drools.hello.summary;

import java.util.Map;
import java.util.Scanner;

import org.drools.builder.ResourceType;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.zachard.drools.common.util.DroolsValidateUtil;
import com.zachard.drools.hello.summary.dao.OrderDao;
import com.zachard.drools.hello.summary.dao.UserDao;
import com.zachard.drools.hello.summary.model.Order;
import com.zachard.drools.hello.summary.model.User;

/**
 * 规则执行测试类
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class HelloSummaryApp {
	
	/**
	 * 规则文件路径
	 */
	private static final String FILE_PATH = "rules/integral.drl";
	
	/**
	 * 规则文件测试入口方法
	 * <pre>
	 *     注意各个规则触发执行的顺序
	 * </pre>
	 * 
	 * @param args
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		DroolsValidateUtil.validateDroolsFile(FILE_PATH, HelloSummaryApp.class, ResourceType.DRL);
		
		/*
		 * KieServices是一个线程安全的,由Kie提供的,用于获取其他Service的中心
		 */
		KieServices kieServices = KieServices.Factory.get();
		KieContainer kieContainer = kieServices.getKieClasspathContainer();
		KieSession kieSession = kieContainer.newKieSession("ksession-rules");
		
		Scanner scanPrice = new Scanner(System.in);
		System.out.println("请输入商品单价: ");
		float price = Float.valueOf(scanPrice.nextLine());
		System.out.println();
		
		Scanner scanCount = new Scanner(System.in);
		System.out.println("请输入商品数量: ");
		int count = Integer.valueOf(scanCount.nextLine());
		System.out.println();
		
		Scanner scanUId = new Scanner(System.in);
		System.out.println("请输入会员编号: ");
		int userId = Integer.valueOf(scanUId.nextLine());
		float amount = price * count;    // 计算商品总额
		
		// 实例化并创建响应的数据对象
		User user = new User();
		Order order = new Order();
		UserDao userDao = new UserDao();
		OrderDao orderDao = new OrderDao();
		
		order.setOrderCount(count);
		order.setOrderAmount(amount);
		
		Map<String, Object> userMap = userDao.getUserById(userId);
		user.setUserId(userId);
		user.setUserName(userMap.get("u_name").toString());
		user.setUserLevel(userMap.get("u_level").toString());
		user.setUserPoint(Float.valueOf(userMap.get("u_point").toString()));
		user.setFlag(Integer.valueOf(userMap.get("t_flag").toString()));
		
		// 将对象插入到Working Memory之中,并触发所有规则
		kieSession.insert(user);
		kieSession.insert(order);
		kieSession.fireAllRules();
		
		// 将规则中修改的数据保存到数据库
		int id = user.getUserId();
		float point = user.getUserPoint();
		userDao.updatePointById(id, point);
		
		int oCount = order.getOrderCount();
		float oAmount = order.getOrderAmount();
		float oPoint = order.getOrderPoint();
		orderDao.insertOrder(id, oCount, oAmount, oPoint);
	}
}
