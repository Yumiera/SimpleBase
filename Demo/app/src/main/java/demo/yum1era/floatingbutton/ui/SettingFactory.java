package demo.yum1era.floatingbutton.ui;
import android.view.View;
import demo.yum1era.floatingbutton.setting.Setting;
import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Switch;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AdapterView;
import android.widget.Adapter;
import android.widget.EditText;
import android.text.TextWatcher;
import android.text.Editable;
import android.view.inputmethod.InputMethodManager;

public class SettingFactory {
    public static View createViewForSetting(final Context context, final Setting setting, final Runnable onValueChanged) {
		LinearLayout layout = new LinearLayout(context);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setPadding(4, 4, 4, 4);
        
        //control the module's state
        /*
        Switch stateSwitch = new Switch(context);
        stateSwitch.setChecked(setting.getParentModule().isEnabled());
        stateSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
            {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    setting.getParentModule().setEnabled(isChecked);
                    onValueChanged.run();

                }
            });

        layout.addView(stateSwitch);
        */

		TextView textView = new TextView(context);
		textView.setText(setting.getName());
		layout.addView(textView);

		if (setting.isCheckSetting()) {
			Switch aSwitch = new Switch(context);
			aSwitch.setChecked(setting.isEnabled());
			aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
                {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        setting.setEnabled(isChecked);
                        onValueChanged.run();

                    
                    }
                });

            layout.addView(aSwitch);


		} else if (setting.isSliderSetting()) {
            final SeekBar seekBar =  new SeekBar(context);

            final TextView valueDisplay = new TextView(context);

            double min = setting.getMin();
            double max = setting.getMax();
            double value = setting.getValue();

            int progress = (int)((value - min) / (max - value) * 100);
            seekBar.setMax(100);
            seekBar.setProgress(progress);

            valueDisplay.setText(String.valueOf(value));
            layout.addView(valueDisplay);

            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

                    @Override 
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        double newValue = setting.getMin() + (setting.getMax() - setting.getMin()) * progress / 100;
                        if (setting.isIntOnly()) {
                            newValue = (int) newValue;

                        }

                        setting.setValue(newValue);
                        valueDisplay.setText(String.valueOf(newValue));
                        onValueChanged.run();
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {}

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {}



                });

            layout.addView(seekBar);
            
            
        }else if(setting.isModeSetting())
        {
            Spinner spinner = new Spinner(context);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item,setting.getOptions());
            adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
            spinner.setAdapter(adapter);
            
            String currentMode = setting.getMode();
            for(int i = 0;i < setting.getOptions().size();i++)
            {
                if(setting.getOptions().get(i).equals(currentMode))
                {
                    spinner.setSelection(i);
                    break;
                }
            }
            
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                @Override
                public void onItemSelected(AdapterView<?> parent,View view,int position,long id)
                {
                    String selected = parent.getItemAtPosition(position).toString();
                    setting.setMode(selected);
                    onValueChanged.run();
                    
                }
                
                @Override
                public void onNothingSelected(AdapterView<?> parent){}
                
                
                
            });
                
                layout.addView(spinner);
            
        }else if(setting.isTextSetting())
        {
            final EditText editText = new EditText(context);
            editText.setText(setting.getTextValue());

   
            editText.setSingleLine(true); 
            editText.setFocusable(true);
            editText.requestFocus(); 

            editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        setting.setText(s.toString());
                        onValueChanged.run();
                    }

                    @Override
                    public void afterTextChanged(Editable s) {}
            });
            
            layout.addView(editText);
            
            editText.post(new Runnable() {
                    @Override
                    public void run() {
                        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                        if (imm != null) {
                            imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
                        }
                    }
                });
        }

        return layout;

	}


}
