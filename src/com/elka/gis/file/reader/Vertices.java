package com.elka.gis.file.reader;

import java.util.Iterator;
import java.util.TreeSet;

public class Vertices extends TreeSet<Integer> {

	private static final long serialVersionUID = 1L;

	public Vertices(){
		super();
	}
	
	public Vertices(Vertices v){
		super(v);
	}
	
	public void printVertices(){
		Iterator<Integer> iter = iterator();
		while (iter.hasNext()){
			System.out.println(iter.next() + " ");
		}
	}

}
