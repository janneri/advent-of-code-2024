import util.Coord
import util.IDirection

// See puzzle in https://adventofcode.com/2024/day/4

class Day04(inputLines: List<String>) {
    private val tiles: Map<Coord, Char> = inputLines.flatMapIndexed { y, line ->
        line.mapIndexed { x, char -> Coord(x, y) to char }
    }.toMap()

    private val width = tiles.keys.maxBy { it.x }.x
    private val height = tiles.keys.maxBy { it.y }.y
    private val validCorners = setOf("MSMS", "MMSS", "SMSM", "SSMM")

    private fun collectWords(fromCoord: Coord): List<String> {
        return IDirection.entries.map { direction ->
            val dirCoords = fromCoord.moveCollect(direction, 3).filter { it in tiles }
            dirCoords.fold("") { acc, c -> acc + tiles[c] }
        }
    }

    fun part1(): Int = tiles.keys.filter { tiles[it] == 'X' }
        .sumOf { coord ->
            collectWords(coord).count { it == "XMAS" }
        }

    fun part2(): Int = tiles.keys
        .filter { tiles[it] == 'A' && it.x in 1..<width && it.y in 1..<height }
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