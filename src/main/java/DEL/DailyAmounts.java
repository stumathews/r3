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

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * A settng that holds daily amounts of things
 * @author Stuart
 */
public class DailyAmounts 
{
    @NotNull
    public long id;
    @Min(1)
    public int maxCarbUnitsPerDay;
    @Min(1)
    public int maxProteinUnitsPerDay;
    @Min(1)
    public int maxFatUnitsPerDay;
    
    public DailyAmounts( long id, int maxCarbUnitsPerDay, int maxProteinUnitsPerDay, int maxFatUnitsPerDay) 
    {
        this.id = id;
        this.maxCarbUnitsPerDay = maxCarbUnitsPerDay;
        this.maxProteinUnitsPerDay = maxProteinUnitsPerDay;
        this.maxFatUnitsPerDay = maxFatUnitsPerDay;
    }

    public DailyAmounts() 
    {
    }
    
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public int getMaxCarbUnitsPerDay() {
        return maxCarbUnitsPerDay;
    }

    public void setMaxCarbUnitsPerDay(int maxCarbUnitsPerDay) {
        this.maxCarbUnitsPerDay = maxCarbUnitsPerDay;
    }

    public int getMaxProteinUnitsPerDay() {
        return maxProteinUnitsPerDay;
    }

    public void setMaxProteinUnitsPerDay(int maxProteinUnitsPerDay) {
        this.maxProteinUnitsPerDay = maxProteinUnitsPerDay;
    }

    public int getMaxFatUnitsPerDay() {
        return maxFatUnitsPerDay;
    }

    public void setMaxFatUnitsPerDay(int maxFatUnitsPerDay) {
        this.maxFatUnitsPerDay = maxFatUnitsPerDay;
    }    
}
