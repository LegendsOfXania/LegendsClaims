package fr.xania.legendsClaims

import dev.jorel.commandapi.CommandAPI
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class LegendsClaims : JavaPlugin() {

    private lateinit var snippetsConfig: YamlConfiguration

    override fun onEnable() {
        saveDefaultConfig()

        loadConfig()
        logger.info("Language configuration loaded.")

        loadSnippets()
        logger.info("Language configuration reloaded.")

        logger.info("LegendsClaims enabled.")
        logger.warning("This plugin is a work in progress and is not yet functional.")
    }

    override fun onDisable() {
        CommandAPI.onDisable()
        logger.info("LegendsClaims disabled.")
    }

    private fun loadConfig() {
        logger.info("Configuration loaded.")
    }

    private fun loadSnippets() {
        val snippetsFile = File(dataFolder, "snippets.yml")
        if (!snippetsFile.exists()) {
            saveResource("snippets.yml", false)
        }
        snippetsConfig = YamlConfiguration.loadConfiguration(snippetsFile)
    }

    fun reloadSnippets() {
        val snippetsFile = File(dataFolder, "snippets.yml")
        if (snippetsFile.exists()) { snippetsConfig = YamlConfiguration.loadConfiguration(snippetsFile) }
    }

    fun getSnippetsString(key: String): String {
        return snippetsConfig.getString(key) ?: ""
    }
}