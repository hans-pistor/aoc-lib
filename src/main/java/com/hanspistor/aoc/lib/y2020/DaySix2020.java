package com.hanspistor.aoc.lib.y2020;

import com.hanspistor.aoc.common.AdventDay;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DaySix2020 extends AdventDay {
    public DaySix2020() {
        super(6, 2020);
    }

    @Override
    public String solvePartOne(Stream<String> input) {
        List<List<String>> inputGroups = parseToGroups(input);

        return ((Long) inputGroups.stream()
                .map(group -> group.stream().map(this::getGroupChars).flatMap(Collection::stream).distinct().count())
                .mapToLong(Long::longValue).sum())
                .toString();
    }

    @Override
    public String solvePartTwo(Stream<String> input) {
        List<List<String>> inputGroups = parseToGroups(input);
        long count = 0L;
        for (List<String> group : inputGroups) {
            List<Character> allAns = group.stream().map(this::getGroupChars).flatMap(Collection::stream).collect(Collectors.toList());
            count += allAns.stream().distinct().filter(ans -> Collections.frequency(allAns, ans) == group.size()).count();
        }
        return Long.toString(count);
    }

    private List<List<String>> parseToGroups(Stream<String> input) {
        String inputStr = input.collect(Collectors.joining("\n"));
        String[] groupSplit = inputStr.split("\n\n");
        List<List<String>> inputGroups = new ArrayList<>();
        for (String group: groupSplit) {
            inputGroups.add(Arrays.asList(group.split("\n")));
        }
        return inputGroups;
    }

    public List<Character> getGroupChars(String s) {
        return s.chars().distinct().mapToObj(c -> (char)c).collect(Collectors.toList());
    }

}
