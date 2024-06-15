package dev.feliperf.craftmentor.Presenter.Controllers

import dev.feliperf.craftmentor.Domain.Models.PlayerModel
import dev.feliperf.craftmentor.Infra.PlayerRepository

class PlayerController {
    companion object {
        fun getAllPlayers(): List<PlayerModel> {
            return PlayerRepository.getAllPlayers() ?: emptyList()
        }

        fun updatePlayerPoints(playerName: String, points: Double) {
            PlayerRepository.updatePlayerPoints(playerName, points)
        }
    }
}