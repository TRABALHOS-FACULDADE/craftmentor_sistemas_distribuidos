package dev.feliperf.craftmentor.Presenter.Controllers

import dev.feliperf.craftmentor.Domain.Models.PlayerModel
import org.bukkit.Bukkit
import org.bukkit.ChatColor

class CraftMentorController {

    companion object {
        fun playersRankingBroadcastChatMessage(players: List<PlayerModel>) {
            val playersRanking = players.sortedBy { it.points }.mapIndexed { index, it -> "${ChatColor.YELLOW}[${index+1}] ${ChatColor.RESET}--> ${ChatColor.GREEN}${it.name} ${ChatColor.RESET}| ${ChatColor.GREEN}${it.points} points\n" }.joinToString()
            Bukkit.getServer().broadcastMessage("${ChatColor.RED}${ChatColor.BOLD} [PLAYERS RANKING]\n\n${playersRanking}")
        }
    }

}