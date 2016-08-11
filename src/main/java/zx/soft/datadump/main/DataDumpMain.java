package zx.soft.datadump.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zx.soft.datadump.common.ConfigUtil;
import zx.soft.datadump.common.MybatisConfig;
import zx.soft.datadump.domain.Type;
import zx.soft.datadump.service.DataDumpService;
import zx.soft.utils.log.LogbackUtil;

/**
 *实现断点续传，db2
 * @author lb
 *
 */
public class DataDumpMain {

	private final DataDumpService dataDumpService;
	private static String insert = "INSERT INTO USER (NAME,BIRTHDAY) VALUES '%s'";
	private static Logger logger = LoggerFactory.getLogger(DataDumpMain.class);
	private static Properties pros = ConfigUtil.getProps("configuration.properties");
	private static int id;
	private static int size;
	private static int cycles;
	private static String dir;
	private static File file;

	public DataDumpMain() {
		dataDumpService = new DataDumpService(MybatisConfig.ServerEnum.db2);
		id = Integer.parseInt(pros.getProperty("start"));
		size = Integer.parseInt(pros.getProperty("size"));
		cycles = Integer.parseInt(pros.getProperty("size"));
		dir = pros.getProperty("dir");
	}

	/**
	 * 主函数
	 * @throws IOException
	 */
	public static void main(String[] args) {
		DataDumpMain dataDumpMain = new DataDumpMain();

		try {
			dataDumpMain.run();
			ConfigUtil.setProps(pros, "start", Integer.toString(id), "configuration.properties");
		} catch (Exception e) {
			logger.error("Exception:{}", LogbackUtil.expection2Str(e));
		}
	}

	public void run() {

		logger.info("Starting dump ...");

		//新建文件，文件名为当前时间
		LocalDateTime datetime = LocalDateTime.now();
		file = new File(dir + datetime.toString() + ".txt");
		creatFile(file);

		for (int i = 0; i < cycles; i++) {

			List<Type> data = dataDumpService.select(id, size);
			persistence(data);
			//更改起始点
			id += size;
		}
		// 关闭资源
		dataDumpService.close();
		logger.info("Finishing dump ...");
	}

	//数据持久化
	private void persistence(List<Type> data) {

		StringBuilder sBuilder = new StringBuilder();
		for (Type type : data) {
			sBuilder.append(type.toString() + ",");
		}
		sBuilder.deleteCharAt(sBuilder.lastIndexOf(","));
		sBuilder.append(";");

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
			String cont = String.format(insert, sBuilder.toString());
			try {
				writer.write(cont);
				writer.write("\r\n");
			} finally {
				writer.close();
			}
		} catch (IOException e) {
			logger.error("Exception:{}", LogbackUtil.expection2Str(e));
		}
	}

	//创建新文件
	private void creatFile(File file) {
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
