package Config;

import java.util.Locale;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.ui.context.support.ResourceBundleThemeSource;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.theme.FixedThemeResolver;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

/**
 * Spring MVC Configuration
 * @author Stuart
 */
@Configuration
@Import(RootConfig.class)
@EnableWebMvc
@EnableWebMvcSecurity
@EnableTransactionManagement
@ComponentScan(basePackageClasses = {
                Website.Controllers.MealController.class,                 
                Website.Controllers.NavigationController.class,
                Website.Controllers.MealDayController.class,
                Website.Controllers.SettingsController.class,
                Website.Controllers.ErrorHandling.GlobalErrorHandler.class,
                Website.Controllers.LoginController.class,
                DAL.MealRepository.class,
                DAL.MealDayRepository.class,
                DAL.SettingsRepository.class,
                BSL.MealService.class,
                BSL.MealDayService.class,
                BSL.SettingsService.class
})
public class WebConfig extends WebMvcConfigurerAdapter 
{
  
  /***
   * Configure static content handling
   * forward requests for static resources to the servlet container’s default servlet 
   * @param configurer 
   */
  @Override
  public void configureDefaultServletHandling( DefaultServletHandlerConfigurer configurer)
  {
    configurer.enable();    
  } 
  
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
         LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("language");
        registry.addInterceptor(interceptor);

    }
  
  
  
  /***
   * Configure Theymleaf view resolver
   * @param templateEngine
   * @return 
   */
  @Bean
  public ViewResolver viewResolver(SpringTemplateEngine templateEngine){
    
    ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
    viewResolver.setTemplateEngine(templateEngine);
    return viewResolver;
  }
  
  @Bean
  public SpringTemplateEngine templateEngine(TemplateResolver templateResolver)
  {   
    SpringTemplateEngine templateEngine = new SpringTemplateEngine();
    templateEngine.addDialect(new SpringSecurityDialect());
    templateEngine.setTemplateResolver(templateResolver);
    return templateEngine;  
  }
  
  @Bean 
  public TemplateResolver templateResolver()
  {    
    TemplateResolver templateResolver = new ServletContextTemplateResolver();
    templateResolver.setPrefix("/WEB-INF/pages/");
    templateResolver.setSuffix(".html");
    templateResolver.setTemplateMode("HTML5");
    return templateResolver;    
  }  
  @Bean
  public ExceptionHandlerExceptionResolver exceptionHandlerExceptionResolver()
  {
    return new ExceptionHandlerExceptionResolver();
  }
    
  public MultipartResolver multipartResolver()
  {
      //return new org.springframework.web.multipart.commons.CommonsMultipartResolver();
      //return new CommonsMultipartResolver();
      return new StandardServletMultipartResolver();
  }
  
  @Bean 
  public ResourceBundleThemeSource themeSource()
  {
    ResourceBundleThemeSource resourceBundleThemeSource = new ResourceBundleThemeSource();
    resourceBundleThemeSource.setBasenamePrefix("theme-");
    return resourceBundleThemeSource;
  }
  
  @Bean 
  public FixedThemeResolver themeResolver()
  {
    FixedThemeResolver fixedThemeResolver = new FixedThemeResolver();
    fixedThemeResolver.setDefaultThemeName("default");
    return fixedThemeResolver;
  }
  
  @Bean 
  public ResourceBundleMessageSource messageSource()
  {
    ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
    resourceBundleMessageSource.setBasename("Messages");
    return resourceBundleMessageSource;
  }   
  
  @Bean
  public LocaleResolver localeResolver(){
    CookieLocaleResolver resolver = new CookieLocaleResolver();
    resolver.setDefaultLocale(new Locale("en-GB"));
    resolver.setCookieName("myLocaleCookie");
    resolver.setCookieMaxAge(4800);
    return resolver;
    } 
  
}
