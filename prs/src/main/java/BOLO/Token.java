/**
 * Business Object to represent a login token
 */
package BOLO;

import java.sql.Timestamp;
import java.util.Date;


/**
 * @author stuart
 *
 */

public class Token
{
	
	private String token;
	private Date issued_time;
	private int mins_valid;	

	public String getToken() {
		return token;
	}
	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
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
