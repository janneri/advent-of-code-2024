import util.middleValue
import util.parseInts
import util.parseSections

// See puzzle in https://adventofcode.com/2024/day/5

class Day05(input: String) {
    private val inputSections = parseSections(input)
    private val rulePairs: List<Pair<Int, Int>> = inputSections[0].map { parseRule(it) }
    private val updateLists: List<List<Int>> = inputSections[1].map { parseInts(it) }

    private fun parseRule(str: String): Pair<Int, Int> =
        str.split("|").let { it[0].toInt() to it[1].toInt() }

    private fun violatesRule(rule: Pair<Int, Int>, updateNums: List<Int>): Boolean {
        val firstIdx = updateNums.indexOf(rule.first)
        val secondIdx = updateNums.indexOf(rule.second)
        return firstIdx != -1 && secondIdx != -1 && firstIdx > secondIdx
    }

    fun part1(): Int = updateLists
            .filter { updateNums -> rulePairs.all { rule -> !violatesRule(rule, updateNums) } }
            .sumOf { it.middleValue() }

    fun part2(): Int = updateLists
            .filter { updateNums -> rulePairs.any { rule -> violatesRule(rule, updateNums) } }
            .map { updateNums ->
                updateNums.sortedWith { a, b ->
                    when {
                        rulePairs.any { it == a to b } -> -1
                        rulePairs.any { it == b to a } -> 1
                        else -> 0
                    }
                }
            }.sumOf { it.middleValue() }
}