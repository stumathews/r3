/*
 * Copyright (c) 2016, Stuart
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

import DAL.Interfaces.IMealDayNoteRepository;
import DEL.Interfaces.IMealDay;
import DEL.Interfaces.IMealDayNote;
import DEL.MealDayNote;
import java.io.Serializable;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class MealDayNoteRepository implements IMealDayNoteRepository 
{   
    
    @Autowired
    private SessionFactory sessionFactory;  
    
    private final String MEALDAY_CACHE = "MealDayNoteCache";
    
    @Transactional
    public IMealDayNote addMealDayNote(IMealDay mealday, String note) 
    {
        IMealDayNote mealDayNote = new MealDayNote();
        
        Serializable id = sessionFactory.getCurrentSession().save(mealDayNote);
        return (IMealDayNote) sessionFactory.getCurrentSession().get(MealDayNote.class, id);
    }
    
    @Transactional
    public void remove(IMealDayNote mealDayNote) 
    {        
        sessionFactory.getCurrentSession().delete(mealDayNote);
    }
    
    @Transactional
    public IMealDayNote getMealDayNote(long id)
    {
       IMealDayNote mdn = (IMealDayNote) sessionFactory.getCurrentSession().get(MealDayNote.class, id);   
       
       return mdn;
    }
}

