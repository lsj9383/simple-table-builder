# Simple Table Builder
这是一种利用office中的表描述进行建表的工具。
##一.基本示例

###1.建表语句生成
演示了如何通过Java代码加载具有表结构描述的excel文件，并将生成对应的建表语句。
```Java
public class CreatTableTest {
	@Test
	public void test() throws ClassNotFoundException, SqlBuilderException {
		SqlTableBuilder stb = new ExcelSheetTableBuilder(SqlTableBuilder.SqlType.SqlServer);		//创建支持SqlServer建表的SqlTableBuilder
		List<String> buildSqls = stb.buildsqls(new File("nb.xlsx"), new HashMap<>());				//获得对应excel中所有sheet的建表语句，hashMap用于记录错误
		System.out.println(buildSqls.get(0));
	}
}
```

###2.Excel Sheet的格式化输出
演示了加载具有二维表结构的excel文件，并将其格式化输出。这里面使用Table.format()方法，该表格式化是通过将行格式化的结果进行拼接得到的。
```Java
public class SheetFormatTest {
	@Test
	public void test() throws Exception {
		List<Table> tables = TableUtils.createTableByExcel(new File("nb.xlsx"));	//所有sheet转为Table
		String out = tables.get(0).format(new FormatProcess(){						//每个Table都支持格式化
			@Override
			public String output(int index, Line line) {				//会按行顺序进入该方法，以格式化该行。最终的Table格式化就是所有行格式化结果的拼接
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

```


##二.组件
###1.Line
逻辑行，这是该工具项目中最小的组件，也是Table组件的构成的基本元素(Table由多个Line组成)。Line的作用类似于Map，都是通过关键词获取数据，但是Line和Table是强关联的，只能获取Table中的Line，Line中的关键词就是Table中的关键词。<br>
由于Line和Table相关联，因此这里简单介绍些Table，更细节的Table使用在后面将给出。Table是一个`行-关键字`组成的二维结构，这不同于`行-列`组成的二维结构，前者是通过行和关键字进行索引的，后者是通过行和列进行索引的。更进一步说关键字就是该二维结构的列。如果以面向对象打比方，Table就是一个Javabean的集合，下标等价于行，而Javabean的字段等价于关键字。<br>
例如以下就是一个简单Table结构：
![]()

###2.Table
逻辑表，是该工具中的基本组件之一，其抽象是Table，现仅有SimpleTable作为其实现。表的逻辑组织是有多个关键词