package com.example.analyzer;

import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.stereotype.Component;

/**
 * <pre>
 *
 * </pre>
 *
 * @author 杨帮东 (qq:397827222)
 * @version 1.0
 * @date 2020/10/28 23:25
 **/
//@Component
public class FailureAnalyzerTestTrigger implements SmartInitializingSingleton {

	@Override
	public void afterSingletonsInstantiated() {
		throw new MyException("FailureAnalyzerTestTrigger测试.............");
	}
}
