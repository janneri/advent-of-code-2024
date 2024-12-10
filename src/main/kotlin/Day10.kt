import util.Coord
import util.Grid

// See puzzle in https://adventofcode.com/2024/day/10

class Day10(inputLines: List<String>) {
    private val grid = Grid.ofInts(inputLines)

    private fun solve(start: Coord, countDistinctEndpoints: Boolean = false): Int {
        val queue = ArrayDeque<Set<Coord>>()
        queue.add(mutableSetOf(start))
        val distinctPaths = mutableSetOf<Set<Coord>>()

        while (queue.isNotEmpty()) {
            val path = queue.removeFirst()
            val current = path.last()
            if (grid[current] == 9) {
                distinctPaths += path
            }
            else {
                current.neighbors()
                    .filter { neighbor ->
                        neighbor in grid && grid[neighbor] == grid[current]!! + 1
                    }
                    .forEach { neighbor ->
                        queue.add(path + neighbor)
                    }
            }
        }

        return when {
            countDistinctEndpoints -> distinctPaths.map { it.last() }.toSet().size
            else -> distinctPaths.size
        }
    }

    fun part1(): Int =
        grid.findCoords(0).sumOf { solve(start = it, countDistinctEndpoints = true) }

    fun part2(): Int =
        grid.findCoords(0).sumOf { solve(start = it) }
}