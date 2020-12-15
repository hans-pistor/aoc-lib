package com.hanspistor.aoc.lib.y2020;

import com.google.common.collect.Streams;
import com.hanspistor.aoc.common.AdventDay;
import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.alg.util.Pair;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import java.util.*;
import java.util.stream.Stream;

public class DaySeven2020 extends AdventDay {
    Graph<String, DefaultWeightedEdge> graph;
    public DaySeven2020() {
        super(7, 2020);
    }

    @Override
    public String solvePartOne(Stream<String> input) {
        graph = getGraphFromInput(input);

        DijkstraShortestPath path = new DijkstraShortestPath(graph);
        return Long.toString(graph.vertexSet().stream().filter(s -> !s.equals("shiny gold")).filter(s -> path.getPath(s, "shiny gold") != null).count());
    }

    @Override
    public String solvePartTwo(Stream<String> input) {
        graph = getGraphFromInput(input);
        Pair<String, Integer> pair = new Pair<>("shiny gold", 1);

        return Integer.toString(getActualWeightedEdge(pair).mapToInt(p -> p.getSecond()).sum() - 1);
    }

    public Stream<Pair<String, Integer>> getActualWeightedEdge(Pair<String, Integer> pair) {
        Set<DefaultWeightedEdge> edges = graph.outgoingEdgesOf(pair.getFirst());
        if (edges.isEmpty()) {
            return Stream.of(pair);
        }
        else {
            return Streams.concat(Stream.of(pair), edges
                    .stream()
                    .map(e -> new Pair<>(graph.getEdgeTarget(e), (int) (graph.getEdgeWeight(e) * pair.getSecond())))
                    .flatMap(this::getActualWeightedEdge));
        }
    }

    public static SimpleDirectedWeightedGraph<String, DefaultWeightedEdge> getGraphFromInput(Stream<String> input) {
        SimpleDirectedWeightedGraph<String, DefaultWeightedEdge> graph = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
        input.map(Rule::new).forEach(rule -> {
            graph.addVertex(rule.color);
            rule.containMap.forEach((key, val) -> {
                graph.addVertex(key);
                Graphs.addEdge(graph, rule.color, key, val);
            });
        });
        return graph;
    }

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

    }

}
