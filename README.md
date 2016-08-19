:heartpulse:LoadingLayer-Android:heartpulse:
============


### 效果图

<img src="http://i4.buimg.com/4851/a251f2482da9bdb5.gif">

## 使用方式一
### 预加载
预先在xml布局中添加对应状态的布局   
    
 1. 布局
```xml
<RelativeLayout
        android:layout_above="@id/ll_bottom"
        android:id="@+id/rl_layer_container"
        android:layout_width="match_parent"
        android:layout_height="match_parent">
        <include
            android:id="@+id/rl_empty"
            layout="@layout/aa_layout_empty"/>
        <include
            android:id="@+id/rl_error"
            layout="@layout/aa_layout_error"/>
        <include
            android:id="@+id/rl_loadding"
            layout="@layout/aa_layout_loadding"/>
        <include
            android:id="@+id/rl_success"
            layout="@layout/success_view"/>
</RelativeLayout>
```

 2. 初始化切换布局控制器
```java
mLayerViewController= new LayerViewController(rl_layer_container,LayerViewHelperImp.LayerModel.preload);
```
 3. 调用视图切换
```java
mLayerViewController.showVariableView(rl_loadding, LayerViewHelperImp.LayerStatus.loadding);
```
--------------------------------


### 响应式
在xml布局中只保留父view视图布局

 1. 布局
```xml
<FrameLayout
        android:id="@+id/rl_layer_container"
        android:layout_width="match_parent"
        android:layout_height="match_parent">
</FrameLayout>
```
 2. 初始化切换布局控制器
```java
mLayerViewController= new LayerViewController(rl_layer_container,LayerViewHelperImp.LayerModel.responsive);
```
 3. 调用视图切换
```java 
View loaddingView = LayoutInflater.from(this).inflate(R.layout.aa_layout_loadding, null);
mLayerViewController.showVariableView(loaddingView, LayerViewHelperImp.LayerStatus.loadding);
```
--------------------------------


### Gradle依赖
```gradle
dependencies {
    compile 'cn.andaction.loadinglayer:layer:1.0.2'
}
```


### 提示

* 父视图布局必须 instanceof FrameLayout || RelativeLayout
  

    

    

    
    
   
    
    
       

    



## License

    Copyright 2015 Andaction

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.