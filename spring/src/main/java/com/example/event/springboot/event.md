事件说明<br>
#####ContextRefreshedEvent
当 Spring 容器处于初始化或者刷新阶段时就会触发，事实是ApplicationContext#refresh()方法被调用时，此时容器已经初始化完毕。


#####ContextStartedEvent
当调用 ConfigurableApplicationContext接口下的 start() 方法时触发，表示 Spring 容器启动；通常用于 Spring 容器显式关闭后的启动。


#####ContextStoppedEvent
当调用 ConfigurableApplicationContext 接口下的 stop()方法时触发，表示 Spring 容器停止，此时能通过其 start()方法重启容器。


#####ContextClosedEvent
当 Spring 容器调用 ApplicationContext#close() 方法时触发，此时 Spring 的 beans 都已经被销毁，并且不会重新启动和刷新。


#####RequestHandledEvent
只在 Web 应用下存在，当接受到 HTTP 请求并处理后就会触发，实际传递的默认实现类 ServletRequestHandledEvent
