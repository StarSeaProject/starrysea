package top.starrysea.dao;

import java.util.List;

import top.starrysea.common.DaoResult;
import top.starrysea.object.dto.Funding;

public interface IFundingDao {

	DaoResult getAllFundingDao(Funding funding);
	
	DaoResult saveFundingDao(List<Funding> fundings);
	
	DaoResult deleteFundingDao(Funding funding);
}
