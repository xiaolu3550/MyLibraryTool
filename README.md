# MyLibraryTool
自用Library
#### jcenter
`implementation 'com.xiaolu:Mytool:1.0.2'`

#### 在项目中build文件中添加支持butterknife

`classpath 'com.jakewharton:butterknife-gradle-plugin:10.1.0'`

#### 初始化:



    /**
     * 在Application中添加
     **/
    MyLibrary.getInstance()
    .init(Application)      //初始化
    .isDeBug(boolean);     //是否打印log信息


#### 设置统一url请求:

     /**
        * 在Application中添加
     **/
    public static RetrofitClient getClient() {
        return MyLibrary.getInstance().RetrofitConfig(url); //设置请求url头路径
    }
    
    
#### RxTool文档路径: 
[点击这里](https://tamsiree.com/TechnicalResearch/Android/RxTool/Wiki/RxTool-Wiki/#RxTool-Wiki)

#### 项目中引用:

###### retrofit:

`com.squareup.retrofit2:retrofit:2.7.2`

`com.squareup.retrofit2:adapter-rxjava2:2.7.2`

`com.squareup.retrofit2:converter-gson:2.7.2`

###### RxJava2 RxAndroid 2.0
`io.reactivex.rxjava2:rxandroid:2.1.1`

`io.reactivex.rxjava2:rxjava:2.1.1`
###### butterknife
`com.jakewharton:butterknife:10.1.0`

`annotationProcessor 'com.jakewharton:butterknife-compiler:10.1.0'`

###### Glide4.5
`com.github.bumptech.glide:glide:4.5.0`

` annotationProcessor 'com.github.bumptech.glide:compiler:4.5.0'`

###### 上拉刷新与下拉加载

`'com.scwang.smartrefresh:SmartRefreshLayout:1.1.0-andx-12'`

`'com.scwang.smartrefresh:SmartRefreshHeader:1.1.0-andx-12'`

###### EventBus

`org.greenrobot:eventbus:3.1.1`
 
