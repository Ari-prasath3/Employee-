package com.example.EmpApp.Service;

import com.example.EmpApp.Entity.Employee;
import com.example.EmpApp.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private EmployeeRepository repo;

    @Override
    public UserDetails loadUserByUsername(String email) {
        Employee emp = repo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Employee not found"));
        return new org.springframework.security.core.userdetails.User(
                emp.getEmail(), emp.getPassword(), Collections.emptyList()
        );
    }
}