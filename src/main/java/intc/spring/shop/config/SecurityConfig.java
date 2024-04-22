package intc.spring.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity //웹 시큐리티를 쓸수있도록 하겠다는거다
public class SecurityConfig {

    /*BCryptPasswordEncoder 이걸로 하든 PasswordEncoder를 하든 똑같이 암호화 된다*/
    @Bean
    BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http)throws Exception{


//        http  이거는 시작할때만 이렇게 사용
//                .formLogin(Customizer.withDefaults()) //로그인 페이지 이동
//                .logout(Customizer.withDefaults()); // 로그아웃

        http
                .formLogin(form ->form  //form 매개변수를 만들어서 그매개 변수 받은거를 이용해서
                        .loginPage("/member/login") //로그인이 걸리는 것들은 전부다 login페이지로 이동
                        .defaultSuccessUrl("/")// 정상이면 루트로 이동
                        .failureUrl("/member/login/error") //로그인 실패시 이동할 페이지
                        .usernameParameter("email") //로그인부분 서비스단에서 loadUserByUsername 이렇게 다 username만 받는데 이거를 config에서 username을 email로 변경해주면 로그인이된다
                       // .passwordParameter("password") // 여기도 password를 안쓰고 pw라고 쓰는 사람이 있기 때문에 이렇게 예시로 써둔다
                        .permitAll() );// 모든사람들이 쓸수 있다


        http.logout(Customizer.withDefaults()); //기본적인 로그아웃
        

        http
                .authorizeHttpRequests(request-> request
                        .requestMatchers("/css/**").permitAll() // 이렇게 해서 권한을 열어줘야  css가 먹는다
                        .requestMatchers("/","/member/**, /error").permitAll() //접근하게 만들거 ,/error 999에러 방지
                        .requestMatchers("/admin/**").hasRole("ADMIN") //admin 권한이 있으면 접속 가능 //컨트롤러에 url이 없어서 404뜨니까 후에 컨트롤 만드록 다시 403뜨는지 확인하기
                        .anyRequest().authenticated());//인증을 걸거다 //위에 권한을 열어준거 빼고는 전부다 로그인 페이지로 이동


        http.exceptionHandling(exception-> exception //exception이 발생하면 처리
                //.accessDeniedPage("/accessDenid"); //접근 거부 페이지
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint())); //CustomAuthenticationEntryPoint() 이클래스를 만드럭다

        return http.build();

    }

}
