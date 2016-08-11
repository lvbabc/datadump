package zx.soft.datadump.common;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zx.soft.utils.log.LogbackUtil;

/**
 * 配置文件读取类
 *
 * @author wanggang
 *
 */
public class ConfigUtil {

	private static Logger logger = LoggerFactory.getLogger(ConfigUtil.class);

	public static Properties UTILS_PROPS;

	public static Properties getProps(String confFileName) {
		Properties result = new Properties();
		logger.info("Load resource: " + confFileName);
		try (FileInputStream in = new FileInputStream(confFileName);) {
			result.load(in);
			return result;
		} catch (final Exception e) {
			logger.error("Config file '{}' does not exist!", confFileName);
			throw new RuntimeException();
		}
	}

	public static void setProps(Properties props, String key, String value, String confFileName) {
		props.setProperty(key, value);
		// 文件输出流
		logger.info("Set resource: " + confFileName);
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(confFileName);
			// 将Properties集合保存到流中
			props.store(fos, "Update");
			fos.close();// 关闭流
		} catch (IOException e) {
			logger.error("Exception:{}", LogbackUtil.expection2Str(e));
		}
	}
}
