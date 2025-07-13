package demo.yum1era.floatingbutton.module;

import demo.yum1era.floatingbutton.module.Category;
import java.util.ArrayList;
import demo.yum1era.floatingbutton.setting.Setting;
import demo.yum1era.floatingbutton.Client;

public abstract class Module {
    public String name;
	public Category category;
	public boolean enabled = false;
	public boolean isWhite = false;
	
	public ArrayList<Setting> settings = new ArrayList<Setting>();
	
	public Module(String name,Category category)
	{
		this.name = name;
		this.category = category;
	}
    
	public void setWhite(boolean isWhite)
	{
		this.isWhite = isWhite;
	}
	
	//开启事件
	public void onEnable()
	{
		
	}
	
	//关闭事件
	public void onDisable()
	{
		
	}
	
	public void setEnabled(boolean enabled)
	{
		this.enabled = enabled;
		if(enabled == true)
		{
			this.onEnable();
		}else{
			this.onDisable();
		}
	}
	
	public void setEnabledWithoutEvent(boolean enabled)
	{
		this.enabled = enabled;
	}
	
	public void toggle()
	{
		setEnabled(!this.enabled);
	}
	
	public void toggleWithoutEvent()
	{
		setEnabledWithoutEvent(!this.enabled);
	}
	
	public boolean isEnabled()
	{
		return this.enabled;
	}
	
	public void addSetting(Setting... settings)
	{
		for(Setting setting:settings)
		{
			if(setting.getParentModule() == null)
			{
				setting.parent = this;
			}
			
			Client.settingManager.addSetting(setting);
		}
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public Category getCategory()
	{
		return this.category;
	}
	
}
