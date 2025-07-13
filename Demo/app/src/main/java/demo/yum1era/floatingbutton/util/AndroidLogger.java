package demo.yum1era.floatingbutton.util;
import android.util.Log;
import java.io.StringWriter;
import java.io.PrintWriter;
import org.json.JSONObject;
import android.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONException;

/*
 @Author: 7M0v0
 */

public class AndroidLogger {
    private static final String DEFAULT_TAG = "7MengLog";
    private static boolean isDebug = true;

    public static void setDebug(boolean debug) {
        isDebug = debug;
    }

    public static boolean isDebug() {
        return isDebug;
    }
    
    // 日志监听器接口
    public interface OnLogListener {
        void onNewLog(String level, String message);
    }
    
    private static OnLogListener logListener;
    
    public static void setOnLogListener(OnLogListener listener) {
        logListener = listener;
    }

    //获取调用者的信息（类名、方法名、行号）
    private static StackTraceElement getTargetStackTrace() {
        StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
        for (int i = 0;i < stacks.length;i++) {

            StackTraceElement stack = stacks[i];
            String className = stack.getClassName();
            if (!className.equals(AndroidLogger.class.getName())) {
                return stacks[i];
            }
        }
        return stacks[3]; //fallback
    }

    //提取简化类名
    private static String getSimpleClassName(String className) {
        int dot = className.lastIndexOf('.');
        return dot > 0 ? className.substring(dot + 1) : className;

    }

    private static void log(int level, String tag, String msg) {
        if (!isDebug) return;

        StackTraceElement target = getTargetStackTrace();

        String fullTag = tag == null ? DEFAULT_TAG : tag;
        String logMessage = String.format("[%s] %s", getSimpleClassName(target.getClassName()), msg);

        switch (level) {
            case Log.VERBOSE:
                Log.v(fullTag, logMessage);
                break;
            case Log.DEBUG:
                Log.d(fullTag, logMessage);
                break;
            case Log.INFO:
                Log.i(fullTag, logMessage);
                break;
            case Log.WARN:
                Log.w(fullTag, logMessage);
                break;
            case Log.ERROR:
                Log.e(fullTag, logMessage);
                break;
        }
        
        if (logListener != null) {
            String levelStr = "";
            switch (level) {
                case Log.VERBOSE: levelStr = "V"; break;
                case Log.DEBUG:   levelStr = "D"; break;
                case Log.INFO:    levelStr = "I"; break;
                case Log.WARN:    levelStr = "W"; break;
                case Log.ERROR:   levelStr = "E"; break;
            }
            logListener.onNewLog(levelStr, msg);
        }
    }

    //打印VERBOSE日志
    public static void v(String msg) 
    {
        log(Log.VERBOSE,null,msg);
    }
    
    public static void v(String tag,String msg)
    {
        log(Log.VERBOSE,tag,msg);
    }
    
    //Debug
    public static void d(String msg) {
        log(Log.DEBUG, null, msg);
    }

    public static void d(String tag, String msg) {
        log(Log.DEBUG, tag, msg);
    }
    
    public static void w(String msg) {
        log(Log.WARN, null, msg);
    }

    public static void w(String tag, String msg) {
        log(Log.WARN, tag, msg);
    }
    
    public static void i(String msg) {
        log(Log.INFO, null, msg);
    }

    public static void i(String tag, String msg) {
        log(Log.INFO, tag, msg);
    }
    
    public static void e(String msg) {
        log(Log.ERROR, null, msg);
    }

    public static void e(String tag, String msg) {
        log(Log.ERROR, tag, msg);
    }
    
    public static void e(Throwable throwable) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        throwable.printStackTrace(pw);
        e("Exception", sw.toString());
    }
    
    public static void json(@Nullable String tag, @Nullable String json) {
        if (!isDebug || json == null) return;

        try {
            if (json.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(json);
                String prettyJson = jsonObject.toString(4);
                printChunk(tag, Log.DEBUG, prettyJson);
            } else if (json.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(json);
                String prettyJson = jsonArray.toString(4);
                printChunk(tag, Log.DEBUG, prettyJson);
            } else {
                d(tag, json);
            }
        } catch (JSONException e) {
            d(tag, json); // 如果不是合法 JSON，直接打印原始字符串
        }
    }
    
    private static void printChunk(String tag, int level, String msg) {
        String[] lines = msg.split("\\r?\\n");
        for (String line : lines) {
            log(level, tag, line);
        }
    }

}
