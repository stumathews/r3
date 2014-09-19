
package DAL;

import DAL.Interfaces.ITokenDAO;
import DAL.Interfaces.IUserDAO;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Manages the interaction with the database when it comes to tokens.
 * This is capable of being injected as a dependency as it interfaces with IToken
 */
public class TokenDAO implements ITokenDAO
{
  private IUserDAO userDAO;
  private SessionFactory sessionFactory;

  @Autowired
  public void setSessionFactory( SessionFactory sessionFactory)
  {
      this.sessionFactory = sessionFactory;
  }

  @Autowired
  public void setUserDAO( IUserDAO userDAO)
  {
      this.userDAO = userDAO;
  }

  public void storeToken(BOLO.Token token, String username, String password ) throws Exception
  {
          // A user must exist for thiss token, check this, create otherwise (Administrator)
          DEL.User del_user = userDAO.retrieve(username, password);

          if( del_user == null ){ // user not found, auto-create it...
                  userDAO.createUser(username, password);
                  del_user = userDAO.retrieve(username, password);
          }

          Session session = sessionFactory.getCurrentSession();		

          DEL.Token del_token = new DEL.Token();
          del_token.setIssued_time(token.getIssued_time());
          del_token.setMins_valid(token.getMins_valid());
          del_token.setTokenString(token.getTokenString());
          del_token.setUser(del_user);

          session.save(del_token);
  }	
  @SuppressWarnings("unchecked")        
  public BOLO.Token getToken(String token) throws Exception
  {
          Session session = sessionFactory.getCurrentSession();

          String sql_query = String.format("from Token where tokenString = '%s'", token);

          @SuppressWarnings("rawtypes")
          List<DEL.Token> results = session.createQuery(sql_query).list();

          for( DEL.Token db_token : (List<DEL.Token>) results)
          {
              if( db_token.getTokenString().equals(token) )
              {
                  BOLO.Token bol_token = new BOLO.Token();
                  bol_token.setIssued_time(db_token.getIssued_time());
                  bol_token.setMins_valid(db_token.getMins_valid());
                  bol_token.setTokenString(db_token.getTokenString());
                  return bol_token;
              }
          }
          throw new Exception("Token provided doesn't exist.");		
  }
      
  public void cleanOldUserTokens(String token) throws Exception
  {					

  }        
    
  public ArrayList<DEL.Token> GetAllTokens()
  {
          ArrayList<DEL.Token> items = new ArrayList<DEL.Token>();		
          Session session = sessionFactory.getCurrentSession();
          List<DEL.Token> results = session.createQuery("from Token").list();

          for( DEL.Token token : results )
          {
              items.add( token);
          }

          return items;				
  }
	
}
