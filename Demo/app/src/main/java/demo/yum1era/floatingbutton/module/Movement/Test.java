package demo.yum1era.floatingbutton.module.Movement;
import demo.yum1era.floatingbutton.module.Module;
import demo.yum1era.floatingbutton.module.Category;
import demo.yum1era.floatingbutton.setting.Setting;

public class Test extends Module{
    
    
    Setting test =  new Setting("test",this,false);
    
    public Test()
    {
        super("Test",Category.Movement);
        addSetting(test);
    }
}
