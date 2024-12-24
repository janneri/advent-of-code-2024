
import util.readInputText
import kotlin.test.Test
import kotlin.test.assertEquals

class Day24Test {
    @Test
    fun part1_test1() {
        val result = Day24("""
            x00: 1
            x01: 1
            x02: 1
            y00: 0
            y01: 1
            y02: 0

            x00 AND y00 -> z00
            x01 XOR y01 -> z01
            x02 OR y02 -> z02
        """.trimIndent()).part1()
        assertEquals(4, result)
    }

    @Test
    fun part1_test2() {
        val result = Day24(readInputText("Day24_test")).part1()
        assertEquals(2024, result)
    }

    @Test
    fun part1_real() {
        val result = Day24(readInputText("Day24")).part1()
        assertEquals(57588078076750, result)
    }
    
    @Test
    fun part2_real() {
        val result = Day24(readInputText("Day24")).part2()
        assertEquals("kcd,pfn,shj,tpk,wkb,z07,z23,z27", result)
    }
}