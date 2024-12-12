import util.readInput
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class Day11Test {
    @Test
    fun part1_test() {
        val result = Day11(readInput("Day11_test")).part1()
        assertEquals(55312, result)
    }

    @Test
    fun part1_real() {
        val result = Day11(readInput("Day11")).part1()
        assertEquals(183248, result)
    }
    
    @Test
    fun part2_test() {
        val result = Day11(readInput("Day11_test")).part2()
        assertEquals(65601038650482, result)
    }

    @Test
    fun part2_real() {
        val result = Day11(readInput("Day11")).part2()
        assertEquals(218811774248729, result)
    }
}