package com.plan.planningapp.security;

import com.plan.planningapp.model.UserInfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserInfoDetails  implements UserDetails {
    private UserInfo userInfo;
    private Integer uiId;

    public UserInfoDetails(UserInfo ui){
        userInfo = ui;uiId = ui.getId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public String getPassword() {
        return userInfo == null ? null: userInfo.getPass();
    }

    @Override
    public String getUsername() {
        return userInfo == null ? null: userInfo.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }
    public Integer getUiId(){
        return uiId;
    }
}
