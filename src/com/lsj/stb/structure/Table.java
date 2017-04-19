package com.lsj.stb.structure;

import java.util.Map;

public interface Table extends Iterable<Line>{
	public String getName();
	public String[] getKeys();
	public String get(int line, String key);
	
	public void addLine(Map<String, String> line);
	public void addLine(String[] line);
	
	public String format(FormatProcess fp);
}
