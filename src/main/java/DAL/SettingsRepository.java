package DAL;

import DAL.Interfaces.ISettingsRepository;
import DEL.DailyAmounts;
import DEL.MacroUnitProfile;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Manages settings in the database
 * @author Stuart Mathews
 */
public class SettingsRepository implements ISettingsRepository 
{    
    @Autowired
    private SessionFactory sessionFactory;
    
    @Transactional
    public void saveSettings(MacroUnitProfile settings) 
    {
        settings.setId(1);
        sessionFactory.getCurrentSession().merge(settings);
    }
    
    @Transactional
    public void saveDailyAmounts(DailyAmounts dailyAmounts)
    {
        dailyAmounts.setId(1);
        sessionFactory.getCurrentSession().merge(dailyAmounts);
    }
    
    @Transactional
    public DailyAmounts getDailyAmounts()
    {
        Query q = sessionFactory.getCurrentSession().createQuery("from DailyAmounts where id = :id");
        q.setLong("id", 1);
         List<DailyAmounts> results = (List<DailyAmounts>) q.list();
        if( results.isEmpty() )
        {
            return null;
        }
        return results.get(0);
    }
    

    @Transactional
    public MacroUnitProfile getSettings() 
    {
        Query q = sessionFactory.getCurrentSession().createQuery("from MacroUnitProfile where id = :id");
        q.setLong("id", 1);
        List<MacroUnitProfile> results = (List<MacroUnitProfile>) q.list();
        if( results.isEmpty() )
        {
            return null;
        }
        return results.get(0);
    }
    
}
