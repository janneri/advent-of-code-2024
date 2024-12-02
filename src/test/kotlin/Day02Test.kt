import util.readInput
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class Day02Test {
    @Test
    fun part1_test() {
        val result = Day02(readInput("Day02_test")).part1()
        assertEquals(2, result)
    }

    @Test
    fun part1_real() {
        val result = Day02(readInput("Day02")).part1()
        assertEquals(314, result)
    }
    
    @Test
    fun part2_test() {
        val result = Day02(readInput("Day02_test")).part2()
        assertEquals(4, result)
    }

    @Test
    fun part2_real() {
        val result = Day02(readInput("Day02")).part2()
        assertEquals(373, result)
    }
}