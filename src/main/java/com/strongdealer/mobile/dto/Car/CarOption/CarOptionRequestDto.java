package com.strongdealer.mobile.dto.Car.CarOption;

import com.strongdealer.mobile.domain.Car.CarOption.CarOption;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CarOptionRequestDto {

    private Long id; // carId
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

    public CarOption toEntity() {
        return CarOption.builder()
                .carId(id)
                .requestDto(this)
                .build();


    }
}
