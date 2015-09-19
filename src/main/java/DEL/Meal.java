/*
 * Copyright (c) 2015, Stuart
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package DEL;

import DEL.Interfaces.IMeal;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * A Meal entity
 * @author Stuart
 */
public class Meal implements IMeal
{
  
  @NotNull
  private long id;
  
  @NotEmpty  
  private String title;
  
  @Min(0)  
  private int carbs;
  
  @Min(0)  
  private int proteins;
  
  @Min(0)  
  private int fats;

  public Meal(String title, int carbs, int proteins, int fats)
  {
    this.title = title;
    this.carbs = carbs;
    this.proteins = proteins;
    this.fats = fats;
  }

  public Meal()
  {
  }
  
  public long getId()
  {
    return id;
  }

  public void setId(long id)
  {
    this.id = id;
  }
  
   
  public String getTitle()
  {
    return title;
  }

  public void setTitle(String title)
  {
    this.title = title;
  }

  public int getCarbs()
  {
    return carbs;
  }

  public void setCarbs(int carbs)
  {
    this.carbs = carbs;
  }

  public int getProteins()
  {
    return proteins;
  }

  public void setProteins(int proteins)
  {
    this.proteins = proteins;
  }

  public int getFats()
  {
    return fats;
  }

  public void setFats(int fats)
  {
    this.fats = fats;
  }

  @Override
  public int hashCode()
  {
    int hash = 7;
    hash = 97 * hash + (int) (this.id ^ (this.id >>> 32));
    hash = 97 * hash + (this.title != null ? this.title.hashCode() : 0);
    hash = 97 * hash + this.carbs;
    hash = 97 * hash + this.proteins;
    hash = 97 * hash + this.fats;
    return hash;
  }

  @Override
  public boolean equals(Object obj)
  {
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    final Meal other = (Meal) obj;
    if (this.id != other.id)
      return false;
    if ((this.title == null) ? (other.title != null) : !this.title.equals(other.title))
      return false;
    if (this.carbs != other.carbs)
      return false;
    if (this.proteins != other.proteins)
      return false;
    if (this.fats != other.fats)
      return false;
    return true;
  }

  @Override
  public String toString()
  {
    return "Meal{" + "title=" + title + ", carbs=" + carbs + ", proteins=" + proteins + ", fats=" + fats + '}';
  }
}
