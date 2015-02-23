[![Build Status](https://travis-ci.org/liaohuqiu/android-cube-app.svg?branch=master)](https://travis-ci.org/liaohuqiu/android-cube-app)

这个项目的目的是作为一个APP开发参考。

### 依赖

*   support-v4, 版本: r7
*   clog, 版本: 1.0.2, https://github.com/liaohuqiu/android-CLog
*   cube-sdk, 版本: 1.0.44.6-SNAPSHOT, https://github.com/etao-open-source/cube-sdk
*   ultra-pull-to-refresh, 版本: 1.0.6, https://github.com/liaohuqiu/android-Ultra-Pull-To-Refresh
*   event-bus, 版本: 1.3.4, https://github.com/square/otto

### 关于Eclipse运行项目

通过gradle(Android Studio / Intellij IDEA)以及maven可以无阻碍地运行项目。但对于eclipse的同学来说，可能会遇到一些障碍。

一些依赖，直接是jar包, (support-v4, clog, event-bus), 我都放在了libs目录下，直接引入到工程即可。

另外依赖的一些项目因为含有资源文件，无法仅打包成jar， 需要打包成aar或者apklib，而Eclipse是没有办法直接使用这两种格式的。

建议：将这些依赖的类库的项目在Eclipse作为工程打开，引入到你的项目中。以下是需要引入的项目:

*  cube-sdk.  https://github.com/etao-open-source/cube-sdk  core文件夹作为一个类库项目引入

*  ultra-ptr. https://github.com/liaohuqiu/android-Ultra-Pull-To-Refresh. ptr-lib文件夹，作为一个类库项目引入。

注意： **请使用dev分支中最新的代码**

### 联系方式和问题建议

* 微博: http://weibo.com/liaohuqiu
* 博客: http://www.liaohuqiu.net
* QQ 群: 271918140
* srain@php.net
* twitter: https://twitter.com/liaohuqiu
