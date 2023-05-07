package com.stochastictinkr.skywing.awt.geom

import com.stochastictinkr.skywing.awt.geom.test.assertPathsAreEqual
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertSame
import org.junit.jupiter.api.Test
import java.awt.Shape
import java.awt.geom.AffineTransform
import java.awt.geom.Area
import java.awt.geom.Line2D
import java.awt.geom.Point2D

internal class AffineTransformKtTest {

    @Test
    fun makeTransform() {
        val body: AffineTransform
        val result = makeTransform {
            body = this
        }
        assertSame(body, result)
    }

    @Test
    fun plusAssign() {
        val left = mockk<AffineTransform>()
        val right = mockk<AffineTransform>()
        every { left.concatenate(right) } returns Unit
        left += right

        verify(exactly = 1) { left.concatenate(right) }
    }

    @Test
    fun `invoke(Transformable)`() {
        val affineTransform = mockk<AffineTransform>()
        val transformable = mockk<Transformable<*>>()
        val expected = mockk<Transformable<*>>()
        every { transformable.transform(affineTransform) } returns expected
        val result = affineTransform(transformable)
        assertSame(expected, result)

        verify(exactly = 1) { transformable.transform(affineTransform) }
    }

    @Test
    fun `invoke(Point2D)`() {
        val affineTransform = mockk<AffineTransform>()
        val point = mockk<Point2D>()
        val expected = mockk<Point2D>()
        every { affineTransform.transform(point, any()) } returns expected
        val result = affineTransform(point)
        assertSame(expected, result)

        verify(exactly = 1) { affineTransform.transform(point, any()) }
    }

    @Test
    fun `invoke(Line2D)`() {
        val affineTransform = makeTransform {
            translate(4.0, 4.0)
        }
        val line: Line2D = line(1.0, 2.0, 3.0, 4.0)

        val result = affineTransform(line).run { doubleArrayOf(x1, y1, x2, y2) }

        assertArrayEquals(doubleArrayOf(5.0, 6.0, 7.0, 8.0), result)

    }

    @Test
    fun `invoke(Shape)`() {
        val line: Shape = line(0.0, 1.0, 2.0, 3.0)
        val result = makeTransform {
            translate(10.0, 15.0)
        }(line)
        val expectedPath = line(10.0, 16.0, 12.0, 18.0).getPathIterator(null)
        val actualPath = result.getPathIterator(null)
        assertPathsAreEqual(expectedPath, actualPath)
    }


    @Test
    fun `invoke(Area)`() {
        val transform = makeTransform {
            this.shear(1.0, 2.0)
        }
        val shape: Shape = rectangle(0, 1, 10, 12)
        val result = transform(Area(shape))

        assertPathsAreEqual(shape.getPathIterator(transform), result.getPathIterator(null))
    }


    @Test
    fun `transformEach(Iterable)`() {
        val transform = mockk<AffineTransform>()
        val list = List<Transformable<*>>(5) { mockk() }
        val resultObjects = List<Transformable<*>>(5) { mockk() }
        list.indices.forEach {
            every { list[it].transform(transform) } returns resultObjects[it]
        }
        val result = list.map(transform)

        assertEquals(list.size, result.size)

        result.indices.forEach { idx ->
            assertSame(resultObjects[idx], result[idx])
        }

        list.forEach {
            verify(exactly = 1) { it.transform(transform) }
        }
    }

    @Test
    fun `transformPoints(Iterable)`() {
    }

    @Test
    fun `transformLines(Iterable)`() {
    }

    @Test
    fun `transformAreas(Iterable)`() {
    }

    @Test
    fun `transformShapes(Iterable)`() {
    }

    @Test
    fun `transformEach(Sequence)`() {
    }

    @Test
    fun `transformPoints(Sequence)`() {
    }

    @Test
    fun `transformLines(Sequence)`() {
    }

    @Test
    fun `transformShapes(Sequence)`() {
    }

    @Test
    fun `transformAreas(Sequence)`() {
    }
}

