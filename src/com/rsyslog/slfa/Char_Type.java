package com.rsyslog.slfa;

import java.util.Properties;

public class Char_Type extends Type {

	@Override
	public int anon(String msg, int idx) {
		return msg.charAt(idx);
	}

	@Override
	public void getConfig(Properties prop) {
		return;
	}

}
