package com.strongdealer.mobile.dto.Car.Car4Sale;

import com.strongdealer.mobile.domain.Car.Car4Sale.Car4Sale;
import com.strongdealer.mobile.domain.Car.CarInfo.Car;
import com.strongdealer.mobile.domain.User.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class Car4SaleResponseDto {

    private Long id;
    private Long carId;
    private Long userId;
    private LocalDateTime startTime;

    public Car4SaleResponseDto(Car4Sale car4Sale) {
        this.id = car4Sale.getId();
        this.carId = car4Sale.getCar().getId();
        this.userId = car4Sale.getUser().getId();
        this.startTime = car4Sale.getCreatedDate();

    }

}
