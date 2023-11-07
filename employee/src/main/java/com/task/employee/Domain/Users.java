package com.task.employee.Domain;

import jakarta.persistence.*;
import lombok.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Setter
@Getter
@Entity
@Table(name = "userinfo")
    public class Users implements UserDetails {
        @Id
        @GeneratedValue
        private Integer id;

        @Column(name = "first_name")
        private String firstname;

        @Column(name = "last_name")
        private String lastname;

        @Column(name= "email")
        private String email;

        @Column(name = "password")
        private String password;

        @Enumerated(EnumType.STRING)
        private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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
}


