
import util.readInput
import kotlin.test.Test
import kotlin.test.assertEquals

class Day22Test {
    @Test
    fun part1_test() {
        val result = Day22(readInput("Day22_test")).part1()
        assertEquals(37327623, result)
    }

    @Test
    fun part1_real() {
        val result = Day22(readInput("Day22")).part1()
        assertEquals(14119253575, result)
    }
    
    @Test
    fun part2_test() {
        val result = Day22("""
            1
            2
            3
            2024
        """.trimIndent().lines()).part2()
        assertEquals(23, result)
    }

    @Test
    fun part2_real() {
        val result = Day22(readInput("Day22")).part2()
        assertEquals(1600, result)
    }
}