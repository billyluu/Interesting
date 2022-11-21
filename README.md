# Interesting


### Project A (分享數據App)
AndroidManifest.xml
```xml
<Manifest
    package="com.example.interesting">
    
    <permission android:name="com.example.permission.READ_PERMISSION"
        android:protectionLevel="signature"/>
    <permission android:name="com.example.permission.WRITE_PERMISSION"
        android:protectionLevel="signature"/>`

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
    
    <uses-permission
        android:name="com.example.permission.READ_PERMISSION"/>
    <uses-permission
        android:name="com.example.permission.WRITE_PERMISSION"/>

    <queries>
        <package android:name="com.example.interesting"/>
    </queries>
</Manifest>
```