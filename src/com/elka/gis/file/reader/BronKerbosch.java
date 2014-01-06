package com.elka.gis.file.reader;

import java.io.IOException;


public class BronKerbosch {
	private int[][] graphMatrix;
	private int verticesDim;
	private Vertices candidates, biggestClique, compsub;
	
	
	public BronKerbosch(final int[][] graphMatrix){
		this.candidates = new Vertices();
		this.biggestClique = new Vertices();
		this.compsub = new Vertices();
		this.graphMatrix = graphMatrix;
		this.verticesDim = graphMatrix.length;
		this.setVerticesList(verticesDim);
	}
	private void setVerticesList(int verticesDim) {
		for (int i=0; i<verticesDim; i++){
			this.candidates.add(i);
		}
	}
	
	private int getMaximumDegreeNode (Vertices x){
		int max = 0;
		int maxIndex=0;
		int suma;
		for (Integer vertex : x){
			suma = 0;
			for (int j=0; j<this.verticesDim; j++){
				suma += graphMatrix[vertex][j];
			}
			if (suma>max) {
				max = suma;
				maxIndex=vertex;
			}
		}
		return maxIndex;
	}
	
	private Vertices unionOfSets(Vertices a, Vertices b) {
		Vertices tmp = new Vertices(a);
		tmp.addAll(b);
		return tmp;
	}
	
	private Vertices differenceOfSets(Vertices a, Vertices b){
		Vertices tmp = new Vertices(a);
		tmp.removeAll(b);
		return tmp;
	}
	private Vertices intersectionOfSets(Vertices a, Vertices b){
		Vertices tmp = new Vertices();
		for (Integer x : a) {
			if (b.contains(x))
				tmp.add(x);
		}
		return tmp;
	}
	
	private Vertices getNeighbors(int x) {
		Vertices neighbors = new Vertices();
		for (int i=0; i<verticesDim; ++i) {
			if (graphMatrix[x][i] == 1)
				neighbors.add(i);
		}
		return neighbors;
	}
	public Vertices bronKerboschMaximumClique() {
		bronKerboschMaximumClique(candidates, new Vertices());
		addOneToCliqueVertices();
		return biggestClique;
	}
	private void bronKerboschMaximumClique(Vertices candidates, Vertices not) {
		int pivot;
		Vertices candidatesToCheck = new Vertices();
		Vertices newCandidates = new Vertices();
		Vertices newNot = new Vertices();
		if (candidates.isEmpty() && not.isEmpty()) {
			if (biggestClique.size() < compsub.size() )
			{
				biggestClique = (Vertices)compsub.clone();
			}
		}
		else {
			pivot = getMaximumDegreeNode(unionOfSets(candidates,not));
			candidatesToCheck = differenceOfSets(candidates, getNeighbors(pivot));
			for (Integer v : candidatesToCheck) {
				compsub.add(v);
				candidates.remove(v);
				Vertices neighbors = getNeighbors(v);
				newCandidates = new Vertices(intersectionOfSets(candidates, neighbors));
				newNot = new Vertices(intersectionOfSets(not, neighbors));
				bronKerboschMaximumClique(newCandidates, newNot);
				compsub.remove(v);
				not.add(v);
			}
		}
	}
	private void addOneToCliqueVertices(){
		for (int i=verticesDim; i>0; i--){
			if (this.biggestClique.contains(i-1)){
				biggestClique.remove(i-1);
				biggestClique.add(i);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		String filePath = "C:\\Users\\trocha\\Desktop\\graf.txt";
		FileReader fr = new FileReader(filePath);
		
		MatrixInitializer mi = new MatrixInitializer(fr.getListOfVertices());
		BronKerbosch bk = new BronKerbosch(mi.getGraphMatrix());
		Vertices u = bk.bronKerboschMaximumClique();
		u.printVertices();
	}
}
