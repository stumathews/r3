/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package configuration;

import BOL.Interfaces.IUserSessionManager;
import BOL.UserSessionManager;
import BOL.security.UserAuthService;
import BOLO.sessions.ISessionUserDetails;
import BSL.Interfaces.ICharacteristicAdmin;
import BSL.Interfaces.ILoginAdmin;
import BSL.Interfaces.IProductAdmin;
import BSL.Interfaces.IRecommendationAdmin;
import BSL.Interfaces.IReviewAdmin;
import BSL.Interfaces.IUserAdmin;
import BSL.UserAdmin;
import DAL.Interfaces.IRecommendationDAO;
import DAL.RecommendationDAO;
import WSL.CharacteristicFacade;
import WSL.Interfaces.IAdminFacade;
import WSL.UserFacade;
import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.Proxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate3.HibernateExceptionTranslator;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.ui.context.support.ResourceBundleThemeSource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.theme.FixedThemeResolver;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

/**
 *
 * @author Stuart
 */
@Configuration
@EnableWebMvc
@EnableWebMvcSecurity
@EnableTransactionManagement
@ComponentScan( basePackages = {"Website.Controllers","configuration","BOL","DAL", "Webflow.Controllers","BSL.Interfaces", "BOLO.Validators", "Website.Initialisation","configuration"})//, "BOL", "Webflow.Controllers", "BOLO.Validators", "Website.Initialisation","configuration""} )//Website.Controllers, BOL, Webflow.Controllers, BOLO.Validators, Website.Initialisation,configuration")
public class WebConfig extends WebMvcConfigurerAdapter {
  
  @Override
  public void configureDefaultServletHandling( DefaultServletHandlerConfigurer configurer)
  {
    configurer.enable();
  }
  
  /* ::Theymleaf::*/
  
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
    templateResolver.setSuffix(".jsp");
    templateResolver.setTemplateMode("HTML5");
    return templateResolver;    
  }
  
  @Bean 
  public IUserSessionManager userSessionManager()
  {
    BOL.UserSessionManager userSessionManager = new UserSessionManager();
    return userSessionManager;
  }
  
  @Bean 
  public IRecommendationAdmin recommendationAdmin()
  {
    return new BSL.RecommendationAdmin();
  }
        
  @Bean
  public IReviewAdmin reviewAdmin()
  {
    return new BSL.ReviewAdmin();
  }
  @Bean 
  public IUserAdmin userAdmin()
  { 
    return new UserAdmin();
  }
  @Bean
  public ILoginAdmin loginAdmin()
  {
    return new BSL.LoginAdmin();
  }
  @Bean
  public ICharacteristicAdmin characteristicAdmin()
  {
    return new BSL.CharacteristicAdmin();
  }
  @Bean
  public IProductAdmin productAdmin()
  {
    return new BSL.ProductAdmin();
  }
  @Bean
  public ISessionUserDetails sessionUserDetails()
  {
    return new BOLO.sessions.SessionUserDetails();
  }
  @Bean
  public BOL.Interfaces.ICharacteristic characteristicBOL()
  {
    return new BOL.Characteristic();
  }
  @Bean
  public BOL.Interfaces.IAuthentication authenticationBOL()
  {
    return new BOL.Authentication();
  }
  @Bean BOL.Interfaces.IRecommendation recommendationBOL()
  {
    return new BOL.Recommendation();
  }
  @Bean
  public BOL.Interfaces.IProduct productLogic()
  {
    return new BOL.Product();
  }
  @Bean
  public BOL.Interfaces.IReview ReviewLogic()
  {
    return new BOL.Review();
  }
  @Bean
  public BOL.Interfaces.IToken tokenBOL()
  {
    return new BOL.Token();
  }
  @Bean
  public BOL.Interfaces.IUser userBOL()
  {
    return new BOL.User();
  }
  
  @Bean
  public BOL.Interfaces.IServiceAuthoriser serviceAuthoriser ()
  {
    return new BOL.ServiceAuthoriser();
  }
  @Bean
  public BOL.Interfaces.ICommonUtil commonUtil()
  {
    return new BOL.CommonUtil();
  }
  
  /* Data access Objects */
  
  @Bean
  public IRecommendationDAO recommendationDAO ()
  {
    return new RecommendationDAO();
  }
  @Bean
  public DAL.Interfaces.IReviewDAO reviewDAO()
  {
    return new DAL.ReviewDAO();
  }
  @Bean
  public DAL.Interfaces.ICharacteristicReviewDAO characteristicReviewDAO()
  {
    return new DAL.CharacteristicReviewDAO();
  }
  @Bean
  public DAL.Interfaces.IProductDAO productDAO()
  {
    return new DAL.ProductDAO();
  }
  @Bean
  public DAL.Interfaces.IUserDAO userDAO()
  {
    return new DAL.UserDAO();
  }
  @Bean
  public DAL.Interfaces.ITokenDAO tokenDAO()
  {
    return new DAL.TokenDAO();
  }
  @Bean
  public DAL.Interfaces.ICharacteristicsDAO characteristicDAO()
  {
    return new DAL.CharacteristicDAO();
  }
  
  @Bean
  public BOLO.R3GlobalConfig r3config()
  {
    BOLO.R3GlobalConfig global = new BOLO.R3GlobalConfig();
    
    return global;
    
  }
  @Bean
  public BSL.Interfaces.ICharacteristicAdmin characAdmin()
  {
    return new BSL.CharacteristicAdmin();
  }
  
  @Bean
  @Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
  public BOLO.Interfaces.IUserSessionInfo userSessionInfo()
  {
    return new BOLO.UserSessionInfo();
  }
  
   @Bean   
   public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
      HibernateTransactionManager txManager = new HibernateTransactionManager();
      txManager.setSessionFactory(sessionFactory);
 
      return txManager;
   }
  
   Properties hibernateProperties() {
      return new Properties() {
         {
            setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
            setProperty("hibernate.show_sql", "true");
            setProperty("hibernate.c3p0.min_size", "5");
            setProperty("hibernate.c3p0.max_size", "20");
            setProperty("hibernate.c3p0.timeout", "300");
            setProperty("hibernate.c3p0.max_statements", "50");
            setProperty("hibernate.c3p0.idle_test_period", "3000");
            setProperty("hibernate.hbm2ddl.auto", "create");
         }
      };
   }
  
   
  @Bean 
  public org.springframework.jdbc.datasource.DriverManagerDataSource myDataSource()
  {
    DriverManagerDataSource myDataSource = new DriverManagerDataSource();
    myDataSource.setDriverClassName("org.hsqldb.jdbcDriver");
    myDataSource.setUrl("jdbc:hsqldb:mem:r3");
    myDataSource.setUsername("sa");
    myDataSource.setPassword("");
    return myDataSource;    
  }
  
    
  @Bean
  public LocalSessionFactoryBean sessionFactory(DataSource myDataSource)
  {    
    
   LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
   sessionFactory.setDataSource(myDataSource);
   Properties properties = new Properties();    
   
   properties.put("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
   properties.put("hibernate.show_sql", "true");
   properties.put("hibernate.c3p0.min_size", "5");
   properties.put("hibernate.c3p0.max_size", "20");
   properties.put("hibernate.c3p0.timeout", "300");
   properties.put("hibernate.c3p0.max_statements", "50");
   properties.put("hibernate.c3p0.idle_test_period", "3000");
   properties.put("hibernate.hbm2ddl.auto", "create");
   sessionFactory.setMappingResources( new String[] {"DEL/Characteristic.hbm.xml",
     "DEL/CharacteristicArea.hbm.xml",
     "DEL/CharacteristicReview.hbm.xml",
     "DEL/Product.hbm.xml",
     "DEL/Recommendation.hbm.xml",
     "DEL/Review.hbm.xml",
     "DEL/Token.hbm.xml",
     "DEL/User.hbm.xml"});
   sessionFactory.setHibernateProperties(properties);   
   
   return sessionFactory;
  }
    
  @Bean public HibernateExceptionTranslator hibernateExceptionTranslator()
  {
    return new HibernateExceptionTranslator();
  }
  
  @Bean 
  public BeanPostProcessor persistenceTranslation(){    
    return new PersistenceExceptionTranslationPostProcessor();
  }
  @Bean 
  public UserDetailsService myUserAuthService()
  {
    BOL.security.UserAuthService myUserAuthService = new UserAuthService();
    myUserAuthService.setUserDAO(userDAO());
    return myUserAuthService;
  }
  
  @Bean
  public IAdminFacade adminFacade()
  {
    return new WSL.AdminFacade();
  }
  
  @Bean 
  public UserFacade userFacade()
  {
    return new UserFacade();
  }
  
  @Bean 
  public CharacteristicFacade  characteristicFacade()
  {
    return new CharacteristicFacade();
  }
  
  @Bean 
  public WSL.ProductFacade productFacade()
  {
    return new WSL.ProductFacade();
  }
  
  @Bean
  public ExceptionHandlerExceptionResolver exceptionHandlerExceptionResolver()
  {
    return new ExceptionHandlerExceptionResolver();
  }
  
  @Bean 
  public CommonsMultipartResolver multipartResolver()
  {
    CommonsMultipartResolver cmr = new CommonsMultipartResolver();
    cmr.setMaxUploadSize(100000);
    return cmr;
  }
  
  @Bean 
  public ResourceBundleThemeSource themeSource()
  {
    ResourceBundleThemeSource resourceBundleThemeSource = new ResourceBundleThemeSource();
    resourceBundleThemeSource.setBasenamePrefix("there-");
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
    
}
