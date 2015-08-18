package DAL;
import DAL.Interfaces.ICharacteristicReviewDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;


public class CharacteristicReviewDAO implements ICharacteristicReviewDAO
{
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    public DEL.CharacteristicReview getCharacteristicReview(Long id) 
    {
        Session session = sessionFactory.getCurrentSession();
        DEL.CharacteristicReview dCharactersticReview = (DEL.CharacteristicReview) session.load(DEL.CharacteristicReview.class, id);
        return dCharactersticReview;
    }
    
}
