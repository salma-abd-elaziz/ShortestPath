package eg.edu.alexu.csd.filestructure.graphs;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class Mine {

	public static void main(String[] args) {
	Implementation g = new Implementation();
	File file = new File("C://Users//salma  pc//Desktop//Read.txt");
	/*im.readGraph(file);
	im.size();
	im.getVertices();
	im.getNeighbors(0);
	int[] distances = new int[5];
	im.runDijkstra(3, distances);
//	im.getDijkstraProcessedOrder();
//	im.runBellmanFord(0, distances);
	System.out.println();
	for(int i=0; i<distances.length ;i++){
		System.out.println(distances[i]);
		}
	im.runDijkstra(0, distances);
	System.out.println((im.getD Graph g = new Graph();
     File file  = new File ("test.txt");*/
     g.readGraph(file);
     int []array = new int [8];
     g.runDijkstra(0, array);
     System.out.println(Arrays.toString(array));
	}

}
