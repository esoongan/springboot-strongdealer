package com.strongdealer.mobile.dto.Car;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// 사용자로부터 데이터 받아오는 클래스
@NoArgsConstructor
@Setter
@Getter
public class CarUpdateRequestDto {

    // DB에 저장된 PK
    private Long id;
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
    // 사고이력
    private String history;
    // 명의
    private String owner;
    // 기타이력
    private String historyEtc;
}
