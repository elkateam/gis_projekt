package com.elka.gis.file.reader;

import java.io.IOException;
import java.util.ArrayList;

public class MatrixInitializer {

	private ArrayList<ArrayList<Integer>> graphList;
	private int[][] graphMatrix;
	private int[][] graphDopelnienieMatrix;
	private int graphMatrixDim;
	
	public MatrixInitializer(ArrayList<ArrayList<Integer>> fileGraphList){
		this.graphList = fileGraphList;
		this.graphMatrixDim = fileGraphList.size();
		this.graphMatrix = new int[graphMatrixDim][graphMatrixDim];
		this.graphDopelnienieMatrix = new int[graphMatrixDim][graphMatrixDim];
		this.getGraphListToGraphMatrix();
		this.makeDopelnienieGrafu();
	}
	
	private void getGraphListToGraphMatrix(){
		this.fillGraphMatrixWithZeros();
		int rowIndex = 0;
		for (ArrayList<Integer> graphListElement : graphList){
			for (Integer intElement : graphListElement){
				int columnIndex = intElement-1;	//poniewaz indexowanie w tablicy od zera, wiec odejmowanie-1 od numeru wierzcholka polaczonego
				this.graphMatrix[rowIndex][columnIndex] = 1;
			}
			rowIndex++;
		}
	}
	
	private void fillGraphMatrixWithZeros(){
		for (int i=0; i<this.graphMatrixDim; i++){
			for (int j=0; j<this.graphMatrixDim; j++){
				this.graphMatrix[i][j] = 0;
			}
		}
	}
	
	private void makeDopelnienieGrafu(){
		for (int i=0; i<this.graphMatrixDim; i++){
			for (int j=0; j<this.graphMatrixDim; j++){
				if (i!=j){
					this.graphDopelnienieMatrix[i][j] = (graphMatrix[i][j] == 1) ? 0 : 1;
				}
			}
		}
	}
	
	public void printGraphMatrix(){
		for (int i=0; i<this.graphMatrixDim; i++){
			for (int j=0; j<this.graphMatrixDim; j++){
				System.out.print(this.graphMatrix[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public void printGraphDopelnienieMatrix(){
		for (int i=0; i<this.graphMatrixDim; i++){
			for (int j=0; j<this.graphMatrixDim; j++){
				System.out.print(this.graphDopelnienieMatrix[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public int[][] getGraphMatrix(){
		return this.graphMatrix;
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String filePath = "C:\\Users\\£ukasz\\Desktop\\graf.txt";
		FileReader fr = new FileReader(filePath);
		fr.printlnListOfVertices();
		System.out.println();
		MatrixInitializer mi = new MatrixInitializer(fr.getListOfVertices());
		mi.printGraphMatrix();
		System.out.println();
		mi.printGraphDopelnienieMatrix();
	}

}
