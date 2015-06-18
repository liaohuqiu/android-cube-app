[![Android Gems](http://www.android-gems.com/badge/liaohuqiu/android-cube-app.svg)](http://www.android-gems.com/lib/liaohuqiu/android-cube-app)

##### 欢迎关注我

Github: https://github.com/liaohuqiu

twitter: https://twitter.com/liaohuqiu

blog: http://liaohuqiu.net

微博: http://weibo.com/liaohuqiu

---


[![Build Status](https://travis-ci.org/liaohuqiu/android-cube-app.svg?branch=master)](https://travis-ci.org/liaohuqiu/android-cube-app)
### 添加scrollview的加载更多功能
        必须引用https://github.com/qs-lll/cube-sdk
            更改cube- sdk 源码

这个项目的目的是作为一个APP开发参考。

#### 功能点

* 加载更多
    <div class='row'>
        <img src='http://srain-github.qiniudn.com/load-more/load-more-for-list-view.gif' width="300px" style='border: #f1f1f1 solid 1px'/>
        <img src='http://srain-github.qiniudn.com/load-more/load-more-for-grid-view.gif' width="300px" style='border: #f1f1f1 solid 1px'/>
    </div>


### 依赖

*   support-v4, 版本: r7
*   clog, 版本: 1.0.2, https://github.com/liaohuqiu/android-CLog
*   cube-sdk, 版本: 1.0.44.33-SNAPSHOT, https://github.com/etao-open-source/cube-sdk
*   ultra-pull-to-refresh, 版本: 1.0.9.1-SNAPSHOT, https://github.com/liaohuqiu/android-Ultra-Pull-To-Refresh
*   eventbus, 版本: 2.4.0, https://github.com/greenrobot/EventBus

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
