package com.task.employee.Domain;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Entity
@Table(name = "userinfo")
@Data
    public class Users {
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


