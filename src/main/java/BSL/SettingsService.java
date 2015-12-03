package BSL;

import BSL.Interfaces.ISettingsService;
import DAL.Interfaces.ISettingsRepository;
import DEL.DailyAmounts;
import DEL.MacroUnitProfile;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Stuart Mathews
 */
public class SettingsService implements ISettingsService 
{
    @Autowired 
    ISettingsRepository settingsRepository;
    
    public void saveSettings(MacroUnitProfile macroUnitProfile) 
    {
        settingsRepository.saveSettings(macroUnitProfile);
    }

    public MacroUnitProfile getSettings() 
    {
        return settingsRepository.getSettings();
    }

    public void saveDailyAmounts(DailyAmounts dailyAmounts) 
    {
        settingsRepository.saveDailyAmounts(dailyAmounts);
    }

    public DailyAmounts getDailyAmounts() 
    {
        return settingsRepository.getDailyAmounts();
    }
    
}
