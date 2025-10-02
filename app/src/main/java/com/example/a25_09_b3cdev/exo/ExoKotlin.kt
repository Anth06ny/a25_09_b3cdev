package com.example.a25_09_b3cdev.exo

import com.example.a25_09_b3cdev.PRICE_BAGUETTE
import com.example.a25_09_b3cdev.PRICE_CROISSANT
import com.example.a25_09_b3cdev.PRICE_SANDWITCH

var v2: String? = "hello"

fun main() {
    var res = boulangerie(1, 2, 3)
    println("res=$res")
    println(boulangerie(nbS = 3)) //boulangerie(0, 0, 3)

}

fun boulangerie(
    nbCroissant: Int = 0,
    nbBag: Int = 0, nbS: Int = 0
): Double = nbCroissant * PRICE_CROISSANT + nbBag * PRICE_BAGUETTE + nbS * PRICE_SANDWITCH










