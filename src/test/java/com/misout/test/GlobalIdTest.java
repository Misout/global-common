package com.misout.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.misout.interfaces.IdService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring.xml")
public class GlobalIdTest {

	@Autowired
	private IdService uuidService;
	
	@Autowired
	private IdService snowflakeService;

	@Test
	public void getUUIDTest() {
		long minCost = 0, maxCost = 0, sumCost = 0;
		for (int i = 0; i < 10000; i++) {
			long start = System.currentTimeMillis();
			String id = uuidService.getGlobalId();
			long cost = System.currentTimeMillis() - start;
			System.out.println(id + " 耗时：" + cost + "ms");
			if (i == 0) {
				minCost = cost;
				maxCost = cost;
			} else {
				if (cost < minCost) {
					minCost = cost;
				} else if(cost > maxCost) {
					maxCost = cost;
				}
			}
			sumCost += cost;
		}
		System.out.println("最小耗时：" + minCost + "ms");
		System.out.println("最大耗时：" + maxCost + "ms");
		System.out.println("平均耗时：" + (sumCost/1000) + "ms");
	}
	
	@Test
	public void getSnowFlakeIdTest() {
		long minCost = 0, maxCost = 0, sumCost = 0;
		for (int i = 0; i < 10000; i++) {
			long start = System.currentTimeMillis();
			String id = snowflakeService.getGlobalId();
			long cost = System.currentTimeMillis() - start;
			System.out.println(id + " 耗时：" + cost + "ms");
			if (i == 0) {
				minCost = cost;
				maxCost = cost;
			} else {
				if (cost < minCost) {
					minCost = cost;
				} else if(cost > maxCost) {
					maxCost = cost;
				}
			}
			sumCost += cost;
		}
		System.out.println("最小耗时：" + minCost + "ms");
		System.out.println("最大耗时：" + maxCost + "ms");
		System.out.println("平均耗时：" + (sumCost/1000) + "ms");
	}
}
