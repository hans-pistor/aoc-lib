package com.hanspistor.aoc.lib.y2020;

import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.function.Function;
import java.util.stream.Stream;

public class DayNine2020Test {


    @Test
    public void testPartOne() {
        DayNine2020 day = new DayNine2020();

        InputStream inputStream = this.getClass().getResourceAsStream(day.getInputPath());
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        Stream<String> stringStream = new BufferedReader(inputStreamReader).lines();

        String result = DayNine2020.findFirstBadNumber(stringStream, 5).toString();
//        Function<Stream<String>, String> fn = day::solvePartOne;
//        String result = fn.apply(stringStream);
        Assert.assertEquals("127", result);
    }

    @Test
    public void testPartTwo() {
        DayNine2020 day = new DayNine2020();

        InputStream inputStream = this.getClass().getResourceAsStream(day.getInputPath());
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        Stream<String> stringStream = new BufferedReader(inputStreamReader).lines();

        Function<Stream<String>, String> fn = day::solvePartTwo;
        String result = fn.apply(stringStream);
        Assert.assertEquals("62", result);
    }
}
