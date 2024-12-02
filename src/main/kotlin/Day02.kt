import util.parseInts

// See puzzle in https://adventofcode.com/2024/day/2

class Day02(inputLines: List<String>) {
    private val inputNumLines = inputLines.map { parseInts(it) }

    private fun List<Int>.isSafe(): Boolean {
        val diffs = this.zipWithNext().map { (prev, next) -> prev - next }
        return diffs.all { it in 1..3 }
                || diffs.all { it in -3 .. -1 }
    }

    // Remove each number one at a time and check if the list is safe without that number
    private fun List<Int>.isSafeDamped(): Boolean =
        this.indices.any { idx ->
            val newList = this.filterIndexed { index, _ -> index != idx }
            newList.isSafe()
        }

    fun part1(): Int = inputNumLines.count { it.isSafe() }
    
    fun part2(): Int = inputNumLines.count { it.isSafe() || it.isSafeDamped() }
}