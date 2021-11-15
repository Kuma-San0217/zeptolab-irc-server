package me.yuri0217.zeptolab.irc.server.entities

import io.netty.channel.ChannelId
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

data class User(
    val login: Login,
    var currentIrcChannel: IrcChannel? = null,
    private val password: Password,
    private val connections: MutableList<ChannelId>,
) {
    private val connectionsMutex = Mutex()

    fun check(password: Password) : Boolean = this.password.data == password.data

    fun getConnections() : List<ChannelId> = connections.toList()

    suspend fun addConnection(channelId: ChannelId) = connectionsMutex.withLock { connections.add(channelId) }

    suspend fun removeConnection(channelId: ChannelId) = connectionsMutex.withLock { connections.remove(channelId) }
}

@JvmInline
value class Login(val data: String)

@JvmInline
value class Password(val data: String)
