package com.makarproject.lessonsletscod.cervices;


import com.makarproject.lessonsletscod.entity.Role;
import com.makarproject.lessonsletscod.entity.User;
import com.makarproject.lessonsletscod.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService implements UserDetailsService {

    private final UserRepo userRepository;

    public UserService(UserRepo userRepository) {
        this.userRepository = userRepository;
    }

//    @Autowired
//    BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not founded!");
        }
        return user;
    }

}

//    public boolean createUser(User user){
//        User usr = userRepository.findByUsername(user.getUsername());
//        if(usr != null){
//            return false;
//        }
//        user.setActive(true);
//        user.setRoles(Collections.singleton(Role.USER));
//        String code = UUID.randomUUID().toString();
//        user.setPassword(user.getPassword());
//        userRepository.save(user);
//        return true;
//    }
//    public User findUserById(Long userId) {
//        Optional<User> userFromDb = userRepository.findById(userId);
//        return userFromDb.orElse(new User());
//    }
//
//    public List<User> allUsers() {
//        return userRepository.findAll();
//    }
//
//    public boolean saveUser(User user) {
//        User userFromDB = userRepository.findByUsername(user.getUsername());
//
//        if (userFromDB != null) {
//            return false;
//        }
//
//        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        userRepository.save(user);
//        return true;
//    }
