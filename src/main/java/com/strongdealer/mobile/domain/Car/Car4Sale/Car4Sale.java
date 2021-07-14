package com.strongdealer.mobile.domain.Car.Car4Sale;

import com.strongdealer.mobile.domain.Car.CarInfo.Car;
import com.strongdealer.mobile.domain.User.User;
import lombok.Getter;

import javax.persistence.*;

// 관계테이블
@Getter
@Entity
public class Car4Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment로 pk자동생성
    private Long id;

    @OneToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;





}
