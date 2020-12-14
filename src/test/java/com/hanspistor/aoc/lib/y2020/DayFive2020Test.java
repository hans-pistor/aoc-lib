package com.hanspistor.aoc.lib.y2020;

import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.function.Function;
import java.util.stream.Stream;

public class DayFive2020Test {

    @Test
    public void testGetRow() {
        DayFive2020 day = new DayFive2020();
        String exampleRow = "FBFBBFF";

        Assert.assertEquals(44, day.getRow(exampleRow));
    }

    @Test
    public void testGetCol() {
        DayFive2020 day = new DayFive2020();
        String exampleCol = "RLR";

        Assert.assertEquals(5, day.getCol(exampleCol));
    }

    @Test
    public void testGetSeatId() {
        DayFive2020 day = new DayFive2020();
        String seat = "FBFBBFFRLR";
        Assert.assertEquals(357, day.getSeatId(seat));
    }

    @Test
    public void testPartOne() {
        DayFive2020 day = new DayFive2020();

        InputStream inputStream = this.getClass().getResourceAsStream(day.getInputPath());
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        Stream<String> stringStream = new BufferedReader(inputStreamReader).lines();

        Function<Stream<String>, String> fn = day::solvePartOne;
        String result = fn.apply(stringStream);
        Assert.assertEquals("820", result);
    }

    @Test public void testPartTwo() {
        DayFive2020 day = new DayFive2020();

        InputStream inputStream = this.getClass().getResourceAsStream(day.getInputPath());
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        Stream<String> stringStream = new BufferedReader(inputStreamReader).lines();

        Function<Stream<String>, String> fn = day::solvePartTwo;
        String result = fn.apply(stringStream);
//        Assert.assertEquals(result, "241861950");
    }
}
