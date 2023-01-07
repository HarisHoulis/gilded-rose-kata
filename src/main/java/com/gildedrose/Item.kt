package com.gildedrose

data class Item(
    val name: String,
    val sellIn: Int,
    val quality: Int,
    private val aging: () -> Int = Aging.standard,
    private val degradation: (Int, Int) -> Int = Degradation.standard,
    private val saturation: (Int) -> Int = Saturation.standard,
) {
    fun updated(): Item {
        val sellIn = sellIn - aging()
        return copy(
            sellIn = sellIn,
            quality = saturation(this.quality - degradation(this.quality, sellIn)),
        )
    }

    override fun toString(): String = "$name, $sellIn, $quality"
}
