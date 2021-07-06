package com.strongdealer.mobile.service;

import com.strongdealer.mobile.domain.User.User;
import com.strongdealer.mobile.domain.User.UserRepository;
import com.strongdealer.mobile.dto.Kakao.KakaoUserResponseDto;
import com.strongdealer.mobile.dto.User.UserRequestDto;
import com.strongdealer.mobile.dto.User.UserResponseTempDto;
import com.strongdealer.mobile.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserResponseTempDto register(KakaoUserResponseDto responseDto) {
        // PK
        Long Id = responseDto.getId();
        // 카카오로부터 응답내용을 DB에 저장
        User user = userRepository.save(User.builder()
                .id(Id)
                .email(responseDto.getEmail())
                .gender(responseDto.getGender())
                .profileImage(responseDto.getProfile_image())
                .thumbnailImage(responseDto.getThumbnail_image())
                .build()
        );

        return new UserResponseTempDto(user);
    }

    @Transactional
    public void registerDone(UserRequestDto requestDto) {
        Long id = requestDto.getId();
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        log.info("사용자추출성공", user.getEmail());

        user.update(requestDto);

    }

    // 등록된적이 있는 회원인지 아닌지 확인
    @Transactional
    public Boolean is_need_register(Long id) {
        return userRepository.existsById(id);
    }


}
