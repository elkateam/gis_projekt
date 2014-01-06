package com.elka.gis.file.reader;

import java.io.IOException;
import java.util.Iterator;

public class AlgNaiwny {

	private Vertices u;
	private Vertices x;
	private Vertices[] neighbours;
	private int[][] graphMatrix;
	private int verticesDim;
	
	public AlgNaiwny(final int[][] graphMatrix){
		this.u = new Vertices();
		this.x = new Vertices();
		this.graphMatrix = graphMatrix;
		this.verticesDim = graphMatrix.length;
		this.neighbours = new Vertices[verticesDim];
		this.setNeighbours();
		this.makeAlgWithAddingVertices();
		this.addOneToUVertices();
	}
	
	private void setNeighbours(){
		for (int row=0; row<verticesDim; row++){
			this.neighbours[row] = new Vertices();
			for (int column=0; column<verticesDim; column++){
				if (graphMatrix[row][column]==1){
					this.neighbours[row].add(column);	
				}
			}
		}
	}
	
	public Vertices[] getNeighbours(){
		return this.neighbours;
	}
	
	public Vertices getUVertices(){
		return this.u;
	}
	
	public Vertices getXVertices(){
		return this.x;
	}
	
	private void makeAlgWithAddingVertices(){
		this.setXVerticies();
		int chosenVertex = 0;
		while (!x.isEmpty()){
			chosenVertex = getVertexWithTheLowestAmountOfNeighbours();
			u.add(chosenVertex);
			removeVertexAndHisNeighboursFromX(chosenVertex);
		}
	}
	
	private void setXVerticies(){
		for (int i=0; i<verticesDim; i++){
			this.x.add(i);
		}
	}
	
	private int getVertexWithTheLowestAmountOfNeighbours(){
		int actualCount;
		int lowestNeighbourCount = 10000000;	// warunek poczatkowy, duza ilosc sasiadow
		int vertexNumberWithLowestNeighbours = 0;
		for (Integer vertexInX : this.x){
			actualCount = 0;
			Iterator<Integer> iterator = this.neighbours[vertexInX].iterator();
			while(iterator.hasNext()){
				if(checkIfVertexIsInX(iterator.next())){
					actualCount++;
				}
			}
			if (actualCount < lowestNeighbourCount){
				lowestNeighbourCount = actualCount;
				vertexNumberWithLowestNeighbours = vertexInX;
			}
		}
		return vertexNumberWithLowestNeighbours;
	}
	
	private boolean checkIfVertexIsInX(int vertex){
		boolean result = false;
		if (this.x.contains(vertex)){
			result = true;
		}
		return result;
	}
	
	private void removeVertexAndHisNeighboursFromX(int vertex){
		Iterator<Integer> iterator = this.neighbours[vertex].iterator();
		while(iterator.hasNext()){
			int element = iterator.next();
			if (this.x.contains(element)){
				this.x.remove(element);
			}
		}
		this.x.remove(vertex);
	}
	
	//zeby sie zgadzalo, bo w tablicach numerowanie od 0, a w wierzcholkach od 1
	private void addOneToUVertices(){
		for (int i=verticesDim; i>0; i--){
			if (this.u.contains(i-1)){
				u.remove(i-1);
				u.add(i);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String filePath = "C:\\Users\\£ukasz\\Desktop\\graf1.txt";
		FileReader fr = new FileReader(filePath);
//		fr.printlnListOfVertices();
//		System.out.println();
		MatrixInitializer mi = new MatrixInitializer(fr.getListOfVertices());
//		mi.printGraphDopelnienieMatrix();
		AlgNaiwny an = new AlgNaiwny(mi.getDopelnienieGraphMatrix());
		Vertices u = an.getUVertices();
		u.printVertices();
//		Vertices[] neigh = an.getNeighbours();
//		for (int i=0; i<neigh.length; i++){
//			System.out.println("Sasiedzi wierzcholka "+i);
//			neigh[i].printVertices();
//			System.out.println();
//		}
//		System.out.println();
//		Vertices x = an.getXVertices();
//		x.printVertices();
	}

}
