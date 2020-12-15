package com.hanspistor.aoc.lib.y2020;

import com.hanspistor.aoc.common.AdventDay;
import org.jgrapht.alg.util.Pair;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class DayEight2020 extends AdventDay {
    public DayEight2020() {
        super(8, 2020);
    }

    @Override
    public String solvePartOne(Stream<String> input) {
        List<Instruction> program = input.map(Instruction::new).collect(Collectors.toList());
        return runProgram(program).getFirst();
    }

    public static Pair<String, Boolean> runProgram(List<Instruction> program) {
        int ip = 0;
        int acc = 0;
        boolean success = false;
        Set<Integer> visitedInstructions = new HashSet();
        while (true) {
            if (ip == program.size()) {
                success = true;
                break;
            }
            if (!visitedInstructions.add(ip)) {
                break;
            }


            Instruction instruction = program.get(ip);
            if (instruction.type.equals("acc")) {
                acc += instruction.value;
            }

            if (instruction.type.equals("jmp")) {
                ip += instruction.value;
            } else {
                ip += 1;
            }

        }

        return new Pair(Integer.toString(acc), success);
    }

    @Override
    public String solvePartTwo(Stream<String> input) {
        List<Instruction> program = input.map(Instruction::new).collect(Collectors.toList());
        int[] jmpNopLoc = IntStream.range(0, program.size()).filter(i -> (program.get(i).type.equals("nop") || program.get(i).type.equals("jmp"))).toArray();
        Pair<String, Boolean> pair = Arrays.stream(jmpNopLoc).mapToObj(i -> {
            List<Instruction> newProgram = program.stream().map(Instruction::new).collect(Collectors.toList());

            if (newProgram.get(i).type.equals("jmp")) {
                newProgram.get(i).type = "nop";
            } else {
                newProgram.get(i).type = "jmp";
            }
            return newProgram;
        }).map(DayEight2020::runProgram).filter(Pair::getSecond).findFirst().orElseThrow(IllegalStateException::new);
        return pair.getFirst();
    }
}


class Instruction {
    public String type;
    public int value;

    public Instruction(Instruction instruction) {
        this.type = instruction.type;
        this.value = instruction.value;
    }

    public Instruction(String instruction) {
        this.type = instruction.split(" ")[0];
        this.value = Integer.parseInt(instruction.split(" ")[1]);
    }

    @Override
    public String toString() {
        return "Instruction{" +
                "type='" + type + '\'' +
                ", value=" + value +
                '}';
    }
}