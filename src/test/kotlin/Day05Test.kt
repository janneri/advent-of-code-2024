import util.readInputText
import kotlin.test.Test
import kotlin.test.assertEquals

class Day05Test {
    @Test
    fun part1_test() {
        val result = Day05(readInputText("Day05_test")).part1()
        assertEquals(143, result)
    }

    @Test
    fun part1_real() {
        val result = Day05(readInputText("Day05")).part1()
        assertEquals(4185, result)
    }
    
    @Test
    fun part2_test() {
        val result = Day05(readInputText("Day05_test")).part2()
        assertEquals(123, result)
    }

    @Test
    fun part2_real() {
        val result = Day05(readInputText("Day05")).part2()
        assertEquals(4480, result)
    }
}