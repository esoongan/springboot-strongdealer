package com.strongdealer.mobile.domain.Car.CarInfo;

import com.strongdealer.mobile.domain.Car.Option.Option;
import com.strongdealer.mobile.dto.Car.CarResponseDtoFromNation;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Car {

    // PK
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment로 pk자동생성
    private Long id;

    // 옵션
    @OneToOne
    @JoinColumn(name = "option_id")
    private Option option;

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
    private Integer mileage;
    // 출고가
    private Integer price;
    // 최초등록일
    private Integer initialRegistration;

    // 국가로부터 데이터 받아서 저장하는 엔티티 만드는 생성자
    @Builder
    public Car(CarResponseDtoFromNation carResponseDtoFromNation) {
        this.carNo = carResponseDtoFromNation.getCarNo();
        this.manufacturer = carResponseDtoFromNation.getManufacturer();
        this.type = carResponseDtoFromNation.getType();
        this.model = carResponseDtoFromNation.getModel();
        this.detailModel = carResponseDtoFromNation.getDetailModel();
        this.rating = carResponseDtoFromNation.getRating();
        this.detailRating = carResponseDtoFromNation.getDetailRating();
        this.gearbox = carResponseDtoFromNation.getGearbox();
        this.fuel = carResponseDtoFromNation.getFuel();
        this.color = carResponseDtoFromNation.getColor();
        this.modelYear = carResponseDtoFromNation.getModelYear();
        this.mileage = carResponseDtoFromNation.getMileage();
        this.price = carResponseDtoFromNation.getPrice();
        this.initialRegistration = carResponseDtoFromNation.getInitialRegistration();
    }

    // 사용자로부터 추가정보와 옵션여부 받아서 업데이트
    public void update(CarResponseDtoFromNation requestDto, Option option) {
        this.option = option;
    }


}
