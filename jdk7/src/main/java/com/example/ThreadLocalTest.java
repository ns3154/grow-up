package com.example;

import java.util.WeakHashMap;

/**
 * <pre>
 *
 * </pre>
 *
 * @author 杨帮东 (qq:397827222)
 * @version 1.0
 * @date 2020/11/02 23:21
 **/
public class ThreadLocalTest {

	public static void main(String[] args) {
		ThreadLocal<Integer> threadNum = new ThreadLocal<>();
		threadNum.set(1);
		threadNum.get();
		threadNum.remove();

		WeakHashMap<String, String> m = new WeakHashMap<>();
		m.put("a", "b");
	}
}
