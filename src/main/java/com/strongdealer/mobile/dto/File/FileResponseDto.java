package com.strongdealer.mobile.dto.File;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.strongdealer.mobile.domain.File.File;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class FileResponseDto {

    private Long id;
    private Long carId;
    private String origFilename;
    private String filename;
    private String filepath;

    public FileResponseDto(File entity) {
        this.id = entity.getId();
        this.carId = entity.getCar().getId();
        this.origFilename = entity.getOrigFilename();
        this.filename = entity.getFilename();
        this.filepath = entity.getFilepath();
    }
}