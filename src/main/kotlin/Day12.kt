import util.*

// See puzzle in https://adventofcode.com/2024/day/12

class Day12(val inputLines: List<String>) {
    private val grid = Grid.of(inputLines)

    private fun Coord.isAreaNeighbor(coord: Coord): Boolean =
        grid[this] == grid[coord] && this in coord.neighbors()

    private fun Coord.findArea(): Set<Coord> {
        val area = mutableSetOf(this)
        val stack = ArrayDeque<Coord>(); stack.add(this)

        while (stack.isNotEmpty()) {
            val current = stack.removeFirst()
            current.neighbors()
                .filter { it in grid && it !in area && it.isAreaNeighbor(current) }
                .forEach {
                    area.add(it)
                    stack.add(it)
                }
        }

        return area
    }

    private fun Grid<Char>.areas(): List<Set<Coord>> {
        val visited = mutableSetOf<Coord>()
        val areas = mutableListOf<Set<Coord>>()

        this.coords().forEach { coord ->
            if (coord !in visited) {
                val area = coord.findArea()
                visited.addAll(area)
                areas.add(area)
            }
        }

        return areas
    }

    private fun Set<Coord>.fenceDirections(coord: Coord): List<Direction> =
        Direction.entries.filter { coord.move(it) !in this }

    private fun Set<Coord>.perimeterPart1(): Int = this.sumOf { coord -> fenceDirections(coord).size }

    private fun Set<Coord>.perimeterPart2(): Int {
        val fencesByCoord: Map<Coord, List<Direction>> = this.associateWith { fenceDirections(it) }

        return this.sumOf { coord ->
            val fences = fencesByCoord[coord] ?: emptyList()
            val leftNeighborFences = fencesByCoord[coord.move(Direction.LEFT)] ?: emptyList()
            val upNeighborFences = fencesByCoord[coord.move(Direction.UP)] ?: emptyList()
            // The fence doesn't count, if the left neighbor or upper neighbor has the same fence
            fences.count { dir ->
                !leftNeighborFences.contains(dir) && !upNeighborFences.contains(dir)
            }
        }
    }

    fun part1(): Int = grid.areas().sumOf { it.size * it.perimeterPart1() }

    fun part2(): Int = grid.areas().sumOf { it.size * it.perimeterPart2() }
}