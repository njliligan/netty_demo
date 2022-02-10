package com.njganlili.nettydemo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

//netty.com
public class NettyDiscardServer {

    public static void main(String[] args) throws Exception {
        EventLoopGroup boosGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        // EventLoop: 一个线程， 运行 socket 处理循环
        //              NioEventLoop 内部提供 select 处理循环

        // Channel: 门面模式

        // ChannelHandler
        //bossGroup 接受请求
        //workerGroup  处理请求
        new ServerBootstrap()
                .group(boosGroup, workerGroup)
                .channel(NioServerSocketChannel.class);
//                .childHandler()

        // select
//        for (;;) {
//            ioRatio
//            //ChannelPipeline
//            // handle selectedKey
//            event-write: write(out)
//
//            // handle task queue
//            flushWriteTask
//        }
    }


    static class DiscardServerHandler extends ChannelInboundHandlerAdapter { // (1)

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
            // Discard the received data silently.
            ((ByteBuf) msg).release(); // (3)
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
            // Close the connection when an exception is raised.
            cause.printStackTrace();
            ctx.close();
        }
    }
}
