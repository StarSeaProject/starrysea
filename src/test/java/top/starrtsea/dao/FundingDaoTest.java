package top.starrtsea.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import top.starrysea.StarrtseaApplication;
import top.starrysea.dao.IFundingDao;
import top.starrysea.object.dto.Activity;
import top.starrysea.object.dto.Funding;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StarrtseaApplication.class)
public class FundingDaoTest {

	@Autowired
	private IFundingDao fundingDao;

	@Test
	public void getAllFundingDao() {
		System.out.println(fundingDao.getAllFundingDao(
				new Funding.Builder().activity(new Activity.Builder().activityId(190).build()).build()).getResult(List.class));
	}

	@Test
	public void saveFundingDao() {
		Activity a = new Activity.Builder().activityId(190).build();
		List<Funding> list = new ArrayList<>();
		list.add(new Funding.Builder().activity(a).fundingName("aaa").fundingMoney(12.30).fundingMessage("aaaaaaa")
				.build());
		list.add(new Funding.Builder().activity(a).fundingName("bbb").fundingMoney(78.61).fundingMessage("bbbbbbb")
				.build());
		list.add(new Funding.Builder().activity(a).fundingName("ccc").fundingMoney(95.68).fundingMessage("ccccccc")
				.build());
		System.out.println(fundingDao.saveFundingDao(list).isSuccessed());
	}

	@Test
	public void deleteFundingDao() {
		System.out.println(fundingDao.deleteFundingDao(new Funding.Builder().fundingId(4).build()).isSuccessed());
	}
}
