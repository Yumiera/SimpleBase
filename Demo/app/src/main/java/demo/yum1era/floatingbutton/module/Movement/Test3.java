package demo.yum1era.floatingbutton.module.Movement;
import demo.yum1era.floatingbutton.module.Category;
import demo.yum1era.floatingbutton.module.Module;
import demo.yum1era.floatingbutton.setting.Setting;

public class Test3 extends Module{

    Setting test =  new Setting("test",this,"normal",new String[]{"normal","packet","packet2","packet3","packet4","packet5","packet6"});
    public Test3()
    {
        super("Test3",Category.Movement);
        addSetting(test);
    }

}
