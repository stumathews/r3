package BSL.Interfaces;

import DEL.DailyAmounts;
import DEL.MacroUnitProfile;

/**
 *
 * @author Stuart Mathews
 */
public interface ISettingsService 
{
    public void saveSettings(MacroUnitProfile settings);
    public MacroUnitProfile getSettings();
    
    public void saveDailyAmounts( DailyAmounts dailyAmounts);
    public DailyAmounts getDailyAmounts();
}
