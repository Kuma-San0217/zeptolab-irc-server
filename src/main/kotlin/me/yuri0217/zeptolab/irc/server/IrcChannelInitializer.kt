package me.yuri0217.zeptolab.irc.server

import io.netty.channel.ChannelInitializer
import io.netty.channel.ChannelPipeline
import io.netty.channel.socket.SocketChannel
import io.netty.handler.codec.DelimiterBasedFrameDecoder
import io.netty.handler.codec.Delimiters
import io.netty.handler.codec.string.StringDecoder
import io.netty.handler.codec.string.StringEncoder


class IrcChannelInitializer : ChannelInitializer<SocketChannel>() {
    private val channelProcessingService = ChannelProcessingService()

    override fun initChannel(channel: SocketChannel) {
        val pipeline: ChannelPipeline = channel.pipeline()

        pipeline.addLast(DelimiterBasedFrameDecoder(8192, *Delimiters.lineDelimiter()))
        pipeline.addLast(StringDecoder())
        pipeline.addLast(StringEncoder())
        pipeline.addLast(ChannelHandler(channelProcessingService = channelProcessingService))
    }
}