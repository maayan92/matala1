package ex;

import java.io.File;
import java.util.Scanner;


public class DikjstraAlgorithm {
	public static void main(String[] args) {

		Graph h=new Graph();
		//largeEWD
		long startTime = System.currentTimeMillis();
		// ... do something ...
			//h.readgraph("C:\\Users\\igal\\Desktop\\largeEWD.txt");
		h.readTest("largeEWD.txt","test1.txt");
	//	h.readgraph("mediumEWD.txt");
		//h.readTest2("test1.txt");
	//h.findShortestPaths(0, 7);
		long estimatedTime = System.currentTimeMillis() - startTime;
		System.out.println(estimatedTime);
	}






	public static class Graph {
		static Vertex[] vertices;
		static double maxSize=10;
		static double maxSize1=10;
		int size;
		static Graph k;

		public Graph() {
			this.maxSize = maxSize;
			vertices = new Vertex[(int)maxSize];
		}

		public void addVertex(int name) {
			vertices[size++] = new Vertex(name);
		}

		public void addEdge(int sourceName, int destinationName, double weight) {
			int srcIndex = sourceName;
			int destiIndex = destinationName;
			vertices[srcIndex].adj = new Neighbour(destiIndex, weight, vertices[srcIndex].adj);
			vertices[destiIndex].indegree++;
			//System.out.println();
		}

		public void findShortestPaths(int sourceName,int dest){
			applyDikjstraAlgorith(vertices[sourceName]);
			/*for(int i = 0; i < maxSize; i++){
               System.out.println("Distance of "+vertices[i].name+" from Source: "+ vertices[i].cost);
            }*/

			System.out.println("Distance from "+vertices[sourceName].name+" to edge "+vertices[dest].name+" is:"+ vertices[dest].cost);
			System.out.println(vertices[dest].put);
		}
/*		public void findShortestPaths2(int sourceName,int dest,int [] a){
			applyDikjstraAlgorith2(vertices[sourceName], a);
			for(int i = 0; i < maxSize; i++){
               System.out.println("Distance of "+vertices[i].name+" from Source: "+ vertices[i].cost);
            }

			System.out.println("Distance from "+vertices[sourceName].name+" to edge "+vertices[dest].name+" is:"+ vertices[dest].cost);

		}*/
		public static void readgraph(String files){
			try {
				File file = new File(files);
				Scanner input = new Scanner(file);
				maxSize = input.nextDouble();
				k=new Graph();
				for(int i=0;i<maxSize;i++){
					k.addVertex(i);
				}
				double edges=input.nextDouble();
				while (input.hasNextDouble()) {
					int srcIndex = (int)input.nextDouble();
					int destiIndex = (int)input.nextDouble();
					double c=input.nextDouble();
					k.addEdge(srcIndex, destiIndex, c);
				}
				input.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		public static void readgraph2(String files,int [] a){
			try {
				File file = new File(files);
				Scanner input = new Scanner(file);
				maxSize = input.nextDouble();
				k=new Graph();
				for(int i=0;i<maxSize;i++){
					k.addVertex(i);
				}
				double edges=input.nextDouble();
				while (input.hasNextDouble()) {
					int srcIndex = (int)input.nextDouble();
					int destiIndex = (int)input.nextDouble();
					double c=input.nextDouble();
					if(!contns(srcIndex,destiIndex, a))
						k.addEdge(srcIndex, destiIndex, c);

				}

				input.close();



			} catch (Exception ex) {
				ex.printStackTrace();

			}


		}
		private static boolean contns(int srcIndex, int destiIndex, int[] a) {
			for (int i = 0; i < a.length; i++) {
				if(srcIndex==a[i]||destiIndex==a[i]) return true;
			}
			return false;
		}

		public  void readTest(String files,String test){
			try {


				File file1 = new File(test);
				Scanner input = new Scanner(file1);

				//maxSize = input.nextDouble();
				int max = (int) input.nextDouble();
				for (int i = 0; i < max; i++) {
					int srcIndex = (int)input.nextDouble();
					int destiIndex = (int)input.nextDouble();
					int c=(int)input.nextDouble();
					if(c>0){
						int a []= new int [c];
						for (int j = 0; j < c; j++) {
							a[j]=(int)input.nextDouble();
						}
						readgraph2(files,a);
						findShortestPaths(srcIndex,destiIndex);
					}
					else{
						readgraph(files);
						findShortestPaths(srcIndex,destiIndex);
					}
				}

				input.close();

			} catch (Exception ex) {
				ex.printStackTrace();
			}


		}
	/*	public  void readTest2(String test){
			try {


				File file1 = new File(test);
				Scanner input = new Scanner(file1);

				//maxSize = input.nextDouble();
				int max = (int) input.nextDouble();
				for (int i = 0; i < max; i++) {
					int srcIndex = (int)input.nextDouble();
					int destiIndex = (int)input.nextDouble();
					int c=(int)input.nextDouble();
					if(c>0){
						int a []= new int [c];
						for (int j = 0; j < c; j++) {
							a[j]=(int)input.nextDouble();
						}
						
						findShortestPaths2(srcIndex,destiIndex,a);
					}
					else{
						
						findShortestPaths(srcIndex,destiIndex);
					}
				}

				input.close();

			} catch (Exception ex) {
				ex.printStackTrace();
			}


		}*/

		public class Vertex {
			double cost;
			String put;
			int name;
			Neighbour adj;
			int indegree;
			State state;

			public Vertex(int name) {
				this.name = name;
				cost = Integer.MAX_VALUE;
				state = State.NEW;
					put=name+"";
			}

			public int compareTo(Vertex v) {
				if (this.cost == v.cost) {
					return 0;
				}
				if (this.cost < v.cost) {
					return -1;
				}
				return 1;
			}
		}

		public enum State {
			NEW, IN_Q, VISITED
		}

		public class Neighbour {
			int index;
			Neighbour next;
			double weight;

			Neighbour(int index, double weight, Neighbour next) {
				this.index = index;
				this.next = next;
				this.weight = weight;
			}
		}

		public void applyDikjstraAlgorith(Vertex src) {
			Heap heap = new Heap((int)maxSize);
			heap.add(src);
			src.state = State.IN_Q;
			src.cost = 0;
			while (!heap.isEmpty()) {
				Vertex u = heap.remove();
				u.state = State.VISITED;
				Neighbour temp = u.adj;
				while (temp != null) {
					if (vertices[temp.index].state == State.NEW) {
						heap.add(vertices[temp.index]);
						vertices[temp.index].state = State.IN_Q;
					}
					if (vertices[temp.index].cost > u.cost + temp.weight) {
						vertices[temp.index].cost = (u.cost + temp.weight);
						//vertices[temp.index].put = (u.put +"->"+ vertices[temp.index].put);
						heap.heapifyUP(vertices[temp.index]);
					}
					temp = temp.next;
				}
			}
		}
/*		public void applyDikjstraAlgorith2(Vertex src,int [] a) {
			Heap heap = new Heap((int)maxSize);
			heap.add(src);
			src.state = State.IN_Q;
			src.cost = 0;
			while (!heap.isEmpty()) {
				Vertex u = heap.remove();
				u.state = State.VISITED;
				Neighbour temp = u.adj;
				while (temp != null) {
					if(!contns(temp.index,a)){
						if (vertices[temp.index].state == State.NEW) {
							heap.add(vertices[temp.index]);
							vertices[temp.index].state = State.IN_Q;
						}
						if (vertices[temp.index].cost > u.cost + temp.weight) {
							vertices[temp.index].cost = (u.cost + temp.weight);
							heap.heapifyUP(vertices[temp.index]);
						}
					}
					temp = temp.next;
				}
			}
		}
*/
		private static boolean contns(int index, int[] a) {
			for (int i = 0; i < a.length; i++) {
				if(index==a[i]) return true;
			}
			return false;
		}

		public static class Heap {
			private Vertex[] heap;
			private int maxSize;
			private int size;

			public Heap(int maxSize) {
				this.maxSize = maxSize;
				heap = new Vertex[maxSize];
			}

			public void add(Vertex u) {
				heap[size++] = u;
				heapifyUP(size - 1);
			}

			public void heapifyUP(Vertex u) {
				for (int i = 0; i < maxSize; i++) {
					if (u == heap[i]) {
						heapifyUP(i);
						break;
					}
				}
			}

			public void heapifyUP(int position) {
				int currentIndex = position;
				Vertex currentItem = heap[currentIndex];
				int parentIndex = (currentIndex - 1) / 2;
				Vertex parentItem = heap[parentIndex];
				while (currentItem.compareTo(parentItem) == -1) {
					swap(currentIndex, parentIndex);
					currentIndex = parentIndex;
					if (currentIndex == 0) {
						break;
					}
					currentItem = heap[currentIndex];
					parentIndex = (currentIndex - 1) / 2;
					parentItem = heap[parentIndex];
				}
			}

			public Vertex remove() {
				Vertex v = heap[0];
				swap(0, size - 1);
				heap[size - 1] = null;
				size--;
				heapifyDown(0);
				return v;
			}

			public void heapifyDown(int postion) {
				if (size == 1) {
					return;
				}

				int currentIndex = postion;
				Vertex currentItem = heap[currentIndex];
				int leftChildIndex = 2 * currentIndex + 1;
				int rightChildIndex = 2 * currentIndex + 2;
				int childIndex;
				if (heap[leftChildIndex] == null) {
					return;
				}
				if (heap[rightChildIndex] == null) {
					childIndex = leftChildIndex;
				} else if (heap[rightChildIndex].compareTo(heap[leftChildIndex]) == -1) {
					childIndex = rightChildIndex;
				} else {
					childIndex = leftChildIndex;
				}
				Vertex childItem = heap[childIndex];
				while (currentItem.compareTo(childItem) == 1) {
					swap(currentIndex, childIndex);
					currentIndex = childIndex;
					currentItem = heap[currentIndex];
					leftChildIndex = 2 * currentIndex + 1;
					rightChildIndex = 2 * currentIndex + 2;
					if (heap[leftChildIndex] == null) {
						return;
					}
					if (heap[rightChildIndex] == null) {
						childIndex = leftChildIndex;
					} else if (heap[rightChildIndex].compareTo(heap[leftChildIndex]) == -1) {
						childIndex = rightChildIndex;
					} else {
						childIndex = leftChildIndex;
					}
				}
			}

			public void swap(int index1, int index2) {
				Vertex temp = heap[index1];
				heap[index1] = heap[index2];
				heap[index2] = temp;
			}

			public boolean isEmpty() {

				return size == 0;
			}
		}
	}
}