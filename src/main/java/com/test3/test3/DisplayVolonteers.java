package com.test3.test3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class DisplayVolonteers {

	HashMap<String,Volonteer>volonteers;
	String testik;
	public HashMap<String, Volonteer> getVolonteers() {
		return volonteers;
	}
	public void setVolonteers(HashMap<String, Volonteer> volonteers) {
		this.volonteers = volonteers;
	}
	public DisplayVolonteers(HashMap<String,Volonteer> d)
	{
	 volonteers = d;
	  
	}
	public DisplayVolonteers(String test) {testik=test;}
	
}
