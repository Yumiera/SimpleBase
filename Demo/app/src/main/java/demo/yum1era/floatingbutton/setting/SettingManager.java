package demo.yum1era.floatingbutton.setting;


import demo.yum1era.floatingbutton.setting.Setting;
import java.util.ArrayList;
import demo.yum1era.floatingbutton.module.Module;

public class SettingManager {
    private final ArrayList<Setting> settings;
	
	public SettingManager()
	{
		this.settings =  new ArrayList<Setting>();
	}
    
    public void addSetting(Setting setting)
	{
		this.settings.add(setting);
	}
	
	public ArrayList<Setting> getSettings()
	{
		return this.settings;
	}
	
	public ArrayList<Setting> getSettingsByModule(Module module)
	{
		ArrayList<Setting> settings_ = new ArrayList<Setting>();
		
		for(Setting setting : getSettings())
		{
			if(setting.getParentModule() == module)
			{
				settings_.add(setting);
			}
		}
		
		if(settings.isEmpty())
		{
			return null;
		}
		
		return settings_;
	}
	
	public Setting getSettingByName(Module module,String name)
	{
		for(Setting setting : getSettings())
		{
			if(setting.getParentModule() == module && setting.getName().equals(name))
			{
				return setting;
			}
		}
		return null;
	}
	
	
}
