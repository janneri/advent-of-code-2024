
import util.Coord
import util.Direction
import util.Direction.RIGHT
import util.Grid
import util.drawGrid

// See puzzle in https://adventofcode.com/2024/day/15

class Day15(input: String) {
    private val inputParts = input.split("\n\n")
    private val moves = inputParts[1].filter { it != '\n' }.map { Direction.ofSymbol(it) }
    private val grid = Grid.of(inputParts[0].lines())
    private val enlargedGrid = Grid.of(inputParts[0].lines().map { line ->
        line
            .replace("#", "##")
            .replace("O", "[]")
            .replace(".", "..")
            .replace("@", "@.")
    })

    data class Stone(var coords: List<Coord>) {
        fun move(direction: Direction): Stone {
            coords = coords.map { it.move(direction) }
            return this
        }

        fun touches(otherStone: Stone, direction: Direction) =
            coords.any { it.move(direction) in otherStone.coords }

        fun isBlocked(walls: Set<Coord>, direction: Direction) =
            coords.any { it.move(direction) in walls }

        fun gps(): Int = coords.first().let { 100 * it.y + it.x }

        fun singleCoord(): Coord? = coords.singleOrNull()
        fun startCoord(): Coord? = coords.firstOrNull()
        fun endCoord(): Coord? = coords.takeIf { it.size == 2 }?.last()
    }

    data class Warehouse(val grid: Grid<Char>) {
        private var robot = grid.findCoords('@').first()
        private var walls = grid.findCoords('#')
        private var stones =
            grid.findCoords('O').map { Stone(listOf(it)) } +
            grid.findCoords('[').map { Stone(listOf(it, it.move(RIGHT))) }

        fun move(direction: Direction) {
            val newRobot = robot.move(direction)
            if (newRobot in walls) return

            val stone = stones.find { newRobot in it.coords }
            if (stone != null) {
                val movableStones = findMovableStones(stone, direction)
                if (movableStones.isEmpty()) return

                movableStones.forEach { it.move(direction) }
            }
            robot = newRobot
        }

        private fun findMovableStones(initial: Stone, direction: Direction): List<Stone> {
            val visited = mutableListOf<Stone>()
            var queue = mutableListOf(initial)

            while (queue.isNotEmpty()) {
                if (queue.all { !it.isBlocked(walls, direction) }) {
                    visited += queue
                    val neighbors = stones
                        .filter { it !in visited }
                        .filter { stone -> queue.any { it.touches(stone, direction) } }
                    queue = neighbors.toMutableList()
                }
                else {
                    return emptyList() // some stone is blocked
                }
            }

            return visited
        }

        fun drawGrid(prefix: String, logEnabled: Boolean = true) {
            if (!logEnabled) return

            val smallStones = stones.mapNotNull { it.singleCoord() }
            val stoneStarts = stones.mapNotNull { it.startCoord() }
            val stoneEnds = stones.mapNotNull { it.endCoord() }

            println(prefix)
            drawGrid(grid.coords()) {
                when (it) {
                    robot -> '@'
                    in walls -> '#'
                    in smallStones -> 'O'
                    in stoneStarts -> '['
                    in stoneEnds -> ']'
                    else -> '.'
                }
            }
        }

        fun gpsSum(): Int = stones.sumOf { it.gps() }
    }

    private fun solve(grid: Grid<Char>, logEnabled: Boolean): Int {
        val warehouse = Warehouse(grid)
        warehouse.drawGrid("Initial state:", logEnabled)

        moves.forEach { direction ->
            warehouse.move(direction)
            warehouse.drawGrid("Move ${direction.symbol}:", logEnabled)
        }

        return warehouse.gpsSum()
    }

    fun part1(): Int = solve(grid, logEnabled = false)

    fun part2(): Int = solve(enlargedGrid, logEnabled = false)
}