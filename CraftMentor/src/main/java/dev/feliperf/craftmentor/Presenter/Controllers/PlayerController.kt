package dev.feliperf.craftmentor.Presenter.Controllers

import dev.feliperf.craftmentor.Domain.Models.PlayerModel
import dev.feliperf.craftmentor.Infra.PlayerRepository

class PlayerController {
    companion object {
        fun getAllPlayers(): List<PlayerModel> {
            return PlayerRepository.getAllPlayers() ?: emptyList()
        }

        fun insertNewPlayer(name: String, id: String): PlayerModel? {
            return PlayerRepository.insertNewPlayer(name, id)
        }

        fun updatePlayerPoints(playerName: String, points: Double) {
            PlayerRepository.updatePlayerPoints(playerName, points)
        }

        fun playerExists(playerName: String) : PlayerModel? {
            return PlayerRepository.playerExists(playerName)
        }
    }
}