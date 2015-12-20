package DAL.Interfaces;

import DEL.DailyAmounts;
import DEL.MacroUnitProfile;

/**
 *
 * @author Stuart Mathews
 */
public interface ISettingsRepository 
{
    public void saveSettings(MacroUnitProfile settings);
    public MacroUnitProfile getSettings();

    DailyAmounts getDailyAmounts();

    void saveDailyAmounts(DailyAmounts dailyAmounts);
}
