package com.qitai.sso;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MyPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence charSequence) {
        //charSequence 为用户输入的密码
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String str) {
        //charSequence 为用户输入的密码
        System.out.println(charSequence);
        //str 为数据库密码
        System.out.println(str);
        /*try {
            MessageDigest.getInstance("md5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }*/

        str = DigestUtils.md5Hex(str);
        return true;
    }
}
