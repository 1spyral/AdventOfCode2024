// AOC 2024 Day 14 Problem 2

import java.io.File
import java.io.BufferedReader
import java.awt.image.BufferedImage
import javax.imageio.ImageIO

val file: File = File("input.txt")
val reader: BufferedReader = file.bufferedReader()

val re: Regex = Regex("[^0-9-]")

val width: Int = 101
val height: Int = 103

// x, y, diffX, diffY
val robots: MutableList<MutableList<Int>> = mutableListOf()

var input: String? = reader.readLine()
while (input != null) {
    with (input!!.split(" ", ",").map { x: String -> x.replace(re, "").toInt() }) {
        robots.add(mutableListOf(get(0), get(1), get(2), get(3)))
    }
    input = reader.readLine()
}

for (i in 1 until 100000) {
    val visual: BufferedImage = BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY)

    for (robot in robots) {
        robot[0] += robot[2]
        robot[0] %= width
        robot[0] += width
        robot[0] %= width

        robot[1] += robot[3]
        robot[1] %= height
        robot[1] += height
        robot[1] %= height

        visual.setRGB(robot[0], robot[1], 0xFFFFFF)
    }
    ImageIO.write(visual, "png", File("output/Visual$i.png"))
    visual.flush()
}
