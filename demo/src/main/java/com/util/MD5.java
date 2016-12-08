package com.util;

import java.security.MessageDigest;

/**
 * Description��
 * ���ڣ�2015-4-10
 * ģ�����ƣ�
 * ������ ��׼MD5���ܷ�����ʹ��java����security����MessageDigest�ദ�� <BR>
 *      Ҳ�ɱ�Ϊ�Ǳ�׼MD5�����޸��������λ�㷨
 * ��ע��
 * ��Ȩ�������������Ȩ������ɴ���Ϣ�������޹�˾���У���ֹ�κ�δ��Ȩ�Ĵ�����ʹ�ã�
 * @author liuchun
 * @version 1.0
 */
public class MD5 {
	/**
	 * ���MD5��������ķ���
	 */
	public static String getMD5ofStr(String origString) {
		String origMD5 = null;
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] result = md5.digest(origString.getBytes());
			origMD5 = byteArray2HexStr(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return origMD5;
	}

	/**
	 * ���MD5��������ķ���
	 */
	public static String getMD5ofStr(String origString, int encKey1, int encKey2) {
		String origMD5 = null;
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] result = md5.digest(origString.getBytes());
			origMD5 = byteArray2HexStr(result, encKey1, encKey2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return origMD5;
	}

	/**
	 * �����ֽ�����õ�MD5����ķ���
	 */
	private static String byteArray2HexStr(byte[] bs) {
		StringBuffer sb = new StringBuffer();
		for (byte b : bs) {
			sb.append(byte2HexStr(b));
		}
		return sb.toString();
	}

	/**
	 * �����ֽ�����õ�MD5����ķ���
	 */
	private static String byteArray2HexStr(byte[] bs, int encKey1, int encKey2) {
		StringBuffer sb = new StringBuffer();
		for (byte b : bs) {
			sb.append(byte2HexStr(b, encKey1, encKey2));
		}
		return sb.toString();
	}

	/**
	 * �ֽڱ�׼��λתʮ�����Ʒ���
	 */
	private static String byte2HexStr(byte b) {
		String hexStr = null;
		int n = b;
		if (n < 0) {
			// ����Ҫ�Զ������,���޸������λ�㷨����
			n = b & 0x7F + 128;
		}
		hexStr = Integer.toHexString(n / 16) + Integer.toHexString(n % 16);
		return hexStr.toUpperCase();
	}

	/**
	 * �ֽڱ�׼��λתʮ�����Ʒ���
	 */
	private static String byte2HexStr(byte b, int encKey1, int encKey2) {
		String hexStr = null;
		int n = b;
		if (n < 0) {
			// ����Ҫ�Զ������,���޸������λ�㷨����
			n = b & encKey1 + encKey2;
		}
		hexStr = Integer.toHexString(n / 16) + Integer.toHexString(n % 16);
		return hexStr.toUpperCase();
	}

	/**
	 * �ṩһ��MD5��μ��ܷ���
	 */
	public static String getMD5ofStr(String origString, int times) {
		String md5 = getMD5ofStr(origString);
		for (int i = 0; i < times - 1; i++) {
			md5 = getMD5ofStr(md5);
		}
		return getMD5ofStr(md5);
	}

	/**
	 * ������֤����
	 */
	public static boolean verifyPassword(String inputStr, String MD5Code) {
		return getMD5ofStr(inputStr).equals(MD5Code);
	}

	/**
	 * ��μ���ʱ��������֤����
	 */
	public static boolean verifyPassword(String inputStr, String MD5Code,
			int times) {
		return getMD5ofStr(inputStr, times).equals(MD5Code);
	}

	/**
	 * �ṩһ�����Ե�������
	 */
	public static void main(String[] args) {
		System.out.println("12345:" + getMD5ofStr("12345"));
		System.out.println("111111:" + getMD5ofStr("111111"));
	}
}
