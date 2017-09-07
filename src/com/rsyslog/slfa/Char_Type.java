package com.rsyslog.slfa;

import java.util.Properties;

public class Char_Type extends Type {

	@Override
	public int anon(String msg, int idx, StringBuffer output) {
		output.append(msg.charAt(idx));
		return idx + 1;
	}

	@Override
	public void getConfig(Properties prop) {
		return;
	}

}
