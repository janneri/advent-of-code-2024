
import util.readInputText
import kotlin.test.Test
import kotlin.test.assertEquals

class Day19Test {
    @Test
    fun part1_test() {
        val result = Day19(readInputText("Day19_test")).part1()
        assertEquals(6, result)
    }

    @Test
    fun part1_real() {
        val result = Day19(readInputText("Day19")).part1()
        assertEquals(336, result)
    }
    
    @Test
    fun part2_test() {
        val result = Day19(readInputText("Day19_test")).part2()
        assertEquals(16, result)
    }

    @Test
    fun part2_real() {
        val result = Day19(readInputText("Day19")).part2()
        assertEquals(758890600222015, result)
    }
}