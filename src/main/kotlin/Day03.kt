// See puzzle in https://adventofcode.com/2024/day/3

class Day03(inputLines: List<String>) {
    private val input = inputLines.reduce { acc, s -> acc + s }
    private val linePattern = """mul\(\d+,\d+\)""".toRegex()
    private val mulPattern = """^mul\((\d+),(\d+)\)$""".toRegex()

    data class Multiply(val left: Int, val right: Int, val index: Int)

    private fun findInstructions(str: String): List<Multiply> =
        linePattern.findAll(str).toList().map {
            val instructionStr = it.value
            val (left, right) = mulPattern.find(instructionStr)!!.destructured
            val idx = str.indexOf("mul($left,$right)")
            Multiply(left.toInt(), right.toInt(), idx)
        }

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
                val closestDo: Int = doPositions.findLast { it < multiplyInstruction.index } ?: 0
                val closestDont: Int = dontPositions.findLast { it < multiplyInstruction.index } ?: -1
                closestDo > closestDont
            }
            .sumOf { it.left * it.right }
    }
}