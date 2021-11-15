package me.yuri0217.zeptolab.irc.server.entities

import java.time.LocalDateTime

data class Message(
    val date: LocalDateTime = LocalDateTime.now(),
    val sentBy: User,
    val content: String
) : Comparable<Message> {
    override fun compareTo(other: Message): Int {
        return if (this.date.isEqual(other.date)) 0
        else if (this.date.isAfter(other.date)) 1
        else -1
    }

    override fun toString(): String = "${sentBy.login.data}: $content at $date"
}
