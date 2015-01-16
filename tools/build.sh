keytool -genkey -v -keystore debug.keystore -storepass android -alias androiddebugkey -keypass android -validity 36500 -dname "CN=Android Debug,O=Android,C=US"
keytool -genkey -v -keystore cubeapp.keystore -storepass cubeapp -alias cubeapp -keypass cubeapp -validity 36500 -dname "CN=Android Debug,O=Android,C=CN"
