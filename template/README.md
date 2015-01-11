这个项目的目的是作为一个APP开发参考。

### 关于Eclipse运行项目

通过gradle(Android Studio / Intellij IDEA)以及maven可以无阻碍地运行项目。但对于eclipse的同学来说，可能会遇到一些障碍。

对于依赖的jar包，我都放在了libs目录下，直接引入到工程即可。

项目另外依赖一些aar或者apklib的包，在eclipse中，下面两个方法，选择其一:

1. 使用maven插件

2. 将依赖的aar或者apklib包的项目的源码，作为工程打开，引入到你的项目中。以下是需要引入的项目:

    *  cube-sdk.  https://github.com/etao-open-source/cube-sdk  core文件夹作为一个类库项目引入

    *  ultra-ptr. https://github.com/liaohuqiu/android-Ultra-Pull-To-Refresh. ptr-lib文件夹，作为一个独立项目引入。

### 联系方式和问题建议

* 微博: http://weibo.com/liaohuqiu
* 博客: http://www.liaohuqiu.net
* QQ 群: 271918140
* srain@php.net
* twitter: https://twitter.com/liaohuqiu
