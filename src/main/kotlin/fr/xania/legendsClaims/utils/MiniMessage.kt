package fr.xania.legendsClaims.utils

import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer

fun String.asMini() = MiniMessage.miniMessage().deserialize(this)
fun String.legacy() = LegacyComponentSerializer.legacySection().serialize(this.asMini())