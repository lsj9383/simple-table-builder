package com.lsj.stb;

import java.util.HashMap;
import java.util.Map;

/**
 * 该抽象类用于准备静态资源
 * 
 * @author think
 *
 */
public abstract class AbstractStaticInformationTableBuilder implements TableBuilder{
	/**
	 * col2pro用于将office中的列名和SqlField中的属性名进行对应，这样可以不用强行规定office中的列名顺序，并且支持更多的列名选择。
	 */
	protected static Map<String, String> col2pro = new HashMap<>();
	static{
		col2pro.put("序号", "number");
		col2pro.put("number", "number");
		col2pro.put("num", "number");
		
		col2pro.put("中文", "chineseName");
		col2pro.put("chineseName", "chineseName");
		col2pro.put("chinese", "chineseName");
		col2pro.put("chineses_name", "chineseName");
		
		col2pro.put("英文", "name");
		col2pro.put("fieldName", "name");
		col2pro.put("field_name", "name");
		
		col2pro.put("类型", "type");
		col2pro.put("type", "type");
		
		col2pro.put("长度", "length");
		col2pro.put("length", "length");
		
		col2pro.put("备注", "desc");
		col2pro.put("remarks", "desc");
		col2pro.put("remark", "desc");
		col2pro.put("desc", "desc");
	}

}
