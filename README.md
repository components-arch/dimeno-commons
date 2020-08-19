# dimeno-commons
公共类库  

[![](https://jitpack.io/v/dimeno-tech/dimeno-commons.svg)](https://jitpack.io/#dimeno-tech/dimeno-commons)
> implementation 'com.github.dimeno-tech:dimeno-commons:0.0.1'

#### @DoubleClick防连击注解
连击判定时长可自定义，默认为500ms

**使用方式**

1. 项目根目录添加依赖

	``` java
	buildscript {
	    repositories {
	        google()
	        jcenter()
	    }
	    dependencies {
	        classpath "com.android.tools.build:gradle:4.0.1"
	        classpath 'com.hujiang.aspectjx:gradle-android-plugin-aspectjx:2.0.10'
	    }
	}
	```
2. 应用插件

	``` java
	apply plugin: 'android-aspectjx'
	```

3. 添加注解

	``` java
	findViewById(R.id.btn_double_click).setOnClickListener(new View.OnClickListener() {
	    @DoubleClick
	    @Override
	    public void onClick(View v) {
	        Log.e("TAG", "-> click");
	    }
	});
	```

#### 应用界面
AppUtils
> 使用前请先初始化：AppUtils.install(Context)

``` java
public class AppUtils {
    public static void install(Application application);
    public static Application getApplication();
    public static Context getContext();
    public static int dip2px(float dip);
    public static float px2dip(int px);
    public static int sp2px(float spVal);
    public static float px2sp(float pxVal);
    public static int getScreenWidthPixels();
    public static int getScreenHeightPixels();
    public static Handler getHandler();
    public static Resources getResources();
    public static int getColor(int resId);
    public static Drawable getDrawable(int resId);
    public static String getString(int resId);
    public static String[] getStringArray(int resId);
    public static int getDimens(int resId);
    public static boolean isOnMainThread();
    public static void showSoftInput(View view);
    public static boolean hideSoftInput(View view);
    public static void setViewEnabled(View view, boolean enabled);
    public static int getStatusBarHeight();
    public static String getAppName();
    public static String getVersionName();
    public static int getVersionCode();
    public static Activity getRealActivity(Context ctx);
    public static String getRealFilePath(Uri uri);
}
```


#### 加密
EncryptionUtil

``` java
public class EncryptionUtil {
    public static String getMD5(String s);
    public static String getMD5Small(String s);
    public static String getSHA256(String s);
    private static String byte2Hex(byte[] bytes);
    public static String getValueEncoded(String value);
    public static String AesDecrypt(String src);
}
```
Base64Util

``` java
public class Base64Util {
    public static String imageToBase64(String path, int quality);
    public static String imageToBase64(Bitmap bitmap, int quality);
    public static String fileToBase64(String path);
}
```

#### IO管理
IOUtils

``` java
public class IOUtils {
    public static void close(Closeable... closeables);
}
```

#### 数据解析
JsonUtils

``` java
public class JsonUtils {
    public static <T> T parseObject(String jsonString, Class<T> cls);
    public static <T> T parseObject(String jsonString, Type typeOfT);
    public static <T> T parseObject(JsonElement json, Class<T> cls);
    public static <T> T parseObject(String jsonString, Class<T> cls, Class<?>... generics);
    public static <T> List<T> parseArray(String jsonString, Class<T> generic);
    public static String toJsonString(Object object);
}
```

#### 网络
NetUtils

``` java
public class NetUtils {
    public static boolean isAvailable();
    public static boolean isMobile();
    public static boolean isWifi();
    private static NetworkInfo getNetworkInfo();
    public static String getHostIP();
}
```

#### 集合操作
IList

``` java
public class IList {
    public static <T> boolean isNotEmpty(List<T> list);
    public static <T> boolean isEmpty(List<T> list);
}
```

#### 数据传递
DataTransfer

``` java
public class DataTransfer {
    public static DataTransfer get();
    public void put(@Nullable String key, @Nullable Object value);
    public Object getData(@Nullable String key);
    public Reference<?> remove(@Nullable String key);
    public void removeAll();
}
```

















