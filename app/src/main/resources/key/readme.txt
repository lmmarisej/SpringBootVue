tomcathttps
    生成指令
        keytool -genkeypair -keyalg RSA -alias tomcathttps -keystore tomcathttps.p12 -storepass 123456 -validity 365 -keysize 2048 -storetype pkcs12
    转为jks
        keytool -importkeystore -srckeystore tomcathttps.p12 -srcstoretype pkcs12 -srcalias tomcathttps -destkeystore tomcathttps.p12.jks -deststoretype jks -deststorepass 123456 -destalias tomcathttps
    密码
        123456

