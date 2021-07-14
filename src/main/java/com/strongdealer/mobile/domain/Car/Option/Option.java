package com.strongdealer.mobile.domain.Car.Option;

import com.strongdealer.mobile.dto.Car.Option.OptionRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@Getter
@Entity
public class Option {

    @Id
    @Column(name = "car_id")
    private Long id;

    private Boolean sunLoop;
    private Boolean hid;
    private Boolean autoSideMirror;
    private Boolean parkingDetect;
    private Boolean smartKey;
    private Boolean navigation;
    private Boolean hotSheetFront;
    private Boolean coolSheetFront;
    private Boolean autoSheetDriver;
    private Boolean leatherSheet;
    private String etc;

    @Builder
    public Option(Long carId, OptionRequestDto requestDto){
        this.id = carId;
        this.sunLoop = requestDto.getSunLoop();
        this.hid = requestDto.getHid();
        this.autoSideMirror = requestDto.getAutoSideMirror();
        this.parkingDetect = requestDto.getParkingDetect();
        this.smartKey = requestDto.getSmartKey();
        this.navigation = requestDto.getNavigation();
        this.hotSheetFront = requestDto.getHotSheetFront();
        this.coolSheetFront = requestDto.getCoolSheetFront();
        this.autoSheetDriver = requestDto.getAutoSheetDriver();
        this.leatherSheet = requestDto.getLeatherSheet();
        this.etc = requestDto.getEtc();
    }
}
