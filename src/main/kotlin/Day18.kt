
import util.Coord
import java.util.*

// See puzzle in https://adventofcode.com/2024/day/18

class Day18(inputLines: List<String>) {
    private val wallCoords = inputLines.map { Coord.of(it) }

    private fun findPath(start: Coord, end: Coord, walls: Set<Coord>): Int? {
        val seen = mutableSetOf<Coord>()
        val queue = PriorityQueue<Pair<Coord, Int>>(compareBy { it.second })
        queue.add(start to 0)
        val validRange = 0..end.x

        while (queue.isNotEmpty()) {
            val (currentPos, cost) = queue.poll()
            if (currentPos == end) return cost

            if (seen.add(currentPos)) {
                currentPos.neighbors()
                    .filter { it.x in validRange && it.y in validRange && it !in walls && it !in seen }
                    .forEach { neighbor -> queue.add(neighbor to cost + 1) }
            }
        }
        return null
    }

    private fun findPath(gridSize: Int, wallCount: Int): Int? =
        findPath(Coord(0, 0), Coord(gridSize - 1, gridSize - 1), wallCoords.take(wallCount).toSet())

    fun part1(gridSize: Int, wallCount: Int): Int = findPath(gridSize, wallCount) ?: -1

    fun part2(gridSize: Int): String {
        var low = 0
        var high = wallCoords.size

        while (low < high) {
            val mid = (low + high) / 2
            if (findPath(gridSize, mid) == null) high = mid else low = mid + 1
        }

        return wallCoords[low - 1].toString()
    }
}