package com.hanspistor.aoc.lib.y2020;

import com.hanspistor.aoc.common.AdventDay;

import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DayFive2020 extends AdventDay {
    public DayFive2020() {
        super(5, 2020);
    }

    @Override
    public String solvePartOne(Stream<String> input) {
        return input.map(this::getSeatId).max(Long::compare).get().toString();
    }

    @Override
    public String solvePartTwo(Stream<String> input) {
        return null;
    }

    public long getSeatId(String seat) {
        String rowStr = seat.substring(0, 7);
        String colStr = seat.substring(7, 10);
        return getRow(rowStr) * 8 + getCol(colStr);
    }

    public long getRow(String rowStr) {
        String binaryStr =  rowStr.chars().mapToObj(c -> (char) c).map(c -> c == 'F' ? '0' : '1').map(Object::toString).collect(Collectors.joining());
        return Long.parseLong(binaryStr, 2);
    }

    public long getCol(String colStr) {
        String binaryStr =  colStr.chars().mapToObj(c -> (char) c).map(c -> c == 'L' ? '0' : '1').map(Object::toString).collect(Collectors.joining());
        return Long.parseLong(binaryStr, 2);
    }

}
