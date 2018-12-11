package com.cskaoyan;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

import java.util.Arrays;

/**
 * yuexia
 * 2018/12/11
 * 17:37
 */
public class realmTest  {
    @Test
    public void test1() {

        IniSecurityManagerFactory factory =
                new IniSecurityManagerFactory("classpath:shiro_realm.ini");
        SecurityManager securityManager = factory.getInstance();
        //通过securityUtils拿到subject
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();

        //token 令牌
        //这里是用户自己的
        String username="zhangsan";
        String  password="11111";
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        //执行认证操作
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        boolean authenticated = subject.isAuthenticated();
        System.out.println(username+"认证通过了吗？："+authenticated);
        //上面的全部是认证，下面进行授权
        String permission = "user:create2";
        boolean permitted = subject.isPermitted(permission);
        System.out.println(username+"是否拥有permission"+permission+":"+permitted);
        boolean role2 = subject.hasRole("role2");
        System.out.println("has role2"+role2);
        boolean[] booleans = subject.hasRoles(Arrays.asList("role1", "role2"));
        System.out.println(Arrays.toString(booleans));
        boolean[] permitted1 = subject.isPermitted("user,query", "user,create");
        System.out.println(Arrays.toString(permitted1));


    }



}
