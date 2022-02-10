package com.njganlili.nettydemo.simpleNettyDemo;

/**
 * @author njgan
 * @description
 * @date 2022/2/10 21:00
 */

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * Handles a server-side channel.
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter { // (1)

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
        // Discard the received data silently.
        //直接丢弃数据
        //((ByteBuf) msg).release(); // (3)
        try{
            ByteBuf byteBuf = (ByteBuf) msg;
            while (byteBuf.isReadable()){
                System.out.println((char) byteBuf.readByte());
                System.out.flush();
            }
        }finally {
            ReferenceCountUtil.release(msg);
        }
        //返回消息
        //ctx.writeAndFlush(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}
