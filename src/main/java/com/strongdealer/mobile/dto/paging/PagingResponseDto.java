package com.strongdealer.mobile.dto.paging;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.strongdealer.mobile.dto.File.FileResponseDto;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

// Getter/Setter없이 Property를 읽도록 하는 방법
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@AllArgsConstructor // -> 빌더패턴으로 고치기
public class PagingResponseDto {

    private Long id; // 게시글의 id
    @JsonProperty("thumbnail")
    private List<FileResponseDto> fileResponseDto;
    private String title; // 차량풀네임이 제목
    private String userId; // 차량 소유주
    private LocalDateTime createdDate; // 게시글 작성일 및 시간
}
