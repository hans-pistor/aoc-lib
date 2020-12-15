package com.hanspistor.aoc.lib.y2020;

import com.google.common.collect.*;
import com.hanspistor.aoc.common.AdventDay;
import org.jgrapht.alg.util.Pair;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class DayNine2020 extends AdventDay {
    public DayNine2020() {
        super(9, 2020);
    }

    @Override
    public String solvePartOne(Stream<String> input) {
        return findFirstBadNumber(input, 25).toString();
    }

    @Override
    public String solvePartTwo(Stream<String> input) {
        List<String> collected = input.collect(Collectors.toList());
        Long badInt = findFirstBadNumber(collected.stream(), 25);

        LinkedList<Long> ll = new LinkedList<>();
        Long astVal = collected.stream().mapToLong(Long::parseLong).flatMap(i -> {
            ll.add(i);
            while (ll.stream().mapToLong(j -> j).sum() > badInt) {
                ll.removeFirst();
            }
            System.out.println(ll);
            if (ll.stream().mapToLong(j -> j).sum() == badInt) {
                return LongStream.of(i);
            } else {
                return LongStream.empty();
            }
        }).findFirst().getAsLong();



        List<Long> finalInts = ll.stream().sorted().collect(Collectors.toList());

        Long weakness = finalInts.get(0) + finalInts.get(finalInts.size() - 1);
        return weakness.toString();
    }

    public static Long findFirstBadNumber(Stream<String> input, int preamble) {
        EvictingQueue<Long> queue = EvictingQueue.create(preamble);
        Long firstWrong = input.mapToLong(Long::parseLong).flatMap(value -> {
            if (queue.size() != preamble) {
                queue.add(value);
                return LongStream.empty();
            }
            Set<Set<Long>> combinations = Sets.combinations(Sets.newHashSet(queue), 2);
            OptionalLong xmas = combinations.stream().mapToLong(s -> {
                Iterator<Long> iter = s.iterator();
                return iter.next() + iter.next();
            }).filter(sum -> sum == value).findFirst();

            if (xmas.isPresent()) {
                queue.add(value);
                return LongStream.empty();
            } else {
                return LongStream.of(value);
            }

        }).findFirst().getAsLong();
        return firstWrong;
    }
}
