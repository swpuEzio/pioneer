package com.yiling.pioneer.service.Impl;

import com.yiling.pioneer.mapper.MyUserMapper;
import com.yiling.pioneer.model.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xc
 * @Date: 2019/7/9 13:05
 * @Description:
 **/
@Service
public class CustomerServiceImpl implements UserDetailsService {
    @Autowired
    MyUserMapper myUserMapper;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        MyUser find = myUserMapper.getUserAllInfoByUserName(s);
        if (find == null){
            throw new UsernameNotFoundException("用户名不存在");
        }


        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        simpleGrantedAuthorities.add(new SimpleGrantedAuthority(find.getRole()));
        return new User(find.getUsername(),find.getPassword(),simpleGrantedAuthorities);
    }
}
