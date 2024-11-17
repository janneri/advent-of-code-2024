// See puzzle in https://adventofcode.com/2023/day/1

class Day01(private val inputLines: List<String>) {

    fun part1(): Int =
        inputLines.sumOf { line ->
            val first = line.first { it.isDigit() }
            val last = line.last { it.isDigit() }
            "$first$last".toInt()
        }

    fun part2(): Int {
        val words = mapOf(
            "one" to 1, "two" to 2, "three" to 3, "four" to 4,
            "five" to 5, "six" to 6, "seven" to 7, "eight" to 8, "nine" to 9
        )

        fun findDigit(idx: Int, str: String): Int? {
            return str[idx].digitToIntOrNull() ?:
            words.entries.firstOrNull { str.startsWith(it.key, idx) }?.value
        }

        return inputLines.sumOf { line ->
            val digits = line.mapIndexedNotNull { i, _ -> findDigit(i, line) }
            val result = if (digits.size == 1) "${digits[0]}${digits[0]}" else "${digits.first()}${digits.last()}"
            result.toInt()
        }

    }
}