package com.example.demo;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * <pre>
 *
 * </pre>
 *
 * @author 杨帮东 (qq:397827222)
 * @version 1.0
 * @date 2020/11/02 23:11
 **/
public class BigDecimalTest {

	public static void main(String[] args) {
		BigDecimal a = new BigDecimal(10);
		BigDecimal b = new BigDecimal(3);
		System.out.println(a.divide(b, 2, RoundingMode.HALF_UP));
		System.out.println(a.divide(b));
	}
}
