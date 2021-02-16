package com.example.demo;

/**
 * <pre>
 *
 * </pre>
 *
 * @author 杨帮东 (qq:397827222)
 * @version 1.0
 * @date 2021/02/16 04:15
 **/
public class ThisEscape {
	private String value = "";

	public ThisEscape() {
		new Thread(new TestDemo()).start();
		this.value = "this escape";
	}

	public class TestDemo implements Runnable {
		@Override
		public void run() {
			/** * 这里是可以通过ThisEscape.this调用外围类对象的，但是测试外围累对象可能还没有构造完成， * 所以会发生this逃逸现象 */
			System.out.println(ThisEscape.this.value);
		}
	}

}
