package com.cloth_management.config;


import com.cloth_management.Models.User;
import com.cloth_management.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Lazy
    @Autowired
    private UserRepository userRepository;


    @Override
    public com.cloth_management.config.MyUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username");
        }

        // Need to update timeStamp
        return new com.cloth_management.config.MyUserDetails(user);
    }

}