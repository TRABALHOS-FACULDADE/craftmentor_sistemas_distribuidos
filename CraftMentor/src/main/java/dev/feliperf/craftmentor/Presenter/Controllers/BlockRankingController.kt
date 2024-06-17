package dev.feliperf.craftmentor.Presenter.Controllers

import dev.feliperf.craftmentor.Domain.Models.BlockRanking
import dev.feliperf.craftmentor.Domain.Models.PlayerModel
import org.bukkit.Bukkit
import org.bukkit.Material.*
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class BlockRankingController {
    companion object {
        private var blocks = mutableListOf<BlockRanking>()
        fun fillBlocks() {
            if (blocks.isNotEmpty()) {
                blocks.clear()
            }
            blocks.add(BlockRanking(id = blocks.size, type = SAND, points = 0.5))
            blocks.add(BlockRanking(id = blocks.size, type = DIRT, points = 0.5))
            blocks.add(BlockRanking(id = blocks.size, type = SNOW, points = 0.5))
            blocks.add(BlockRanking(id = blocks.size, type = STICK, points = 1.0))
            blocks.add(BlockRanking(id = blocks.size, type = BEETROOT_SEEDS, points = 0.5))
            blocks.add(BlockRanking(id = blocks.size, type = MELON_SEEDS, points = 0.5))
            blocks.add(BlockRanking(id = blocks.size, type = PUMPKIN_SEEDS, points = 0.5))
            blocks.add(BlockRanking(id = blocks.size, type = TORCHFLOWER_SEEDS, points = 0.5))
            blocks.add(BlockRanking(id = blocks.size, type = WHEAT_SEEDS, points = 0.5))
            blocks.add(BlockRanking(id = blocks.size, type = SNOW_BLOCK, points = 2.0))
            blocks.add(BlockRanking(id = blocks.size, type = OAK_PLANKS, points = 2.5))
            blocks.add(BlockRanking(id = blocks.size, type = ACACIA_PLANKS, points = 2.5))
            blocks.add(BlockRanking(id = blocks.size, type = CHERRY_PLANKS, points = 2.5))
            blocks.add(BlockRanking(id = blocks.size, type = BIRCH_PLANKS, points = 2.5))
            blocks.add(BlockRanking(id = blocks.size, type = JUNGLE_PLANKS, points = 2.5))
            blocks.add(BlockRanking(id = blocks.size, type = DARK_OAK_PLANKS, points = 2.5))
            blocks.add(BlockRanking(id = blocks.size, type = MANGROVE_PLANKS, points = 2.5))
            blocks.add(BlockRanking(id = blocks.size, type = SPRUCE_PLANKS, points = 2.5))
            blocks.add(BlockRanking(id = blocks.size, type = COBBLESTONE, points = 4.5))
            blocks.add(BlockRanking(id = blocks.size, type = WOODEN_PICKAXE, points = 3.0))
            blocks.add(BlockRanking(id = blocks.size, type = CRAFTING_TABLE, points = 2.0))
            blocks.add(BlockRanking(id = blocks.size, type = STONE, points = 5.0))
            blocks.add(BlockRanking(id = blocks.size, type = COAL_ORE, points = 5.0))
            blocks.add(BlockRanking(id = blocks.size, type = GOLD_ORE, points = 5.5))
            blocks.add(BlockRanking(id = blocks.size, type = IRON_ORE, points = 8.0))
            blocks.add(BlockRanking(id = blocks.size, type = LAPIS_ORE, points = 5.5))
            blocks.add(BlockRanking(id = blocks.size, type = COPPER_ORE, points = 6.0))
            blocks.add(BlockRanking(id = blocks.size, type = DIAMOND_ORE, points = 10.0))
            blocks.add(BlockRanking(id = blocks.size, type = EMERALD_ORE, points = 8.5))
            blocks.add(BlockRanking(id = blocks.size, type = REDSTONE_ORE, points = 8.5))
        }

        fun calculatePlayerPoints(player: PlayerModel, bukkitPlayer: Player): Double {
            var points = 0.0
            val inventory = bukkitPlayer.inventory.contents
            for (item: ItemStack? in inventory.asList()) {
                if (item != null) {
                    val itemCount = item.amount
                    val itemType = item.type
                    val totalPoints = blocks.filter { it.type == itemType }.sumOf { it.points }
                    points += (totalPoints * itemCount)
                }
            }
            return points
        }
    }
}