
import util.Coord
import util.Grid

// See puzzle in https://adventofcode.com/2024/day/21

class Day21(private val codes: List<String>) {
    private val numPad = Keypad(listOf("789", "456", "123", "#0A"))
    private val dirPad = Keypad(listOf("#^A", "<v>"))

    data class Keypad(val lines: List<String>) {
        private val grid = Grid.of(lines)
        private val invalidPositions = grid.findCoords('#')
        private val keyPositions: Map<Char, Coord> =
            grid.tileMap.map { (coord, char) -> char to coord }.toMap()

        operator fun get(key: Char): Coord = keyPositions[key]!!

        fun findMoveSequences(start: Coord, end: Coord): List<String> =
            grid.findAllShortestPaths(start, end, invalidPositions).map { path ->
                path.zipWithNext().joinToString("") { (prev, next) ->
                    prev.directionTo(next).symbolStr() } + "A"
            }
    }

    // move string, depth to the lowest move count
    private val cache = mutableMapOf<Pair<String, Int>, Long>()

    // The length of the shortest sequence of button presses
    private fun minLength(sequence: String, robotCount: Int, depth: Int = 0): Long {
        val memoKey = sequence to depth
        cache[memoKey]?.let { return it }

        val keypad = if (depth == 0) numPad else dirPad

        val totalLength = sequence.fold(keypad['A'] to 0L) { (current, accLength), char ->
            val next: Coord = keypad[char]
            val moveSequences: List<String> = keypad.findMoveSequences(current, next)

            val length = if (depth == robotCount) {
                moveSequences.first().length.toLong()
            } else {
                moveSequences.minOf { minLength(it, robotCount, depth + 1) }
            }

            next to accLength + length
        }.second

        return totalLength.also { cache[memoKey] = it }
    }

    private fun solve(robotCount: Int) = codes.sumOf { code ->
        val length = minLength(code, robotCount)
        val numericValue = code.substring(0, 3).toLong()
        length * numericValue
    }

    fun part1() = solve(2)
    fun part2() = solve(25)
}