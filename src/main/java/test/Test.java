/**
 * @Title: Test.java
 * @Package: test
 * @Description: 描述该文件做什么
 * @author: 5109u12412宁誉程
 * @date: 2021/11/22 14:13:28
 * @Company: sony
 * @version: V1.0
 * @Copyright: 版权
 */
package test;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

/**
 * @ClassName: Test
 * @Description: 描述这个类的作用
 * @author: 5109u12412宁誉程
 * @Company: sony
 * @date: 2021/11/22 14:13:28
 */
public class Test {

	/**
	 * @Title: main
	 * @Description: 描述这个方法的作用
	 * @param: @param args 参数说明
	 * @return: void 返回类型
	 */
	public static byte[] getBytesUTF8(char c) {
		Charset cs = Charset.forName("utf-8");
		CharBuffer cb = CharBuffer.allocate(1);
		cb.put(c);
		cb.flip();
		ByteBuffer bb = cs.encode(cb);
		return bb.array();
	}

	public static byte[] getBytesGBK(char c) {
		Charset cs = Charset.forName("GBK");
		CharBuffer cb = CharBuffer.allocate(1);
		cb.put(c);
		cb.flip();
		ByteBuffer bb = cs.encode(cb);
		return bb.array();
	}

	public static void main(String[] args) throws UnsupportedEncodingException {

//		String dirname = "D:/eclipse-jee-2018-12-R-win32-x86_64/wordspace/mts/src/main/java/com/sony/mts";
//		File file = new File(dirname);
//		if (file.isDirectory()) {
//			System.out.println("目录 " + dirname);
//			String s[] = file.list();
//			for (int i = 0; i < s.length; i++) {
//				File f = new File(dirname + "/" + s[i]);
//				if (f.isDirectory()) {
//					System.out.println(s[i] + " 是一个目录");
//				} else {
//					System.out.println(s[i] + " 是一个文件");
//				}
//			}
//		} else {
//			System.out.println(dirname + " 不是一个目录");
//		}

		char c = 'a';
		char cc = '中';
		String str = "a";
		String strr = "中";
		String s = "ａ";
		System.out.println("编码为UTF8:");
		System.out.println("char值为英文字符所占字节长度:" + getBytesUTF8(c).length);
		System.out.println("char值为中文字符所占字节长度:" + getBytesUTF8(cc).length);
		System.out.println("编码为GBK(默认编码):");
		System.out.println("char值为英文字符所占字节长度:" + getBytesGBK(c).length);
		System.out.println("char值为中文字符所占字节长度:" + getBytesGBK(cc).length);
		System.out.println("-------------------------------");
		System.out.println("编码为UTF8");
		System.out.println("String为英文字母所占字节长度:" + str.getBytes("utf-8").length);
		System.out.println("String为中文字母所占字节长度:" + strr.getBytes("utf-8").length);
		System.out.println("编码为GBK:");
		System.out.println("String为英文字母所占字节长度:" + str.getBytes("GBK").length);
		System.out.println("String为中文字母所占字节长度:" + strr.getBytes("GBK").length);
		System.out.println("String为英文字母（全角）所占字节长度:" + s.getBytes("GBK").length);

	}

}
