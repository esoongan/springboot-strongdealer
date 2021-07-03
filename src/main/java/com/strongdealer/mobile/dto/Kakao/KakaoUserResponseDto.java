package com.strongdealer.mobile.dto.Kakao;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
public class KakaoUserResponseDto {

    private Long id;
    private String profile_image;
    private String thumbnail_image;
    private String email;
    private String gender;


    @Builder
    public KakaoUserResponseDto(Long id, String profile_image, String thumbnail_image, String email, String gender) {
        this.id = id;
        this.profile_image = profile_image;
        this.thumbnail_image = thumbnail_image;
        this.email = email;
        this.gender = gender;
    }
}
