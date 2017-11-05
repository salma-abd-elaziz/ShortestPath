package eg.edu.alexu.csd.filestructure.graphs;

import java.util.ArrayList;


public class Graph {
	private int verticesNumber = 0;
	private int edgesNumber = 0;
	private ArrayList<ArrayList<Integer>> graph;
	private int[][] weights;
	private ArrayList<Integer> dijkstraProcessedOrder;

	public Graph(int verticesNumber, int edgesNumber) {
		this.verticesNumber = verticesNumber;
		this.edgesNumber = edgesNumber;
		graph = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < verticesNumber; i++){
			graph.add(new ArrayList<Integer>());
		}
		weights = new int[verticesNumber][verticesNumber];
		dijkstraProcessedOrder = new ArrayList<Integer>();
	}

	public int getEdgesNumber() {
		return edgesNumber;
	}

	public void setEdgesNumber(int edgesNumber) {
		this.edgesNumber = edgesNumber;
	}

	public int getVertecesNumber() {
		return verticesNumber;
	}

	public void setVertecesNumber(int vertecesNumber) {
		this.verticesNumber = vertecesNumber;
	}

	public void addEdge(int src, int dest, int weight) {
		if (src > -1 && src < verticesNumber && dest > -1
				&& dest < verticesNumber) {
			graph.get(src).add(dest);
			weights[src][dest] = weight;
		}
	}

	public ArrayList<Integer> getVertices() {
		ArrayList<Integer> vertices = new ArrayList<Integer>();
		for (int i = 0; i < graph.size(); i++) {
			vertices.add(i);
		}
		return vertices;
	}

	public ArrayList<Integer> getNeighbors(int v) {
		return graph.get(v);
	}

	public void runDijkstra(int src, int[] distances) {
		boolean[] visit = new boolean[verticesNumber];
		for (int i = 0; i < distances.length; i++) {
			distances[i] = Integer.MAX_VALUE;
		}
		distances[src] = 0;
		for (int i = 0; i < verticesNumber - 1; i++) {
			int firstVertex = getMin(visit, distances);
			if(firstVertex < 0){
				return;
			}
			visit[firstVertex] = true;
			dijkstraProcessedOrder.add(firstVertex);
			for (int j = 0; j < getNeighbors(firstVertex).size(); j++) {
				// relaxation
				if (distances[firstVertex] != Integer.MAX_VALUE && !visit[getNeighbors(firstVertex).get(j)]&& distances[firstVertex]+ weights[firstVertex][getNeighbors(firstVertex).get(j)] < distances[getNeighbors(firstVertex).get(j)]) {
					distances[getNeighbors(firstVertex).get(j)] = distances[firstVertex]+ weights[firstVertex][getNeighbors(firstVertex).get(j)];
				}
			}
		}
		for (int i = 0; i < visit.length; i++) {
			if (!visit[i]) {
				dijkstraProcessedOrder.add(i);
			}
		}
		
	}

	public ArrayList<Integer> getDijkstraProcessedOrder() {
		return dijkstraProcessedOrder;
	}
	
	// works with negative weights. 
	public boolean runBellmanFord(int src, int[] distances) {
		for (int i = 0; i < distances.length; i++) {
			distances[i] = Integer.MAX_VALUE;
		}
		distances[src] = 0;
		for (int i = 0; i < verticesNumber - 1; i++) {
			for (int j = 0; j < verticesNumber; j++) {
				ArrayList<Integer> edges = graph.get(j);
				for (int k = 0; k < edges.size(); k++) {
					int dest = edges.get(k);
					if (distances[j] != Integer.MAX_VALUE && distances[j] + weights[j][dest] < distances[dest]) {
						distances[dest] = distances[j]+ weights[j][dest];
					}
				}
			}
		}
		// Negative cycle exists.
		for (int j = 0; j < verticesNumber; j++) {
			ArrayList<Integer> edges = graph.get(j);
			for (int k = 0; edges != null && k < edges.size(); k++) {
				if (distances[j] + weights[j][edges.get(k)] < distances[edges.get(k)]) {
					return false;
				}
			}
		}
		return true;
	}

	private int getMin(boolean[] visit, int[] distances) {
		int minValue = Integer.MAX_VALUE;
		int minIndex = Integer.MIN_VALUE;
		for (int i = 0; i < verticesNumber; i++) {
			if (!visit[i] && distances[i] <= minValue) {
				minValue = distances[i];
				minIndex = i;
			}
		}
		return minIndex;
	}
}
