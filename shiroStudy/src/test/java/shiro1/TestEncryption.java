package shiro1;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;

public class TestEncryption {

	public static void main(String[] args) {
		String password = "12345";
		String salt = new SecureRandomNumberGenerator().nextBytes().toString();
		int times = 1;
		String algorithmName = "md5";
		
		String encodedPassword = new SimpleHash(algorithmName,password,salt,times).toString();



		System.out.printf("原始密码是 %s , 盐是： %s, 运算次数是： %d, 运算出来的密文是：%s ",password,salt,times,encodedPassword);
	}

	@Test
	public void md5Test(){
		String password = "12345";
		String salt = new SecureRandomNumberGenerator().nextBytes().toString();
		int times = 1;
		String algorithmName = "md5";

		String encodedPassword = new SimpleHash(algorithmName,password,salt,times).toString();



		System.out.printf("原始密码是 %s , 盐是： %s, 运算次数是： %d, 运算出来的密文是：%s ",password,salt,times,encodedPassword);
	}
}
