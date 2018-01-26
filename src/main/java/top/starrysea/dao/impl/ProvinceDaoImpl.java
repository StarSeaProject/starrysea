package top.starrysea.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import top.starrysea.common.DaoResult;
import top.starrysea.dao.IProvinceDao;
import top.starrysea.kql.clause.WhereType;
import top.starrysea.kql.facede.KumaSqlDao;
import top.starrysea.kql.facede.ListSqlResult;
import top.starrysea.object.dto.Area;
import top.starrysea.object.dto.City;
import top.starrysea.object.dto.Province;
import top.starrysea.object.view.out.AreaForAddOrder;
import top.starrysea.object.view.out.CityForAddOrder;
import top.starrysea.object.view.out.ProvinceForAddOrder;

@Repository("areaDao")
public class ProvinceDaoImpl implements IProvinceDao {

	@Autowired
	private KumaSqlDao kumaSqlDao;

	@Override
	public DaoResult getAllProvinceDao() {
		kumaSqlDao.selectMode();
		ListSqlResult provinceResult = kumaSqlDao.select("province_id").select("province_name").from(Province.class)
				.endForList((rs, row) -> new Province(rs.getInt("province_id"), rs.getString("province_name")));
		List<Province> provinces = (List<Province>) provinceResult.getResult();
		Map<Integer,ProvinceForAddOrder> provincesForAddOrder = new HashMap<>();
		for (Province province : provinces) {
			ProvinceForAddOrder p = province.toVo();
			ListSqlResult cityResult = kumaSqlDao.select("city_id").select("city_name").from(City.class)
					.where("province_id", WhereType.EQUALS, p.getProvinceId())
					.endForList((rs, row) -> new City.Builder().cityId(rs.getInt("city_id"))
							.cityName(rs.getString("city_name")).build());
			List<City> citys = (List<City>) cityResult.getResult();
			Map<Integer,CityForAddOrder> citysForAddOrders = new HashMap<>();
			for (City city : citys) {
				CityForAddOrder c = city.toVo();
				ListSqlResult areaResult = kumaSqlDao.select("area_id").select("area_name").from(Area.class)
						.where("city_id", WhereType.EQUALS, c.getCityId()).endForList((rs, row) -> new Area.Builder()
								.areaId(rs.getInt("area_id")).areaName(rs.getString("area_name")).build());
				List<Area> areas = (List<Area>) areaResult.getResult();
				List<AreaForAddOrder> areasForAddOrder = areas.stream().map(Area::toVo).collect(Collectors.toList());
				c.setAreas(areasForAddOrder);
				citysForAddOrders.put(c.getCityId(), c);
			}
			p.setCitys(citysForAddOrders);
			provincesForAddOrder.put(p.getProvinceId(), p);
		}
		return new DaoResult(true, provincesForAddOrder);
	}

}
