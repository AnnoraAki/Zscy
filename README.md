# 掌上重邮
红岩网校移动开发部半期考核。
>考核要求：按照接口、视觉图、原型图实现掌邮的`邮问`以及`课表`两个大的部分，其他实现ui就好

本次考核只写了总板块中的邮问部分，课表做了个假ui
>是真的很惨..

[app下载](https://github.com/Cchanges/Zscy/blob/master/app/release/app-release.apk "点击之后dowload下载...")

[接口地址](https://github.com/jay68/MRedrock-Exam-2018/blob/master/api.md)

## 邮问实现的部分功能

就感觉其实啥东西都没写（所以为什么写了这么久）

### 无bug总列表

- 视觉图ui的基本仿真
- 拉取问题列表（5个类型的都能够拉取到）
- 查看问题详情
- 查看答案详情
- 发布回答
- 发布评论
- 静默登陆
- 课表的假ui（登陆后一直显示没课）

### 有问题的部分

- 点赞

在问题详情内点赞又取消又点赞之后数字设定有bug

- 采纳部分写了没做显示和点击事件

- 透明状态栏+toolbar感觉跟原生actionbar的高度不一致，看着很诡异

- 图片加载库工具类的本地缓存（不能从文件里面读取到..应该存进去了）

- 部分显示问题

### 写了部分的

- 提问
   - 类型选择
   - 问题与描述的编辑
   - tag选择（弹窗显示有bug）
   >之后准备做时间选择的然后搞了个原生上去...发现居然只有日期的，绝望.jpg
   
### 运行图示


> 没录到的地方就是没写...


![登陆](https://github.com/Cchanges/Zscy/blob/master/gifs/1.gif)

**登陆**


![拉取问题列表](https://github.com/Cchanges/Zscy/blob/master/gifs/2.gif)


**拉取问题列表**

![查看问题详情](https://github.com/Cchanges/Zscy/blob/master/gifs/3.gif)


**查看问题详情**


![添加回答](https://github.com/Cchanges/Zscy/blob/master/gifs/4.gif)

**添加回答**

![查看回答详情/点赞](https://github.com/Cchanges/Zscy/blob/master/gifs/5.gif)

**查看回答详情/点赞**

![添加评论](https://github.com/Cchanges/Zscy/blob/master/gifs/6.gif)

**添加评论**

![提问（只实现了的部分内容）](https://github.com/Cchanges/Zscy/blob/master/gifs/7.gif)

**提问（只实现了部分内容）**



![静默登陆](https://github.com/Cchanges/Zscy/blob/master/gifs/8.gif)


**静默登陆**


### 关于代码

- 原生

- 自封装网络请求类

- 图片加载库（一堆bug的加载库）

- Viewpaper+Fragment+RecyclerView的综合使用

- 仍旧高耦合...本来想尝试解耦的

- 老套的RedioGroup实现的下方导航栏（然后强行拦截了返回键放置bug...海星）

- 初步习得自定义Dialog（懒得重写导致布局gg），踩了popupWindow显示的坑还没解决

- 告诉我RecyclerView封装的重要性

- 告诉我好看的ui是真的难画，花样嵌套受不了了

- 告诉我之前不该摸鱼，而是好好敲代码

- 然后，就这样吧，都辛苦了
