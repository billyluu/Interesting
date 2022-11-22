# Interesting


### Project A (分享數據App, `Content Provider + SQLite`)
AndroidManifest.xml
```xml
<Manifest
    package="com.example.interesting">

    <!-- ### 自訂權限 protectionLevel="signature" 同Keystore簽署才能讀取 -->
    <permission android:name="com.example.permission.READ_PERMISSION"
        android:protectionLevel="signature"/>
    <permission android:name="com.example.permission.WRITE_PERMISSION"
        android:protectionLevel="signature"/>`
    
    <!-- ### authorities自定義暗語 -->
    <provider
        android:name=".MyContentProvider"
        android:authorities="com.example.interesting.provider"
        android:readPermission="com.example.permission.READ_PERMISSION"
        android:writePermission="com.example.permission.WRITE_PERMISSION"
        android:enabled="true"
        android:exported="true" />
</Manifest>
```


### Project B (讀取/新增Project A數據)
AndroidManifest.xml
```xml
<Manifest
    package="com.example.interesting2">
    
    <!-- ###必要 Project A的權限 -->
    <uses-permission
        android:name="com.example.permission.READ_PERMISSION"/>
    <uses-permission
        android:name="com.example.permission.WRITE_PERMISSION"/>

    <!-- ###必要 Project A的包名 -->
    <queries>
        <package android:name="com.example.interesting"/>
    </queries>
</Manifest>
```