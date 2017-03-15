# StreamList
>>横向流式布局 点击删除
## Demo
![](https://github.com/androidSongMeng/StreamList/raw/master/1.png)
![](https://github.com/androidSongMeng/StreamList/raw/master/2.png)

[![](https://jitpack.io/v/androidSongMeng/StreamList.svg)](https://jitpack.io/#androidSongMeng/StreamList)


## How to use?
 #### Step 1. Add the JitPack repository to your build file
```java
  allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
#### Step 2. Add the dependency
```java
  dependencies {
	        compile 'com.github.androidSongMeng:StreamList:v1.0.1'
	}
```

### config in java code

```java
   co<com.example.streamlist.StreamList
        android:id="@+id/sl"
        app:textSize="15"
        app:textColor="#FF8040"
        app:horizontalSpace="10"
        app:verticalSpace="10"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:paddingLeft="10dp"
        android:paddingRight="10dp"
        android:paddingTop="10dp"/>
```
### 代码中调用
```java
   streamList = (StreamList) findViewById(R.id.sl);
        streamList.setType(Contant.TEXT_DELETE);
        streamList.setData(list);
```
```java
        streamList.setType(Contant.TEXT_DELETE);
         Contant.ONLY_TEXT  只有文字没有点击删除
         Contant.TEXT_DELETE   有点击删除
         Contant.VIEW_TEXT    自定义只有文字 在调用setData()之前调用streamList.setViewResouce(R.layout.item);
            并且布局的为:  id必须为tv_item
            <?xml version="1.0" encoding="utf-8"?>
            <TextView xmlns:android="http://schemas.android.com/apk/res/android"
                android:id="@+id/tv_item"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:padding="5dp" />
         Contant.VIEW_TEXT_DELETE      自定义带删除 在调用setData()之前调用streamList.setViewResouce(R.layout.item);
                     id 必须为iv_delete  tv_item
                     <?xml version="1.0" encoding="utf-8"?>
                     <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
                         android:layout_width="wrap_content"
                         android:layout_height="wrap_content"
                         android:background="@drawable/shape"
                         android:gravity="center"
                         android:orientation="horizontal"
                         android:paddingBottom="3dp"
                         android:paddingLeft="5dp"
                         android:paddingRight="5dp"
                         android:paddingTop="3dp">

    <TextView
        android:id="@+id/tv_item"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="大海"
        android:textColor="@android:color/black"
        android:textSize="16sp" />

    <ImageView
        android:id="@+id/iv_delete"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginLeft="10dp"
        android:src="@drawable/delete" />
</LinearLayout>
```

### 同时还可以动态的添加item
  ```java
   streamList.add("sffffffff");
```

