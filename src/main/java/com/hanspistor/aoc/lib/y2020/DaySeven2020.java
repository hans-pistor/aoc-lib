package com.hanspistor.aoc.lib.y2020;

import com.hanspistor.aoc.common.AdventDay;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class DaySeven2020 extends AdventDay {
    public DaySeven2020() {
        super(7, 2020);
    }

    @Override
    public String solvePartOne(Stream<String> input) {
        input.forEach(Rule::new);
        return null;
    }

    @Override
    public String solvePartTwo(Stream<String> input) {
        return null;
    }

    class Rule {
        String color;
        Map<String, Integer> containMap = new HashMap<>();
        public Rule(String sentence) {
            this.color = sentence.split(" bags contain ")[0];
            String containsBags = sentence.split(" bags contain ")[1];
            List<String> otherBags = Arrays.asList(containsBags.split(", |\\."));
            otherBags.forEach(s -> {
                if (s.contains("no other bags")) {
                    return;
                }
                String[] bagWords = s.split(" ");
                int num = Integer.parseInt(bagWords[0]);
                String bagColor = bagWords[1] + " " + bagWords[2];
                this.containMap.put(bagColor, num);
            });

            System.out.println("Bag color: " + this.color);
            for (Map.Entry entry: containMap.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
            System.out.println();
        }

    }
}
