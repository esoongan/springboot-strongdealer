package com.strongdealer.mobile.dto.Car;

import com.strongdealer.mobile.domain.Car.CarInfo.Car;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class CarResponseDto {

    // DB에 저장된 PK
    private Long id;
    // 차번호
    private String carNo;
    // 제조사
    private String manufacturer;
    // 차종 ( 세단, SUV)
    private String type;
    // 모델명
    private String model;
    // 상세 모델명
    private String detailModel;
    // 등급
    private String rating;
    // 상세 등급
    private String detailRating;
    // 변속기 (오토, 수동, 기타 ...)
    private String gearbox;
    // 연료 (가솔린, 디젤, LPG ...)
    private String fuel;
    //색상
    private String color;
    // 연식
    private Integer modelYear;
    // 주행거리
    //private Integer mileage;
    // 출고가
    private Integer price;
    // 최초등록일
    private Integer initialRegistration;

    public CarResponseDto(Car car) {
        this.id = car.getId(); // PK 함께리턴
        this.carNo = car.getCarNo();
        this.manufacturer = car.getManufacturer();
        this.type = car.getType();
        this.model = car.getModel();
        this.detailModel = car.getDetailModel();
        this.rating = car.getRating();
        this.detailRating = car.getDetailRating();
        this.gearbox = car.getGearbox();
        this.fuel = car.getFuel();
        this.color = car.getColor();
        this.modelYear = car.getModelYear();
        //this.mileage = car.getMileage();
        this.price = car.getPrice();
        this.initialRegistration = car.getInitialRegistration();
    }
}
