package ru.rsreu.labs

import ru.rsreu.labs.generator.Generator
import kotlin.math.floor

class MonteCarloShooterSimulation(
    private val groups: List<ShooterGroup>,
    private val generator: Generator
) {

    companion object{
        private const val ITERATION_COUNT = 1000000
    }

    fun start() {
        var counter = 0
        repeat(ITERATION_COUNT) {
            val firstHitProbability = getRandomHitProbability()
            val secondHitProbability = getRandomHitProbability()
            val firstHit = (generator.nextDouble() < firstHitProbability)
            val secondHit = (generator.nextDouble() < secondHitProbability)
            val hit = firstHit or secondHit
            if (hit) counter++
        }
        println(counter.toDouble() / ITERATION_COUNT)
    }

    private fun getRandomHitProbability(): Double {
        val shooterCount = groups.sumOf { it.shooterCount }
        val shooterIndex = floor((generator.nextDouble() * shooterCount)).toInt()
        var acc = 0
        for (i in groups.indices) {
            val group = groups[i]
            if (shooterIndex < group.shooterCount + acc)
                return group.hitProbability
            acc += group.shooterCount
        }
        throw IllegalStateException()
    }
}

data class ShooterGroup(
    val shooterCount: Int,
    val hitProbability: Double
)