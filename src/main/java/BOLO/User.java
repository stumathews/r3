package BOLO;

import java.io.Serializable;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Business Object to represent a User
 * @author Stuart Mathews <stuart@stuartmathews.com>
 */
public class User implements Serializable
{
        @NotEmpty
	private String username;
        @NotEmpty
	private String password;
	/**
	 * @return the password
	 */
        
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the name
	 */
        
	public String getUsername() {
		return username;
	}
	/**
	 * @param name the name to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
}
