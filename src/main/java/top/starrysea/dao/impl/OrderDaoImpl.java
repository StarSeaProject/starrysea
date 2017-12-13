package top.starrysea.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import top.starrysea.common.Common;
import top.starrysea.common.Condition;
import top.starrysea.common.DaoResult;
import top.starrysea.common.SqlWithParams;
import top.starrysea.dao.IOrderDao;
import top.starrysea.object.dto.Area;
import top.starrysea.object.dto.City;
import top.starrysea.object.dto.Orders;
import top.starrysea.object.dto.Province;
import top.starrysea.object.dto.Work;

import static top.starrysea.common.Common.*;

import java.util.List;

@Repository("orderDao")
public class OrderDaoImpl implements IOrderDao {

	@Autowired
	private JdbcTemplate template;
	// 订单每页显示条数
	public static final int PAGE_LIMIT = 10;

	@Override
	// 根据订单号查询一个订单
	public DaoResult getOrderDao(Orders order) {
		if (isNotNull(order.getOrderNum())) {
			String sql = "SELECT order_name,w.work_name,p.province_name,c.city_name,a.area_name,order_address,order_status,order_expressnum "
					+ "FROM orders AS o " + "LEFT JOIN area AS a " + "ON o.order_area = a.area_id "
					+ "LEFT JOIN city AS c " + "ON a.city_id=c.city_id " + "LEFT JOIN province AS p "
					+ "ON c.province_id = p.province_id " + "LEFT JOIN work AS w " + " ON o.work_id = w.work_id "
					+ "WHERE order_num = ?";
			Orders theResult = template.queryForObject(sql, new Object[] { order.getOrderNum() },
					(rs, row) -> new Orders.Builder().orderName(rs.getString("order_name"))
							.work(new Work.Builder().workName(rs.getString("work_name")).build())
							.orderArea(new Area.Builder().areaName(rs.getString("area_name"))
									.city(new City.Builder().cityName(rs.getString("city_name"))
											.province(new Province(null, rs.getString("province_name"))).build())
									.build())
							.orderAddress(rs.getString("order_address")).orderStatus(rs.getShort("order_status"))
							.orderExpressnum(rs.getString("order_expressnum")).build());
			return new DaoResult(true, theResult);
		} else if (isNotNull(order.getOrderId())) {
			String sql = "SELECT order_name,w.work_name,p.province_name,c.city_name,a.area_name,order_address,order_status,order_expressnum "
					+ "FROM orders AS o " + "LEFT JOIN area AS a " + "ON o.order_area = a.area_id "
					+ "LEFT JOIN city AS c " + "ON a.city_id=c.city_id " + "LEFT JOIN province AS p "
					+ "ON c.province_id = p.province_id " + "LEFT JOIN work AS w " + " ON o.work_id = w.work_id "
					+ "WHERE order_id = ?";
			Orders theResult = template.queryForObject(sql, new Object[] { order.getOrderId() },
					(rs, row) -> new Orders.Builder().orderName(rs.getString("order_name"))
							.work(new Work.Builder().workName(rs.getString("work_name")).build())
							.orderArea(new Area.Builder().areaName(rs.getString("area_name"))
									.city(new City.Builder().cityName(rs.getString("city_name"))
											.province(new Province(null, rs.getString("province_name"))).build())
									.build())
							.orderAddress(rs.getString("order_address")).orderStatus(rs.getShort("order_status"))
							.orderExpressnum(rs.getString("order_expressnum")).build());
			return new DaoResult(true, theResult);
		}
		return new DaoResult(false, "订单号和订单id不能同时为空");
	}

	@Override
	// 对一个作品进行下单
	public DaoResult saveOrderDao(Orders order) {
		String sql = "INSERT INTO orders(order_id,work_id,order_num,order_name,order_area,order_address,order_status,order_time) "
				+ "VALUES(?,?,?,?,?,?,?,?)";
		template.update(sql, Common.getCharId("O-", 10), order.getWork().getWorkId(), Common.getCharId(30),
				order.getOrderName(), order.getOrderArea().getAreaId(), order.getOrderAddress(), 1,
				System.currentTimeMillis());
		return new DaoResult(true);
	}

	@Override
	// 修改一个订单的状态
	public DaoResult updateOrderDao(Orders order) {
		String sql = "";
		if (order.getOrderExpressnum() != null) {
			sql = "UPDATE orders " + "SET order_status = ? , order_expressnum = ?" + "WHERE order_id = ?";
			template.update(sql, order.getOrderStatus(), order.getOrderExpressnum(), order.getOrderId());
		} else {
			sql = "UPDATE orders " + "SET order_status = ? " + "WHERE order_id = ?";
			template.update(sql, order.getOrderStatus(), order.getOrderId());
		}
		return new DaoResult(true);
	}

	@Override
	// 删除一个订单
	public DaoResult deleteOrderDao(Orders order) {
		String sql = "DELETE FROM orders " + "WHERE order_id = ?";
		template.update(sql, order.getOrderId());
		return new DaoResult(true);
	}

	@Override
	public DaoResult getAllOrderDao(Condition condition, Orders order) {
		SqlWithParams sqlWithParams = getTheSqlForGetAll(order);
		String sql = "SELECT order_id,order_num,order_name,order_status,order_time " + "FROM orders "
				+ sqlWithParams.getWhere() + "ORDER BY order_time DESC " + "LIMIT "
				+ (condition.getPage() - 1) * PAGE_LIMIT + "," + PAGE_LIMIT;
		List<Orders> theResult = template.query(sql, sqlWithParams.getParams(),
				(rs, row) -> new Orders.Builder().orderId(rs.getString("order_id")).orderNum(rs.getString("order_num"))
						.orderName(rs.getString("order_name")).orderStatus(rs.getShort("order_status"))
						.orderTime(rs.getLong("order_time")).build());
		return new DaoResult(true, theResult);
	}

	private SqlWithParams getTheSqlForGetAll(Orders order) {
		StringBuilder whereBuffer = new StringBuilder();
		int insertIndex;
		Object[] preParams = new Object[1];
		int paramsIndex = 0;
		whereBuffer.append("WHERE 1=1 ");

		if (isNotNull(order.getOrderNum())) {
			insertIndex = whereBuffer.indexOf("WHERE") + 5;
			whereBuffer.insert(insertIndex, " order_num LIKE ? AND ");
			preParams[paramsIndex] = "%" + order.getOrderNum() + "%";
			paramsIndex++;
		}

		if (isNotNull(order.getOrderStatus())) {
			insertIndex = whereBuffer.indexOf("WHERE") + 5;
			whereBuffer.insert(insertIndex, " order_status = ? AND ");
			preParams[paramsIndex] = order.getOrderStatus();
			paramsIndex++;
		}

		if (isNotNull(order.getOrderName())) {
			insertIndex = whereBuffer.indexOf("WHERE") + 5;
			whereBuffer.insert(insertIndex, " order_name LIKE ? AND ");
			preParams[paramsIndex] = "%" + order.getOrderName() + "%";
			paramsIndex++;
		}

		Object[] params = new Object[paramsIndex];
		System.arraycopy(preParams, 0, params, 0, paramsIndex);
		return new SqlWithParams(whereBuffer.toString(), params);
	}

	@Override
	public DaoResult getOrderCountDao(Condition condition, Orders order) {
		SqlWithParams sqlWithParams = getTheSqlForGetAll(order);
		String sql = "SELECT COUNT(*) " + "FROM orders " + sqlWithParams.getWhere();
		Object[] params = sqlWithParams.getParams();
		Integer theResult = template.queryForObject(sql, params, Integer.class);
		return new DaoResult(true, theResult);
	}

}
