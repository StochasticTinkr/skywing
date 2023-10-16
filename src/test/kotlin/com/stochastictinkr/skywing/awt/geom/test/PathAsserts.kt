package com.stochastictinkr.skywing.awt.geom.test

import org.junit.jupiter.api.Assertions
import java.awt.geom.PathIterator

fun assertPathsAreEqual(expectedPath: PathIterator, actualPath: PathIterator) {
    while (!actualPath.isDone && !expectedPath.isDone) {
        val expectedCords = DoubleArray(6)
        val expectedType = expectedPath.currentSegment(expectedCords)
        expectedPath.next()

        val actualCords = DoubleArray(6)
        val actualType = actualPath.currentSegment(actualCords)
        actualPath.next()

        Assertions.assertEquals(expectedType, actualType, "Incorrect type of segment in path")
        Assertions.assertArrayEquals(expectedCords, actualCords, "Incorrect coordinates for segment")
    }
    Assertions.assertEquals(expectedPath.isDone, actualPath.isDone, "Different length of paths")
}