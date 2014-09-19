package BOLO;

/**
 * A login Detail object
 * @author Stuart Mathews <stuart@stuartmathews.com>
 */
public class LoginDetails 
{

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    private String username;
    private String password;
}
