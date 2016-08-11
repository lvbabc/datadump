package zx.soft.datadump.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import zx.soft.datadump.domain.Type;

/**
 * 查询DB2数据
 * @author lb
 *
 */
public interface DataDumpMapper {

	/**
	 * 查询数据---id
	 */
	@Select("SELECT * FROM `num` where `id` > #{id} ORDER BY `id` asc LIMIT #{size}")
	public List<Type> select(@Param("id") int id, @Param("size") int size);

}
