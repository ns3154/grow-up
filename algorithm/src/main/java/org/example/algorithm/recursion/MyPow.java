package org.example.algorithm.recursion;


/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2021/10/31 16:16
 **/
public class MyPow {
	
	public void test () {
		System.out.println(myPow(2, -2147483648));
		System.out.println(myPow1(2, -2147483648));
	}
	
	public double myPow1 (double x, int n) {
		if (x == 0) {
			return 0;
		}
		if (n == 0) {
			return 1D;
		}
		long n1 = n;
		n1 = n > 0 ? n1 : -n1;
		double t = 1D;
		
		while (n1 > 0) {
			if ((n1 & 1) == 1) {
				t = t * x;
			}
			x = x * x;
			n1 >>=  1;
		}
		return n > 0 ? t : 1 / t;
	}

	
	//x = 2.10000, n = 3
	public double myPow (double x, int n) {
		if (x == 0) {
			return 0;
		}
		
		if (n == 0) {
			return 1D;
		}
		long t = n;
		
		return n > 0 ? p(x, t) : 1 / p(x, -t);
	}
	
	public double p (double x, long n) {
		if (n == 0) {
			return 1D;
		}
		double p = p(x, n / 2);
		return n % 2 == 0 ? p * p : x * p * p;
	}
}
