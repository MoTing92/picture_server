package com.moting.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	private SecuritySettings securitySettings;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.formLogin().loginPage("/login").permitAll().successHandler(loginSuccessHandler())
			.and().authorizeRequests()
			.antMatchers("/images/*","/script/*").permitAll()
			.mvcMatchers(securitySettings.getPermitall().split(",")).permitAll()
			.anyRequest().authenticated()
			.and().csrf().requireCsrfProtectionMatcher(requireCsrfProtectionMatcher())
			.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)
			.and().logout().logoutSuccessUrl(securitySettings.getLogoutsuccessurl())
			.and().exceptionHandling().accessDeniedPage(securitySettings.getDeniedpage())
			.and().rememberMe().tokenValiditySeconds(1209600).tokenRepository(tokenRepository());
	}

	private PersistentTokenRepository tokenRepository() {
		// TODO Auto-generated method stub
		return null;
	}

	private RequestMatcher requireCsrfProtectionMatcher() {
		// TODO Auto-generated method stub
		return null;
	}

	private AuthenticationSuccessHandler loginSuccessHandler() {
		// TODO Auto-generated method stub
		return null;
	}
}
