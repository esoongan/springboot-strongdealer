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

    // 모델명
    private String model;
    // 등급
    private String rating;
    // 세부등급
    private String detailRating;
    // 변속기
    private String gearbox;
    // 연료
    private String fuel;
    //색상
    private String color;
    // 연식
    private Integer modelYear;
    // 최초등록일
    private Integer initialRegistration;
    // 출고가
    private Integer price;


    String carNo;
}
