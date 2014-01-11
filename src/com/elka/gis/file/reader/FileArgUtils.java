package com.elka.gis.file.reader;

import java.io.File;
import java.io.IOException;

public class FileArgUtils {
	
	public FileArgUtils(){
		
	}
	
	private boolean checkIfPathValid(String filename){
		File tempFile = new File(filename);
		boolean isValid = true;
		try{
			if (tempFile.createNewFile()){
				tempFile.delete();
			}
		} catch (IOException ioe){
			isValid = false;
		}
		return isValid;
	}
	
	public boolean checkArgsValidation(String[] args){
		boolean result = true;
		if (args.length == 0 || args.length == 1){
			System.out.println("Brak podanej sciezki pliku z grafem lub brak sciezki do zapisu pliku, prosze sprobowac jeszcze raz ");
			System.exit(1);
		} else if (args.length == 2){
			if (checkIfPathValid(args[0]) && checkIfPathValid(args[1])){
				System.out.println("Obie podane sciezki plikow poprawne.");
			} else {
				System.out.println("Jedna lub obie sciezki plikow sa niepoprawne, prosze sprobowac jeszcze raz");
				System.exit(2);
			}
		} else {
			System.out.println("za duzo argumentow wejsciowych, prosze sprobowac jeszcze raz");
			System.exit(3);
		}
		return result;
	}

}
