import util.parseInts

// See puzzle in https://adventofcode.com/2024/day/5

class Day05(input: String) {
    private val inputParts = input.split("\n\n")

    private val rulePairs: List<Pair<Int, Int>> = inputParts[0].split("\n")
        .map { ruleLine -> ruleLine.split("|").let { it[0].toInt() to it[1].toInt() } }

    private val updateLists: List<List<Int>> = inputParts[1].split("\n")
        .map { ruleLine -> parseInts(ruleLine) }

    private fun violatesRule(rule: Pair<Int, Int>, updateNums: List<Int>): Boolean {
        val firstIdx = updateNums.indexOf(rule.first)
        val secondIdx = updateNums.indexOf(rule.second)
        return firstIdx != -1 && secondIdx != -1 && firstIdx > secondIdx
    }

    fun part1(): Int = updateLists
            .filter { updateNums -> rulePairs.all { rule -> !violatesRule(rule, updateNums) } }
            .sumOf { it[it.size / 2] }

    fun part2(): Int = updateLists
            .filter { updateNums -> rulePairs.any { rule -> violatesRule(rule, updateNums) } }
            .map { updateNums ->
                updateNums.sortedWith { a, b ->
                    when {
                        rulePairs.any { it.first == a && it.second == b } -> -1
                        rulePairs.any { it.first == b && it.second == a } -> 1
                        else -> 0
                    }
                }
            }.sumOf { it[it.size / 2] }
}