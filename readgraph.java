package matala1mivnei;

import static org.junit.Assert.*;
import matala1mivnei.DikjstraAlgorithm.Graph;

import org.junit.Test;

public class readgraph {

	@Test
	public void test() {
		DikjstraAlgorithm test=new DikjstraAlgorithm();
		
		Graph g=new Graph();
		g.readgraph("C:\\res\\tinyEWD.txt");
		g.readgraph("C:\\res\\mediumEWD.txt");
		//g.readgraph("C:\\res\\largeEWD.txt");
	}

}
