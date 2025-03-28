package fr.xania.legendsClaims.manager.commands

import fr.xania.legendsClaims.LegendsClaims
import fr.xania.legendsClaims.utils.legacy
import net.kyori.adventure.text.Component
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class LegendsClaimsCommand(plugin: LegendsClaims) : Command("legendsclaims") {
    private val subCommands = mutableMapOf<String, (Player) -> Unit>()

    init {
        plugin.server.commandMap.register("legendsclaims", this)

        registerSubCommand("reload") { player -> reloadCommand(plugin, player) }
    }

    private fun registerSubCommand(name: String, action: (Player) -> Unit) {
        subCommands[name] = action
    }

    override fun execute(sender: CommandSender, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) {
            sender.sendMessage(Component.text("<red>Only players can execute this command.".legacy()))
            return true
        }

        val subCommand = args.getOrNull(0) ?: return false
        subCommands[subCommand]?.invoke(sender) ?: sender.sendMessage(Component.text("<red>Unknown command.".legacy()))
        return true
    }
}