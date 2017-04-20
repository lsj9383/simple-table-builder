# Simple Table Builder
这是一种利用office中的表描述进行建表的工具。
##一.基本示例
###1.建表语句生成
```Java
TableBuilder tb = new ExcelSheetTableBuilder(TableBuilder.SqlType.SqlServer);
		Map<Integer, SqlBuildingException> map = new HashMap<>();
		List<String> buildSqls = tb.buildsqls(new File("C:/Users/lu/Desktop/Temporary/nb.xlsx"), map);
		for(String sql : buildSqls){
			System.out.println(sql);
			System.out.println("*******************************");
		}
```

##二.组件
###1.Table
逻辑表，即Table结构，是该工具中的基本组件之一。表的逻辑组织是有多个关键词