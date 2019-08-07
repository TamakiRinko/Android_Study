# Android_Study

## **时间**：2019年暑假

## **过程**：7月中旬学习：[Android开发基础教程](https://space.bilibili.com/137860026/)。7月底开始学习：[Android开发, Android Studio, Android微专业](https://www.bilibili.com/video/av50720825)，在此感谢老师的讲解！

### **一. Android开发, Android Studio, Android微专业 学习进度：**

* 2019/8/2：`P14`  
自定义控件基础入门(1)完毕。学习View的工作原理，并依据简单按钮需求实例讲解如何通过继承View来实现自定义控件。  
代码：**Customize_Control**
* 2019/8/3：`P15，P16，P17`  
自定义控件完毕，完善了昨天的红色按钮实例，并学习了如何自定义视图属性，同时学习dp/sp与px间的转化；  
Fragment初步，学习了Fragment的构造方法，了解了通过 layout资源文件中直接加入 和 代码调用 两种方式使用Fragment。  
代码：**Customize_Control，MyFragment**
* 2019/8/4：`P18，P19`  
Fragment完毕，学习Fragment的生命周期，Fragment静态初始化方式newInstance以及Bundle的使用。**余留疑问**：findFragmentByTag()函数总是返回null；  
Handler初步，使用handler构造了一个计时器。学习了静态内部类的使用及内存泄漏方面的问题。  
代码：**MyFragment，Handler**
* 2019/8/5：`P20，P21，P22，P23`  
Handler完毕，学习Handler中Looper的作用和PostDelayed方法，了解Handler在定时，延迟做任务方面的作用；  
Service完毕，学习Service(后台服务)的使用方式，使用音乐播放器的实例了解其 Started和Bound 两种操作模式的异同，学习IntentService与Activity的数据交流方式。**余留问题**：如何制作在线音乐播放器  
代码：**Handler，Service**
* 2019/8/6：`P24，P25`  
BroadcastReceiver完毕，学习广播接收器的 静态，动态 两种注册方式以及 普通广播，本应用广播，系统广播的 使用方式，各自特点；  
WebView初步，学习使用WebView控件来加载线上URL和本地html文件，学习使用WebView来与JavaScript交互，如何让JS代码和原生APP代码相互调用。**余留问题**：JS方面完全不会，交互学习大多不理解  
代码：**BroadCastReceiver，WebView**
* 2019/8/7：`P26，P27，P28`  
WebView完毕，学习如何回退值上一个页面，如何使用历史记录以及通过setWebChromeClient方法完成更多的类的结构和常用设置。  
Wdiget完毕，利用桌面小插件实例学习Widget的实现及其相对独立的各类函数（如点击事件）。  
代码：**WebView，Widget**
