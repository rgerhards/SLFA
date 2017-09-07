package com.rsyslog.slfa;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LogFile {
	private BufferedReader fileRd;
	
	public LogFile(String name) {
		try {
			fileRd = new BufferedReader(new FileReader(name));
		} catch (FileNotFoundException e) {
			System.out.println("Exception: " + e);
		}
	}
	
	public void anon() {
		String msg = null;
		try {
			msg =  fileRd.readLine();
		} catch (IOException e) {
			System.out.println("Exception: " + e);
			e.printStackTrace();
		}
		if(msg != null) {
			for(int i = 0; i < msg.length(); i++) {
				//parse
			}
		}
	}
}
