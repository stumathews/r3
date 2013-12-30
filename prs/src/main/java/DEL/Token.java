/**
 * Entity to represent a login token
 */
package DEL;


import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



/***
 * A token represents a user authentication
 * @author Stuart Mathews <stumathews@gmail.com>
 */
public class Token
{
	
	private String tokenString;
	private Date issued_time;
	private int mins_valid;	
	private Long id;
	private User user;
	
	public Token(User user){
		setUser(user);		
	}
	
	public Token(){}
	
	
	/**
	 * @return the id
	 */
	
	public Long getId() 
	{
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) 	{		
		this.id = id;
	}
	
	
	public String getTokenString() {
		return tokenString;
	}
	/**
	 * @param token the token to set
	 */
	
	public void setTokenString(String token) {
		this.tokenString = token;
	}
	/**
	 * @return the issued_date_time
	 */
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date getIssued_time() {
		return issued_time;
	}
	/**
	 * @param issued_date_time the issued_date_time to set
	 */
	public void setIssued_time(Date issued_time) {
		this.issued_time = issued_time;
	}
	/**
	 * @return the mins_valid
	 */
	
	public int getMins_valid() {
		return mins_valid;
	}
	/**
	 * @param mins_valid the mins_valid to set
	 */
	public void setMins_valid(int mins_valid) {
		this.mins_valid = mins_valid;
	}
	
	
	public User getUser(){
		return this.user;
	
	}
	public void setUser(User user)
	{
		this.user = user;
	}
	
}
