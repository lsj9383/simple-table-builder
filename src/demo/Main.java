package demo;

import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lsj.stb.ExcelSheetTableBuilder;
import com.lsj.stb.Format;
import com.lsj.stb.TableBuilder;
import com.lsj.stb.exception.SqlBuilderException;
import com.lsj.stb.exception.SqlBuildingException;
import com.lsj.stb.sql.field.SqlFieldBuilder;

public class Main {
	
	static void test1() throws ClassNotFoundException, SqlBuilderException{
		TableBuilder tb = new ExcelSheetTableBuilder(TableBuilder.SqlType.SqlServer);
		Map<Integer, SqlBuildingException> map = new HashMap<>();
		List<String> buildSqls = tb.buildsqls(new File("D:/Desktop/temp/nb.xlsx"), map);
		for(String sql : buildSqls){
			System.out.println(sql);
			System.out.println("*******************************");
		}
		for(Entry<Integer, SqlBuildingException> entry : map.entrySet()){
			System.out.println(entry.getKey()+":"+entry.getValue().getMessage());
		}
	}
	
	static void test2() throws ClassNotFoundException, SqlBuilderException, SqlBuildingException{
		TableBuilder tb = new ExcelSheetTableBuilder(TableBuilder.SqlType.SqlServer);
		List<String> buildSqls = tb.buildFormats(new File("D:/Desktop/temp/nb.xlsx"), new Format(){
			@Override
			public String output(int index, SqlFieldBuilder builder) {
				String origin = 
						"<div class=\"layui-form-item\">\n"
						+ "\t<label class=\"layui-form-label\">%s</label>\n"
						+ "\t<div class=\"layui-input-block\">\n"
						+ "\t\t<input type=\"text\" name=\"%s\" lay-verify=\"title\" value=\"%d\" autocomplete=\"off\" class=\"layui-input\">\n"
						+ "\t</div>\n"
						+ "</div>\n";
				return String.format(origin, builder.getChineseName(), sqlName2JavaName(builder.getName()), index);
			}
		});
		for(String sql : buildSqls){
			System.out.println(sql);
			System.out.println("*******************************");
		}
	}
	
	static void test3() throws ClassNotFoundException, SqlBuilderException, SqlBuildingException{
		TableBuilder tb = new ExcelSheetTableBuilder(TableBuilder.SqlType.SqlServer);
		List<String> buildSqls = tb.buildFormats(new File("D:/Desktop/temp/nb.xlsx"), new Format(){
			@Override
			public String output(int index, SqlFieldBuilder builder) {
				String origin = 
						"'%s': $(\"input[name='%s']\").val(),\n";
				return String.format(origin, sqlName2JavaName(builder.getName()), sqlName2JavaName(builder.getName()));
			}
		});
		for(String sql : buildSqls){
			System.out.println(sql);
			System.out.println("*******************************");
		}
	}
	
	static void test4() throws ClassNotFoundException, SqlBuilderException, SqlBuildingException{
		TableBuilder tb = new ExcelSheetTableBuilder(TableBuilder.SqlType.SqlServer);
		List<String> buildSqls = tb.buildFormats(new File("D:/Desktop/temp/nb.xlsx"), new Format(){
			@Override
			public String output(int index, SqlFieldBuilder builder) {
				String origin = 
						"<div class=\"layui-form-item\">\n"
						+ "\t<label class=\"layui-form-label\">%s</label>\n"
						+ "\t<div class=\"layui-input-block\">\n"
						+ "\t\t<input type=\"text\" name=\"%s\" lay-verify=\"title\" value=\"${bridgeCard.%s}\" autocomplete=\"off\" class=\"layui-input\">\n"
						+ "\t</div>\n"
						+ "</div>\n";
				return String.format(origin, builder.getChineseName(), sqlName2JavaName(builder.getName()), sqlName2JavaName(builder.getName()));
			}
		});
		for(String sql : buildSqls){
			System.out.println(sql);
			System.out.println("*******************************");
		}
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, SqlBuilderException, SqlBuildingException{
		test4();
		System.out.println("done!");
	}
	
	static private String sqlName2JavaName(String sqlName){
		String[] parts = sqlName.split("_");
		StringBuilder sb = new StringBuilder();
		sb.append(parts[0]);
		for(int i=1; i<parts.length; i++){
			sb.append(parts[i].substring(0, 1).toUpperCase()+parts[i].substring(1));
		}
		return sb.toString();
	}
}
