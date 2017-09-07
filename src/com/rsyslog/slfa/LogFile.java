package com.rsyslog.slfa;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LogFile {
	private BufferedReader fileRd;
	private ArrayList<Type> list;
	
	public LogFile(String name, ArrayList<Type> typelist) {
		list = typelist;
		for(int i = 0; i < list.size(); i++) {
			list.get(i).onFileStart();
		}
		try {
			fileRd = new BufferedReader(new FileReader(name));
		} catch (FileNotFoundException e) {
			System.out.println("Exception: " + e);
		}
	}
	
	public void anon() {
		StringBuffer output = new StringBuffer();
		int newIdx;
		String msg = null;
		try {
			msg =  fileRd.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int i;
		while(msg != null) {	
//			System.out.println("TTTTTTT: " + msg);
			i = 0;
			while(i < msg.length()) {
//				System.out.println("test" + msg.length() + i);
				for(int j = 0; j < list.size(); j++) {
					newIdx = list.get(j).anon(msg, i, output);
					if(newIdx != i) {
						i = newIdx;
						break;
					}
				}
			}
			System.out.println(output);
			output.delete(0, output.length());
			try {
				msg = fileRd.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			fileRd.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
