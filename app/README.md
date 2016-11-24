# Android快速开发框架说明


## 使用的开源框架

### [Butter Knife](https://github.com/JakeWharton/butterknife)
Bind Android views and callbacks to fields and methods. 
结合AndroidStudio的插件**[android-butterknife-zelezny](https://github.com/avast/android-butterknife-zelezny)**

### [Logger](https://github.com/orhanobut/logger)
Simple, pretty and powerful logger for android。
文档参考github说明文档。

### [stetho](https://github.com/facebook/stetho)
使用Facebook的进行网络和数据库的分析。

### [Retrofit](http://square.github.io/retrofit/)
Type-safe HTTP client for Android and Java by Square, Inc.

### [RxJava](https://github.com/ReactiveX/RxJava)
RxJava – Reactive Extensions for the JVM – a library for composing asynchronous and event-based programs using observable sequences for the Java VM.
学习资料：
https://github.com/rengwuxian/RxJavaSamples

### [GreenDAO](http://greenrobot.org/greendao/documentation)
greenDAO is a light & fast ORM solution for Android that maps objects to SQLite databases. 
使用的是GreenDAO 3

### [Glide](https://github.com/bumptech/glide)
An image loading and caching library for Android focused on smooth scrolling


## 工具

### [tinypng](https://tinypng.com/)
png的压缩处理使用

### [leakcanary](https://github.com/square/leakcanary/wiki/FAQ)
A memory leak detection library for Android and Java.

### 使用友盟实现多渠道打包
http://blog.csdn.net/mynameishuangshuai/article/details/51783303

## UI组件库

[Material Design Android Library](https://github.com/navasmdc/MaterialDesignLibrary)
Material风格的组件库

[android-open-project](https://github.com/Trinea/android-open-project)
Collect and classify android open source projects

[Material Design Icons](https://materialdesignicons.com/)
里面有很多现成的图标，来自社区和其他一些app，作为一个美术功底为零的开发者，做自己的项目的时候直接拿里面的icon来用

[android-iconify](https://github.com/JoanZapata/android-iconify)

[Android Material Design Icon Generator Plugin](https://github.com/konifar/android-material-design-icon-generator-plugin)
This plugin help you to set [material design icons](https://github.com/google/material-design-icons) to your Android project.

ASD(Android Support Design)
APL(Android Percent Layout)
DBL(Data Binding Library)
Tab滑页 、广告页 的 PagerSlidingTabStrip 或 ViewPagerIndicator +ViewPager 很常用；
还有查看图片的 PhotoView ；
方便选择时间日期 省市地域的 各种改版 WheelView ；
UI遵循Android设计标准的话， 会用到support包里的 SwipeRefreshLayout, DrawerLayout，新出的 recyclerview cardview 等 ，还有toolbar，design ，第三方的材料设计组件 等等；
给Listview 加动效的listviewanimations ；
提高操作性的左滑后退 SwipeBackLayout ；
多标签流的 flowlayout;
工具类
[AndroidUtilCode](https://github.com/Blankj/AndroidUtilCode)
[android-common](https://github.com/Trinea/android-common)


## 编码规范

[android-best-practices](https://github.com/futurice/android-best-practices/blob/master/translations/Chinese/README.cn.md)

### 资源文件 Resources

- **命名** 遵循前缀表明类型的习惯，形如`type_foo_bar.xml`。例如：`fragment_contact_details.xml`,`view_primary_button.xml`,`activity_main.xml`.

**组织布局文件** 若果你不确定如何排版一个布局文件，遵循一下规则可能会有帮助。

- 每一个属性一行，缩进4个空格
- `android:id` 总是作为第一个属性
- `android:layout_****` 属性在上边
- `style` 属性在底部
- 关闭标签`/>`单独起一行，有助于调整和添加新的属性
- 考虑使用[Designtime attributes 设计时布局属性](http://tools.android.com/tips/layout-designtime-attributes)，Android Studio已经提供支持，而不是硬编码`android:text`
(译者注：墙内也可以参考stormzhang的这篇博客[链接](http://stormzhang.com/devtools/2015/01/11/android-studio-tips1/))。

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
	xmlns:android="http://schemas.android.com/apk/res/android"
	xmlns:tools="http://schemas.android.com/tools"
	android:layout_width="match_parent"
	android:layout_height="match_parent"
	android:orientation="vertical"
	>

	<TextView
		android:id="@+id/name"
		android:layout_width="match_parent"
		android:layout_height="wrap_content"
		android:layout_alignParentRight="true"
		android:text="@string/name"
		style="@style/FancyText"
		/>

	<include layout="@layout/reusable_part" />

</LinearLayout>
```

作为一个经验法则,`android:layout_****`属性应该在 layout XML 中定义,同时其它属性`android:****` 应放在 styler XML中。此规则也有例外，不过大体工作
的很好。这个思想整体是保持layout属性(positioning, margin, sizing) 和content属性在布局文件中，同时将所有的外观细节属性（colors, padding, font）放
在style文件中。


例外有以下这些:

- `android:id` 明显应该在layout文件中
- layout文件中`android:orientation`对于一个`LinearLayout`布局通常更有意义
- `android:text` 由于是定义内容，应该放在layout文件中
- 有时候将`android:layout_width` 和 `android:layout_height`属性放到一个style中作为一个通用的风格中更有意义，但是默认情况下这些应该放到layout文件中。

**使用styles** 几乎每个项目都需要适当的使用style文件，因为对于一个视图来说有一个重复的外观是很常见的。
在应用中对于大多数文本内容，最起码你应该有一个通用的style文件，例如：

```xml
<style name="ContentText">
	<item name="android:textSize">@dimen/font_normal</item>
	<item name="android:textColor">@color/basic_black</item>
</style>
```

应用到TextView 中:

```xml
<TextView
	android:layout_width="wrap_content"
	android:layout_height="wrap_content"
	android:text="@string/price"
	style="@style/ContentText"
	/>
```


你或许需要为按钮控件做同样的事情，不要停止在那里。将一组相关的和重复`android:****`的属性放到一个通用的style中。


**将一个大的style文件分割成多个文件** 你可以有多个`styles.xml` 文件。Android SDK支持其它文件，`styles`这个文件名称并没有作用，起作用的是在文件
里xml的`<style>`标签。因此你可以有多个style文件`styles.xml`,`style_home.xml`,`style_item_details.xml`,`styles_forms.xml`。
不用于资源文件路径需要为系统构建起的有意义，在`res/values`目录下的文件可以任意命名。



**`colors.xml`是一个调色板** 在你的`colors.xml`文件中应该只是映射颜色的名称一个RGBA值，而没有其它的。不要使用它为不同的按钮来定义RGBA值。

*不要这样做*

```xml
<resources>
	<color name="button_foreground">#FFFFFF</color>
	<color name="button_background">#2A91BD</color>
	<color name="comment_background_inactive">#5F5F5F</color>
	<color name="comment_background_active">#939393</color>
	<color name="comment_foreground">#FFFFFF</color>
	<color name="comment_foreground_important">#FF9D2F</color>
	...
	<color name="comment_shadow">#323232</color>
```


使用这种格式，你会非常容易的开始重复定义RGBA值，这使如果需要改变基本色变的很复杂。同时，这些定义是跟一些环境关联起来的，如`button`或者`comment`,
应该放到一个按钮风格中，而不是在`color.xml`文件中。


相反，这样做:

```xml
<resources>

	<!-- grayscale -->
	<color name="white"     >#FFFFFF</color>
	<color name="gray_light">#DBDBDB</color>
	<color name="gray"      >#939393</color>
	<color name="gray_dark" >#5F5F5F</color>
	<color name="black"     >#323232</color>

	<!-- basic colors -->
	<color name="green">#27D34D</color>
	<color name="blue">#2A91BD</color>
	<color name="orange">#FF9D2F</color>
	<color name="red">#FF432F</color>

</resources>
```

向应用设计者那里要这个调色板，名称不需要跟"green", "blue", 等等相同。
"brand_primary", "brand_secondary", "brand_negative" 这样的名字也是完全可以接受的。
像这样规范的颜色很容易修改或重构，会使应用一共使用了多少种不同的颜色变得非常清晰。
通常一个具有审美价值的UI来说，减少使用颜色的种类是非常重要的。


**像对待colors.xml一样对待dimens.xml文件** 与定义颜色调色板一样，你同时也应该定义一个空隙间隔和字体大小的“调色板”。
一个好的例子，如下所示：

```xml
<resources>

	<!-- font sizes -->
	<dimen name="font_larger">22sp</dimen>
	<dimen name="font_large">18sp</dimen>
	<dimen name="font_normal">15sp</dimen>
	<dimen name="font_small">12sp</dimen>

	<!-- typical spacing between two views -->
	<dimen name="spacing_huge">40dp</dimen>
	<dimen name="spacing_large">24dp</dimen>
	<dimen name="spacing_normal">14dp</dimen>
	<dimen name="spacing_small">10dp</dimen>
	<dimen name="spacing_tiny">4dp</dimen>

	<!-- typical sizes of views -->
	<dimen name="button_height_tall">60dp</dimen>
	<dimen name="button_height_normal">40dp</dimen>
	<dimen name="button_height_short">32dp</dimen>

</resources>
```
	
布局时在写 margins 和 paddings 时，你应该使用`spacing_****`尺寸格式来布局，而不是像对待String字符串一样直接写值。
这样写会非常有感觉，会使组织和改变风格或布局是非常容易。

Activity对应的布局文件的命名都是以activity开头，例如activity_main。
ListView对应的item的布局文件都是以item开头，例如activity_main对应的listview的item的布局item_main。


***
### 用tools
```xml
xmlns:tools="http://schemas.android.com/tools"
```
tools可以告诉Android Studio，哪些属性在运行的时候是被忽略的，只在设计布局的时候有效。比如我们要让android:text属性只在布局预览中有效可以这样.
```xml
<TextView
 android:id="@+id/text_main"
 android:layout_width="match_parent"
 android:layout_height="wrap_content"
 android:textAppearance="@style/TextAppearance.Title"
 android:layout_margin="@dimen/main_margin"
 tools:text="I am a title" />
```
tools可以覆盖android的所有标准属性，将android:换成tools:即可。同时在运行的时候就连tools:本身都是被忽略的，不会被带进apk中。

***

将BaseActivity中的initView和initData暂时注释掉，使用butterknife后initView不在需要，为了代码的简洁initData后期需要加上。



