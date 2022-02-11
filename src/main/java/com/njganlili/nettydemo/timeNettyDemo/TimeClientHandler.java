package com.njganlili.nettydemo.timeNettyDemo;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

/**
 * @author njgan
 * @description
 * @date 2022/2/10 21:55
 */
//这里的Channel In bound HandlerAdapter 注意其中的IN，代表的是入站事件
public class TimeClientHandler extends ChannelInboundHandlerAdapter {

//    private ByteBuf byteBuf;
//
//    @Override
//    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
//        byteBuf = ctx.alloc().buffer(4);
//    }
//
//    @Override
//    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
//        byteBuf.release();
//        byteBuf = null;
//    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        ByteBuf readByteBuf = (ByteBuf) msg;
//        byteBuf.writeBytes(readByteBuf);
//        readByteBuf.release();

        ByteBuf readByteBuf = (ByteBuf) msg;
        try {
            long currentTimeMillis = (readByteBuf.readUnsignedInt() - 2208988800L) * 1000L;
            System.out.println(new Date(currentTimeMillis));
            ctx.close();
        }finally {
            readByteBuf.release();
        }

//        确保接受四个字节
//        if(byteBuf.readableBytes() >= 4 ) {
//            long currentTimeMillis = (byteBuf.readUnsignedInt() - 2208988800L) * 1000L;
//            System.out.println(new Date(currentTimeMillis));
//            ctx.close();
//        }

    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
