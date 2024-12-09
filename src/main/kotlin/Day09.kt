// See puzzle in https://adventofcode.com/2024/day/9


class Day09(input: String) {
    private val aDisk = Disk.of(input)

    data class AFile(val id: Int, var position: Int, val size: Int)
    data class Space(var position: Int, var size: Int)

    data class Disk(
        var files: MutableList<AFile>,
        var spaces: MutableList<Space>
    ) {

        fun rearrangeFiles(): Disk {
            files.reversed().forEach { file ->
                findSpace(file)?.let { space -> moveFile(file, space) }
            }
            return this
        }

        fun reArrangedBits(): List<Int> {
            val newDisk = mutableListOf<Int>()
            val bits = bits()
            val reversedBits = bits.reversed()

            var reverseIdx = 0
            (0..<files.sumOf { it.size }).forEach { idx ->
                if (bits[idx] != -1) {
                    newDisk.add(bits[idx])
                }
                else {
                    while (reversedBits[reverseIdx] == -1) {
                        reverseIdx += 1
                    }
                    newDisk.add(reversedBits[reverseIdx])
                    reverseIdx += 1
                }
            }

            return newDisk
        }

        fun bits(): List<Int> {
            return (0..<totalSize()).map { idx ->
                val file = files.find { idx in (it.position..<it.position + it.size) }
                file?.id ?: -1
            }
        }

        fun checksum(bits: List<Int>): Long = bits.foldIndexed(0L) { idx, acc, id ->
            if (id == -1) acc else acc + (idx * id)
        }

        private fun totalSize() = files.sumOf { it.size } + spaces.sumOf { it.size }

        private fun findSpace(file: AFile): Space? =
            spaces.find { space -> space.size >= file.size && space.position < file.position }

        private fun moveFile(file: AFile, space: Space) {
            val origFilePos = file.position

            file.position = space.position
            if (file.size == space.size) {
                space.position = origFilePos
            }
            else {
                spaces.add(Space(origFilePos, file.size))
                space.position += file.size
                space.size -= file.size
            }
            files.sortBy { it.position }
            spaces.sortBy { it.position }
        }


        override fun toString(): String =
            bits().joinToString("") { if (it == -1) "." else it.toString() }

        companion object {
            fun of(input: String): Disk {
                val fileSizes: List<Int> = input.filterIndexed { idx, _ -> idx % 2 == 0 }.map { it.digitToInt() }
                val spaceSizes: List<Int> = input.filterIndexed { idx, _ -> idx % 2 != 0 }.map { it.digitToInt() }

                val aDisk = Disk(mutableListOf(), mutableListOf())
                var position = 0
                fileSizes.forEachIndexed { idx, fileSize ->
                    aDisk.files.add(AFile(idx, position, fileSize))
                    position += fileSize
                    if (idx < fileSizes.lastIndex) {
                        aDisk.spaces.add(Space(position, spaceSizes[idx]))
                        position += spaceSizes[idx]
                    }
                }
                return aDisk
            }
        }
    }

    fun part1(): Long = aDisk.checksum(aDisk.reArrangedBits())

    fun part2(): Long = aDisk.rearrangeFiles().checksum(aDisk.bits())
}