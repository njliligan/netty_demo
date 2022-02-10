package com.njganlili.nettydemo.timeNettyDemo;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * @author njgan
 * @description
 * @date 2022/2/10 22:04
 * 处理字符读取
 */
//第一种
public class TimeDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        if (byteBuf.readByte() < 4){
            return;
        }
        list.add(byteBuf.readBytes(4));
    }
}
//第二种
//public class TimeDecoder extends ReplayingDecoder<Void> {
//    @Override
//    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
//        list.add(byteBuf.readBytes(4));
//    }
//}
