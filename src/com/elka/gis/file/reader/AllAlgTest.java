package com.elka.gis.file.reader;

import java.io.IOException;

public class AllAlgTest {

	public static void main(String[] args) throws IOException {
		String filePath = "C:\\Users\\£ukasz\\Desktop\\graf2.txt";
		FileReader fr = new FileReader(filePath);
		MatrixInitializer mi = new MatrixInitializer(fr.getListOfVertices());
		
		//BRON-KERBOSCH
		long startBRONTime = System.nanoTime();
		BronKerbosch bk = new BronKerbosch(mi.getGraphMatrix());
		Vertices u = bk.bronKerboschMaximumClique();
		long endBRONTime = System.nanoTime();
		long durationBRON = endBRONTime - startBRONTime;
		System.out.println("Algorytm Brona-Kerboscha");
		u.printVertices();
		System.out.println("Czas wykonania: "+durationBRON +" ns");
		System.out.println();
		
		//ALG NAIWNY Z DODAWANIEM WIERZCHOLKOW
		long startNAIWNYTime = System.nanoTime();
		AlgNaiwny an = new AlgNaiwny(mi.getDopelnienieGraphMatrix());
		long endNAIWNYTime = System.nanoTime();
		long durationNAIWNY = endNAIWNYTime - startNAIWNYTime;
		Vertices u_an = an.getUVertices();
		System.out.println("Algorytm uproszczony z dodawaniem wierzcholkow");
		u_an.printVertices();
		System.out.println("Czas wykonania: "+durationNAIWNY+" ns");
		System.out.println();
		
		//ALG NAIWNY Z ODEJMOWANIEM WIERZCHOLKOW
		long startNAIWNY1Time = System.nanoTime();
		AlgNaiwny1 an1 = new AlgNaiwny1(mi.getDopelnienieGraphMatrix());
		long endNAIWNY1Time = System.nanoTime();
		long durationNAIWNY1 = endNAIWNY1Time - startNAIWNY1Time;
		Vertices u_an1 = an1.getUVertices();
		System.out.println("Algorytm uproszczony z odejmowaniem wierzcholkow");
		u_an1.printVertices();
		System.out.println("Czas wykonania: "+durationNAIWNY1+" ns");
		System.out.println();
	}

}
