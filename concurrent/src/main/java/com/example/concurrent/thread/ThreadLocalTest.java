package com.example.concurrent.thread;

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

	public static void main(String[] args) {
		ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
//		ThreadLocal<Map<String, String>> mapThreadLocal = ThreadLocal.withInitial(HashMap::new);
		threadLocal.set(1);
		Integer integer = threadLocal.get();
		System.out.println(integer);
		threadLocal.remove();
	}
}
