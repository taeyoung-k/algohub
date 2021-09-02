package algohub.config;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.lang.annotation.*;

// MockMVC 출력 결과 한글 처리
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@AutoConfigureMockMvc
@Import(EnableMockMvc.Config.class)
public @interface EnableMockMvc {
    class Config {

        @Bean
        public CharacterEncodingFilter characterEncodingFilter() {
            return new CharacterEncodingFilter("UTF-8", true);
        }
    }

/**
 *     문제를 해결하기 위해 MockMvc buid 시 CharacterEncodingFilter를 추가해주면 된다.
 *     MockMvc 설정을 위해 붙이는 @AutoConfigureMockMvc를 훑어보면 MockMvc build 시 SpringBootMockMvcBuilderCustomizer를 사용하는 것을 확인할 수 있다.
 *     SpringBootMockMvcBuilderCustomizer.addFilters에서는 bean으로 등록된 filter들을 가져와 mockMvc filter에 설정해준다.
 *     그러므로 테스트 구동 시 CharacterEncodingFilter를 빈으로 등록해주면 문제를 해결할 수 있다.
 *
 *     참고
 *     https://pompitzz.github.io/blog/Spring/MockMvc_Encoding.html#%E1%84%8B%E1%85%A8%E1%84%89%E1%85%B5
 */
}
