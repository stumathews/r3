package Utility;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SwapableCollection<T> 
{
    public List<T> ConvertSetToList( Set<T> aSet)
    {
        List<T> list = new ArrayList<T>();
        list.addAll(aSet);
        return list;
    }
    
    public Set<T> ConvertListToSet( List<T> aList)
    {
        Set<T> set = new HashSet<T>();
        set.addAll(aList);
        return set;
    }
}
