package com.elka.gis.file.reader;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class AllAlgTest {
	
	private boolean testFlag = true;

	public void findClique(String[] args) throws IOException {
		String fileSavePath;
		PrintStream out;
		PrintStream original = new PrintStream(System.out);
		FileReader fr;
		if (testFlag){
			GraphGenerator gg = new GraphGenerator();
			fileSavePath = "C:\\Users\\£ukasz\\Desktop\\gis_testy\\test.txt";
			try {
				out = new PrintStream(new FileOutputStream(fileSavePath));
				System.setOut(out);
				gg.printlnListOfVertices(gg.getListFromMatrix(gg.GenerateMatrixGraph(100, 0.9)));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			System.setOut(original);
			fr = new FileReader(fileSavePath);
		} else {
			String fileLoadPath = args[0];
			fileSavePath = args[1];
			fr = new FileReader(fileLoadPath);
			out = new PrintStream(new FileOutputStream(fileSavePath));
			System.setOut(out);
		}
		
		MatrixInitializer mi = new MatrixInitializer(fr.getListOfVertices());
		
		//ALG NAIWNY Z DODAWANIEM WIERZCHOLKOW
		long startNAIWNYTime = System.nanoTime();
		AlgNaiwny an = new AlgNaiwny(mi.getDopelnienieGraphMatrix());
		long endNAIWNYTime = System.nanoTime();
		long durationNAIWNY = endNAIWNYTime - startNAIWNYTime;
		double dblTime = (double) durationNAIWNY/1000000000;
		Vertices u_an = an.getUVertices();
		System.out.println("----Algorytm uproszczony z dodawaniem wierzcholkow----");
		System.out.println("Maksymalna klika:");
		u_an.printVertices();
		System.out.println("Czas wykonania: "+dblTime+" sek.");
		System.out.println("Rozmiar znalezionej kliki: "+u_an.size());
		System.out.println();
		
		//ALG NAIWNY Z ODEJMOWANIEM WIERZCHOLKOW
		long startNAIWNY1Time = System.nanoTime();
		AlgNaiwny1 an1 = new AlgNaiwny1(mi.getDopelnienieGraphMatrix());
		long endNAIWNY1Time = System.nanoTime();
		long durationNAIWNY1 = endNAIWNY1Time - startNAIWNY1Time;
		double dblTime1 = (double) durationNAIWNY1/1000000000;
		Vertices u_an1 = an1.getUVertices();
		System.out.println("----Algorytm uproszczony z odejmowaniem wierzcholkow----");
		System.out.println("Maksymalna klika:");
		u_an1.printVertices();
		System.out.println("Czas wykonania: "+dblTime1+" sek.");
		System.out.println("Rozmiar znalezionej kliki: "+u_an1.size());
		System.out.println();
		
		//BRON-KERBOSCH
		long startBRONTime = System.nanoTime();
		BronKerbosch bk = new BronKerbosch(mi.getGraphMatrix());
		Vertices u = bk.bronKerboschMaximumClique();
		long endBRONTime = System.nanoTime();
		long durationBRON = endBRONTime - startBRONTime;
		double dblTimeBRON = (double) durationBRON/1000000000;
		System.out.println("----Algorytm Brona-Kerboscha----");
		System.out.println("Maksymalna klika:");
		u.printVertices();
		System.out.println("Czas wykonania: "+dblTimeBRON+" sek.");
		System.out.println("Rozmiar znalezionej kliki: "+u.size());
		System.out.println();
		
		System.setOut(original);
		
		System.out.println("Program zakonczony pomyslnie");
		System.out.println("Wyniki zapisane w pliku: "+args[1]);
	}

}
