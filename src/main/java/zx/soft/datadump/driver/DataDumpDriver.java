package zx.soft.datadump.driver;

import zx.soft.datadump.main.DataDumpMain;
import zx.soft.utils.driver.ProgramDriver;

/**
 * 驱动类
 *
 * @author lvbing
 *
 */
public class DataDumpDriver {

	/**
	 * 主函数
	 */
	public static void main(String[] args) {

		int exitCode = -1;
		ProgramDriver pgd = new ProgramDriver();
		try {
			pgd.addClass("datadump", DataDumpMain.class, "数据转存");
			pgd.driver(args);
			// Success
			exitCode = 0;
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
		System.exit(exitCode);
	}
}
