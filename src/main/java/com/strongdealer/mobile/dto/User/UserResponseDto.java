package com.strongdealer.mobile.dto.User;

import com.strongdealer.mobile.domain.User.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class UserResponseDto {

    private Long id;
    private String realName;    //사용자
    private String email;   //카카오
    private String username;  //사용자
    private String phoneNumber; //사용자
    private String profileImage; // 카카오
    private String thumbnailImage;  //카카오
    private String gender;  //카카오
    private String refreshToken; //카카오
    private LocalDate birth;



    public UserResponseDto(User user){
        this.id = user.getId();
        this.realName = user.getRealName();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.phoneNumber = user.getPhoneNumber();
        this.profileImage = user.getProfileImage();
        this.thumbnailImage = user.getThumbnailImage();
        this.gender = user.getGender();
        this.refreshToken = user.getRefreshToken();
        this.birth = user.getBirth();
    }
}
