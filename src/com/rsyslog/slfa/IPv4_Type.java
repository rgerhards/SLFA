package com.rsyslog.slfa;

import java.util.Properties;

public class IPv4_Type extends Type {

	private enum ipv4mode {ZERO, RANDOM};
	private ipv4mode mode;
	private Boolean cons = false;
	private int bits;

	private int getposint(String msg, int idx) {
		int val = -1;
		char c = msg.charAt(idx);
		while(c <= '9' && c >= '0') {
			if(val == -1) {
				val = 0;
			}
			val = val * 10 + (c - '0');
			idx++;
			c = msg.charAt(idx);
		}
		return val;
	}
	
	private int syntax(String msg, int idx) {
		int len = 0;
		Boolean isIP = false;
		
		if(isIP) {
			return len;
		} else {
			return 0;
		}
	}
	
	@Override
	public int anon(String msg, int idx, StringBuffer output) {
		String address;
		int iplen;
		int caddresslen;
		
		iplen = syntax(msg, idx);
		
		return idx;
	}

	@Override
	public void getConfig(Properties prop) {
		String var;
		
		var = prop.getProperty("ipv4.bits");
		if(var != null) {
			bits = Integer.parseInt(var);
		} else {
			bits = 16;
		}
		if(bits < 1 || bits > 32) {
			System.out.println("config error: invalid number of ipv4.bits (" + bits + "), corrected to 32");
			bits = 32;
		}
		
		var = prop.getProperty("ipv4.mode");
		if(var.contentEquals("zero")) {
			mode = ipv4mode.ZERO;
		} else if(var.contentEquals("random")) {
			mode = ipv4mode.RANDOM;
		} else if(var.contentEquals("random-consistent")) {
			mode = ipv4mode.RANDOM;
			cons = true;
		} else {
			mode = ipv4mode.ZERO;
		}
	}
	
	public void testtest() {
		System.out.println("test: " + mode + " " + cons + " " + bits);
	}

}
