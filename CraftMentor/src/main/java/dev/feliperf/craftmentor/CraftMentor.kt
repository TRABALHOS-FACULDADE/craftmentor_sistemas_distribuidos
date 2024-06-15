package dev.feliperf.craftmentor

import dev.feliperf.craftmentor.Domain.Models.PlayerModel
import dev.feliperf.craftmentor.Presenter.Controllers.BlockRankingController
import dev.feliperf.craftmentor.Presenter.Controllers.PlayerController
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import java.util.Timer
import java.util.TimerTask

class CraftMentor : JavaPlugin() {
    override fun onEnable() {
        BlockRankingController.fillBlocks()
        val players = PlayerController.getAllPlayers();
        updatePlayersPointsTimer(players)
    }

    override fun onLoad() {
        Bukkit.getServer().broadcastMessage("Plugins successfully initialized!")
        super.onLoad()
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}

private fun updatePlayersPointsTimer(players: List<PlayerModel>) {
    val timer = Timer()
    timer.schedule(object : TimerTask() {
        override fun run() {
            for (player in players) {
                val points = BlockRankingController.calculatePlayerPoints(player)
                PlayerController.updatePlayerPoints(player.name, points)
            }
            updatePlayersPointsTimer(players)
        }

    }, 10000) // 10 seconds
}
