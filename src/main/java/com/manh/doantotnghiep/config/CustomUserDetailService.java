package com.manh.doantotnghiep.config;


import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.manh.doantotnghiep.bean.entity.RoleEntity;
import com.manh.doantotnghiep.bean.entity.UserEntity;
import com.manh.doantotnghiep.dao.UserDao;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    UserDao accountRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userOpt = accountRepo.findByUsername(username);
        if (userOpt.isPresent()) {
            UserEntity accountEntity = userOpt.get();

            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

            Set<RoleEntity> roles = accountEntity.getRoles();

            for (RoleEntity role : roles) {
                grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
            }

            return new org.springframework.security.core.userdetails.User(accountEntity.getUsername(), accountEntity.getPassword(), grantedAuthorities);
        }
        throw new UsernameNotFoundException("Not found account with username: " + username);
    }

}