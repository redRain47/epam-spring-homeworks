package com.epam.hw5.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String fullName;

    @OneToOne(cascade = CascadeType.ALL)
    private Account account;
}
