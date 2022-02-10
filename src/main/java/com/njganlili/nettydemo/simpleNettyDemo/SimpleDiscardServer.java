package com.njganlili.nettydemo.simpleNettyDemo;


import io.netty.bootstrap.ServerBootstrap;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author njgan
 * @description
 * @date 2022/2/10 20:33
 * telnet localhost 8080
 */
public class SimpleDiscardServer {
    private int port;
    public SimpleDiscardServer(int port){
        this.port = port;
    }
    public void run() throws Exception{
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try{
            //ServerBootstrap的childHandler()与handler()添加的handlers是针对不同的EventLoopGroup起作用
            //通过handler添加的handlers是对bossGroup线程组起作用
            //通过childHandler添加的handlers是对workerGroup线程组起作用
            //客户端Bootstrap只有handler()方法，因为客户端只需要一个事件线程组

            //设置服务器哦的帮助类
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup,workerGroup)
                    //实例化新通道，这里代表使用NioServerSocketChannel
                .channel(NioServerSocketChannel.class)
                    //客户端连接之后的handlder
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new DiscardServerHandler());
                    }
                })
                    //设置优化参数
                .option(ChannelOption.SO_BACKLOG,128)
                .childOption(ChannelOption.SO_KEEPALIVE,true);
            ChannelFuture channelFuture = serverBootstrap.bind("127.0.0.1",port).sync();
            //不加这句服务器会自动退出
            // Wait until the server socket is closed.
            // In this example, this does not happen, but you can do that to gracefully
            // shut down your server.
            //等到服务器套接字关闭
            // 在此示例中，这不会发生，但是你可以优雅的做到这一点
            // 关闭你的服务器
            //实际上就是为了防止直接运行完关闭了netty
            channelFuture.channel().closeFuture().sync();
        }finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
    public static void main(String[] args) throws Exception{
        int port = 8080;
        if(args.length > 0){
            port = Integer.parseInt(args[0]);
        }
        new SimpleDiscardServer(port).run();
    }
}
