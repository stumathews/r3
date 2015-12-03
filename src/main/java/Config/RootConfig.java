package Config;

import BSL.Interfaces.ISettingsService;
import DAL.Interfaces.IMealDayRepository;
import DAL.Interfaces.IMealRepository;
import java.util.ArrayList;
import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.support.CompositeCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate3.HibernateExceptionTranslator;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * This is where middle tier and data beans/components are configured/created(beans)
 * This is in contrast to the 
 * @author Stuart
 */
@Configuration
@EnableCaching
@ComponentScan( basePackageClasses = {
                  DAL.UserDAO.class, 
                  BSL.MealService.class,               
                  BSL.MealDayService.class,
                  BSL.SettingsService.class,
                  BOL.User.class,
                  DAL.MealRepository.class,
                  DAL.MealDayRepository.class,
                  DAL.SettingsRepository.class} ,
        excludeFilters = {
            @Filter(type=FilterType.ANNOTATION, value=EnableWebMvc.class)
        })
public class RootConfig {
  @Bean
  public BOL.Interfaces.IUser userBOL()
  {
    return new BOL.User();
  }
    
  @Bean
  public DAL.Interfaces.IUserDAO userDAO()
  {
    return new DAL.UserDAO();
  }
  
  
  
  @Bean
  public BOLO.R3GlobalConfig r3config()
  {
    BOLO.R3GlobalConfig global = new BOLO.R3GlobalConfig();
    
    return global;    
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
   sessionFactory.setMappingResources( new String[] {
     "DEL/User.hbm.xml",
     "DEL/Meal.hbm.xml",
     "DEL/MealDay.hbm.xml",
     "DEL/MacroUnitProfile.hbm.xml",
     "DEL/DailyAmounts.hbm.xml"
     
   });
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
  public CacheManager cacheManager()
  {
    CompositeCacheManager cacheManager = new CompositeCacheManager();
    ArrayList<CacheManager> managers = new ArrayList<CacheManager>();
    managers.add(new ConcurrentMapCacheManager("mealCache","mealDayCache"));
    cacheManager.setCacheManagers(managers);
    return cacheManager;    
  }
  
  @Bean
  public BSL.Interfaces.IMealService mealService()
  {
    return new BSL.MealService();
  }
  
  @Bean 
  public IMealRepository mealRepository()
  {
    return new DAL.MealRepository();
  }
  
  @Bean
  public BSL.Interfaces.IMealDayService mealDayService()
  {
    return new BSL.MealDayService();
  }
  
  @Bean 
  public IMealDayRepository mealDayRepository()
  {
    return new DAL.MealDayRepository();
  }
  
  @Bean
  public ISettingsService settingsService()
  {
      return new BSL.SettingsService();
  }
  
  @Bean 
  public DAL.Interfaces.ISettingsRepository settingsRepository()
  {
      return new DAL.SettingsRepository();
  }
  
}
