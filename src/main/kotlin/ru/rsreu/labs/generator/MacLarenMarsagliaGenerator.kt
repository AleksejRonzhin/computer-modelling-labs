package ru.rsreu.labs.generator

import java.util.*

class MacLarenMarsagliaGenerator(private val k: Int) {
    private val values: MutableList<Double>
    private val random = Random()

    init {
        values = MutableList(k) { random.nextDouble() }
    }

    fun nextDouble(): Double {
        val index = (random.nextDouble() * k).toInt()
        val res = values[index]
        values[index] = random.nextDouble()
        return res
    }
}