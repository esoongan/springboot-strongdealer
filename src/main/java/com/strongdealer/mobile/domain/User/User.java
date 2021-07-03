package com.strongdealer.mobile.domain.User;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// 엔티티는 반드시 파라미터가 없는 생성자가 있어야 하고, 이는 public 또는 protected 여야 한다.

@NoArgsConstructor
@Entity
public class User {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment로 pk자동생성
    private Long id;

    private String realName;    //사용자
    private String email;   //카카오
    private String userId;  //사용자
    private String phoneNumber; //사용자
    private String profileImage; // 카카오
    private String thumbnailImage;  //카카오
    private String gender;  //카카오
    private String refreshToken; //카카오

    @Builder
    public User(Long id, String realName, String email, String userId, String phoneNumber, String profileImage, String thumbnailImage, String gender, String refreshToken) {
        this.id = id;
        this.realName = realName;
        this.email = email;
        this.userId = userId;
        this.phoneNumber = phoneNumber;
        this.profileImage = profileImage;
        this.thumbnailImage = thumbnailImage;
        this.gender = gender;
        this.refreshToken = refreshToken;
    }
}
