package demo.yum1era.floatingbutton;

import android.view.WindowManager;
import android.view.View;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.view.Gravity;
import android.content.Intent;
import android.provider.Settings;
import android.view.MotionEvent;
import demo.yum1era.floatingbutton.ui.ClickGUI;


public class FloatingWindow {

    private static WindowManager wmManager;
    private static WindowManager.LayoutParams wmParams;
    private static View floatingBall;
    private static View floatingWindow;
    private static boolean floatingBallVisible = false;
    private static boolean floatingBallxs = false;

   
    public static void createFloatingWindow(Context context, int floatBallID) {

       
        if (floatingBallxs == false) {

        } else {
            return;
        }

        
        wmManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        wmParams = new WindowManager.LayoutParams();

        
        if (Build.VERSION.SDK_INT >= 26) {
            // Android 8.0 (Oreo)+
            wmParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            //TYPE_SYSTEM_ALERT
            wmParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        }

        wmParams.format = PixelFormat.RGBA_8888; // RGBA_8888 透明
        wmParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE; 
        wmParams.gravity = Gravity.LEFT | Gravity.TOP; 
        wmParams.x = wmManager.getDefaultDisplay().getWidth() / 6; 
        wmParams.y = wmManager.getDefaultDisplay().getHeight() / 5; 
        wmParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        wmParams.height = WindowManager.LayoutParams.WRAP_CONTENT;

        
        floatingBall = View.inflate(context, floatBallID, null); 
        floatingWindow = ClickGUI.buildGUI(context); 

        floatingBall.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					wmManager.removeView(floatingBall);  
					wmManager.addView(floatingWindow, wmParams); 
					floatingBallVisible = false;  
				}
			});

        
        floatingBall.setOnTouchListener(new View.OnTouchListener() {
				float firstX, firstY;
				int wmX, wmY;

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					switch (event.getAction()) {
						case MotionEvent.ACTION_DOWN:
							firstX = event.getRawX();
							firstY = event.getRawY();
							wmX = wmParams.x;
							wmY = wmParams.y;
							break;
						case MotionEvent.ACTION_MOVE:
							wmParams.x = wmX + (int) (event.getRawX() - firstX);
							wmParams.y = wmY + (int) (event.getRawY() - firstY);
							wmManager.updateViewLayout(floatingBall, wmParams);  
							break;
					}
					return false;
				}
			});

        
        floatingWindow.setOnTouchListener(new View.OnTouchListener() {
				float firstX, firstY;
				int wmX, wmY;

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					switch (event.getAction()) {
						case MotionEvent.ACTION_DOWN:
							firstX = event.getRawX();
							firstY = event.getRawY();
							wmX = wmParams.x;
							wmY = wmParams.y;
							break;
						case MotionEvent.ACTION_MOVE:
							wmParams.x = wmX + (int) (event.getRawX() - firstX);
							wmParams.y = wmY + (int) (event.getRawY() - firstY);
							wmManager.updateViewLayout(floatingWindow, wmParams); 
							break;
					}
					return false;
				}
			});

        
        wmManager.addView(floatingBall, wmParams);
        floatingBallxs = true; 
    }
    
    public static void hide()
    {
        wmManager.removeView(floatingWindow); 
        wmManager.addView(floatingBall, wmParams); 
        floatingBallVisible = true;  
    }
    
}
