package com.example.a25_09_b3cdev.model

import java.time.temporal.TemporalAdjusters.next
import java.util.Random

fun main() {
    var t1 = ThermometerBean(min = -20, max = 50, value = 0)
    println("Température de ${t1.value}") // 0

    //Cas qui marche
    t1.value = 10
    println("Température de ${t1.value}") // 10 attendu

    //Borne minimum
    t1.value = -30
    println("Température de ${t1.value}") // -20 attendu

    //Borne maximum
    t1.value = 100
    println("Température de ${t1.value}") // 50 attendu

    //Pour les plus rapides : Cas de départ
    t1 = ThermometerBean(min = -20, max = 50, value = -100)
    println("Température de ${t1.value}") // -20 attendu

    val t2 = ThermometerBean.getCelsiusThermometer()

}

class RandomName(){
    private val list = arrayListOf("Toto", "tata", "Bob")
    private var oldName = ""

    fun next2() = Pair(nextDiff(), nextDiff())

    fun nextDiff2(): String {
        oldName = list.filter { it != oldName }.random()
        return oldName
    }

    fun nextDiff(): String {
        var newName = next()
        while(newName == oldName) {
            newName = next()
        }

        oldName = newName
        return newName
    }

    fun addAll(vararg names :String){
        for( n in names){
            add(n)
        }
    }

    fun next() = list.random()

    fun add(name:String?){
        if(!name.isNullOrBlank() && name !in list){
            list.add(name)
        }
    }
}

class ThermometerBean(var min: Int, var max: Int, value: Int) {
    var value = value.coerceIn(min, max)
        set(newValue) {
            field = if (newValue < min) min else if (newValue > max) max else newValue
        }

    companion object {
        fun getCelsiusThermometer() = ThermometerBean(-30, 50, 0)
        fun getFahrenheitThermometer() = ThermometerBean(20, 120, 32)
    }
}

class PrintRandomIntBean(var max: Int) {
    private val random: Random = Random()

    init {
        println(random.nextInt(max))
        println(random.nextInt(max))
        println(random.nextInt(max))
    }

}

class HouseBean(var color: String, width: Int, length: Int) {
    var area = width * length

    fun print() = println("La maison $color fait ${area}m²")
}

data class CarBean(var marque: String, var model: String) {
    var color: String = ""

    fun print() = "C'est une $marque $model de $color"
}