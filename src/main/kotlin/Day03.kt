// See puzzle in https://adventofcode.com/2024/day/3

class Day03(private val input: String) {
    private val instructionRegex = """mul\((\d+),(\d+)\)""".toRegex()

    data class Multiply(val left: Int, val right: Int, val position: Int)

    private fun findInstructions(str: String): List<Multiply> =
        instructionRegex.findAll(str).map { match ->
            val (left, right) = match.destructured
            Multiply(left.toInt(), right.toInt(), match.range.first)
        }.toList()

    private fun findAllOccurrences(input: String, substring: String): List<Int> =
        Regex(substring).findAll(input)
            .map { it.range.first }
            .toList()

    fun part1(): Int = findInstructions(input).sumOf { it.left * it.right }

    fun part2(): Int {
        val doPositions = findAllOccurrences(input, "do()")
        val dontPositions = findAllOccurrences(input, "don't()")

        return findInstructions(input)
            .filter { multiplyInstruction ->
                val closestDo: Int = doPositions.findLast { it < multiplyInstruction.position } ?: 0
                val closestDont: Int = dontPositions.findLast { it < multiplyInstruction.position } ?: -1
                closestDo > closestDont
            }
            .sumOf { it.left * it.right }
    }
}