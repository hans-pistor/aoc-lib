package com.hanspistor.aoc.lib.y2020;

import com.hanspistor.aoc.common.AdventDay;

import java.util.List;
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
        List<BoardingPass> bps = input.map(this::getBoardingPass).filter(bp -> (bp.row > 0 && bp.row < 127)).sorted().collect(Collectors.toList());
        for (int i = 0; i < bps.size() - 1; i++) {
            BoardingPass current = bps.get(i);
            BoardingPass next =bps.get(i+1);
            if (current.seatId + 2 == next.seatId) {
                return Long.toString(current.seatId + 1);
            }
        }

        return "Seat not found";
    }

    public BoardingPass getBoardingPass(String seat) {
        String rowStr = seat.substring(0, 7);
        String colStr = seat.substring(7, 10);

        long row = getRow(rowStr);
        long col = getCol(colStr);

        return new BoardingPass(row, col);
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

    class BoardingPass implements Comparable<BoardingPass> {
        long row;
        long col;
        long seatId;

        public BoardingPass(long row, long col) {
            this.row = row;
            this.col = col;
            this.seatId = 8 * this.row + this.col;
        }


        @Override
        public int compareTo(BoardingPass o) {
            if (o.seatId > this.seatId) {
                return -1;
            }
            return 1;
        }
    }
}
