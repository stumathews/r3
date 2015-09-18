/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Config;

import java.io.File;
import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * AbstractAnnotationConfigDispatcherServletInitializer creates both a DispatcherServlet(Spring's application context) and a ContextLoaderListener (responsible for loading other beans).
 * This is for servlet 3.0 and above compatible containers
 * @author Stuart
 */
@EnableWebMvcSecurity
public class R3WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer 
{
    private int maxUploadSizeInMb = 5 * 1024 * 1024; // 5 MB
    
    
  /***
   * Map DispatcherServlet to /
   * @return 
   */
  @Override
  protected String[] getServletMappings(){
    return new String[] {"/"};
  }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) 
    {
       MultipartConfigElement multipartConfigElement = new MultipartConfigElement("/",100000, 200000, 50000);

        registration.setMultipartConfig(multipartConfigElement);
    }

    @Override
    protected javax.servlet.Filter[] getServletFilters() {
        return  new Filter[]{new MultipartFilter()};
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
