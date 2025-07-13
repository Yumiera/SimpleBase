package demo.yum1era.floatingbutton.module.Combat;
import demo.yum1era.floatingbutton.module.Module;
import demo.yum1era.floatingbutton.module.Category;
import demo.yum1era.floatingbutton.setting.Setting;

public class KillAura extends Module{
    Setting range = new Setting("range",this,2.0,1.0,8.0,false);
    Setting test =  new Setting("test",this,false);
    
    public KillAura()
    {
        super("KillAura",Category.Combat);
        this.addSetting(range,test);
    }
    
}
