package com.gildedrose

open class Item(
    val name: String,
    var sellIn: Int,
    var quality: Int,
) {
    override fun toString(): String = "$name, $sellIn, $quality"
}

open class BaseItem(
    name: String,
    sellIn: Int,
    quality: Int,
    private val aging: () -> Int = { 1 },
    private val degradation: (Int, Int) -> Int = { _, sellIn: Int ->
        when {
            sellIn < 0 -> 2
            else -> 1
        }
    },
    protected open val saturation: (Int) -> Int = { quality: Int ->
        when {
            quality < 0 -> 0
            quality > 50 -> 50
            else -> quality
        }
    },
) : Item(name, sellIn, quality) {

    fun update() {
        sellIn -= aging()
        quality = saturation(quality - degradation(quality, sellIn))
    }

}

class Brie(name: String, sellIn: Int, quality: Int) : BaseItem(
    name,
    sellIn,
    quality,
    degradation = { _, sellIn ->
        when {
            sellIn < 0 -> -2
            else -> -1
        }
    }
)

class Pass(name: String, sellIn: Int, quality: Int) : BaseItem(
    name,
    sellIn,
    quality,
    degradation = { quality, sellIn ->
        when {
            sellIn < 0 -> quality
            sellIn < 5 -> -3
            sellIn < 10 -> -2
            else -> -1
        }
    }
)

class Sulfuras(name: String, sellIn: Int, quality: Int) : BaseItem(
    name,
    sellIn,
    quality,
    aging = { 0 },
    degradation = { _, _ -> 0 },
    saturation = { quality -> quality }
)
