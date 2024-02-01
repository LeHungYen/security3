package com.example.security3.service;

import com.example.security3.entity.CustomUserDetails;
import com.example.security3.entity.User;
import com.example.security3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        // Kiểm tra xem user có tồn tại trong database không?
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(user);
    }


    public UserDetails loadUserById(int userId) {
        User user = userRepository.getById(userId);
        if (user == null) {
            throw new UsernameNotFoundException(userId+"");
        }
        return new CustomUserDetails(user);
    }
}