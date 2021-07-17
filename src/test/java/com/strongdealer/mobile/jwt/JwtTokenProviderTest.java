package com.strongdealer.mobile.jwt;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;


// JUnit5부터는 DI를 스스로 지원한다. 즉 JUnit이 생성자에 다른 의존성을 주입하려고 먼저 개입한다.
// 단위테스트시 의존성을 주입받으려면 @Autowired를 사용한다.

//(JUnit은 내장된 Runner를 테스트 시 실행함)
// JUnit프레임워크가 내장된 Runner를 실행할때 @RunWith어노테이션을 통해 SpringRunner.class라는 확장된 클래스를 실행하라고 지시한것
@RunWith(SpringRunner.class)
//스프링 부트 어플리케이션 테스트 시 테스트에 필요한 거의 모든 의존성을 제공하는 어노테이션이다.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK) // 가짜 컨테이너 실행
// Mock 테스트시 필요한 의존성을 제공해준다. ( 실제 컨테이너가 실행되는것은 아니지만 로직상 테스트 실행가능)
//@AutoConfigureMockMvc
public class JwtTokenProviderTest {

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Test
    public void 토큰발행후_이름추출() throws Exception {
        //given
        String username = "testuser-1";
        //when
        String accessToken = jwtTokenProvider.createToken(username);
        System.out.println(accessToken);
        //then
        assertThat(jwtTokenProvider.getUsername(accessToken)).isEqualTo(username);
    }



}