package matala1mivnei;

import static org.junit.Assert.*;
import matala1mivnei.DikjstraAlgorithm.Graph;

import org.junit.Test;

public class shortestpathtest {

	@Test
	public void test() {

		DikjstraAlgorithm test=new DikjstraAlgorithm();
		Graph g=new Graph();
	
		g.readgraph("C:\\res\\tinyEWD.txt");
		g.findShortestPaths(4, 7);
		g.findShortestPaths(0, 7);
	
	}

}
