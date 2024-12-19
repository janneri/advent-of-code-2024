import util.parseSections

// See puzzle in https://adventofcode.com/2024/day/19

class Day19(input: String) {
    private val inputSections = parseSections(input)
    private val patterns = inputSections[0][0].split(", ")
    private val designs = inputSections[1]

    private fun String.matchesAt(pattern: String, offset: Int) =
        offset + pattern.length <= this.length &&
                this.substring(offset, offset + pattern.length) == pattern

    private fun countCombinations(design: String): Long {
        // Holds the count of ways to form the substring up to index
        val dp = LongArray(design.length + 1) { 0L }
        dp[0] = 1L
        for (index in design.indices) {
            for (pattern in patterns) {
                if (design.matchesAt(pattern, index)) {
                    // Increase the count of ways to form the substring up to index
                    dp[index + pattern.length] += dp[index]
                }
            }
        }
        return dp[design.length]
    }


    fun part1(): Int = designs.count { countCombinations(it) > 0 }

    fun part2(): Long = designs.sumOf { countCombinations(it) }
}