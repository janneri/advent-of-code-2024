
import util.Coord
import util.Direction
import util.Direction.RIGHT
import util.Grid
import java.util.*

// See puzzle in https://adventofcode.com/2024/day/16

class Day16(inputLines: List<String>) {
    private val grid = Grid.of(inputLines)
    private val start = grid.findCoords('S').first()
    private val end = grid.findCoords('E').first()

    data class State(val prev: State?, val pos: Coord, val cost: Int, val direction: Direction) : Comparable<State> {
        override fun compareTo(other: State): Int = this.cost - other.cost

        fun getPath(): Set<Coord> = (prev?.getPath() ?: emptySet()) + pos

        fun nexStates(): List<State> =
            mapOf(
                direction to 1,
                direction.turnLeft() to 1001,
                direction.turnRight() to 1001
            ).map { (nextDir, dirCost) ->
                State(this, pos.move(nextDir), cost + dirCost, nextDir)
            }
    }

    private fun solve(): List<State> {
        val initialState = State(null, start, 0, RIGHT)
        val pq = PriorityQueue<State>()
        pq.add(initialState)

        val minCosts = mutableMapOf<Pair<Coord, Direction>, Int>()
        minCosts[start to RIGHT] = 0

        val optimalPaths = mutableListOf<State>() // Stores all shortest paths
        var bestCost = Int.MAX_VALUE

        while (pq.isNotEmpty()) {
            val current = pq.poll()

            // The priority queue guarantees we can stop looking, because all next paths will be worse
            if (current.cost > bestCost) break

            if (current.pos == end) {
                if (current.cost < bestCost) {
                    bestCost = current.cost
                    optimalPaths.clear()
                }
                optimalPaths.add(current) // equally good path found!
                continue
            }

            for (next in current.nexStates()) {
                if (next.pos in grid && grid[next.pos] != '#') {
                    val key = next.pos to next.direction
                    val prevCost = minCosts[key] ?: Int.MAX_VALUE
                    if (next.cost <= prevCost) {
                        minCosts[key] = next.cost
                        pq.add(next)
                    }
                }
            }
        }

        return optimalPaths
    }

    fun part1(): Int = solve().first().cost

    fun part2(): Int = solve().flatMap { it.getPath() }.toSet().size
}