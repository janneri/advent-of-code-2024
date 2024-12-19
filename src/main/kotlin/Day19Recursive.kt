import util.parseSections

// See puzzle in https://adventofcode.com/2024/day/19

class Day19Recursive(input: String) {
    private val inputSections = parseSections(input)
    private val patterns = inputSections[0][0].split(", ")
    private val designs = inputSections[1]

    private fun String.matchesAt(pattern: String, offset: Int) =
        offset + pattern.length <= this.length &&
                this.substring(offset, offset + pattern.length) == pattern

    private val memo = mutableMapOf<String, Long>()

    private fun countPossible(design: String, start: Int): Long {
        if (start == design.length) return 1

        memo[design.substring(start)]?.let { return it }

        val ways = patterns
            .filter { design.matchesAt(it, start) }
            .sumOf { countPossible(design, start + it.length) }

        memo[design.substring(start)] = ways
        return ways
    }

    fun part1(): Int = designs.count { countPossible(it, 0) > 0 }

    fun part2(): Long = designs.sumOf { countPossible(it, 0) }
}