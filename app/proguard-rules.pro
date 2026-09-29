# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in C:\Users\Microsoft\AppData\Local\Android\Sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
-keepclassmembers class * {
    @android.webkit.JavascriptInterface <methods>;
}

-keepattributes JavascriptInterface
-keepattributes *Annotation*

-dontwarn com.razorpay.**
-keep class com.razorpay.** {*;}

-optimizations !method/inlining/*

-keepclasseswithmembers class * {
  public void onPayment*(...);
}

-keep class org.apache.http.** { *; }

-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}


-keepattributes *Annotation*
-keepclassmembers class ** {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }

# Platform calls Class.forName on types which do not exist on Android to determine platform.
-dontnote retrofit2.Platform
# Platform used when running on Java 8 VMs. Will not be used at runtime.
-dontwarn retrofit2.Platform$Java8
# Retain generic type information for use by reflection by converters and adapters.
-keepattributes Signature
# Retain declared checked exceptions for use by a Proxy instance.
-keepattributes Exceptions

-keep class payfreedom.BalanceCheck.dto.** { *; }
-keep class payfreedom.BrowsePlan.dto.** { *; }
-keep class payfreedom.Dashboard.dto.** { *; }
-keep class payfreedom.DisputeReport.dto.** { *; }
-keep class payfreedom.** { *; }
-keep class payfreedom.DMRReport.dto.** { *; }
-keep class payfreedom.FundRecReport.dto.** { *; }
-keep class payfreedom.LedgerReport.dto.** { *; }
-keep class payfreedom.Login.dto.** { *; }
-keep class payfreedom.RechargeReport.dto.** { *; }
-keep class payfreedom.Register.dto.** { *; }
-keep class payfreedom.UserDayBook.dto.** { *; }
-keep class payfreedom.Util.dto.** { *; }


-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }

-keep class javamail.** {*;}
-keep class javax.mail.** {*;}
-keep class javax.activation.** {*;}

-keep class com.sun.mail.dsn.** {*;}
-keep class com.sun.mail.handlers.** {*;}
-keep class com.sun.mail.smtp.** {*;}
-keep class com.sun.mail.util.** {*;}
-keep class mailcap.** {*;}
-keep class mimetypes.** {*;}
-keep class myjava.awt.datatransfer.** {*;}
-keep class org.apache.harmony.awt.** {*;}
-keep class org.apache.harmony.misc.** {*;}


-keep class com.google.gson.** { *; }
-keep class com.google.inject.** { *; }
-keep class org.apache.http.** { *; }
-keep class org.apache.james.mime4j.** { *; }
-keep class javax.inject.** { *; }

# For using GSON @Expose annotation
#-keepattributes *Annotation*

# Gson specific classes
-keep class sun.misc.Unsafe { *; }
#-keep class com.google.gson.stream.** { *; }

# Application classes that will be serialized/deserialized over Gson
-keep class com.google.gson.examples.android.model.** { *; }

# Prevent proguard from stripping interface information from TypeAdapterFactory,
# JsonSerializer, JsonDeserializer instances (so they can be used in @JsonAdapter)
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer


-dontwarn java.awt.**
-dontwarn java.beans.Beans
-dontwarn javax.security.**
#-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn org.apache.http.**
-dontwarn java.awt.**,javax.activation.**,java.beans.**
-dontwarn org.apache.commons.**
