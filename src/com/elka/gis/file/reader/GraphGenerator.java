package com.elka.gis.file.reader;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GraphGenerator {
	
	public GraphGenerator(){
		
	}
	
	public int[][] GenerateMatrixGraph(int numOfVertices, double density){
		Random r = new Random();
		Double doubleEdges = density*numOfVertices*(numOfVertices-1)/2;
		int densityNumOfEdges = doubleEdges.intValue();
		int matrixSize = numOfVertices+1;
		int edgesCount = 0;
		int[][] graphMatrix = new int[matrixSize][matrixSize];
		outer:
			for (int i=1; i<matrixSize; i++){
				for (int j=i+1; j<matrixSize; j++){
					graphMatrix[i][j] = r.nextInt(2);
					graphMatrix[j][i] = graphMatrix[i][j];
					if (graphMatrix[i][j]==1){
						edgesCount++;
					}
					if (edgesCount==densityNumOfEdges){
						break outer;
					}
				}
			}
		if (edgesCount < densityNumOfEdges){
			int i = 0;
			int j = 0; 
			while(edgesCount < densityNumOfEdges){
				i = r.nextInt(numOfVertices)+1;
				j = r.nextInt(numOfVertices)+1;
				if (i!=j && graphMatrix[i][j]==0){
					graphMatrix[i][j] = 1;
					graphMatrix[j][i] = graphMatrix[i][j];
					edgesCount++;
				}
			}
		}
		return graphMatrix;
	}
	
	/*
	private void printGraphMatrix(int[][] matrix){
		for (int i=1; i<matrix.length; i++){
			for (int j=1; j<matrix.length; j++){
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	};
	*/
	
	public ArrayList<ArrayList<Integer>> getListFromMatrix(int[][] matrix){
		ArrayList<ArrayList<Integer>> resultList = new ArrayList<ArrayList<Integer>>();
		addZaslepkaZeroToList(resultList);
		for(int i=1; i<matrix.length; i++){
			ArrayList<Integer> elementList = new ArrayList<Integer>();
			for(int j=1; j<matrix.length; j++){
				if(matrix[i][j]==1){
					elementList.add(j);
				}
			}
			resultList.add(elementList);
		}
		return resultList;
	}
	
	private void addZaslepkaZeroToList(List<ArrayList<Integer>> list){
		ArrayList<Integer> zaslepkaZero = new ArrayList<Integer>();
		list.add(zaslepkaZero);
	}
	
	public void printlnListOfVertices(ArrayList<ArrayList<Integer>> listOfVertices){
		for (int i=1; i<listOfVertices.size(); i++){
			System.out.print(i + " : ");
			for (int j=0; j<listOfVertices.get(i).size(); j++){
				System.out.print(listOfVertices.get(i).get(j) + " ");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		GraphGenerator gg = new GraphGenerator();
		String filePath = "C:\\Users\\£ukasz\\Desktop\\dupsko.txt";
		PrintStream out;
		try {
			out = new PrintStream(new FileOutputStream(filePath));
			System.setOut(out);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		gg.printlnListOfVertices(gg.getListFromMatrix(gg.GenerateMatrixGraph(200, 0.5)));
	}

}
