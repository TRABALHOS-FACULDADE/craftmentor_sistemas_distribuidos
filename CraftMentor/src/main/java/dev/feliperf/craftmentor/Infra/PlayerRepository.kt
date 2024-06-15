package dev.feliperf.craftmentor.Infra

import dev.feliperf.craftmentor.Domain.Models.PlayerModel
import dev.feliperf.craftmentor.External.Endpoints.PlayerEndpoints
import dev.feliperf.craftmentor.External.RetrofitNetwork

class PlayerRepository {
    companion object {
        private const val BASE_URL = "http://host.docker.internal:8001"
        private val client = RetrofitNetwork.getRetrofitInstance(BASE_URL)
        private val endpoint = client.create(PlayerEndpoints::class.java)

        fun getAllPlayers() : List<PlayerModel>? {
            val callback = endpoint.getAllPlayers()
            val body = callback.execute().body()
            return body
        }

        fun updatePlayerPoints(playerName: String, points: Double) {
            val callback = endpoint.updatePlayerPoints(playerName, hashMapOf("points" to points))
            callback.execute()
        }
    }
}