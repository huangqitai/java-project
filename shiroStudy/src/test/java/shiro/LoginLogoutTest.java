package shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

public class LoginLogoutTest {

    @Test
    public void testHelloworld() {
        //1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
        /*在1.4后IniSecurityManagerFactory标记不推荐使用
        Factory<SecurityManager> factory =
        new IniSecurityManagerFactory("classpath:shiro.ini");
         //2、得到SecurityManager实例 并绑定给SecurityUtils
        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
        */

        //使用DefaultSecurityManager创建管理器
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        IniRealm iniRealm = new IniRealm("classpath:shiro.ini");
        defaultSecurityManager.setRealm(iniRealm);
       //2、得到SecurityManager实例 并绑定给SecurityUtils
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        //4.创建令牌
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
        try {
            //5、登录，即身份验证
            subject.login(token);
            System.out.println(subject.isAuthenticated());
        } catch (AuthenticationException e) {
            //6、身份验证失败
        }
        //subject.isAuthenticated()返回boolean值，表示用户是否认证成功
        Assert.assertEquals("验证",true, subject.isAuthenticated()); //断言用户已经登录

        //检查用户是否拥有这两个角色权限，没有会报错
        subject.checkRoles("admin","user");

        //7、退出
        subject.logout();
        System.out.println(subject.isAuthenticated());
    }

    public void login(String user,String pwd){
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        IniRealm iniRealm = new IniRealm("classpath:shiro.ini");
        defaultSecurityManager.setRealm(iniRealm);
        SecurityUtils.setSecurityManager(defaultSecurityManager);

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user,pwd);

        subject.login(token);

    }

    @Test
    public void checkRoles(){
        login("wang","123");
        Subject subject = SecurityUtils.getSubject();
        //判断认证用户是否拥有否个权限
        subject.checkPermission("user:read");
        Assert.assertTrue(subject.isPermitted("user:read"));
        Assert.assertFalse(subject.isPermitted("user:update"));
        subject.logout();
    }

    @Test
    public void checkRolesJdbc(){
        loginJdbc("li","123");
        Subject subject = SecurityUtils.getSubject();
        //判断认证用户是否拥有否个权限
        subject.checkPermission("user:read");
        Assert.assertTrue(subject.isPermitted("user:read"));
        Assert.assertFalse(subject.isPermitted("user:update"));
        subject.logout();

    }

    public void loginJdbc(String user,String pwd){
        Factory<SecurityManager> factory =
                new IniSecurityManagerFactory("classpath:shiro-jdbc.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user,pwd);

        subject.login(token);
        //断言用户已经登录
        Assert.assertEquals("验证",true, subject.isAuthenticated());

    }

}
