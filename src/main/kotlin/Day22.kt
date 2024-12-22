// See puzzle in https://adventofcode.com/2024/day/22

class Day22(inputLines: List<String>) {
    private val nums = inputLines.map { it.toLong() }
    private val modulo = 16777216L

    private fun evolveSecretNumber(secretNumber: Long): Long = secretNumber
        .let { (it xor (it shl 6)) % modulo }
        .let { (it xor (it shr 5)) % modulo }
        .let { (it xor (it shl 11)) % modulo }

    fun part1(): Long = nums.sumOf { number ->
        generateSequence(number) { evolveSecretNumber(it) }
            .take(2001)
            .last()
    }

    fun part2(): Long {
        val total = nums.fold(mutableMapOf<List<Long>, Long>().withDefault { 0L }) { acc, initial ->
            val cache = buildMap {
                var seq = List(4) { 0L }
                var currentSecret = initial

                repeat(2000) { i ->
                    val lastDigit = currentSecret % 10
                    currentSecret = evolveSecretNumber(currentSecret)
                    val diff = currentSecret % 10 - lastDigit
                    seq = seq.drop(1) + diff

                    if (i >= 3 && seq !in this) {
                        this[seq] = currentSecret % 10
                    }
                }
            }

            cache.forEach { (key, value) ->
                acc[key] = acc.getValue(key) + value
            }
            acc
        }

        return total.maxByOrNull { it.value }
            ?.also { println("Max profit: ${it.value} with sequence ${it.key}") }
            ?.value ?: 0L
    }
}