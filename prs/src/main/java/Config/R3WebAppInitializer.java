/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Config;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * AbstractAnnotationConfigDispatcherServletInitializer creates both a DispatcherServlet(Spring's application context) and a ContextLoaderListener (responsible for loading other beans).
 * This is for servlet 3.0 and above compatible containers
 * @author Stuart
 */
@EnableWebMvcSecurity
public class R3WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
  /***
   * Map DispatcherServlet to /
   * @return 
   */
  @Override
  protected String[] getServletMappings(){
    return new String[] {"/"};
  }
    
  /***
   * Load middle-tier and data-tier components(beans) that drive the back end of the application.
   * @return 
   */
  @Override
  protected Class<?>[] getRootConfigClasses(){
    return new Class<?>[] { RootConfig.class, SecurityConfig.class };
    
  }
  
  /***
   * Specify configuration class which is used to load web components(beans)
   * @return 
   */
  @Override 
  protected Class<?>[] getServletConfigClasses(){
    return new Class<?>[] { WebConfig.class };
  }
}
