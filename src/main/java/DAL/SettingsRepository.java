/*
 * Copyright (c) 2015, Stuart Mathews
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
