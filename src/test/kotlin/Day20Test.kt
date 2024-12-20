
import util.readInput
import kotlin.test.Test
import kotlin.test.assertEquals

class Day20Test {
//    @Test
//    fun part1_test() {
//        val result = Day20(readInput("Day20_test")).part1()
//        assertEquals(2, result)
//    }

    @Test
    fun part1_real() {
        val result = Day20(readInput("Day20")).part1()
        assertEquals(1311, result)
    }
    
//    @Test
//    fun part2_test() {
//        val result = Day20(readInput("Day20_test")).part2()
//        assertEquals(2, result)
//    }

    @Test
    fun part2_real() {
        val result = Day20(readInput("Day20")).part2()
        assertEquals(961364, result)
    }
}