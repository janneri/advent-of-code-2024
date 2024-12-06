import util.readInput
import kotlin.test.Test
import kotlin.test.assertEquals

class Day06Test {
    @Test
    fun part1_test() {
        val result = Day06(readInput("Day06_test")).part1()
        assertEquals(41, result)
    }

    @Test
    fun part1_real() {
        val result = Day06(readInput("Day06")).part1()
        assertEquals(4647, result)
    }
    
    @Test
    fun part2_test() {
        val result = Day06(readInput("Day06_test")).part2()
        assertEquals(6, result)
    }

    @Test
    fun part2_real() {
        val result = Day06(readInput("Day06")).part2()
        assertEquals(1723, result)
    }
}