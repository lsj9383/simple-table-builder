package demo;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lsj.stb.ExcelSheetTableBuilder;
import com.lsj.stb.TableBuilder;
import com.lsj.stb.exception.SqlBuilderException;
import com.lsj.stb.exception.SqlBuildingException;
import com.lsj.stb.sql.SqlUtils;
import com.lsj.stb.structure.FormatProcess;
import com.lsj.stb.structure.Line;
import com.lsj.stb.structure.SimpleTable;
import com.lsj.stb.structure.Table;
import com.lsj.stb.structure.AbstractTable;

public class Main {
	
	static void test1() throws ClassNotFoundException, SqlBuilderException{
		TableBuilder tb = new ExcelSheetTableBuilder(TableBuilder.SqlType.SqlServer);
		Map<Integer, SqlBuildingException> map = new HashMap<>();
		List<String> buildSqls = tb.buildsqls(new File("C:/Users/lu/Desktop/Temporary/nb.xlsx"), map);
		for(String sql : buildSqls){
			System.out.println(sql);
			System.out.println("*******************************");
		}
	}
	
	static void test2(){
		AbstractTable table = new SimpleTable(new String[]{"name", "sex", "age"});
		table.addLine(new String[]{"lsj", "boy", "24"});
		table.addLine(new String[]{"hjs", "girl", "18"});
		String ou = table.format(new FormatProcess() {
			@Override
			public String output(int index, Line line) {
				String origin = "%s,%s,%s\n";
				return String.format(origin, line.get("name"), line.get("sex"), line.get("age"));
			}
		});
		System.out.println(ou);
	}
	
	static void test3() throws ClassNotFoundException, SqlBuilderException{
		TableBuilder tb = new ExcelSheetTableBuilder(TableBuilder.SqlType.SqlServer);
		List<Table> tables = tb.loadTable(new File("C:/Users/lu/Desktop/Temporary/nb.xlsx"));
		for(Table table : tables){
			String out = table.format(new FormatProcess(){
				@Override
				public String output(int index, Line line) {
					String origin = 
							"<div class=\"layui-form-item\">\n"
							+ "\t<label class=\"layui-form-label\">%s</label>\n"
							+ "\t<div class=\"layui-input-block\">\n"
							+ "\t\t<input type=\"text\" name=\"%s\" lay-verify=\"title\" value=\"%d\" autocomplete=\"off\" class=\"layui-input\">\n"
							+ "\t</div>\n"
							+ "</div>\n";
					return String.format(origin, 
							line.get("中文"), 
							SqlUtils.sqlName2JavaName(line.get("英文")), 
							index);
				}
			});
			System.out.println(out);
			System.out.println("*****************************************");
		}
	}
	
	public static void main(String[] args) throws Exception{
		test1();
		System.out.println("done!");
	}
}
