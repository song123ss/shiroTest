package com.cskaoyan.shiro;

import com.cskaoyan.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * yuexia
 * 2018/12/11
 * 20:22
 */
public class CustomRealm extends AuthorizingRealm {
    @Override
    public String getName() {
        return "customRealm";
    }

    //认证过程
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //来源是用户输入
        String principal = (String) authenticationToken.getPrincipal();
        String password_from_db = "11111";
        //模拟从数据库获得密码
        User user = new User();
        user.setUsername("zhangsan");
        String aaa="zhenxiang";
        SimpleAuthenticationInfo simpleAuthenticationInfo =
                new SimpleAuthenticationInfo(user, password_from_db, getName());
        return simpleAuthenticationInfo;

    }

    //授权过程
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取认证器里面提交的参数primaryPrincipal，是simpleAuthenticationInfo
        //里面的第一个参数
        User primaryPrincipal = (User) principalCollection.getPrimaryPrincipal();
        String username = primaryPrincipal.getUsername();
        //增加一个permission和role 来源于数据库
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //增加的permission和role来源于数据库
        simpleAuthorizationInfo.addStringPermission("user:query");
        simpleAuthorizationInfo.addRole("role1");
        return simpleAuthorizationInfo;
    }

}
