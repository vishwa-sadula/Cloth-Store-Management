package com.cloth_management.config;

import com.cloth_management.Models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyUserDetails implements UserDetails {


    private User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       // return Collections.singleton(new SimpleGrantedAuthority("USER"));

        List<GrantedAuthority> grantList = new ArrayList<>();
        if (user == null) {
            return grantList;
        }
        GrantedAuthority authority = new SimpleGrantedAuthority(this.user.getUser_type());
        grantList.add(authority);
        return grantList;
    }
//
    @Override
    public String getPassword() {
        return this.user.getUser_password();
    }

    @Override
    public String getUsername() {
        return this.user.getUser_password();
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

    public MyUserDetails(User user) {
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}