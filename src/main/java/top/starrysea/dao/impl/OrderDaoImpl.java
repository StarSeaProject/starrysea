package top.starrysea.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import top.starrysea.common.Common;
import top.starrysea.common.Condition;
import top.starrysea.common.DaoResult;
import top.starrysea.dao.IOrderDao;
import top.starrysea.kql.clause.OrderByType;
import top.starrysea.kql.clause.SelectClause;
import top.starrysea.kql.clause.UpdateSetType;
import top.starrysea.kql.clause.WhereType;
import top.starrysea.kql.facede.EntitySqlResult;
import top.starrysea.kql.facede.IntegerSqlResult;
import top.starrysea.kql.facede.KumaSqlDao;
import top.starrysea.kql.facede.ListSqlResult;
import top.starrysea.object.dto.Area;
import top.starrysea.object.dto.City;
import top.starrysea.object.dto.Orders;
import top.starrysea.object.dto.Province;

import static top.starrysea.common.Common.*;

@Repository("orderDao")
public class OrderDaoImpl implements IOrderDao {

	@Autowired
	private KumaSqlDao kumaSqlDao;
	// 订单每页显示条数
	public static final int PAGE_LIMIT = 10;

	@Override
	// 根据订单号查询一个订单
	public DaoResult getOrderDao(Orders order) {
		kumaSqlDao.selectMode();
		if (isNotNull(order.getOrderNum())) {
			EntitySqlResult<Orders> theResult = kumaSqlDao.select("order_name").select("province_name", "p")
					.select("city_name", "c").select("area_name", "a").select("order_address").select("order_status")
					.select("order_expressnum").select("order_time").select("order_email").select("order_remark")
					.from(Orders.class, "o").leftjoin(Area.class, "a", "area_id", Orders.class, "order_area")
					.leftjoin(City.class, "c", "city_id", Area.class, "city_id")
					.leftjoin(Province.class, "p", "province_id", City.class, "province_id")
					.where("order_num", WhereType.EQUALS, order.getOrderNum())
					.endForObject((rs, row) -> new Orders.Builder().orderName(rs.getString("order_name"))
							.orderArea(new Area.Builder().areaName(rs.getString("area_name"))
									.city(new City.Builder().cityName(rs.getString("city_name"))
											.province(new Province(null, rs.getString("province_name"))).build())
									.build())
							.orderAddress(rs.getString("order_address")).orderStatus(rs.getShort("order_status"))
							.orderExpressnum(rs.getString("order_expressnum")).orderTime(rs.getLong("order_time"))
							.orderEMail(rs.getString("order_email")).orderRemark(rs.getString("order_remark")).build());
			return new DaoResult(true, theResult.getResult());
		} else if (isNotNull(order.getOrderId())) {
			EntitySqlResult<Orders> theResult = kumaSqlDao.select("order_name").select("province_name", "p")
					.select("city_name", "c").select("area_name", "a").select("order_address").select("order_status")
					.select("order_expressnum").select("order_time").select("order_email").select("order_num")
					.select("order_remark").select("order_phone").from(Orders.class, "o")
					.leftjoin(Area.class, "a", "area_id", Orders.class, "order_area")
					.leftjoin(City.class, "c", "city_id", Area.class, "city_id")
					.leftjoin(Province.class, "p", "province_id", City.class, "province_id")
					.where("order_id", WhereType.EQUALS, order.getOrderId())
					.endForObject((rs, row) -> new Orders.Builder().orderName(rs.getString("order_name"))
							.orderArea(new Area.Builder().areaName(rs.getString("area_name"))
									.city(new City.Builder().cityName(rs.getString("city_name"))
											.province(new Province(null, rs.getString("province_name"))).build())
									.build())
							.orderAddress(rs.getString("order_address")).orderStatus(rs.getShort("order_status"))
							.orderExpressnum(rs.getString("order_expressnum")).orderTime(rs.getLong("order_time"))
							.orderEMail(rs.getString("order_email")).orderRemark(rs.getString("order_remark"))
							.orderNum(rs.getString("order_num")).orderPhone(rs.getString("order_phone")).build());
			return new DaoResult(true, theResult.getResult());
		}
		throw new IllegalArgumentException("订单号和订单id不能同时为空");
	}

	@Override
	// 对一个作品进行下单
	public DaoResult saveOrderDao(Orders order) {
		kumaSqlDao.insertMode();
		order.setOrderNum(Common.getCharId(30));
		kumaSqlDao.insert("order_id", order.getOrderId()).insert("order_num", order.getOrderNum())
				.insert("order_name", order.getOrderName()).insert("order_area", order.getOrderArea().getAreaId())
				.insert("order_address", order.getOrderAddress()).insert("order_status", 1)
				.insert("order_time", System.currentTimeMillis()).insert("order_email", order.getOrderEMail())
				.insert("order_remark", order.getOrderRemark()).insert("order_phone", order.getOrderPhone())
				.table(Orders.class).end();
		return new DaoResult(true, order);
	}

	@Override
	// 修改一个订单的状态
	public DaoResult updateOrderDao(Orders order) {
		kumaSqlDao.updateMode();
		kumaSqlDao.update("order_status", UpdateSetType.ASSIGN, order.getOrderStatus())
				.update("order_expressnum", UpdateSetType.ASSIGN, order.getOrderExpressnum()).table(Orders.class)
				.where("order_id", WhereType.EQUALS, order.getOrderId()).end();
		return new DaoResult(true);
	}

	@Override
	// 删除一个订单
	public DaoResult deleteOrderDao(Orders order) {
		kumaSqlDao.deleteMode();
		kumaSqlDao.table(Orders.class).where("order_id", WhereType.EQUALS, order.getOrderId()).end();
		return new DaoResult(true);
	}

	@Override
	public DaoResult getAllOrderDao(Condition condition, Orders order) {
		kumaSqlDao.selectMode();
		ListSqlResult<Orders> theResult = kumaSqlDao.select("order_id").select("order_num").select("order_name")
				.select("order_status").select("order_time").from(Orders.class)
				.where("order_num", WhereType.FUZZY, order.getOrderNum())
				.where("order_status", WhereType.EQUALS, order.getOrderStatus())
				.where("order_name", WhereType.FUZZY, order.getOrderName()).orderBy("order_status")
				.orderBy("order_time", OrderByType.DESC).limit((condition.getPage() - 1) * PAGE_LIMIT, PAGE_LIMIT)
				.endForList((rs, row) -> new Orders.Builder().orderId(rs.getString("order_id"))
						.orderNum(rs.getString("order_num")).orderName(rs.getString("order_name"))
						.orderStatus(rs.getShort("order_status")).orderTime(rs.getLong("order_time")).build());
		return new DaoResult(true, theResult.getResult());
	}

	@Override
	public DaoResult getOrderCountDao(Condition condition, Orders order) {
		kumaSqlDao.selectMode();
		IntegerSqlResult theResult = kumaSqlDao.select(SelectClause.COUNT).from(Orders.class)
				.where("order_num", WhereType.FUZZY, order.getOrderNum())
				.where("order_status", WhereType.EQUALS, order.getOrderStatus())
				.where("order_name", WhereType.FUZZY, order.getOrderName()).endForNumber();
		return new DaoResult(true, theResult.getResult());
	}

}
