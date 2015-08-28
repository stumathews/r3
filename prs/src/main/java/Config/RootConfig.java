/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * This is where middle tier and data beans/components are configured/created(beans)
 * This is in contrast to the 
 * @author Stuart
 */
@Configuration
@ComponentScan( basePackages = {"Website.Controllers, BOL, Webflow.Controllers, BOLO.Validators, Website.Initialisation, Config"},
        excludeFilters = {
            @Filter(type=FilterType.ANNOTATION, value=EnableWebMvc.class)
        })
public class RootConfig {
  
}
