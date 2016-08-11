package zx.soft.datadump.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zx.soft.datadump.common.MybatisConfig;
import zx.soft.datadump.domain.Type;
import zx.soft.datadump.mapper.DataDumpMapper;
import zx.soft.utils.log.LogbackUtil;

/**
 * 查询DB2数据
 * @author lb
 *
 */
public class DataDumpService {

	private static Logger logger = LoggerFactory.getLogger(DataDumpService.class);

	private final SqlSessionFactory sqlSessionFactory;

	public DataDumpService(MybatisConfig.ServerEnum server) {
		try {
			sqlSessionFactory = MybatisConfig.getSqlSessionFactory(server);
		} catch (RuntimeException e) {
			logger.error("Exception:{}", LogbackUtil.expection2Str(e));
			throw new RuntimeException(e);
		}
	}

	public void close() {

	}

	/**
	 */
	public List<Type> select(int start, int size) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			DataDumpMapper dataDumpMapper = sqlSession.getMapper(DataDumpMapper.class);
			dataDumpMapper.select(start, size);
			return null;
		}
	}
}
