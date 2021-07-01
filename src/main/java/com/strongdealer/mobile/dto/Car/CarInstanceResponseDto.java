package com.strongdealer.mobile.dto.Car;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarInstanceResponseDto {

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


    @Builder
    public CarInstanceResponseDto(String model, String rating, String detailRating, String gearbox, String fuel, String color, Integer modelYear, Integer initialRegistration, Integer price) {
        this.model = model;
        this.rating = rating;
        this.detailRating = detailRating;
        this.gearbox = gearbox;
        this.fuel = fuel;
        this.color = color;
        this.modelYear = modelYear;
        this.initialRegistration = initialRegistration;
        this.price = price;
    }
}
