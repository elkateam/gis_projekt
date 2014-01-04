package com.elka.gis.file.reader;

import java.io.File;
import java.io.IOException;

public class FileArgUtils {
	
	public FileArgUtils(){
		
	}
	
	boolean checkIfPathValid(String filename){
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

}
