#学习笔记

## BIO、NIO、AIO
> 参考：[Java IO 编程（BIO、NIO、AIO完整实例代码）](http://blog.anxpp.com/index.php/archives/895/)
### BIO
#### 一.简介
1. BIO模型：采用BIO通信模型的服务端，通常由一个独立的Acceptor线程负责监听客户端的连接，它接收到客户端连接请求之后为每个客户端创建一个新的线程进行链路处理没处理完成后，通过输出流返回应答给客户端，线程销毁。即典型的一请求一应答通宵模型。
2. 原理及过程： 服务端提供IP和监听端口，客户端通过连接操作想服务端监听的地址发起连接请求，通过三次握手连接，如果连接成功建立，双方就可以通过套接字进行通信。 传统的同步阻塞模型开发中，ServerSocket负责绑定IP地址，启动监听端口；Socket负责发起连接操作。连接成功后，双方通过输入和输出流进行同步阻塞式通信。 
3. 传统BIO通信模型图：
> ![BIO通信模型](doc/img/BioNioAio/bio_model.png)
#### 二.实现源码：详细见study/io/bio

### NIO

### AIO

## lombok

