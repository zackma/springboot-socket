package com.example.demo.controller;

import com.example.demo.entity.SysPermission;
import com.example.demo.entity.SysRole;
import com.example.demo.entity.UserInfo;
import com.example.demo.service.UserInfoServiceI;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;

public class MyShiroRealm extends AuthorizingRealm {

    @Resource
    private UserInfoServiceI userInfoService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // TODO Auto-generated method stub
        System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        UserInfo userInfo = (UserInfo) principalCollection.getPrimaryPrincipal();

        for(SysRole role:userInfo.getRoleList()){

            authorizationInfo.addRole(role.getRole());
            System.out.println(role.getPermissions());
            for(SysPermission p:role.getPermissions()){
                System.out.println(p);
                authorizationInfo.addStringPermission(p.getPermission());
            }
        }
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("MyShiroRealm.doGetAuthenticationInfo()");
        // 获取用户的输入帐号
        String username = (String) authenticationToken.getPrincipal();
        System.out.println(authenticationToken.getCredentials());
        // 通过username从数据库中查找 User对象，如果找到，没找到.
        // 实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        UserInfo userInfo = userInfoService.findByUsername(username);
        System.out.println("----->>userInfo=" + userInfo);
        if (userInfo == null) {
            return null;
        }

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userInfo, // 用户名
                userInfo.getPassword(), // 密码
                ByteSource.Util.bytes(userInfo.getCredentialsSalt()), // salt=username+salt
                getName() // realm name
        );
        return authenticationInfo;
    }
}
