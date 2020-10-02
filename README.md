## 定义拦截器的步骤
一、定义类实现Interceptor 接口

二、重写四个方法
    
    .初始化
    .单Event处理
    .多Event处理
    .释放资源
    
三、定义静态内部类Builder

四、打包上传到flume/lib 目录

五、在配置文件中指定关联连接器，方式：全类名$Builder