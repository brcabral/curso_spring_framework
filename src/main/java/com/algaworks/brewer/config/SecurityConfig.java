package com.algaworks.brewer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.algaworks.brewer.security.AppUserDetailsService;

@EnableWebSecurity
@ComponentScan(basePackageClasses = AppUserDetailsService.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
			.antMatchers("/layout/**")
			.antMatchers("/images/**");
	}

	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/cidades/nova").hasRole("CADASTRAR_CIDADE") // Se usar o hasRole precisa ter o prefixo ROLE_ na permissão no BD
				.antMatchers("/usuarios/**").hasRole("CADASTRAR_USUARIO")
				//.antMatchers("/cidades/nova").hasAuthority("CADASTRAR_CIDADE")  // Se usar o hasAuthority NÃO precisa ter o prefixo ROLE_ na permissão
				// .antMatchers("/*/novo").hasRole("CADASTRAR")  // Exemplo para bloquear todos os cadastros
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login").permitAll()
				.and()
			.exceptionHandling().accessDeniedPage("/403")
				.and()
			.csrf().disable();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
