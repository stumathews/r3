package Config;

import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;

@Configuration
@Profile("prod")
public class ProdConfig {
    
    Properties hibernateProperties() 
    {      
         return new Properties() {
         {
            setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
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
    myDataSource.setDriverClassName("com.mysql.jdbc.Driver");
    myDataSource.setUrl("jdbc:mysql://${OPENSHIFT_MYSQL_DB_HOST}:${OPENSHIFT_MYSQL_DB_PORT}/${OPENSHIFT_APP_NAME}");
    myDataSource.setUsername("${OPENSHIFT_MYSQL_DB_USERNAME}");
    myDataSource.setPassword("${OPENSHIFT_MYSQL_DB_PASSWORD}");
    return myDataSource;    
  }
  
  @Bean
  public LocalSessionFactoryBean sessionFactory(DataSource myDataSource)
  {    
    
   LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
   sessionFactory.setDataSource(myDataSource);
   Properties properties = hibernateProperties();   
     
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
    
}
