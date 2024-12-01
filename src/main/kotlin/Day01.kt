import kotlin.math.abs

// See puzzle in https://adventofcode.com/2024/day/1

class Day01(inputLines: List<String>) {
    private val leftNums = mutableListOf<Int>()
    private val rightNums = mutableListOf<Int>()

    init {
        inputLines.map { line ->
            val (left, right) = line.split("   ")
            leftNums.add(left.toInt())
            rightNums.add(right.toInt())
        }
    }

    fun part1(): Int {
        leftNums.sort()
        rightNums.sort()

        return leftNums.indices.sumOf { index ->
            abs(leftNums[index] - rightNums[index])
        }
    }
    
    fun part2(): Int =
        leftNums.sumOf { left ->
            left * rightNums.count { it == left }
        }
}