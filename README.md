# RN-Native-Demo

# Android原生模块的开发
1.编写原生模块的java代码
2.暴露接口与数据交互
3.注册与导出React Native原生模块

原生模块文件结构
.
├── MainActivity.java 					
├── MainApplication.java 				
└── crop
    ├── Crop.java 						接口，CropImpl.java中会实现该接口中的方法
    ├── CropImpl.java 					实现接口Crop中的方法，实际操作原生接口来实现读取相册中的图片和裁剪图片的功能
    ├── ImageCropModule.java 			该类继承ReactContextBaseJavaModule，负责将CropImpl中实现的本地接口暴露给JS
    ├── ImageCropReactPackage.java 		该类继承ReactPackage。所有要提供给JS调用的方法必须通过该注册模块进行注册才能够使用。
    ├── IntentUtils.java 				跳转意图工具类
    └── Utils.java 						工具类