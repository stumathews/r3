package BOLO;

/**
 * A site wite bean that holds configuration info for this site.
 * @author Stuart
 */
public final class R3GlobalConfig
{
  boolean ValidateToken;

  public boolean isValidateToken() {
    return ValidateToken;
  }

  public void setValidateToken(boolean ValidateToken) {
    this.ValidateToken = ValidateToken;
  }
}
