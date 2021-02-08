package org.example;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

/**
 * <pre>
 *
 * </pre>
 *
 * @author 杨帮东 (qq:397827222)
 * @version 1.0
 * @date 2021/02/08 23:41
 **/
public class CRC16 {

	/**
	 * 计算CRC16校验码
	 *
	 * @param bytes 字节数组
	 * @return {@link String} 校验码
	 * @since 1.0
	 */
	public static String getCRC(byte[] bytes) {
		int CRC = 0x0000ffff;
		int POLYNOMIAL = 0x0000a001;
		int i, j;
		for (i = 0; i < bytes.length; i++) {
			CRC ^= ((int) bytes[i] & 0x000000ff);
			for (j = 0; j < 8; j++) {
				if ((CRC & 0x00000001) != 0) {
					CRC >>= 1;
					CRC ^= POLYNOMIAL;
				} else {
					CRC >>= 1;
				}
			}
		}
		return Integer.toHexString(CRC);
	}

	/**
	 * 将16进制单精度浮点型转换为10进制浮点型
	 *
	 * @return float
	 * @since 1.0
	 */
	private float parseHex2Float(String hexStr) {
		BigInteger bigInteger = new BigInteger(hexStr, 16);
		return Float.intBitsToFloat(bigInteger.intValue());
	}

	/**
	 * 将十进制浮点型转换为十六进制浮点型
	 *
	 * @return String
	 * @since 1.0
	 */
	private String parseFloat2Hex(float data) {
		return Integer.toHexString(Float.floatToIntBits(data));
	}

	public static void main(String[] args) {
		System.out.println(getCRC("sdfsfsdf".getBytes(StandardCharsets.UTF_8)));
	}

}
