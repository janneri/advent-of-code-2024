// See puzzle in https://adventofcode.com/2024/day/11

class Day11(inputLines: List<String>) {
    private val stones = inputLines.first().split(" ")

    private fun blink(times: Int): Long {
        // {3=1, 386358=1, 86195=1, 85=1, 1267=1, 3752457=1, 0=1, 741=1}
        var stoneCounts: Map<String, Long> =
            stones.groupingBy { it }.eachCount().mapValues { it.value.toLong() }

        repeat(times) {
            val newStoneCounts = mutableMapOf<String, Long>().withDefault { 0 }
            stoneCounts.forEach { (numberStr, currentCount) ->
                if (numberStr.length % 2 == 0) {
                    val mid = numberStr.length / 2
                    val firstHalf = numberStr.substring(0, mid)
                    val secondHalf = numberStr.substring(mid).dropWhile { it == '0' }.ifEmpty { "0" }
                    newStoneCounts[firstHalf] = newStoneCounts.getValue(firstHalf) + currentCount
                    newStoneCounts[secondHalf] = newStoneCounts.getValue(secondHalf) + currentCount
                }
                else if (numberStr == "0") {
                    newStoneCounts["1"] = newStoneCounts.getValue("1") + currentCount
                }
                else {
                    val newNumberStr = (numberStr.toLong() * 2024).toString()
                    newStoneCounts[newNumberStr] = newStoneCounts.getValue(newNumberStr) + currentCount
                }
            }
            stoneCounts = newStoneCounts
        }
        return stoneCounts.values.sum()
    }

    fun part1(): Long = blink(25)
    
    fun part2(): Long = blink(75)

}