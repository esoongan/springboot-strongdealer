package com.strongdealer.mobile.dto.paging;

import com.strongdealer.mobile.domain.Car.CarInfo.Car;
import lombok.Data;

import java.time.LocalDateTime;

// 페이징을 위한 DTO객체
@Data
public class PagingDto {

    private Long id; // CarId
    private String title; //
    private String rating; // 게시글 작성자
    private String mileage;
    private String addr;
    private LocalDateTime createdDate; // 게시글 작성일 및 시간


    public PagingDto(Long id, String title, String rating, String mileage, String addr, LocalDateTime createdDate) {
        this.id = id;
        this.title = title;
        this.rating = rating;
        this.mileage = mileage;
        this.addr = addr;
        this.createdDate = createdDate;
    }
}
