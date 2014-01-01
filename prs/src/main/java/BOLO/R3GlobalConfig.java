/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
