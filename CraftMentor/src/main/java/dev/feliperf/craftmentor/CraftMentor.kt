package dev.feliperf.craftmentor

import dev.feliperf.craftmentor.Presenter.Controllers.BlockRankingController
import dev.feliperf.craftmentor.Presenter.Controllers.CraftMentorController
import dev.feliperf.craftmentor.Presenter.Controllers.PlayerController
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.java.JavaPlugin
import java.util.Timer
import java.util.TimerTask

@EventHandler
fun onJoinServer(event: PlayerJoinEvent) {

    val player = event.player

    if(PlayerController.playerExists(player.name) == null) {
        PlayerController.insertNewPlayer(player.name, player.uniqueId.toString())
    }
}

class CraftMentor : JavaPlugin(), Listener {
    override fun onEnable() {
        Bukkit.getServer().pluginManager.registerEvents(this, this)
        BlockRankingController.fillBlocks()
        updatePlayersPointsTimer()
    }

    override fun onLoad() {
        Bukkit.getServer().broadcastMessage("Plugins successfully initialized!")
        super.onLoad()
    }

    override fun onDisable() {
        Bukkit.getServer().broadcastMessage("Shutting down server and plugins...")
    }
}

private fun updatePlayersPointsTimer() {
    val timer = Timer()
    val players = PlayerController.getAllPlayers()
    timer.schedule(object : TimerTask() {
        override fun run() {
            for (player in players) {
                val bukkitPlayer = Bukkit.getPlayer(player.name)
                if (bukkitPlayer != null) {
                    if (bukkitPlayer.isOnline) {
                        val points = BlockRankingController.calculatePlayerPoints(player, bukkitPlayer)
                        PlayerController.updatePlayerPoints(player.name, points)
                    }
                }
            }
            CraftMentorController.playersRankingBroadcastChatMessage(players)
            timer.cancel()
            updatePlayersPointsTimer()
        }

    }, 10000) // 10 seconds
}
