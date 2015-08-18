/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package configuration;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

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
          http.authorizeRequests()
              .anyRequest()
              .authenticated()
              .and()
              .formLogin()
              .and()
              .logout()
              .permitAll();
              
  }
  
  @Override
  protected void configure( AuthenticationManagerBuilder auth) throws Exception
  {
    auth.inMemoryAuthentication().withUser("admin").password("apps3cur3").roles("USER");
            
  }
}
