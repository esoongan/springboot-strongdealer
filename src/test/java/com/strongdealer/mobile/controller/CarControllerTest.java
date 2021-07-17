package com.strongdealer.mobile.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.strongdealer.mobile.domain.Car.CarInfo.Car;
import com.strongdealer.mobile.domain.Car.CarInfo.CarRepository;
import com.strongdealer.mobile.domain.User.User;
import com.strongdealer.mobile.domain.User.UserRepository;
import com.strongdealer.mobile.dto.Car.CarUpdateRequestDto;
import com.strongdealer.mobile.jwt.JwtTokenProvider;
import com.strongdealer.mobile.model.HttpResponseMessage;
import com.strongdealer.mobile.model.HttpStatusCode;
import com.strongdealer.mobile.service.CarService;
import org.apache.tomcat.jni.Local;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Date;

import static org.assertj.core.api.InstanceOfAssertFactories.future;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // 가짜 컨테이너 실행
@AutoConfigureMockMvc // HTTP요청,응답을 위한 목객체
public class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarService carService;

    @BeforeEach
    void setUp() {
        carRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void 차량_경매_등록() throws Exception {

        //given

        // 사용자등록
        User user = User.builder()
                .id((long) 1232346365)
                .email("sjinlee97@gmail.com")
                .gender("female")
                .phoneNumber("01088332891")
                .username("sjinlee97")
                .build();
        User user1 = userRepository.save(user);

        // 토큰발급
        String token = jwtTokenProvider.createToken(user1.getUsername());

        // 등록할 차량정보
        Car car = carService.getCarInfoFromNationApi("12가1234");
        String content = objectMapper.writeValueAsString(car);

        //when
        final ResultActions actions = mockMvc.perform(post("/api/car")
                .header("Authorization", token)
                .content(content)
                .contentType("application/json")
        );

        // them
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.carNo").value("12가1234"));

    }

    @AfterEach
    void tearDown() {
    }
}