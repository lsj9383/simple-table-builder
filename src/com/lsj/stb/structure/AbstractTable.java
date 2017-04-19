package com.lsj.stb.structure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class AbstractTable implements Table, Iterable<Line>{
	private final String tableName;
	private final String[] keys;
	private final Map<String, Integer> key2index = new HashMap<>();
	private final List<Line> lines = new ArrayList<>();
	
	public AbstractTable(String tableName, String[] keys){
		this.tableName = tableName;
		this.keys = keys;
		for(int i=0; i<keys.length; i++){
			key2index.put(keys[i], i);
		}
	}
	
	public AbstractTable(String[] keys){
		this("no-name", keys);
	}
	
	@Override
	public String getName(){
		return tableName;
	}
	
	@Override
	public String[] getKeys(){
		String[] tmpKeys = new String[keys.length];
		for(int i=0; i<keys.length; i++){
			tmpKeys[i] = keys[i];
		}
		return tmpKeys;
	}
	
	@Override
	public String get(int line, String key){
		if(line >= lines.size()){
			return null;
		}
		return lines.get(line).get(key);
	}
	
	@Override
	public void addLine(Map<String, String> line){
		lines.add(new TableLine(line));
	}
	
	@Override
	public void addLine(String[] line){
		lines.add(new TableLine(line));
	}
	
	@Override
	public String format(FormatProcess fp){
		StringBuilder sb = new StringBuilder();
		for(int index=0; index<lines.size(); index++){
			sb.append(fp.output(index, lines.get(index)));
		}
		return sb.toString();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(tableName+"\n");
		for(String key : keys){
			sb.append(key+"\t");
		}
		for(Line line : lines){
			sb.append("\n");
			for(String key : keys){
				sb.append(line.get(key)+"   ");
			}
		}
		return sb.toString();
	}
	
	@Override
	public Iterator<Line> iterator() {
		return new Iterator<Line>(){
			private int index = 0;
			@Override
			public boolean hasNext() {
				if(index >= lines.size()){
					return false;	
				}else{
					return true;
				}
			}

			@Override
			public Line next() {
				return lines.get(index++);
			}
		};
	}
	
	
	/**
	 * Table表的行数据
	 * 
	 * @author lu
	 *
	 */
	private class TableLine implements Line{
		private final String[] values = new String[keys.length];
		
		public TableLine(String[] values){
			for(int i=0; i<values.length && i<keys.length; i++){
				this.values[i] = values[i];
			}
		}
		
		public TableLine(Map<String, String> line){
			for(int i=0; i<keys.length; i++){
				if( line.get(keys[i])!=null ){
					values[i] = line.get(keys[i]);
				}
			}
		}
		
		@Override
		public String get(String key){
			if(key2index.get(key) == null){
				return null;
			}else{
				return values[key2index.get(key)];
			}
		}

		@Override
		public String[] getKeys() {
			String[] tmpKeys = new String[keys.length];
			for(int i=0; i<keys.length; i++){
				tmpKeys[i] = keys[i];
			}
			return tmpKeys;
		}
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			for(String key : keys){
				sb.append(String.format("%s : %s\n", key, get(key)));
			}
			return sb.toString();
		}
	}
}
