package com.example.concurrent.thread;

import org.junit.jupiter.api.Test;

/**
 * <pre>
 *
 * </pre>
 *
 * @author 杨帮东 (qq:397827222)
 * @version 1.0
 * @date 2020/11/02 23:18
 **/
public class ThreadLocalTest {

	static ThreadLocal<Integer> threadLocal_1 = new ThreadLocal<>();
	static ThreadLocal<Integer> threadLocal_2 = new ThreadLocal<>();
	static ThreadLocal<Integer> threadLocal_3 = new ThreadLocal<>();
	static ThreadLocal<Integer> threadLocal_4 = new ThreadLocal<>();
	static ThreadLocal<Integer> threadLocal_5 = new ThreadLocal<>();
	static ThreadLocal<Integer> threadLocal_6 = new ThreadLocal<>();
	static ThreadLocal<Integer> threadLocal_7 = new ThreadLocal<>();
	static ThreadLocal<Integer> threadLocal_8 = new ThreadLocal<>();
	static ThreadLocal<Integer> threadLocal_9 = new ThreadLocal<>();

	@Test
	public void tst() {
		new Thread(() -> {
			threadLocal_1.set(1);
			threadLocal_2.set(2);
			System.out.println(threadLocal_1.get());
			System.out.println(threadLocal_2.get());
		}).start();
	}
}
