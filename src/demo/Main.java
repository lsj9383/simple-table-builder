package demo;

import java.io.File;
import java.sql.SQLException;

import com.lsj.stb.ExcelSheetTableBuilder;
import com.lsj.stb.TableBuilder;
import com.lsj.stb.exception.SqlBuilderException;

public class Main {
	public static void main(String[] args) throws ClassNotFoundException, SQLException, SqlBuilderException{
		TableBuilder tb = new ExcelSheetTableBuilder("jdbc:sqlserver://121.42.158.223:1433;DatabaseName=b", "lsj", "123");
		tb.build(new File("D:/Desktop/temp/nb.xlsx"));
		tb.close();
		System.out.println("done!");
	}
}
