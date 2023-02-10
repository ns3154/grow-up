package com.example.concurrent.thread;

import jdk.internal.dynalink.beans.StaticClass;
import org.junit.jupiter.api.Test;

import java.lang.ref.WeakReference;
import java.util.Objects;
import java.util.function.Supplier;

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

	static final ThreadLocal<Integer> THREAD_LOCAL = ThreadLocal.withInitial(() -> -1);

	static final InheritableThreadLocal<Integer> I_THREAD_LOCAL = new InheritableThreadLocal<>();



	@Test
	public void test() throws InterruptedException {

		new Thread(() -> {
			System.out.println("threadLocal.withInitial: " + THREAD_LOCAL.get());

			THREAD_LOCAL.set(1);
			System.out.println("threadLocal.get: " + THREAD_LOCAL.get());
			THREAD_LOCAL.remove();
			System.out.println("threadLocal.remove: " + THREAD_LOCAL.get());

			THREAD_LOCAL.set(333);
			System.out.println("threadLocal.get: " + THREAD_LOCAL.get());

			System.gc();
			System.out.println("System.gc() " + THREAD_LOCAL.get());
			I_THREAD_LOCAL.set(33);
			I_THREAD_LOCAL.get();
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			System.out.println("System.gc() " + THREAD_LOCAL.get());
			System.out.println(0x61c88647);
		}).start();

		Thread.sleep(1000000L);




	}



}
