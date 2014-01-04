package com.elka.gis.file.reader;

public class TestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello GIS projekt");
		FileArgUtils fau = new FileArgUtils();
		if (args.length == 0 || args.length == 1){
			System.out.println("Brak podanej sciezki pliku z grafem lub brak sciezki do zapisu pliku, prosze sprobowac jeszcze raz ");
			System.exit(1);
		} else if (args.length == 2){
			System.out.println("Pierwszy argument: " +args[0]);
			if (fau.checkIfPathValid(args[0]) && fau.checkIfPathValid(args[1])){
				System.out.println("Obie sciezki poprawne");
			} else {
				System.out.println("Podano niepoprawna sciezke, prosze sprobowac jeszcze raz");
				System.exit(2);
			}
		} else {
			System.out.println("za duzo argumentow wejsciowych, prosze sprobowac jeszcze raz");
			System.exit(3);
		}
	}

}
