package matala1mivnei;

import static org.junit.Assert.*;
import matala1mivnei.DikjstraAlgorithm.Graph;

import org.junit.Test;

public class blackfile {

	@Test
	public void test() {

		DikjstraAlgorithm test=new DikjstraAlgorithm();
		Graph g=new Graph();
	
		g.readgraph("C:\\res\\tinyEWD.txt");
		g.readTest("C:\\res\\tinyEWD.txt", "C:\\res\\test1.txt");
		
	
	}

}
