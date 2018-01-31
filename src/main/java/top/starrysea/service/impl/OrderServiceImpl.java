package top.starrysea.service.impl;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import top.starrysea.common.Common;
import top.starrysea.common.Condition;
import top.starrysea.common.DaoResult;
import top.starrysea.common.ServiceResult;
import top.starrysea.dao.IProvinceDao;
import top.starrysea.dao.IOrderDao;
import top.starrysea.dao.IWorkDao;
import top.starrysea.dao.IWorkTypeDao;
import top.starrysea.exception.EmptyResultException;
import top.starrysea.exception.LogicException;
import top.starrysea.exception.UpdateException;
import top.starrysea.object.dto.Area;
import top.starrysea.object.dto.City;
import top.starrysea.object.dto.Orders;
import top.starrysea.object.dto.Province;
import top.starrysea.object.dto.Work;
import top.starrysea.object.dto.WorkType;
import top.starrysea.object.view.out.AreaForAddOrder;
import top.starrysea.object.view.out.CityForAddOrder;
import top.starrysea.object.view.out.ProvinceForAddOrder;
import top.starrysea.service.IOrderService;

import static top.starrysea.dao.impl.OrderDaoImpl.PAGE_LIMIT;
import static top.starrysea.common.ResultKey.*;

@Service("orderService")
public class OrderServiceImpl implements IOrderService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private IOrderDao orderDao;
	@Autowired
	private IWorkDao workDao;
	@Autowired
	private IWorkTypeDao workTypeDao;
	@Autowired
	private IProvinceDao provinceDao;

	@Override
	public ServiceResult queryAllOrderService(Condition condition, Orders order) {
		ServiceResult result = new ServiceResult();
		DaoResult daoResult = orderDao.getAllOrderDao(condition, order);
		@SuppressWarnings("unchecked")
		List<Orders> ordersList = daoResult.getResult(List.class);
		int totalPage = 0;
		daoResult = orderDao.getOrderCountDao(condition, order);
		int count = daoResult.getResult(Integer.class);
		if (count % PAGE_LIMIT == 0) {
			totalPage = count / PAGE_LIMIT;
		} else {
			totalPage = (count / PAGE_LIMIT) + 1;
		}
		result.setSuccessed(true);
		result.setResult(ORDER_LIST, ordersList);
		result.setNowPage(condition.getPage());
		result.setTotalPage(totalPage);
		return result;
	}

	@Override
	// 根据订单号查询一个订单的具体信息以及发货情况
	public ServiceResult queryOrderService(Orders order) {
		ServiceResult result = new ServiceResult();
		DaoResult daoResult = orderDao.getOrderDao(order);
		Orders o = daoResult.getResult(Orders.class);
		result.setSuccessed(true);
		result.setResult(ORDER_DETAIL, o);
		return result;
	}

	@Override
	// 用户对一个作品进行下单，同时减少该作品的库存
	@Transactional
	public ServiceResult addOrderService(Orders order) {
		try {
			if (orderDao.isOrderExistDao(order).getResult(Boolean.class)) {
				throw new LogicException("您已经购买过这个应援物,不能重复购买");
			}
			WorkType workType = order.getWorkType();
			workType.setStock(1);
			DaoResult daoResult = workTypeDao.getWorkTypeStockDao(workType);
			int stock = daoResult.getResult(Integer.class);
			if (stock == 0) {
				throw new EmptyResultException("该作品已售空");
			} else if (stock - workType.getStock() < 0) {
				throw new LogicException("作品库存不足");
			}
			workTypeDao.reduceWorkTypeStockDao(workType);
			order.setOrderId(Common.getCharId("O-", 10));
			orderDao.saveOrderDao(order);
			WorkType wt = workTypeDao.getWorkTypeNameDao(workType).getResult(WorkType.class);
			wt.setWork(workDao.getWorkDao(order.getWorkType().getWork()).getResult(Work.class));
			order.setWorkType(wt);
			ServiceResult serviceResult = new ServiceResult(true);
			serviceResult.setResult(ORDER_DETAIL, order);
			return serviceResult;
		} catch (EmptyResultException | LogicException e) {
			ServiceResult serviceResult = new ServiceResult(false);
			serviceResult.setErrInfo(e.getMessage());
			return serviceResult;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new UpdateException(e);
		}

	}

	@Override
	// 修改一个订单的状态
	public ServiceResult modifyOrderService(Orders order) {
		order.setOrderStatus((short) 2);
		orderDao.updateOrderDao(order);
		ServiceResult sr = new ServiceResult(true);
		sr.setResult(ORDER_DETAIL, orderDao.getOrderDao(order).getResult(Orders.class));
		return sr;
	}

	@Override
	// 删除一个订单
	@Transactional
	public ServiceResult removeOrderService(Orders order) {
		workTypeDao.updateWorkTypeStockDao(order);
		orderDao.deleteOrderDao(order);
		return new ServiceResult(true);
	}

	@Override
	@Cacheable(value = "provinces")
	public ServiceResult queryAllProvinceService() {
		List<Area> areas = provinceDao.getAllProvinceDao().getResult(List.class);
		Map<Integer, ProvinceForAddOrder> provinceVos = new HashMap<>();
		for (Area area : areas) {
			int provinceId = area.getCity().getProvince().getProvinceId();
			if (!provinceVos.containsKey(provinceId)) {
				ProvinceForAddOrder province = new ProvinceForAddOrder(provinceId,
						area.getCity().getProvince().getProvinceName());
				province.setCitys(new HashMap<>());
				provinceVos.put(provinceId, province);
			}
			ProvinceForAddOrder provinceVo = provinceVos.get(provinceId);
			int cityId = area.getCity().getCityId();
			Map<Integer, CityForAddOrder> cityVos = provinceVo.getCitys();
			if (!cityVos.containsKey(cityId)) {
				CityForAddOrder city = new CityForAddOrder(cityId, area.getCity().getCityName());
				city.setAreas(new ArrayList<>());
				cityVos.put(cityId, city);
			}
			CityForAddOrder cityVo = cityVos.get(cityId);
			cityVo.getAreas().add(new AreaForAddOrder(area.getAreaId(), area.getAreaName()));
		}
		ServiceResult sr = new ServiceResult(true);
		sr.setResult(ORDER_ADDRESS, provinceVos);
		return sr;
	}

	@Override
	public ServiceResult queryWorkTypeStock(WorkType workType) {
		DaoResult daoResult;
		try {
			workDao.getWorkDao(workType.getWork());
			daoResult = workTypeDao.getWorkTypeStockDao(workType);
			ServiceResult sr = new ServiceResult(true);
			Integer stock = daoResult.getResult(Integer.class);
			if (stock <= 0)
				throw new LogicException("库存不足");
			sr.setResult(WORK_TYPE_STOCK, stock);
			return sr;
		} catch (Exception e) {
			ServiceResult sr = new ServiceResult(false);
			sr.setErrInfo(e.getMessage());
			return sr;
		}
	}

	@Override
	public ServiceResult exportOrderToXlsService() {
		List<Orders> result = orderDao.getAllOrderForXls().getResult(List.class);
		HSSFWorkbook excel = new HSSFWorkbook();
		HSSFSheet sheet = excel.createSheet("发货名单");
		HSSFRow row = sheet.createRow(0);
		row.createCell(0).setCellValue("收货人姓名");
		row.createCell(1).setCellValue("作品名称");
		row.createCell(2).setCellValue("作品类型");
		row.createCell(3).setCellValue("收货地址");
		row.createCell(4).setCellValue("收货人手机");
		row.createCell(5).setCellValue("备注");
		for (int i = 0; i < result.size(); i++) {
			Orders order = result.get(i);
			HSSFRow dataRow = sheet.createRow(i + 1);
			dataRow.createCell(0).setCellValue(order.getOrderName());
			dataRow.createCell(1).setCellValue(order.getWorkType().getWork().getWorkName());
			dataRow.createCell(2).setCellValue(order.getWorkType().getName());
			dataRow.createCell(3)
					.setCellValue(order.getOrderArea().getCity().getProvince().getProvinceName()
							+ order.getOrderArea().getCity().getCityName() + order.getOrderArea().getAreaName()
							+ order.getOrderAddress());
			dataRow.createCell(4).setCellValue(order.getOrderPhone());
			dataRow.createCell(5).setCellValue(order.getOrderRemark());
		}
		try (FileOutputStream fout = new FileOutputStream("/result.xls")) {
			excel.write(fout);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

}
