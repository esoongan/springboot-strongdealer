package com.strongdealer.mobile.domain.Car;

import com.strongdealer.mobile.domain.User.User;

import javax.persistence.*;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment로 pk자동생성
    Long id;

    @ManyToOne
    User user;

    String carNo;
}
