import util.Coord
import util.IDirection

// See puzzle in https://adventofcode.com/2024/day/4

class Day04(inputLines: List<String>) {
    private val tiles: Map<Coord, Char> = inputLines.flatMapIndexed { y, line ->
        line.mapIndexed { x, char -> Coord(x, y) to char }
    }.toMap()

    private val maxX = tiles.keys.maxBy { it.x }.x
    private val maxY = tiles.keys.maxBy { it.y }.y
    private val validCorners = setOf("MSMS", "MMSS", "SMSM", "SSMM")

    private fun collectWords(fromCoord: Coord): List<String> =
        IDirection.entries.map { direction ->
            fromCoord.moveCollect(direction, 3)
                .mapNotNull { tiles[it] }
                .joinToString("")
        }

    fun part1(): Int = tiles.keys
        .filter { tiles[it] == 'X' }
        .sumOf { fromCoord ->
            collectWords(fromCoord).count { it == "XMAS" }
        }

    fun part2(): Int = tiles.keys
        .filter { tiles[it] == 'A' && it.x in 1..<maxX && it.y in 1..<maxY }
        .count { coord ->
            val cornerChars = listOf(
                tiles[Coord(coord.x - 1, coord.y - 1)],
                tiles[Coord(coord.x + 1, coord.y - 1)],
                tiles[Coord(coord.x - 1, coord.y + 1)],
                tiles[Coord(coord.x + 1, coord.y + 1)]
            )
            cornerChars.joinToString("") in validCorners
        }
}