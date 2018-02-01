package top.starrysea.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Repository;

import top.starrysea.common.DaoResult;
import top.starrysea.dao.IOrderDetailDao;
import top.starrysea.kql.clause.SelectClause;
import top.starrysea.kql.clause.WhereType;
import top.starrysea.kql.facede.IntegerSqlResult;
import top.starrysea.kql.facede.KumaSqlDao;
import top.starrysea.kql.facede.ListSqlResult;
import top.starrysea.object.dto.Area;
import top.starrysea.object.dto.City;
import top.starrysea.object.dto.OrderDetail;
import top.starrysea.object.dto.Orders;
import top.starrysea.object.dto.Province;
import top.starrysea.object.dto.Work;
import top.starrysea.object.dto.WorkType;

@Repository("orderDetailDao")
public class OrderDetailDaoImpl implements IOrderDetailDao {

	@Autowired
	private KumaSqlDao kumaSqlDao;

	@Override
	public DaoResult getAllOrderDetailDao(OrderDetail orderDetail) {
		kumaSqlDao.selectMode();
		ListSqlResult theResult = kumaSqlDao
				.select("name", "wt").select("work_name",
						"w")
				.from(OrderDetail.class)
				.innerjoin(WorkType.class, "wt", "work_type_id", OrderDetail.class, "work_type_id")
				.innerjoin(Work.class, "w", "work_id", WorkType.class, "work_id")
				.where("order_id", WhereType.EQUALS, orderDetail.getOrder().getOrderId()).endForList(
						(rs, row) -> new OrderDetail.Builder()
								.workType(new WorkType.Builder().name(rs.getString("name"))
										.work(new Work.Builder().workName(rs.getString("work_name")).build()).build())
								.build());
		return new DaoResult(true, theResult.getResult());
	}

	@Override
	public DaoResult saveOrderDetailsDao(List<OrderDetail> orderDetails) {
		kumaSqlDao.insertMode();
		kumaSqlDao.insert("id").insert("work_type_id").insert("order_id").table(OrderDetail.class)
				.batchEnd(new BatchPreparedStatementSetter() {

					@Override
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						ps.setString(1, orderDetails.get(i).getId());
						ps.setInt(2, orderDetails.get(i).getWorkType().getWorkTypeId());
						ps.setString(3, orderDetails.get(i).getOrder().getOrderId());
					}

					@Override
					public int getBatchSize() {
						return orderDetails.size();
					}
				});
		return new DaoResult(true);
	}

	@Override
	public DaoResult isOrderDetailExistDao(OrderDetail orderDetail) {
		kumaSqlDao.selectMode();
		IntegerSqlResult theResult = kumaSqlDao.select(SelectClause.COUNT).from(OrderDetail.class)
				.leftjoin(Orders.class, "o", "order_id", OrderDetail.class, "order_id")
				.leftjoin(WorkType.class, "wt", "work_type_id", OrderDetail.class, "work_type_id")
				.leftjoin(Work.class, "w", "work_id", WorkType.class, "work_id")
				.where("order_phone", "o", WhereType.EQUALS, orderDetail.getOrder().getOrderPhone())
				.where("work_id", "w", WhereType.EQUALS, orderDetail.getWorkType().getWork().getWorkId())
				.endForNumber();
		int count = theResult.getResult();
		if (count == 0)
			return new DaoResult(true, false);
		else
			return new DaoResult(true, true);
	}

	@Override
	public DaoResult getAllOrderDetailForXls() {
		kumaSqlDao.selectMode();
		ListSqlResult theResult = kumaSqlDao.select("order_name").select("province_name", "p").select("city_name", "c")
				.select("area_name", "a").select("order_address").select("order_remark").select("order_phone")
				.select("name", "wt").select("work_name", "w").from(Orders.class, "o")
				.leftjoin(Area.class, "a", "area_id", Orders.class, "order_area")
				.leftjoin(City.class, "c", "city_id", Area.class, "city_id")
				.leftjoin(Province.class, "p", "province_id", City.class, "province_id")
				.leftjoin(OrderDetail.class, "od", "order_id", Orders.class, "order_id")
				.leftjoin(WorkType.class, "wt", "work_type_id", OrderDetail.class, "work_type_id")
				.leftjoin(Work.class, "w", "work_id", WorkType.class, "work_id")
				.where("order_status", WhereType.EQUALS, 1).orderBy("order_phone").orderBy("order_email")
				.orderBy(
						"order_name")
				.endForList((rs, row) -> new OrderDetail.Builder()
						.order(new Orders.Builder().orderName(rs.getString("order_name"))
								.orderAddress(rs.getString("order_address")).orderRemark(rs.getString("order_remark"))
								.orderPhone(rs.getString("order_phone"))
								.orderArea(new Area.Builder().areaName(rs.getString("area_name"))
										.city(new City.Builder().cityName(rs.getString("city_name"))
												.province(new Province(null, rs.getString("province_name"))).build())
										.build())
								.build())
						.workType(new WorkType.Builder().name(rs.getString("wt.name"))
								.work(new Work.Builder().workName(rs.getString("w.work_name")).build()).build())
						.build());
		return new DaoResult(true, theResult.getResult());
	}

}
