package com.rsyslog.slfa;

import java.util.Properties;

public abstract class Type {

	public void onFileStart() {
		return;
	}
	
	public void onFileEnd() {
		return;
	}
	
	public abstract int anon(String msg, int idx, StringBuffer output);

	public abstract void getConfig(Properties prop);
}
