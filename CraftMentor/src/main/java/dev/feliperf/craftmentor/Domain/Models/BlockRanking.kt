package dev.feliperf.craftmentor.Domain.Models

import org.bukkit.Material

data class BlockRanking(
    val id: Int,
    val type: Material,
    val points: Double,
    )
