package dev.feliperf.craftmentor.External.Endpoints

import dev.feliperf.craftmentor.Domain.Models.PlayerModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path
import retrofit2.http.Query

interface PlayerEndpoints {
    @GET("players")
    fun getAllPlayers(
    ) : retrofit2.Call<List<PlayerModel>>

    @PATCH("player/{name}")
    fun updatePlayerPoints(
        @Path("name") name: String,
        @Body points: HashMap<String, Double>,
    ) : retrofit2.Call<Void>
}