package DEL;

import java.io.Serializable;
import java.util.Set;

/***
 * A User is...
 * @author Stuart Mathews <stumathews@gmail.com>
 */
public class User implements Serializable
{
    private Long id;
    private String username;
    private String password;
   
    public User(){}
    public User( String username, String password){
            setUsername(username);
            setPassword(password);
    }
    /**
     * @return the id
     */

    public Long getId() {
            return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(Long id) {
            this.id = id;
    }
    /**
     * @return the username
     */

    public String getUsername() {
            return username;
    }
    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
            this.username = username;
    }


    public String getPassword() { 
            return password;
    }

    public void setPassword(String password) {
            this.password = password;
    }
    
    @Override
    public int hashCode(){      
    int hash = 7;
    hash = 31 * hash + (this.username != null ? this.username.hashCode() : 0);
    hash = 31 * hash + (this.password != null ? this.password.hashCode() : 0);
    return hash;
    }
    
    @Override 
    public boolean equals( Object obj){
      if( obj == null){
        return false;
      }
      if(!(obj instanceof DEL.User)){
        return false;
      }
      return this.id == ((DEL.User)obj).getId();
    }
	
}
