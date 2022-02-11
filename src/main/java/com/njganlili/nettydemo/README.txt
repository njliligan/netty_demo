ChannelHandler 这个很重要
里面有一幅ASCII的图，可以直观反应结构
ChannelHandler下面分别有
ChannelInboundHandlerAdapter 对应入站事件
ChannelOutboundHandlerAdapter 对应出站事件

ChannelFuture
是netty异步操作的结果，毕竟Future。
要么是完成时态，要么是未完成。执行失败和取消也属于完成
未完成时候
isDone() = false
isSuccess() = false
isCancelled() = false
cause() = null
成功完成
isDone() = true
isSuccess() = true
失败
isDone() = true
cause() = non-null
取消
isDone() = true
isCancelled() = true
方法
ChannelFuture的方法并不多，可以简单的看一下。
channel()：返回ChannelFuture关联的Channel；
addListener()：将指定的listener添加到Future。Future完成时，将通知指定的listener。如果Future已经完成，则立即通知指定的listener；
addListeners()：和上述方法一样，只不过此方法可以新增一系列的listener；
removeListener()：从Future中删除第一次出现的指定listener。完成Future时，不再通知指定的listener。如果指定的listener与此Future没有关联，则此方法不执行任何操作并以静默方式返回。
removeListeners()：和上述方法一样，只不过此方法可以移除一系列的listener；
sync()：等待Future直到其完成，如果这个Future失败，则抛出失败原因；
syncUninterruptibly()：不会被中断的sync()；
await()：等待Future完成；
awaitUninterruptibly()：不会被中断的await ()；
isVoid()：如果此ChannelFuture是void的Future，则返回true，因此不允许调用以下任何方法：
addListener(GenericFutureListener)
addListeners(GenericFutureListener[])
await()
await(long, TimeUnit) ()}
await(long) ()}
awaitUninterruptibly()
sync()
syncUninterruptibly()
总结：
1、Netty中的所有IO操作都是异步的。这意味着任何IO调用都将立即返回，而不能保证所请求的IO操作在调用结束时完成。ChannelFuture是Channel异步IO操作的结果；
2、ChannelFuture或者说是Future，通过引入新的特性解决了原生JDK中Future对于状态模糊不清及阻塞等待获取结果的方式，这个新特性就是引入isSuccess()、cause()方法，同时通过Future-Listener回调机制解决不知道何时能获取到Future结果的问题。
