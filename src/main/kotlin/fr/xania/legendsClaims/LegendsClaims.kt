package fr.xania.legendsClaims

import fr.xania.legendsClaims.manager.commands.LegendsClaimsCommand
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class LegendsClaims : JavaPlugin() {

    private lateinit var langConfig: YamlConfiguration

    override fun onEnable() {
        saveDefaultConfig()
        loadConfig()
        loadLang()
        loadCommands()
        logger.info("LegendsClaims enabled")
        logger.warning("This plugin is a work in progress and is not yet functional.")
    }

    override fun onDisable() {
        logger.info("LegendsClaims disabled.")
    }

    private fun loadCommands() {
        LegendsClaimsCommand(this)
    }

    private fun loadConfig() {
        reloadConfig()
        val config = config
        logger.info("Configuration loaded.")
    }

    private fun loadLang() {
        val langFile = File(dataFolder, "snippet.yml")
        if (!langFile.exists()) {
            saveResource("snippet.yml", false)
        }
        langConfig = YamlConfiguration.loadConfiguration(langFile)
        logger.info("Language configuration loaded.")
    }

    fun reloadLang() {
        val langFile = File(dataFolder, "snippet.yml")
        if (langFile.exists()) {
            langConfig = YamlConfiguration.loadConfiguration(langFile)
            logger.info("Language configuration reloaded.")
        } else {
            logger.warning("Language file snippet.yml does not exist.")
        }
    }

    fun getLangString(key: String): String? {
        return langConfig.getString(key)
    }
}