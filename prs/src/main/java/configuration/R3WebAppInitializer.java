/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 *
 * @author Stuart
 */
public class R3WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
  @Override
  protected String[] getServletMappings(){
    return new String[] {"/"};
  }
  
  @Override
  protected Class<?>[] getRootConfigClasses(){
    return new Class<?>[] { RootConfig.class, SecurityConfig.class };
    
  }
  
  @Override 
  protected Class<?>[] getServletConfigClasses(){
    return new Class<?>[] { WebConfig.class };
  }
}
