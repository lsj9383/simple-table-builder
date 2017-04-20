package demo;

import java.util.Iterator;

import org.junit.Test;
import com.lsj.stb.exception.SqlBuilderException;
import com.lsj.stb.structure.FormatProcess;
import com.lsj.stb.structure.Line;
import com.lsj.stb.structure.SimpleTable;
import com.lsj.stb.structure.Table;

public class SimpleTest {

	@Test
	public void test() throws ClassNotFoundException, SqlBuilderException {
		Table table = new SimpleTable(new String[]{"学号", "姓名", "性别", "年龄"});
		table.addLine(new String[]{"2011102036", "荆轲"  , "男", "21"});
		table.addLine(new String[]{"2011102037", "诸葛亮", "男", "20"});
		table.addLine(new String[]{"2011102038", "妲己"  , "女", "18"});
		System.out.println(table.getLine(0).get("性别"));
		System.out.println(table.get(1, "姓名"));
		System.out.println(table.get(2, "年龄"));
		System.out.println(table.get(3, "年龄"));
		System.out.println(table.getLine(2));
		System.out.println(table.getLine(3));
		
		/* 迭代器遍历 */
		for(Iterator<Line> it=table.iterator(); it.hasNext(); ){
			Line line = it.next();
			System.out.println(line.get("学号"));
		}
		String ou = table.format(new FormatProcess() {
			@Override
			public String output(int index, Line line) {
				String origin = "%s,%s,%s\n";
				return String.format(origin, line.get("姓名"), line.get("性别"), line.get("年龄"));
			}
		});
		System.out.println(ou);
	}

}
