package com.cskaoyan;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

/**
 * yuexia
 * 2018/12/11
 * 17:10
 */
public class test1 {
    @Test
    public void test1(){
        Factory<SecurityManager> factory =
                new IniSecurityManagerFactory("classpath:shiro_realm.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "1111");

        //执行认证
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }

        //是否认证通过
        boolean authenticated = subject.isAuthenticated();
        System.out.println("是否认证通过"+authenticated);

    }
}
