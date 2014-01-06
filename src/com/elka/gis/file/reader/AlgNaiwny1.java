package com.elka.gis.file.reader;

import java.io.IOException;
import java.util.Iterator;

public class AlgNaiwny1 {

	private Vertices[] neighbours;
	private Vertices u;
	private int[][] graphMatrix;
	private int verticesDim;
	
	public AlgNaiwny1(int[][] graphMatrixInput){
		this.graphMatrix = graphMatrixInput;
		this.verticesDim = graphMatrixInput.length;
		this.u = new Vertices();
		this.neighbours = new Vertices[verticesDim];
		this.setNeighbours();
		this.makeAlgWithSubstractVertices();
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
	
	private void makeAlgWithSubstractVertices(){
		this.setUVerticies();
		int chosenVertex = 0;
		while (!checkIfSetOfVerticesIsIndependent()){
			chosenVertex = getVertexWithTheHighestAmountOfNeighbours();
			u.remove(chosenVertex);
		}
	}
	
	private void setUVerticies(){
		for (int i=0; i<verticesDim; i++){
			this.u.add(i);
		}
	}
	
	private boolean checkIfSetOfVerticesIsIndependent(){
		boolean result = true;
		for (Integer vertexInU : this.u){
			Iterator<Integer> iterator = this.neighbours[vertexInU].iterator();
			while(iterator.hasNext()){
				if(checkIfVertexIsInU(iterator.next())){
					result = false;
				}
			}
		}
		return result;
	}
	
	private int getVertexWithTheHighestAmountOfNeighbours(){
		int actualCount;
		int highestNeighbourCount = 0;	// warunek poczatkowy, zerowa ilosc sasiadow
		int vertexNumberWithHighestNeighbours = 0;
		for (Integer vertexInU : this.u){
			actualCount = 0;
			Iterator<Integer> iterator = this.neighbours[vertexInU].iterator();
			while(iterator.hasNext()){
				if(checkIfVertexIsInU(iterator.next())){
					actualCount++;
				}
			}
			if (actualCount > highestNeighbourCount){
				highestNeighbourCount = actualCount;
				vertexNumberWithHighestNeighbours = vertexInU;
			}
		}
		return vertexNumberWithHighestNeighbours;
	}
	
	private boolean checkIfVertexIsInU(int vertex){
		boolean result = false;
		if (this.u.contains(vertex)){
			result = true;
		}
		return result;
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
	
	public Vertices getUVertices(){
		return this.u;
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String filePath = "C:\\Users\\£ukasz\\Desktop\\graf1.txt";
		FileReader fr = new FileReader(filePath);
//		fr.printlnListOfVertices();
//		System.out.println();
		MatrixInitializer mi = new MatrixInitializer(fr.getListOfVertices());
		AlgNaiwny1 an1 = new AlgNaiwny1(mi.getGraphMatrix());
		Vertices u = an1.getUVertices();
		u.printVertices();
	}

}
