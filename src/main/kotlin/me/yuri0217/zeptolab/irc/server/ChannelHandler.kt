package me.yuri0217.zeptolab.irc.server

import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ChannelHandler(
    private val channelProcessingService: ChannelProcessingService
) : SimpleChannelInboundHandler<String>() {
    override fun channelActive(ctx: ChannelHandlerContext) {
        ctx.writeAndFlush("Welcome to test task implementation for Zeptolab! ${System.lineSeparator()}Available commands: ${System.lineSeparator()}1) /login <name> <password> ${System.lineSeparator()}2) /join <channel>; ${System.lineSeparator()}3) /leave ${System.lineSeparator()}4) /users ${System.lineSeparator()}")
        runBlocking {
            launch {
                channelProcessingService.onChannelActive(context = ctx)
            }
        }
        super.channelActive(ctx)
    }

    override fun channelInactive(ctx: ChannelHandlerContext) {
        runBlocking {
            launch {
                channelProcessingService.onChannelInactive(context = ctx)
            }
        }
        super.channelInactive(ctx)
    }

    override fun channelRead0(ctx: ChannelHandlerContext, msg: String) {
        runBlocking {
            launch {
                channelProcessingService.processIncomingMessage(context = ctx, message = msg)
            }
        }
    }
}