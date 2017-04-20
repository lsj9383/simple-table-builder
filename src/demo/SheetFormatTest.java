package demo;

import java.io.File;
import java.util.List;

import org.junit.Test;

import com.lsj.stb.sql.SqlUtils;
import com.lsj.stb.structure.FormatProcess;
import com.lsj.stb.structure.Line;
import com.lsj.stb.structure.Table;
import com.lsj.stb.structure.TableUtils;

public class SheetFormatTest {

	@Test
	public void test() throws Exception {
		List<Table> tables = TableUtils.createTableByExcel(new File("nb.xlsx"));	//所有sheet转为Table
		String out = tables.get(0).format(new FormatProcess(){						//每个Table都支持格式化
			@Override
			public String output(int index, Line line) {
				String origin = "%d, %s, %s\n";
				return String.format(origin, 
						index,
						line.get("中文"), 
						SqlUtils.sqlName2JavaName(line.get("英文")));
			}
		});
		System.out.println(out);
	}

}
