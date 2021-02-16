package com.example.demo;

import org.junit.Test;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnSafe {
	//获取Unsafe实例
	static final Unsafe unsafe;
	//记录state在类TestUsafe中的偏移值
	static final long STATE_OFFSET;
	//变量
	public volatile int result;
	public int[] arr={1,2,3,4,5,6};
	static{
		try{
			//获取成员变量
			Field field=Unsafe.class.getDeclaredField("theUnsafe");
			//设置为可访问
			field.setAccessible(true);
			//是静态字段,用null来获取Unsafe实例
			unsafe=(Unsafe)field.get(null);
			//获取state变量在类中的偏移值
			STATE_OFFSET =unsafe.objectFieldOffset(UnSafe.class.getDeclaredField("result"));
		}catch(Exception e){
			System.out.println(e.getLocalizedMessage());
			throw new Error(e);
		}
	}

	@Test
	public void ss() {
		unsafe.compareAndSwapInt(this, STATE_OFFSET, 0, -1);
		System.out.println(result);
		result = 2;
		System.out.println(STATE_OFFSET);

	}


//	public static void t1() {
//		UnSafe testUnsafe=new UnSafe();
//		//执行并返回结果
//		for(int i=0;i<1000;i++) {
//			unsafe.getAndAddLong(testUnsafe,stateOffset ,3L);
//		}
//		System.out.println(testUnsafe.result);
//		System.out.println(unsafe.arrayBaseOffset(testUnsafe.arr.getClass()));
//		System.out.println(unsafe.arrayIndexScale(testUnsafe.arr.getClass()));
//		System.out.println(unsafe.compareAndSwapLong(testUnsafe, stateOffset, 3000, 4000));
//		System.out.println(unsafe.getLongVolatile(testUnsafe, stateOffset));
//		unsafe.putLongVolatile(testUnsafe, stateOffset, 5000);
//		System.out.println(testUnsafe.result);
//		unsafe.putOrderedLong(testUnsafe, stateOffset, 5500);
//		System.out.println(testUnsafe.result);
//		Thread thread1= new Thread(() -> {
//			System.out.println("线程1开始沉睡");
//			long start=System.currentTimeMillis();
//			long end=System.currentTimeMillis()+8000;
//			unsafe.park(true,end);
//			System.out.println("主线程在"+(System.currentTimeMillis()-start)+"ms后被线程2唤醒");
//		});
//		Thread thread2= new Thread(() -> {
//			try {
//				Thread.sleep(3000);
//				unsafe.unpark(thread1);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		});
//		thread1.start();
//		thread2.start();
//	}
}

