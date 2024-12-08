import util.Coord
import util.Grid
import util.Line

// See puzzle in https://adventofcode.com/2024/day/8

class Day08(inputLines: List<String>) {
    private val grid = Grid.of(inputLines)

    private val antennas: MutableMap<Char, Set<Coord>> = grid.coords()
        .fold(mutableMapOf()) { acc, coord ->
            val tileChar = grid[coord]!!
            if (tileChar != '.') {
                acc[tileChar] = grid.findCoordsByTile { it == tileChar }
            }
            acc
        }

    private fun Coord.findLineCoords(toCoord: Coord, maxCount: Int?): List<Coord> {
        val line = Line(this, toCoord)
        val result = mutableListOf<Coord>()

        var current = toCoord.move(line.xDiff, line.yDiff)
        while (current in grid) {
            result.add(current)
            current = current.move(line.xDiff, line.yDiff)
        }

        return if (maxCount != null) result.take(maxCount) else result + toCoord
    }

    private fun findAntiNodeCoords(coords: Set<Coord>, maxCount: Int?): List<Coord> {
        return coords.flatMap { fromCoord ->
            coords.filter { it != fromCoord }
                .flatMap { toCoord -> fromCoord.findLineCoords(toCoord, maxCount) }
        }
    }

    private fun solve(maxCount: Int?): Int = antennas.values.flatMap { antennaCoords ->
            findAntiNodeCoords(antennaCoords, maxCount)
        }.toSet().size

    fun part1(): Int = solve(maxCount = 1)

    fun part2(): Int = solve(maxCount = null)

}