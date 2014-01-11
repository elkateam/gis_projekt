package com.elka.gis.file.reader;

import java.io.IOException;

public class TestMain {

	public static void main(String[] args) {
		FileArgUtils fau = new FileArgUtils();
		AllAlgTest aat = new AllAlgTest();
		if(fau.checkArgsValidation(args)){
			try {
				System.out.println("Trwa wykonywanie programu, prosze czekac...");
				aat.findClique(args);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
