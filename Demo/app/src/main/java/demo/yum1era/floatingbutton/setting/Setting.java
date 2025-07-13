package demo.yum1era.floatingbutton.setting;
import demo.yum1era.floatingbutton.module.Module;
import java.util.ArrayList;

public class Setting {
    private final String name;
	private final String mode;

	private String sval;
	private ArrayList<String> options;

	private boolean bval;
	private double dval;

	private double min;
	private double max;
	private boolean onlyInt = false;
	
	private String text;
	public Module parent = null;

	public Setting(String name, Module parent, String sval, String[] options) {
		this.name = name;
		this.parent = parent;
		this.sval = sval;
		ArrayList<String> options_ = new ArrayList<String>();

		for (String mode : options) {

			options_.add(mode);
			}

		this.options = options_;
		this.mode = "Combo";
		}

	public Setting(String name,Module parent,boolean bval)
	{
		this.name = name;
		this.parent = parent;
		this.bval = bval;
		this.mode = "Check";
	}
	
	public Setting(String name,Module parent,double dval,double min,double max,boolean onlyInt){
		
		this.name=name;
		this.parent = parent;
		this.dval = dval;
		this.min = min;
		this.max = max;
		this.onlyInt = onlyInt;
		this.mode = "Slider";
	}
	
	public Setting(String name,Module parent,String text){
		
		this.name = name;
		this.parent = parent;
		this.text = text;
		this.mode = "Text";
	}
	
	
	public String getName()
	{
		return name;
	}
	
	public Module getParentModule()
	{
		return parent;
	}
	
	public String getMode()
	{
		return sval;
	}
	
	
	public boolean isMode(String mode)
	{
		if(this.sval.equals(mode)){
		                            
			return true;
		}
		return false;
	}
	
	public void setMode(String mode)
	{
		this.sval = mode;
		//
	}

	public ArrayList<String> getOptions()
	{
		return this.options;
	}
	
	
	public boolean isEnabled(){
		
		return this.bval;
	}
	
	public void setEnabled(boolean enabled)
	{
		this.bval = enabled;
	}
	
	public String getTextValue()
	{
		return this.text;
	}
	
	public void setText(String text)
	{
		this.text = text;
	}
    
    public boolean isIntOnly()
    {
        
        return this.onlyInt;
    }
	
	
	public double getValue()
	{
		if(this.onlyInt)
		{
			this.dval = (int)dval;
			return dval;
		}
		return dval;
	}
	
	public void setValue(double value)
	{
		this.dval = value;
	}
	
	public double getMin()
	{
		return min;
	}
	
	public double getMax()
	{
		
		return max;
	}
	
	public boolean isModeSetting()
	{
		return this.mode.equalsIgnoreCase("Combo");
	}
	
	public boolean isCheckSetting()
	{
		return this.mode.equalsIgnoreCase("Check");
	}
	
	public boolean isSliderSetting()
	{
		return this.mode.equalsIgnoreCase("Slider");
	}
	
	public boolean isTextSetting()
	{
		return this.mode.equalsIgnoreCase("Text");
	}
	}
