package demo.yum1era.floatingbutton.module;

import java.util.ArrayList;
import demo.yum1era.floatingbutton.module.Module;
import demo.yum1era.floatingbutton.module.Movement.DemoModule;
import demo.yum1era.floatingbutton.module.Combat.KillAura;
import demo.yum1era.floatingbutton.module.Player.NoFall;
import demo.yum1era.floatingbutton.module.Client.ClientSetting;
import demo.yum1era.floatingbutton.module.Movement.Test2;
import demo.yum1era.floatingbutton.module.Movement.Test3;
import demo.yum1era.floatingbutton.module.Movement.Test4;
import demo.yum1era.floatingbutton.module.Movement.Test5;

public class ModuleManager {
    public static ArrayList<Module> moduleList;
	public static int moduleSize;

	public ModuleManager() {
		registerModules();

	}

	public void registerModules() {
		moduleList = new ArrayList<Module>();

		addModule(new DemoModule());
        addModule(new KillAura());
        addModule(new NoFall());
        addModule(new ClientSetting());
        addModule(new Test2());
        addModule(new Test3());
        addModule(new Test4());
        addModule(new Test5());

		moduleSize = moduleList.size();
	}

	public void addModule(Module module) {
		moduleList.add(module);
	}

	public ArrayList<Module> getAllModules() {
		return moduleList;
	}

	public ArrayList<Module> getEnabledModules() {
		ArrayList<Module> enabledModules = new ArrayList<Module>();
		for (Module module:getAllModules()) {
			if (module.isEnabled()) {
				enabledModules.add(module);
			}
		}

		if (enabledModules.isEmpty()) {
			return null;
		}

		return enabledModules;
	}

	public Module getModuleByName(String name) {
		for (Module module:getAllModules()) {
			if (module.getName().equals(name))
				return module;
		}
		return null;
	}
	
	public ArrayList<Module> getModulesByCategory(Category category)
	{
		ArrayList<Module> modules = new ArrayList<Module>();
		
		for(Module module:getAllModules())
		{
			if(module.getCategory() == category)
			{
				modules.add(module);
			}
		}
		
		if(modules.isEmpty())
		{
			return null;
		}
		
		return modules;
	}

}
