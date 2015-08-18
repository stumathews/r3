/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 *
 * @author Stuart
 */
@Configuration
public class SecurityWebInitializer extends AbstractSecurityWebApplicationInitializer { }
