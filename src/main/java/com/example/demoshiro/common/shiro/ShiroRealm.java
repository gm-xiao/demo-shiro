package com.example.demoshiro.common.shiro;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashSet;
import java.util.Set;

public class ShiroRealm extends AuthorizingRealm {

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        String username = (String)SecurityUtils.getSubject().getPrincipal();

        Set<String> permissionSet = new HashSet<>();

        permissionSet.add("admin:good");
        permissionSet.add("admin:customer");
        permissionSet.add("admin:information");
        permissionSet.add("admin:system");

        permissionSet.add("good:edit");
        permissionSet.add("good:save");
        permissionSet.add("good:del");

        simpleAuthorizationInfo.setStringPermissions(permissionSet);

        return simpleAuthorizationInfo;

    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String userName = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());

        if (!"admin".equals(userName)){
            throw new UnknownAccountException("用户名错误！");
        }

        if (!"123456".equals(password)){
            throw new IncorrectCredentialsException("密码错误！");
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userName, password, getName());
        return info;
    }
}