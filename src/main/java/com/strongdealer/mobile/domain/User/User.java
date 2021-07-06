package com.strongdealer.mobile.domain.User;

import com.strongdealer.mobile.dto.User.UserRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

// 엔티티는 반드시 파라미터가 없는 생성자가 있어야 하고, 이는 public 또는 protected 여야 한다.


@Getter
@NoArgsConstructor
@Entity
public class User {

    @Id
    // 카카오에서 받은 고유ID로 PK설정할것이므로 자동전략 사용하지 않는다.
//    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment로 pk자동생성
    private Long id;

    private String realName;    //사용자
    private String email;   //카카오
    private String userName;  //사용자
    private String phoneNumber; //사용자
    private String profileImage; // 카카오
    private String thumbnailImage;  //카카오
    private String gender;  //카카오
    private String refreshToken; //카카오
    private LocalDate birth;

    @Builder
    public User(Long id, String realName, String email, String userName, String phoneNumber, String profileImage, String thumbnailImage, String gender, String refreshToken, LocalDate birth) {
        this.id = id;
        this.realName = realName;
        this.email = email;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.profileImage = profileImage;
        this.thumbnailImage = thumbnailImage;
        this.gender = gender;
        this.refreshToken = refreshToken;
        this.birth = birth;

    }

    public void update(UserRequestDto requestDto) {
        this.realName = requestDto.getRealName();
        this.userName = requestDto.getUserName();
        this.phoneNumber = requestDto.getPhoneNumber();
        this.birth = requestDto.getBirth();
    }
}
