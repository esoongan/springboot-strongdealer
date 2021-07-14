package com.strongdealer.mobile.dto.Car;

import com.strongdealer.mobile.domain.Car.CarInfo.Car;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class CarResponseDtoFromNation {

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
    // 주행거리 ? 주행거리도 보내주나?? 확인필요
    private Integer mileage;
    // 출고가
    private Integer price;
    // 최초등록일
    private Integer initialRegistration;

    // 이거는 가짜로 값을 넣기위해서 만든 생성자고 실제 국가api랑 연동하면 requestDto에는 생성자 업어도 됨
    @Builder
    public CarResponseDtoFromNation(String carNo, String manufacturer, String type, String model, String detailModel, String rating, String detailRating, String gearbox, String fuel, String color, Integer modelYear, Integer mileage, Integer price, Integer initialRegistration) {
        this.carNo = carNo;
        this.manufacturer = manufacturer;
        this.type = type;
        this.model = model;
        this.detailModel = detailModel;
        this.rating = rating;
        this.detailRating = detailRating;
        this.gearbox = gearbox;
        this.fuel = fuel;
        this.color = color;
        this.modelYear = modelYear;
        this.mileage = mileage;
        this.price = price;
        this.initialRegistration = initialRegistration;
    }

    public Car toEntity() {
        return Car.builder()
                .carResponseDtoFromNation(this)
                .build();
    }


}
