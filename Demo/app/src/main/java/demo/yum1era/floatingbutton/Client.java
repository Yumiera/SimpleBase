package demo.yum1era.floatingbutton;
import demo.yum1era.floatingbutton.setting.SettingManager;
import demo.yum1era.floatingbutton.module.ModuleManager;

public class Client {
	public static Client INSTANCE;
	public static SettingManager settingManager;
	public static ModuleManager moduleManager;
	
	public Client()
	{
		this.init();
		
	}
    
    public void init()
    {
        this.settingManager =  new SettingManager();
		this.moduleManager =  new ModuleManager();
    }
    
}
