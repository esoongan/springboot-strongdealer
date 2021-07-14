package com.strongdealer.mobile.dto.Car.Option;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class OptionRequestDto {

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
}
