package com.strongdealer.mobile.dto.File;

import com.strongdealer.mobile.domain.Car.CarInfo.Car;
import com.strongdealer.mobile.domain.File.File;
import lombok.Builder;

public class FileRequestDto {

    private Car car;
    private String origFilename;
    private String filename;
    private String filepath;

    @Builder
    public FileRequestDto(Car car, String origFilename, String filename, String filepath) {
        this.car = car;
        this.origFilename = origFilename;
        this.filename = filename;
        this.filepath = filepath;
    }


    // FileRequestDto -> Dto에 담긴 값으로 Entity객체를 만들고 디비에 저장하기위한 메소드
    public File toEntity(){
        return File.builder()
                .car(car)
                .origFilename(origFilename)
                .filename(filename)
                .filepath(filepath)
                .build();
    }
}
