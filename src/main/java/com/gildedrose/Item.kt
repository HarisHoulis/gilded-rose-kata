package com.gildedrose

class Item(
    val name: String,
    var sellIn: Int,
    var quality: Int,
    private val aging: () -> Int = Aging.standard,
    private val degradation: (Int, Int) -> Int = Degradation.standard,
    private val saturation: (Int) -> Int = Saturation.standard,
) {
    fun update() {
        sellIn -= aging()
        quality = saturation(quality - degradation(quality, sellIn))
    }

    fun updated(): Item {
        val sellIn = sellIn - aging()
        val quality = saturation(quality - degradation(quality, sellIn))
        return Item(
            name,
            sellIn,
            quality,
            aging,
            degradation,
            saturation
        )
    }

    override fun toString(): String = "$name, $sellIn, $quality"
}
