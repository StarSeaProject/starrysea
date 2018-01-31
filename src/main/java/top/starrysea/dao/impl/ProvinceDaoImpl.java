package top.starrysea.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import top.starrysea.common.DaoResult;
import top.starrysea.dao.IProvinceDao;
import top.starrysea.kql.facede.KumaSqlDao;
import top.starrysea.kql.facede.ListSqlResult;
import top.starrysea.object.dto.Area;
import top.starrysea.object.dto.City;
import top.starrysea.object.dto.Province;

@Repository("areaDao")
public class ProvinceDaoImpl implements IProvinceDao {

	@Autowired
	private KumaSqlDao kumaSqlDao;

	public DaoResult getAllProvinceDao() {
		kumaSqlDao.selectMode();
		ListSqlResult theResult = kumaSqlDao.select("province_name", "p").select("province_id", "c")
				.select("city_name", "c").select("city_id", "a").select("area_id", "a").select("area_name", "a")
				.from(Province.class, "p").leftjoin(City.class, "c", "province_id", Province.class,
						"province_id")
				.leftjoin(Area.class, "a", "city_id", City.class,
						"city_id")
				.endForList((rs, row) -> new Area.Builder().areaId(rs.getInt("area_id"))
						.areaName(rs.getString("area_name"))
						.city(new City.Builder().cityId(rs.getInt("city_id")).cityName(rs.getString("city_name"))
								.province(new Province(rs.getInt("province_id"), rs.getString("province_name")))
								.build())
						.build());
		return new DaoResult(true, theResult.getResult());
	}
}
