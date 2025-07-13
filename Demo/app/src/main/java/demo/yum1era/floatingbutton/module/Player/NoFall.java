package demo.yum1era.floatingbutton.module.Player;
import demo.yum1era.floatingbutton.module.Module;
import demo.yum1era.floatingbutton.module.Category;
import demo.yum1era.floatingbutton.setting.Setting;

public class NoFall extends Module{
    Setting mode = new Setting("Mode",this,"Test1",new String[]{"Test1","Test2"});
    
    public NoFall()
    {
        super("NoFall",Category.Player);
        this.addSetting(mode);
    }
    
}
