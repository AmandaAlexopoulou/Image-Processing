package com.example.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



public class ImageProcessingUtilTest {

    @Test
    void testNegativeColor() {
        int[][] input = {{0xFFFFFF}};
        int[][] result = ImageProcessingUtil.negativeColor(input);

        int[] rgba = ImageProcessingUtil.getRGBAFromPixel(result[0][0]);

        assertEquals(0, rgba[0]);
        assertEquals(0, rgba[1]);
        assertEquals(0, rgba[2]);
    }

    @Test
    void testTrimBorders() {
        int[][] input = {
            {1,1,1},
            {1,2,1},
            {1,1,1}
        };

        int[][] result = ImageProcessingUtil.trimBorders(input, 1);

        assertEquals(1, result.length);
        assertEquals(1, result[0].length);
        assertEquals(2, result[0][0]);
    }

    @Test
    void testPaintRectangle() {
        int[][] canvas = new int[5][5];
        int color = 0xFF0000;

        int[][] result = ImageProcessingUtil.paintRectangle(canvas, 2, 2, 1, 1, color);

        assertEquals(color, result[1][1]);
        assertEquals(color, result[2][2]);
    }
}