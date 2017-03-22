package demo;

import com.lsj.stb.ExcelSheetTableBuilder;
import com.lsj.stb.TableBuilder;

public class Main {
	public static void main(String[] args){
		TableBuilder tb = new ExcelSheetTableBuilder("jdbc:sqlserver://121.42.158.223:1433;DatabaseName=b");
		tb.build();
		System.out.println("done!");
	}
}
