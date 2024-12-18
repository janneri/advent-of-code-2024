
import util.Coord
import java.util.*

// See puzzle in https://adventofcode.com/2024/day/18

class Day18(inputLines: List<String>) {
    private val wallCoords = inputLines.map { line -> Coord.of(line) }

    private data class PathWithCost(
        val path: Set<Coord>,
        val pos: Coord,
        val cost: Int
    ) : Comparable<PathWithCost> {
        override fun compareTo(other: PathWithCost): Int = this.cost - other.cost
    }

    private fun findPath(start: Coord, end: Coord, walls: Set<Coord>): PathWithCost? {
        val seen = mutableSetOf<Coord>()
        val queue = PriorityQueue<PathWithCost>().apply { add(PathWithCost(emptySet(), start, 0)) }
        val validRange = 0..end.x

        while (queue.isNotEmpty()) {
            val current = queue.poll()
            if (current.pos == end) return current

            if (seen.add(current.pos)) {
                current.pos.neighbors()
                    .filter { it.x in validRange && it.y in validRange && it !in walls && it !in seen }
                    .forEach { queue.add(PathWithCost(current.path + it, it, current.cost + 1)) }
            }
        }
        return null
    }

    private fun findPath(gridSize: Int, wallCount: Int): PathWithCost? =
        findPath(Coord(0, 0), Coord(gridSize - 1, gridSize - 1), wallCoords.take(wallCount).toSet())

    fun part1(gridSize: Int, wallCount: Int): Int = findPath(gridSize, wallCount)!!.cost

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