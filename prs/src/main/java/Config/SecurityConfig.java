/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 *
 * @author Stuart
 */
@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
  @Autowired
  DataSource dataSource;
  
  @Override 
  protected void configure(HttpSecurity http) throws Exception
  {
    http.authorizeRequests().antMatchers("/meals/add",
                                         "/meals/create",
                                         "/meals/delete",
                                         "/settings/**",
                                         "/today/delete/**",
                                         "/today/addbyId/**",
                                         "/today/add")
                            .authenticated()
                            .and()
                            .formLogin().loginPage("/meals/login")
                            .and()
                            .rememberMe()
                                .tokenValiditySeconds(2419200)
                                .key("mealKey")
                            .and()
                            .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                            .logoutSuccessUrl("/meals/login");              
  }
  
  @Override
  protected void configure( AuthenticationManagerBuilder auth) throws Exception
  {
    auth.inMemoryAuthentication().withUser("stuart").password("stuart4049").roles("USER");            
  }
}
