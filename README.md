# MyKotlinDemo
## 功能说明
通过json 数据源: https://arcblockio.cn/blog/posts.json，获取到json列表。</br>
所以利用Recycleview实现了一个简单的列表界面，并使用数据中获取到的path，实现了列表点击进入WebActivity的功能。</br>
使用WebView进行H5页面的展示，为了方便与JS互通，编写了JavaScriptMethods实现调用功能
## 使用框架
### 1.图片框架
使用glide进行图片的加载、展示与图片的缓存管理
### 2.Json数据解析框架
使用Gson进行Json数据的解析
### 3.anko
使用AnkoCommons进行kotlin快速开发
## 设计思路
由于项目功能比较简单，所以使用了MVC的设计模式，项目中使用的各个模块各司其职
## 构建
### 1.开发环境
Android Studio V4.2.2</br>
JVM V1.8
### 2.Build
将程序使用Android Studio打开，等待程序自动编译完成即可调试

## 开发者
高晋龙 Kiton