package com.elka.gis.file.reader;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FileReader {

	private String filePath;
	private ArrayList<ArrayList<Integer>> listOfVertices;
	
	public FileReader(String filePath) throws IOException{
		this.filePath = filePath;
		this.listOfVertices = new ArrayList<ArrayList<Integer>>();
		this.readData();
	}
	
	private void readData() throws IOException{
		FileInputStream fstream = new FileInputStream(this.filePath);
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String[] arrayLine;
		String actualLine = null;
		try {
			while ((actualLine=br.readLine())!=null){
				arrayLine = actualLine.split(" ");
				ArrayList<Integer> tempArrListElement = new ArrayList<Integer>();
				//pominiecie numeru wierzcholka i znaku ":"
				for (int i=2; i<arrayLine.length; i++){
					Integer intElement = Integer.parseInt(arrayLine[i]);
					tempArrListElement.add(intElement);
				}
				listOfVertices.add(tempArrListElement);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<ArrayList<Integer>> getListOfVertices(){
		return this.listOfVertices;
	}
	
	public void printlnListOfVertices(){
		int count = 1;
		for (ArrayList<Integer> arrayElement : listOfVertices){
			System.out.print(count + " : ");
			for (Integer intElement : arrayElement){
				System.out.print(intElement + " ");
			}
			System.out.println();
			count++;
		}
	}
	
	public static void main(String[] args) throws IOException {
		String filePath = "C:\\Users\\£ukasz\\Desktop\\graf.txt";
		FileReader fr = new FileReader(filePath);
		fr.printlnListOfVertices();
	}

}
