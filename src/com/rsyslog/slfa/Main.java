package com.rsyslog.slfa;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws IOException {
		String configFile;

		Config config = new Config();

		configFile = System.getProperty("configfile");
		if(configFile != null) {
			config.setFilename(configFile);
		}
		
		System.out.println("test: " + System.getProperty("test") + "     file: " + configFile);
		
		ArrayList<Type> typelist = config.getTypes();
		
		for(int i = 0; i < args.length; i++) {
			System.out.println("arg " + i + ": " + args[i] + " arglen: " + args.length);
			System.out.println();
			LogFile current = new LogFile(args[i], typelist);
			current.anon();
		}
		
	}

}