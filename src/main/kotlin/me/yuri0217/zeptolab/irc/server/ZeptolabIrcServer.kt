package me.yuri0217.zeptolab.irc.server

import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.ChannelOption
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioServerSocketChannel
import me.yuri0217.zeptolab.irc.server.config.Configuration

fun main() {
    val boss = NioEventLoopGroup()
    val workers = NioEventLoopGroup()
    try {
        val serverBootstrap = ServerBootstrap().group(boss, workers)
            .channel(NioServerSocketChannel::class.java)
            .childHandler(IrcChannelInitializer())
            .option(ChannelOption.SO_BACKLOG, 128)
            .childOption(ChannelOption.SO_KEEPALIVE, true)

        val future = serverBootstrap.bind(Configuration.port).sync()
        future.channel().closeFuture().sync()
    } finally {
        boss.shutdownGracefully()
        workers.shutdownGracefully()
    }
}