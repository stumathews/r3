package DEL.Interfaces;

import java.util.Date;

public interface IMealDay 
{
    public long getId();
    public void setId(long id);

    public Date getDate();
    public void setDate(Date date);

    public IMeal getMeal();
    public void setMeal(IMeal meal);
            
    @Override
    public int hashCode() ;

    @Override
    public boolean equals(Object obj);

    void setLocalTimeString(String localTimeString);
}
