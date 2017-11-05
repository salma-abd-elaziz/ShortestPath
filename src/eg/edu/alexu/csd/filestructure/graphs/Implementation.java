package eg.edu.alexu.csd.filestructure.graphs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Implementation implements IGraph{

	private Graph graph;
	public void readGraph(File file) {
		try {
			FileReader fileReader = new FileReader(file.getAbsolutePath());
			BufferedReader reader = new BufferedReader (fileReader);
			String line = reader.readLine();
			String[] splitArray = line.split("\\s+");
			graph = new Graph(Integer.parseInt(splitArray[0]), Integer.parseInt(splitArray[1]));
			int numberOfEdges = Integer.parseInt(splitArray[1]);
			for(int i = 0; i < numberOfEdges; i++){
				line = reader.readLine();
				splitArray = line.split("\\s+");
				graph.addEdge(Integer.parseInt(splitArray[0]), Integer.parseInt(splitArray[1]), Integer.parseInt(splitArray[2]));
			}
			line = reader.readLine();
			if(line != null){
				reader.close();
				fileReader.close();
				throw new RuntimeException();
			}
			fileReader.close();
			reader.close();
		} catch (Exception e) {
			throw new RuntimeException(); 
		}
	}

	public int size() {
		return graph.getEdgesNumber();
	}

	public ArrayList<Integer> getVertices() {
		return graph.getVertices();
	}

	public ArrayList<Integer> getNeighbors(int v) {
		return graph.getNeighbors(v);
	}

	public void runDijkstra(int src, int[] distances) {
		graph.runDijkstra(src, distances);
	}

	public ArrayList<Integer> getDijkstraProcessedOrder() {
		return graph.getDijkstraProcessedOrder();
	}

	public boolean runBellmanFord(int src, int[] distances) {
		return graph.runBellmanFord(src, distances);
	}
}
