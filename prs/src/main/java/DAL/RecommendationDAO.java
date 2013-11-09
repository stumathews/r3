/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BOLO.Recommendation;
import DAL.Interfaces.IRecommendationDAO;
import DAL.Interfaces.IUserDAO;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This is how we access data in the database for recommendations
 * @author stuartm
 */
public class RecommendationDAO implements IRecommendationDAO
{
  
    private IUserDAO userDAO;
    private SessionFactory sessionFactory; 
    
    @Autowired
    public void setUserDAO(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }
    
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    
    public void createRecommendation(BOLO.Recommendation recommendation) throws Exception {
        Session session = sessionFactory.getCurrentSession();
        // convert the bolo recommendation into a del dtabase entity recommendation 
        DEL.Recommendation del_recommendation = new DEL.Recommendation();
            del_recommendation.setTitle( recommendation.getTitle());
            del_recommendation.setRecommend( recommendation.isRecommend());            
            del_recommendation.setValue( recommendation.getValue());            
        session.save(del_recommendation);
    }

    
    public List<Recommendation> getAllRecommendations() throws Exception {
        Session session = sessionFactory.getCurrentSession();        
        List<DEL.Recommendation> recommendations =  session.createQuery("from Recommendation").list();
        List<BOLO.Recommendation> bolo_recommendations = new ArrayList<BOLO.Recommendation>();
        for( DEL.Recommendation recommendation : recommendations  )
        {
            BOLO.Recommendation bol = new BOLO.Recommendation();
            bol.setTitle(recommendation.getTitle());
            bolo_recommendations.add(bol);
            
        }
        return bolo_recommendations;
    }
    
}
