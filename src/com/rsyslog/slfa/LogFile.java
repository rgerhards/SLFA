package com.rsyslog.slfa;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class LogFile {
	private BufferedReader fileRd;
	private ArrayList<Type> list;
	private Random rand;
	
	public LogFile(String name, ArrayList<Type> typelist) {
		list = typelist;
System.out.println("TTTT" + typelist);
System.out.println("listlen = " + typelist.size() + "  " + typelist.get(1));
System.out.println("listlen = " + list.size() + "  " + list.get(1));
		for(int i = 0; i < list.size(); i++) {
			list.get(i).onFileStart();
		}
		try {
			fileRd = new BufferedReader(new FileReader(name));
		} catch (FileNotFoundException e) {
			System.out.println("Exception: " + e);
		}
		rand = new Random(System.currentTimeMillis());
	}
	
	public void anon() {
		StringBuffer output = new StringBuffer();
		String msgIn = null;
		CurrMsg msg = new CurrMsg();
		int listsize = list.size();
		
		msg.setRand(rand);
		msg.setMsgOut(output);
		try {
			msgIn =  fileRd.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		while(msgIn != null) {	
			msg.setMsgIn(msgIn);
			msg.setCurrIdx(0);
			int msglen = msgIn.length();
//			System.out.println("TTTTTTT: " + msg);
			while(msg.getCurrIdx() < msglen) {
				msg.setNprocessed(0);
//				System.out.println("test" + msg.getMsgIn().length() + " " + msg.getCurrIdx());
				for(int j = 0; j < listsize; j++) {
//					System.out.println("anon with " + j + " out of " + list.size());
					list.get(j).anon(msg);
					if(msg.getNprocessed() > 0) {
						msg.setCurrIdx(msg.getCurrIdx() + msg.getNprocessed());
						break;
					}
				}
			}
			msg.endMsg();
			try {
				msgIn = fileRd.readLine();
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
