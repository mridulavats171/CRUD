package com.task.employee.Domain;

import jakarta.persistence.*;
import lombok.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
@Entity
@Table(name = "userinfo")
    public class Users implements {
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

    }


