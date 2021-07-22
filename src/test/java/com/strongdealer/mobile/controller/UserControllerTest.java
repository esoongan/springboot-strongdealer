package com.strongdealer.mobile.controller;

import com.strongdealer.mobile.domain.User.User;
import com.strongdealer.mobile.domain.User.UserRepository;
import com.strongdealer.mobile.jwt.JwtTokenProvider;
import com.strongdealer.mobile.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;
    // 한글깨짐처리
    @Autowired
    private WebApplicationContext ctx;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;


//    // 한글깨짐처리
//    @Before
//    public void setup() {
//        this.mvc = MockMvcBuilders.webAppContextSetup(ctx)
//                .addFilters(new CharacterEncodingFilter("UTF-8", true))  // 필터 추가
//                .build();
//    }

    @Test
    public void 사용자정보조회_API_테스트() throws Exception {


        //given
        // 사용자등록
        User user = userRepository.save(User.builder()
                .id((long) 1232346365)
                .email("sjinlee97@gmail.com")
                .gender("female")
                .phoneNumber("01088332891")
                .username("sjinlee97")
                .build());

        // 토큰발급
        String token = jwtTokenProvider.createToken(user.getUsername());


        //when
        this.mvc.perform(MockMvcRequestBuilders
                .get("/api/user")
                .header("Authorization", token))
                .andExpect(status().isOk())
                .andDo(print());

    }


}