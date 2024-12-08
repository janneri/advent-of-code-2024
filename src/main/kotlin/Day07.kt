import util.parseLongs

// See puzzle in https://adventofcode.com/2024/day/7

class Day07(inputLines: List<String>) {
    private val equations = inputLines.map { line ->
        line.substringBefore(":").toLong() to parseLongs(line.substringAfter(":"))
    }

    private fun add(a: Long, b: Long): Long = a + b
    private fun multiply(a: Long, b: Long): Long = a * b
    private fun join(a: Long, b: Long): Long = "$a$b".toLong()

    private fun isTruthyEquation(result: Long, numbers: List<Long>, operators: List<(Long, Long) -> Long>): Boolean {
        if (numbers.size == 1) {
            return numbers.first() == result
        }
        return operators.any { operation ->
            val newNumbers = listOf(operation(numbers[0], numbers[1])) + numbers.drop(2)
            isTruthyEquation(result, newNumbers, operators)
        }
    }

    private fun solve(operators: List<(Long, Long) -> Long>) = equations
        .filter { (result, numbers) -> isTruthyEquation(result, numbers, operators) }
        .sumOf { (result, _) -> result }

    fun part1(): Long = solve(listOf(::add, ::multiply))

    fun part2(): Long = solve(listOf(::add, ::multiply, ::join))

}