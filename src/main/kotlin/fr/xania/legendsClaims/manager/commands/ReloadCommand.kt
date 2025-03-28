package fr.xania.legendsClaims.manager.commands

import fr.xania.legendsClaims.LegendsClaims
import fr.xania.legendsClaims.utils.legacy
import net.kyori.adventure.text.Component
import org.bukkit.entity.Player

fun reloadCommand(plugin: LegendsClaims, player: Player) {
    plugin.reloadConfig()
    plugin.reloadLang()

    val prefix = plugin.getLangString("prefix")
    player.sendMessage(Component.text("<light_purple>Configuration reloaded !".legacy()))
}