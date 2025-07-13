package demo.yum1era.floatingbutton.ui;

import android.graphics.Color;
import android.view.View;
import android.content.Context;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import demo.yum1era.floatingbutton.module.Category;
import demo.yum1era.floatingbutton.module.Module;
import demo.yum1era.floatingbutton.Client;
import demo.yum1era.floatingbutton.setting.Setting;
import demo.yum1era.floatingbutton.FloatingWindow;
import demo.yum1era.floatingbutton.ui.SettingFactory;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.FrameLayout;
import demo.yum1era.floatingbutton.util.AndroidLogger;
import android.widget.CheckBox;
import android.widget.CompoundButton;

public class ClickGUI {

    private static Module currentModule;
    private static Category currentCategory;
    
    private static int panelHeight = 650;

    public static View buildGUI(Context context) {
        
        LinearLayout container = new LinearLayout(context);
        container.setOrientation(LinearLayout.VERTICAL); 
        container.setBackgroundColor(Color.parseColor("#FFFFFF"));

        //主容器宽高
        LayoutParams containerParams = new LayoutParams(1600, 800);
        container.setLayoutParams(containerParams);
        
        LinearLayout topBar = new LinearLayout(context);
        topBar.setOrientation(LinearLayout.HORIZONTAL);
        topBar.setBackgroundColor(Color.parseColor("#333333"));

        LayoutParams topBarParams = new LayoutParams(-1, 60);
        topBar.setLayoutParams(topBarParams);

        TextView titleText = new TextView(context);
        titleText.setText("Client Settings");
        titleText.setTextSize(18f);
        titleText.setTextColor(Color.WHITE);
        titleText.setPadding(16, 0, 0, 0);
        titleText.setGravity(android.view.Gravity.CENTER_VERTICAL);

        Button closeButton = new Button(context);
        closeButton.setText("X");
        closeButton.setTextColor(Color.WHITE);
        closeButton.setBackgroundColor(Color.parseColor("#333333"));
        closeButton.setPadding(8, 0, 8, 0);

        //hide
        closeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FloatingWindow.hide();
                }
            });

        topBar.addView(titleText, new LayoutParams(0, -1, 1f));
        topBar.addView(closeButton, new LayoutParams(-2, -1)); 

        //content
        LinearLayout contentPanel = new LinearLayout(context);
        contentPanel.setOrientation(LinearLayout.HORIZONTAL);

        //categoryPanel
        LinearLayout categoryPanelContainer = new LinearLayout(context);
        categoryPanelContainer.setOrientation(LinearLayout.VERTICAL);
        ScrollView categoryScrollView = new ScrollView(context);
        LinearLayout categoryPanel = new LinearLayout(context);
        categoryPanel.setId(View.generateViewId());
        categoryPanel.setOrientation(LinearLayout.VERTICAL);
        categoryPanel.setBackgroundColor(Color.parseColor("#FFFFFF"));
        categoryPanelContainer.addView(categoryScrollView);
        categoryScrollView.addView(categoryPanel);
        contentPanel.addView(categoryPanelContainer, new LayoutParams(400, panelHeight));

        //modulePanel
        LinearLayout modulePanelContainer = new LinearLayout(context);
        modulePanelContainer.setOrientation(LinearLayout.VERTICAL);
        modulePanelContainer.setBackgroundColor(Color.WHITE); 
        ScrollView moduleScrollView = new ScrollView(context);
        LinearLayout modulePanel = new LinearLayout(context);
        modulePanel.setId(View.generateViewId());
        modulePanel.setOrientation(LinearLayout.VERTICAL);
        modulePanel.setBackgroundColor(Color.parseColor("#FFFFFF"));
        modulePanelContainer.addView(moduleScrollView);
        moduleScrollView.addView(modulePanel);
        contentPanel.addView(modulePanelContainer, new LayoutParams(450, panelHeight));
        
        //settingPanel
        LinearLayout settingPanelContainer = new LinearLayout(context);
        settingPanelContainer.setOrientation(LinearLayout.VERTICAL);
        ScrollView settingScrollView = new ScrollView(context);
        LinearLayout settingPanel = new LinearLayout(context);
        settingPanel.setId(View.generateViewId());
        settingPanel.setOrientation(LinearLayout.VERTICAL);
        settingPanel.setBackgroundColor(Color.parseColor("#F0F0F0"));
        settingPanelContainer.addView(settingScrollView);
        settingScrollView.addView(settingPanel);
        contentPanel.addView(settingPanelContainer, new LayoutParams(750, panelHeight));

        //setup
        setupCategoryPanel(context, categoryPanel, modulePanel, settingPanel);

        container.addView(topBar, new LayoutParams(-1, -2)); 
        container.addView(contentPanel, new LayoutParams(-1, 0, 1f));

        return container;
    }

    public static void setupCategoryPanel(final Context context,
                                         LinearLayout categoryPanel,
                                         final LinearLayout modulePanel,
                                         final LinearLayout settingPanel) {
        for (final Category category : Category.values()) {
            Button btn = new Button(context);
            btn.setText(category.name());
            btn.setPadding(8, 8, 8, 8);
            btn.setTextColor(Color.BLACK);
            btn.setBackgroundColor(Color.parseColor("#FFFFFF"));
            btn.setTransformationMethod(null);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    currentCategory = category;
                    updateModulePanel(context, modulePanel, settingPanel);
                }
            });

            categoryPanel.addView(btn);
        }

        //default category
        currentCategory = Category.Client;
        updateModulePanel(context, modulePanel, settingPanel);
    }

    private static void updateModulePanel(final Context context,
                                          final LinearLayout modulePanel,
                                          final LinearLayout settingPanel) {
        modulePanel.removeAllViews();
        settingPanel.removeAllViews();

        for (final Module module : Client.moduleManager.getModulesByCategory(currentCategory)) {

            LinearLayout container = new LinearLayout(context);
            container.setOrientation(LinearLayout.HORIZONTAL);
            container.setLayoutParams(new LinearLayout.LayoutParams(
                                          400,
                                          LinearLayout.LayoutParams.WRAP_CONTENT
                                      ));

            container.setWeightSum(1f);
            container.setClipToPadding(true);
            container.setClipChildren(true);
            
            Button btn = new Button(context);
            btn.setText(module.getName());
            btn.setPadding(4, 4, 4, 4);
            btn.setTextColor(Color.BLACK);
            btn.setBackgroundColor(Color.parseColor("#FFFFFF"));
            btn.setTransformationMethod(null);
            
            btn.setLayoutParams(new LinearLayout.LayoutParams(0,
                                                              LinearLayout.LayoutParams.WRAP_CONTENT, 1f));

            CheckBox checkBox = new CheckBox(context);
            checkBox.setChecked(module.isEnabled()); 
            checkBox.setBackgroundColor(Color.WHITE);
            checkBox.setPadding(8, 8, 8, 8);
            
            

            checkBox.setLayoutParams(new LinearLayout.LayoutParams(
                                         LinearLayout.LayoutParams.WRAP_CONTENT,
                                         LinearLayout.LayoutParams.WRAP_CONTENT));

            btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        currentModule = module;
                        updateSettingPanel(context, settingPanel);
                    }
                });
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
                
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
                {
                    module.setEnabled(isChecked); 
                }
            });

            
            container.addView(btn, new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));
            container.addView(checkBox);

            modulePanel.addView(container);
        }

        if (!Client.moduleManager.getModulesByCategory(currentCategory).isEmpty()) {
            currentModule = Client.moduleManager.getModulesByCategory(currentCategory).get(0);
            updateSettingPanel(context, settingPanel);
        }
    }

    private static void updateSettingPanel(final Context context, final LinearLayout settingPanel) {
        settingPanel.removeAllViews();

        for (final Setting setting : Client.settingManager.getSettingsByModule(currentModule)) {
            View settingView = SettingFactory.createViewForSetting(context, setting, new Runnable() {
                @Override
                public void run() {
                    AndroidLogger.i(setting.getName() + " changed to: " + getCurrentValueAsString(setting));
                }
            });
            settingPanel.addView(settingView);
        }
    }

    private static String getCurrentValueAsString(Setting setting) {
        if (setting.isCheckSetting()) {
            return String.valueOf(setting.isEnabled());
        } else if (setting.isSliderSetting()) {
            return String.valueOf(setting.getValue());
        } else if (setting.isModeSetting()) {
            return setting.getMode();
        } else if (setting.isTextSetting()) {
            return setting.getTextValue();
        }
        return "Unknown";
    }

}
