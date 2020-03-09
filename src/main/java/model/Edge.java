package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import java.util.Objects;
import java.util.Random;

@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Edge {
    private String id;
    private int from;
    private int to;
    private int weight;
    final static Random RANDOM = new Random();
    final static String EDGE_DELIMITER = "_";

    public Edge(int from, int to, int weight) {
        this.id = from + EDGE_DELIMITER + to + EDGE_DELIMITER + weight + EDGE_DELIMITER + RANDOM.nextInt(Graph.MAX_EDGES);
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public Edge(int from, int to) {
        this.id = from + EDGE_DELIMITER + to + EDGE_DELIMITER + 0 + EDGE_DELIMITER + RANDOM.nextInt(Graph.MAX_EDGES);
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return Objects.equals(id, edge.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
