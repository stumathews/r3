/**
 * Business Object to represent a login token
 */
package BOLO;

import java.util.Date;

public class Token
{
	
	private String tokenString;    
	private Date issued_time;
	private int mins_valid;	

        public Token()
        {
            tokenString = "";
            issued_time = new Date();
            mins_valid = 15; // by default token is valid for 15 minutes 
        }
        
        public String getTokenString() 
        {
            return tokenString;
        }

        public void setTokenString(String tokenString)
        {
            this.tokenString = tokenString;
        }
	
	/**
	 * @return the issued_date_time
	 */

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
}
