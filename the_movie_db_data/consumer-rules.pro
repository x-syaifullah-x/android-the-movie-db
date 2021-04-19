# Prevent R8 from leaving Data object members always null
-keepclassmembers,allowobfuscation class * {
    @com.google.gson.annotations.SerializedName <fields>;
}

##---------------Begin: proguard configuration for SQLCipher  ----------
#noinspection ShrinkerUnresolvedReference
-keep,includedescriptorclasses class net.sqlcipher.** { *; }
-keep,includedescriptorclasses interface net.sqlcipher.** { *; }

-dontwarn org.conscrypt.ConscryptHostnameVerifier

-keepclassmembers,allowobfuscation class id.xxx.the.movie.db.data.source.remote.response.** { *; }

# Strip Timber verbose and debug logging
-assumenosideeffects class timber.log.Timber$Tree {
    public void v(**);
    public void d(**);
}