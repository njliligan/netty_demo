package com.njganlili.nettydemo.timeNettyPojoDemo;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author njgan
 * @description
 * @date 2022/2/11 12:51
 */
public class TimeDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        if (byteBuf.readableBytes() < 4){
            return;
        }
        list.add(new UnixTime(byteBuf.readUnsignedInt()));
    }
}
