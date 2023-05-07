package com.stochastictinkr.skywing.awt.geom.test

import org.junit.jupiter.api.Assertions
import java.awt.geom.PathIterator

fun assertPathsAreEqual(expectedPath: PathIterator, actualPath: PathIterator) {
    while (!actualPath.isDone && !expectedPath.isDone) {
        val expectedCoords = DoubleArray(6)
        val expectedType = expectedPath.currentSegment(expectedCoords)
        expectedPath.next()

        val actualCoords = DoubleArray(6)
        val actualType = actualPath.currentSegment(actualCoords)
        actualPath.next()

        Assertions.assertEquals(expectedType, actualType, "Incorrect type of segment in path")
        Assertions.assertArrayEquals(expectedCoords, actualCoords, "Incorrect coordinates for segment")
    }
    Assertions.assertEquals(expectedPath.isDone, actualPath.isDone, "Different length of paths")
}