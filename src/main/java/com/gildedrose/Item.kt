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
) : Item(name, sellIn, quality) {

    fun update() {
        sellIn -= aginng()
        quality = saturation(quality - degradation(quality, sellIn))
    }

    protected open fun aginng() = 1

    protected open fun degradation(quality: Int, sellIn: Int) = when {
        sellIn < 0 -> 2
        else -> 1
    }

    protected open fun saturation(quality: Int) = when {
        quality < 0 -> 0
        quality > 50 -> 50
        else -> quality
    }
}

class Brie(name: String, sellIn: Int, quality: Int) : BaseItem(name, sellIn, quality) {
    override fun degradation(quality: Int, sellIn: Int) = when {
        sellIn < 0 -> -2
        else -> -1
    }
}

class Pass(name: String, sellIn: Int, quality: Int) : BaseItem(name, sellIn, quality) {
    override fun degradation(quality: Int, sellIn: Int) = when {
        sellIn < 0 -> quality
        sellIn < 5 -> -3
        sellIn < 10 -> -2
        else -> -1
    }
}

class Sulfuras(name: String, sellIn: Int, quality: Int) : BaseItem(name, sellIn, quality) {
    override fun degradation(quality: Int, sellIn: Int) = 0
    override fun aginng() = 0
    override fun saturation(quality: Int) = quality
}
