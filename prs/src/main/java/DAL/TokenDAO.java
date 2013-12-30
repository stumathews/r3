
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
	/**
	 * Physically store the token in the database.
	 * @param token
	 */
        public void storeToken(BOLO.Token token, String username, String password ) throws Exception
	{
            // A user must exist for thiss token, check this, create otherwise (Administrator)
            DEL.User del_user = userDAO.retrieve(username, password);

            if( del_user == null ){ // user not found, auto-create it...
                    userDAO.createUser(username, password);
            }

            Session session = sessionFactory.getCurrentSession();		

            DEL.Token del_token = new DEL.Token();
            del_token.setIssued_time(token.getIssued_time());
            del_token.setMins_valid(token.getMins_valid());
            del_token.setTokenString(token.getTokenString());

            session.save(del_token);
	}
	
	/**
	 * Check to see if the token exists and if so, returns it.
	 * @param token
	 * @return the BOLO.Token
	 * @throws Exception token does not exist
	 */
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
        
	/**
	 * Clears out other tokens associated with the user of this token provided
	 * @param token 
	 */        
	public void cleanOldUserTokens(String token) throws Exception
	{					
           			
	}
        
        /***
         * Gets a List of the tokens as database entities
         * @return a list of all the tokens inthe database
         * @throws Exception 
         */
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
