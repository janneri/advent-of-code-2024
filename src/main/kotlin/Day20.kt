
import util.Coord
import util.Grid
import util.Path
import java.util.*

// See puzzle in https://adventofcode.com/2024/day/20

class Day20(inputLines: List<String>) {
    private val grid = Grid.of(inputLines)
    private val walls = grid.findCoords('#')
    private val start = grid.getCoord('S')
    private val end = grid.getCoord('E')

    private fun findPath(): Path? {
        val seen = mutableSetOf<Coord>()
        val queue = PriorityQueue<Path>(compareBy { it.size }).apply { add(listOf(start)) }

        while (queue.isNotEmpty()) {
            val path = queue.poll()
            val currentPos = path.last()
            if (currentPos == end) return path

            if (seen.add(currentPos)) {
                currentPos.neighbors()
                    .filter { it !in walls && it !in seen }
                    .forEach { queue.add(path + it) }
            }
        }
        return null
    }

    private fun calculateSaving(cheatStart: Coord, cheatEnd: Coord, costByCoord: Map<Coord, Int>): Int {
        val cheatLen = cheatStart.distanceTo(cheatEnd)
        val noCheatLen = costByCoord.getValue(cheatEnd) - costByCoord.getValue(cheatStart)
        return noCheatLen - cheatLen
    }

    private fun solve(maxCheatLen: Int, minSave: Int = 100): Int {
        val path = findPath() ?: throw Error("No path")
        val costByCoord = path.withIndex().associate { (index, coord) -> coord to index }

        return path.dropLast(1).sumOf { cheatStart ->
            path.filter { cheatStart.distanceTo(it) in 2..maxCheatLen }
                .count { cheatEnd -> calculateSaving(cheatStart, cheatEnd, costByCoord) >= minSave }
        }
    }

    fun part1(): Int = solve(2)
    fun part2(): Int = solve(20)
}