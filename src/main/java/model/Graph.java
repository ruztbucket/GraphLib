package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.collect.Maps;
import exception.DuplicateVertexException;
import exception.MissingVertexException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Map;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Graph {

    final static int MAX_EDGES = 100;
    Map<Integer, Vertex> vertices;

    public static Graph newGraph() {
        Graph graph = new Graph();
        graph.vertices = Maps.newHashMap();
        return graph;
    }

    public void addVertex(Vertex v) throws DuplicateVertexException {
        if (vertices.containsKey(v.getId())) {
            throw new DuplicateVertexException("Duplicate vertex: " + v.getId());
        }
        vertices.put(v.getId(), v);
    }

    public void addVertex(int id) throws DuplicateVertexException {
        Vertex vertex = new Vertex(id);
        addVertex(vertex);
    }

    public void addEdge(Vertex from, Vertex to, int weight) throws MissingVertexException {
        if (!vertices.containsKey(from.getId())) {
            throw new MissingVertexException("Missing vertex: " + from.getId());
        }
        if (!vertices.containsKey(to.getId())) {
            throw new MissingVertexException("Missing vertex: " + to.getId());
        }
        Edge edge = new Edge(from.getId(), to.getId(), weight);
        from.getOutEdges().put(edge.getId(), edge);
        to.getInEdges().put(edge.getId(), edge);
    }

    public void addEdge(Vertex from, Vertex to) throws MissingVertexException {
        addEdge(from, to, 0);
    }

    public void addEdge(int fromId, int toId, int weight) throws MissingVertexException {
        if (!vertices.containsKey(fromId)) {
            throw new MissingVertexException("Missing vertex: " + fromId);
        }
        if (!vertices.containsKey(toId)) {
            throw new MissingVertexException("Missing vertex: " + toId);
        }
        addEdge(vertices.get(fromId), vertices.get(toId), weight);
    }

    public void addEdge(int fromId, int toId) throws MissingVertexException {
        addEdge(fromId, toId, 0);
    }

    public void addBidirectionalEdge(Vertex v1, Vertex v2, int weight) throws MissingVertexException {
        addEdge(v1, v2, weight);
        addEdge(v2, v2, weight);
    }

    public void addBidirectionalEdge(Vertex v1, Vertex v2) throws MissingVertexException {
        addBidirectionalEdge(v1, v2, 0);
    }

    public void addBidirectionalEdge(int fromId, int toId, int weight) throws MissingVertexException {
        addEdge(fromId, toId, 0);
    }

    public void addBidirectionalEdge(int fromId, int toId) throws MissingVertexException {
        addBidirectionalEdge(fromId, toId, 0);
    }

    public void removeEdge(Edge edge) {
        vertices.get(edge.getFrom()).getOutEdges().remove(edge.getId());
        vertices.get(edge.getTo()).getInEdges().remove(edge.getId());
    }

    public void removeEdge(String id) {
        int fromVertex = getVertexFromEdge(id);
        Edge edge = vertices.get(fromVertex).getOutEdges().get(id);
        int toVertex = edge.getTo();
        vertices.get(fromVertex).getOutEdges().remove(id);
        vertices.get(toVertex).getInEdges().remove(id);
    }

    public void removeVertex(int id) {
        // TODO: do I need this?
    }

    public void print() {
        // TODO: something pretty
    }

    // UTIL Methods
    private int getVertexFromEdge(String edgeId) {
        String[] split = edgeId.split(Edge.EDGE_DELIMITER);
        return Integer.parseInt(split[0]);
    }
}
