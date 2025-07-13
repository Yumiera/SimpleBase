package demo.yum1era.floatingbutton;
 
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.ScrollView;
import demo.yum1era.floatingbutton.util.AndroidLogger;
import android.widget.Button;

public class MainActivity extends Activity {
    
    private TextView logTextView;
    private ScrollView scrollView;
    private Button start;
    private Client client;
    
     
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Requestpermission.requestFloatingWindow(this);
        
        logTextView = findViewById(R.id.logTextView);
        scrollView = findViewById(R.id.logScrollView);
        start = findViewById(R.id.openFloatingBall);
        
        AndroidLogger.setOnLogListener(new AndroidLogger.OnLogListener() {
                @Override
                public void onNewLog(String level, String message) {
                    addLog(level + ": " + message);
                }
            });

        // 启用调试日志
        AndroidLogger.setDebug(true);

        // 示例日志输出
        //AndroidLogger.d("应用启动");
        //AndroidLogger.i("这是信息日志");
        //AndroidLogger.w("这是警告日志");

        start.setOnClickListener(new android.view.View.OnClickListener() {
                @Override
                public void onClick(android.view.View v) {
                    client =  new Client();
                    FloatingWindow.createFloatingWindow(MainActivity.this,R.layout.floating_ball);
                    AndroidLogger.d("启动UI");
                }
            });
        
    }
    
    private void addLog(final String message) {
        runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    logTextView.append("\n" + message);
                    scrollView.post(new Runnable() {
                            @Override
                            public void run() {
                                scrollView.fullScroll(ScrollView.FOCUS_DOWN);
                            }
                        });
                }
            });
    }
	
}
