package com.example.unamoregrande.service.impl;

import com.example.unamoregrande.model.entity.UserEntity;
import com.example.unamoregrande.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UnAmoreGrandeUserServiceImpl implements UserDetailsService {


    private UserRepository userRepository;

    public UnAmoreGrandeUserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntity =
                userRepository.findByUsername(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User with name" + username + "not found"));

        return mapToUserDetails(userEntity);
    }

    private static UserDetails mapToUserDetails(UserEntity userEntity) {

        List<GrantedAuthority> authorities = userEntity
                .getRoles()
                .stream()
                .map(r -> new SimpleGrantedAuthority("ROLE_" + r.getName().name()))
                .collect(Collectors.toList());

        return new UnAmoreUsers(
                userEntity.getUsername(),
                userEntity.getPassword(),
                authorities
        );
    }
}
