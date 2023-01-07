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

    override fun toString(): String = "$name, $sellIn, $quality"
}
