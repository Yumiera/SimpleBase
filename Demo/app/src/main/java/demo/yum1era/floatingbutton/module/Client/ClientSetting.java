package demo.yum1era.floatingbutton.module.Client;
import demo.yum1era.floatingbutton.module.Module;
import demo.yum1era.floatingbutton.module.Category;
import demo.yum1era.floatingbutton.setting.Setting;


public class ClientSetting extends Module {

    Setting test =  new Setting("test",this,false);
    //Setting test2 =  new Setting("test2",this,"test");
    Setting test3 =  new Setting("test3",this,false);
    Setting test4 =  new Setting("test4",this,false);
    Setting test5 =  new Setting("test5",this,false);
    Setting test6 =  new Setting("test6",this,false);
    Setting test7 =  new Setting("test7",this,false);
    Setting test8 =  new Setting("test8",this,false);

    public ClientSetting() {
        super("ClientSetting", Category.Client);
        this.addSetting(test,test3,test,test5,test6,test7,test8);
    }

}
