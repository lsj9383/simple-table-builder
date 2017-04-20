package demo;

import java.io.File;
import java.util.List;

import org.junit.Test;
import com.lsj.stb.sql.SqlUtils;
import com.lsj.stb.structure.FormatProcess;
import com.lsj.stb.structure.Line;
import com.lsj.stb.structure.Table;
import com.lsj.stb.structure.TableUtils;

public class FrontGenerateTest {

	@Test
	public void test() throws Exception {
		List<Table> tables = TableUtils.createTableByExcel(new File("nb.xlsx"));
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

}
