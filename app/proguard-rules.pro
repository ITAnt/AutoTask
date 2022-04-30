# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# 子Module是不需要混淆的，这是因为在主Module中配置了混淆，打包时会作用在所有的Module上，
# 即其他的Module的代码也会被混淆，如果在子Module中再混淆，势必会导致找不到一些类的错误。

# 注意：①所有经由网络JSON转换的实体类，必须实现Serializable且防止混淆！
# 注意：②每新增一个开源库，就要导入相应的混淆规则！

# 关闭代码优化
-dontoptimize
# 处理androidx包
-dontnote androidx.**
-dontwarn androidx.**
# 保留继承的
-keep public class * extends androidx.**
-keep public class * extends android.app.Application
# 保留四大组件，自定义的Application等这些类不被混淆
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference


# google推荐算法
-optimizations !code/simplification/arithmetic,!code/simplification/cast,!field/*,!class/merging/*
# 压缩级别
-optimizationpasses 5
# 优化时允许访问并修改有修饰符的类及类的成员
-allowaccessmodification

# 混淆后类型都为小写
-dontusemixedcaseclassnames
# 混淆时记录日志
-verbose
-ignorewarnings
-keep class javax.inject.** { *; }

# 避免混淆Annotation
-keepattributes *Annotation*
# 防止拥有该成员的类和类成员被混淆
-keepclasseswithmembernames class * {
    native <methods>;
}
-keepclasseswithmembers class * {
    native <methods>;
}
-keepclassmembers class * {
    native <methods>;
}

# 不混淆自定义View
-keepclassmembers public class * extends android.view.View {
   void set*(***);
   *** get*();
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

# 不混淆Activity里的方法，这样就可以在xml的onClick里使用该方法了
#-keepclassmembers class * extends android.app.Activity {
#   public void *(android.view.View);
#}

# 不混淆枚举
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# 不混淆Parcelable子类
-keepclassmembers class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator CREATOR;
}

# 保留R下面的资源
-keepclassmembers class **.R$* {
    public static <fields>;
}
-keep class **.R$* {*;}

# The support library contains references to newer platform versions.
# Don't warn about those in case this app is linking against an older
# platform version.  We know about them, and they are safe.
-dontwarn android.support.**

# 不混淆Keep注解标记的类
-keep class androidx.annotation.Keep
-keep @androidx.annotation.Keep class * {*;}
-keepclasseswithmembers class * {
    @androidx.annotation.Keep <methods>;
}
-keepclasseswithmembers class * {
    @androidx.annotation.Keep <fields>;
}
-keepclasseswithmembers class * {
    @androidx.annotation.Keep <init>(...);
}

# 将源码中有意义的类名转换成SourceFile，用于混淆具体崩溃代码
#-renamesourcefileattribute SourceFile
# 抛出异常时保留代码行号
#-renamesourcefileattribute flooSDK
#-keepattributes SourceFile,LineNumberTable
#-keepattributes Exceptions,InnerClasses,Signature,EnclosingMethod

# 图片选择 PictureSelector 2.0
-keep class com.luck.picture.lib.** { *; }

# 城市选择
-keep class com.lljjcoder.**{
	*;
}
-dontwarn demo.**
-keep class demo.**{*;}
-dontwarn net.sourceforge.pinyin4j.**
-keep class net.sourceforge.pinyin4j.**{*;}
-keep class net.sourceforge.pinyin4j.format.**{*;}
-keep class net.sourceforge.pinyin4j.format.exception.**{*;}

# RxJava RxAndroid
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
-dontwarn com.tbruyelle.rxpermissions.**

# Android源码
-keep class com.google.android.material.** { *; }
-dontwarn com.google.android.material.**
-dontnote com.google.android.material.**
-keep class com.google.** { *; }
-dontwarn androidx.**
-keep interface androidx.** { *; }
-keep class androidx.** {*;}
-keep class com.google.** { *; }
-keep interface com.google.** { *; }
-dontwarn com.google.**
-keep class com.google.protobuf.* {*;}
-dontwarn com.google.protobuf.**

# 一些开源项目
-keep class com.github.** { *; }
-keep class com.wangjie.** { *; }
-keep class com.bigkoo.** { *; }
-keep class com.blankj.** { *; }
-keep class com.miekir.** { *; }

# okhttp
# A resource is loaded with a relative path so the package of this class must be preserved.
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase
# Animal Sniffer compileOnly dependency to ensure APIs are compatible with older versions of Java.
-dontwarn org.codehaus.mojo.animal_sniffer.*
# OkHttp platform used only on JVM and when Conscrypt dependency is available.
-dontwarn okhttp3.internal.platform.ConscryptPlatform
-dontwarn org.conscrypt.ConscryptHostnameVerifier
-dontwarn okio.**
-keep class com.squareup.okhttp.** { *;}
-dontwarn com.squareup.okhttp.**
-dontwarn javax.annotation.**
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }
-dontwarn okhttp3.**

# Retrofit
-keep class retrofit2.** { *; }
-keep interface retrofit2.** { *; }
-dontwarn retrofit2.**
-keepattributes Signature, InnerClasses, EnclosingMethod
# Retrofit does reflection on method and parameter annotations.
-keepattributes RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations
# Keep annotation default values (e.g., retrofit2.http.Field.encoded).
-keepattributes AnnotationDefault
# Retain service method parameters when optimizing.
-keepclassmembers,allowshrinking,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}
# Ignore annotation used for build tooling.
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
# Ignore JSR 305 annotations for embedding nullability information.
-dontwarn javax.annotation.**
# Guarded by a NoClassDefFoundError try/catch and only used when on the classpath.
-dontwarn kotlin.Unit
# Top-level functions that can only be used by Kotlin.
-dontwarn retrofit2.KotlinExtensions
-dontwarn retrofit2.KotlinExtensions$*
# With R8 full mode, it sees no subtypes of Retrofit interfaces since they are created with a Proxy
# and replaces all potential values with null. Explicitly keeping the interfaces prevents this.
-if interface * { @retrofit2.http.* <methods>; }
-keep,allowobfuscation interface <1>
# Keep generic signature of Call, Response (R8 full mode strips signatures from non-kept items).
-keep,allowobfuscation,allowshrinking interface retrofit2.Call
-keep,allowobfuscation,allowshrinking class retrofit2.Response
# With R8 full mode generic signatures are stripped for classes that are not
# kept. Suspend functions are wrapped in continuations where the type argument
# is used.
-keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation

# LeakCanary
-dontwarn com.squareup.haha.guava.**
-dontwarn com.squareup.haha.perflib.**
-dontwarn com.squareup.haha.trove.**
-dontwarn com.squareup.leakcanary.**
-keep class com.squareup.** { *; }
-keep class leakcanary.** { *; }
# Marshmallow removed Notification.setLatestEventInfo()
-dontwarn android.app.Notification

# Glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep class * extends com.bumptech.glide.module.AppGlideModule {
 <init>(...);
}
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
-keep class com.bumptech.glide.load.data.ParcelFileDescriptorRewinder$InternalRewinder {
  *** rewind();
}

# GSON
-keep class * extends com.google.gson.TypeAdapter
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer
-keep class org.json.* {*;}

# BaseQuickAdapter
-keep class com.chad.library.adapter.** {
*;
}
-keep public class * extends com.chad.library.adapter.base.BaseQuickAdapter

# AutoSize
-keep class com.miekir.common.autosize.** { *; }
-keep interface com.miekir.common.autosize.** { *; }

#kotlin
-dontwarn org.jetbrains.**
-dontwarn com.ted.floo.**
-keep class org.jetbrains.** { *; }
-keep interface org.jetbrains.** { *; }
-keep class kotlin.** { *; }
-keep interface kotlin.** { *; }
-keepclassmembers class kotlin.Metadata {
    public <methods>;
}
-keepclassmembers class **.WhenMappings {
    <fields>;
}
-assumenosideeffects class kotlin.jvm.internal.Intrinsics {
    static void checkParameterIsNotNull(java.lang.Object, java.lang.String);
}
-dontwarn kotlin.**
-keep class kotlinx.** { *; }
-keep interface kotlinx.** { *; }
-dontwarn kotlinx.**
# lateinit变量
-keepclasseswithmembers @kotlin.Metadata class * { *; }
-keep @kotlin.Metadata class *

# 饺子播放器
-keep public class cn.jzvd.JZMediaSystem {*; }
# ijk播放器
-keep class tv.danmaku.ijk.media.player.** {*; }
-dontwarn tv.danmaku.ijk.media.player.*
-keep interface tv.danmaku.ijk.media.player.** { *; }

-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}

# 高德地图
# 定位
-keep class com.amap.api.location.**{*;}
-keep class com.amap.api.fence.**{*;}
-keep class com.loc.**{*;}
-keep class com.autonavi.aps.amapapi.model.**{*;}
-dontwarn com.amap.api.**
-dontwarn com.autonavi.**
-keep class com.amap.api.**  {*;}
-keep class com.autonavi.**  {*;}
# 搜索
-keep class com.amap.api.services.**{*;}
# 2D地图
-keep class com.amap.api.maps2d.**{*;}
-keep class com.amap.api.mapcore2d.**{*;}