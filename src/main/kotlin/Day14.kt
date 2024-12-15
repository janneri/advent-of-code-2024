import util.Coord
import util.Grid
import util.drawGrid
import util.parseInts

// See puzzle in https://adventofcode.com/2024/day/14

class Day14(inputLines: List<String>) {
    data class Robot(var pos: Coord, val vel: Coord) {
        fun move(gridW: Int, gridH: Int) {
            pos = (pos + vel).teleport(gridW, gridH)
        }

        fun quadrant(gridCenter: Coord): Int? =
            when {
                pos.x < gridCenter.x && pos.y < gridCenter.y -> 1
                pos.x > gridCenter.x && pos.y < gridCenter.y -> 2
                pos.x < gridCenter.x && pos.y > gridCenter.y -> 3
                pos.x > gridCenter.x && pos.y > gridCenter.y -> 4
                else -> null
            }
    }

    private val robots = inputLines.map { line ->
        val (x1, y1, x2, y2) = parseInts(line)
        Robot(Coord(x1, y1), Coord(x2, y2))
    }

    private fun printGrid(grid: Grid<Char>) {
        drawGrid(grid.coords()) { coord ->
            val count = robots.count { r -> r.pos == coord }
            if (count == 0) '.' else count.digitToChar()
        }
    }

    fun part1(gridW: Int, gridH: Int): Int {
        val grid = Grid.generate(gridW, gridH)

        repeat(100) {
            robots.forEach { robot -> robot.move(grid.width, grid.height) }
        }

        return robots
            .groupingBy { it.quadrant(grid.centerCoord()) }.eachCount()
            .filterKeys { it != null }
            .values
            .fold(1) { acc, count -> acc * count }
    }

    fun part2(gridW: Int, gridH: Int): Int {
        val grid = Grid.generate(gridW, gridH)

        val result = (1..100_000).find { iteration ->
            val seen = mutableSetOf<Coord>()

            var hasDuplicates = false
            robots.forEach { robot ->
                robot.move(grid.width, grid.height)
                if (seen.contains(robot.pos)) {
                    hasDuplicates = true
                }
                seen += robot.pos
            }

            !hasDuplicates
        }

        println("After $result seconds:")
        printGrid(grid)
        return result!!
    }
}
