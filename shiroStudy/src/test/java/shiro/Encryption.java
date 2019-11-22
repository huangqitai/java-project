package shiro;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md2Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;

public class Encryption {

    @Test
    public void md5(){
        /*
        md5加密
         */
        String data = "123";
        String dataMd5 = new Md2Hash(data).toString();
        System.out.println(dataMd5);

        /*
        md5+盐+2次加密
         */
        String password = "123";
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;
        String algorithmName = "md5";

        String encodedPassword = new SimpleHash(algorithmName,password,salt,times).toString();

        System.out.printf("原始密码是 %s , 盐是： %s, 运算次数是： %d, 运算出来的密文是：%s ",password,salt,times,encodedPassword);
    }
}
