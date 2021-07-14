package com.strongdealer.mobile.domain.Car.Car4Sale;

import com.strongdealer.mobile.domain.BaseTimeEntity;
import com.strongdealer.mobile.domain.Car.CarInfo.Car;
import com.strongdealer.mobile.domain.User.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// 관계테이블
@NoArgsConstructor
@Getter
@Entity
public class Car4Sale extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment로 pk자동생성
    private Long id;

    @OneToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Builder
    public Car4Sale(Car car, User user) {
        this.car = car;
        this.user = user;
    }
}
