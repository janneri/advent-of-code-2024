import util.Coord
import util.Direction
import util.Grid

// See puzzle in https://adventofcode.com/2024/day/6

class Day06(inputLines: List<String>) {
    private val grid = Grid.of(inputLines)

    data class Guard(var pos: Coord, var direction: Direction)
    data class GuardPath(val visitedCoords: Set<Coord>, val isLoop: Boolean)

    private fun moveGuard(guard: Guard, extraBlockCoord: Coord? = null): GuardPath {
        val seenStates = mutableSetOf<Guard>()

        while (guard.pos in grid && guard !in seenStates) {
            seenStates += guard.copy()

            val posInFront = guard.pos.move(guard.direction)

            if (grid[posInFront] == '#' || posInFront == extraBlockCoord) {
                guard.direction = guard.direction.turnRight()
            }
            else {
                guard.pos = posInFront
            }
        }
        return GuardPath(seenStates.map {it.pos}.toSet(), isLoop = guard in seenStates)
    }

    fun part1(): Int {
        val guard = Guard(grid.findCoords('^').first(), Direction.UP)
        return moveGuard(guard).visitedCoords.size
    }

    fun part2(): Int {
        val guardPos = grid.findCoords('^').first()

        return grid.coords()
            .filter { it != guardPos }
            .count {
                moveGuard(Guard(guardPos, Direction.UP), extraBlockCoord = it).isLoop
            }
    }
}