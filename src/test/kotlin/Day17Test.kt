
import util.readInputText
import kotlin.test.Test
import kotlin.test.assertEquals

class Day17Test {
    @Test
    fun part1_test() {
        val result = Day17(readInputText("Day17_test")).part1()
        assertEquals("4635635210", result)
    }

    @Test
    fun part1_real() {
        val result = Day17(readInputText("Day17")).part1()
        assertEquals("163656517", result)
    }


    @Test
    fun part2_test() {
        val result = Day17("""
            Register A: 729
            Register B: 0
            Register C: 0

            Program: 0,3,5,4,3,0
        """.trimIndent()).part2()
        assertEquals(117440, result)
    }

    @Test
    fun part2_real() {
        val result = Day17(readInputText("Day17")).part2()
        assertEquals(247839653009594, result)
    }
}