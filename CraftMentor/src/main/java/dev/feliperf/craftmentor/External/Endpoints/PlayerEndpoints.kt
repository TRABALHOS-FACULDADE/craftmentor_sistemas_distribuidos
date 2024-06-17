package dev.feliperf.craftmentor.External.Endpoints

import com.sun.tools.javac.jvm.PoolConstant.Dynamic
import dev.feliperf.craftmentor.Domain.Models.PlayerModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface PlayerEndpoints {
    @GET("players")
    fun getAllPlayers(
    ) : retrofit2.Call<List<PlayerModel>>

    @POST("players")
    fun insertNewPlayer(
        @Body newPlayer: HashMap<String, String>
    ) : retrofit2.Call<PlayerModel>

    @PATCH("player/{name}")
    fun updatePlayerPoints(
        @Path("name") name: String,
        @Body points: HashMap<String, Double>,
    ) : retrofit2.Call<Void>

    @GET("player-exists/{name}")
    fun playerExists(
        @Path("name") name: String,
    ) : retrofit2.Call<PlayerModel?>
}