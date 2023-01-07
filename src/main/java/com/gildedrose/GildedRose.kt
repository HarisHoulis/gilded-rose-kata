package com.gildedrose

typealias GildedRose = List<Item>

fun GildedRose.updated() = map { it.updated() }
