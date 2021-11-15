package me.yuri0217.zeptolab.irc.server.entities

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

data class IrcChannel(
    val name: String,
    // It seems that it would be a better way to use Kotlin Flows for messages, but I'm not sure
    private val messages: MutableList<Message> = mutableListOf(),
    private val connectedUsers: MutableList<User> = mutableListOf()
) {
    private val messagesMutex = Mutex()
    private val usersMutex = Mutex()

    fun getConnectedUsers() = connectedUsers.toList()

    fun getLastMessages(size: Int) = messages.toList().sorted().takeLast(size)

    suspend fun send(message: Message) = messagesMutex.withLock { messages.add(message) }

    suspend fun connect(user: User) = usersMutex.withLock { connectedUsers.add(user) }

    suspend fun disconnect(user: User) = usersMutex.withLock { connectedUsers.remove(user) }
}
