# dimeno-commons

> 公共类库

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
3.版本依赖

	[![](https://jitpack.io/v/dimeno-tech/dimeno-commons.svg)](https://jitpack.io/#dimeno-tech/dimeno-commons)

	``` java
	implementation 'com.github.dimeno-tech:dimeno-commons:0.0.1'
	```
4. 添加注解

	``` java
	findViewById(R.id.btn_double_click).setOnClickListener(new View.OnClickListener() {
	    @DoubleClick
	    @Override
	    public void onClick(View v) {
	        Log.e("TAG", "-> click");
	    }
	});
	```

xxx

