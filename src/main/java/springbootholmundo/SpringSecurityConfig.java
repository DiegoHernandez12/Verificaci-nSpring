package springbootholmundo;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
	private static final String[] PUBLIC_MATCHERS = {
			"/singup/**"			
	};
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests()
		.antMatchers(PUBLIC_MATCHERS).permitAll();
	}

}
