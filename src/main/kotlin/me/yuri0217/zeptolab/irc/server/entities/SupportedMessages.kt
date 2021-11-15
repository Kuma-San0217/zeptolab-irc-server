package me.yuri0217.zeptolab.irc.server.entities

enum class SupportedMessages(val message: String) {
    LOGIN("/login"),
    JOIN("/join"),
    LEAVE("/leave"),
    USERS("/users")
}