package top.starrysea.controller.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import top.starrysea.common.Condition;
import top.starrysea.common.ServiceResult;
import top.starrysea.controller.IWorkController;
import top.starrysea.object.dto.Work;
import top.starrysea.object.view.in.WorkForAdd;
import top.starrysea.object.view.in.WorkForAll;
import top.starrysea.object.view.in.WorkForOne;
import top.starrysea.object.view.in.WorkTypeForModify;
import top.starrysea.object.view.in.WorkTypeForRemove;
import top.starrysea.service.IWorkService;

import static top.starrysea.common.Const.*;
import static top.starrysea.common.ResultKey.*;

@Controller
public class WorkControllerImpl implements IWorkController {

	@Autowired
	private IWorkService workService;

	@Override
	@RequestMapping(value = "/work", method = RequestMethod.GET)
	// 查询所有作品，此方法可用于作品管理，也可用于查看旧货
	public ModelAndView queryAllWorkController(Condition condition, WorkForAll work, Device device) {
		ModelAndView modelAndView = new ModelAndView();
		ServiceResult serviceResult = workService.queryAllWorkService(condition, work.toDTO());
		List<Work> result = serviceResult.getResult(WOKR_LIST);
		List<top.starrysea.object.view.out.WorkForAll> voResult = result.stream().map(Work::toVoForAll)
				.collect(Collectors.toList());
		modelAndView.addObject("result", voResult);
		modelAndView.addObject("nowPage", serviceResult.getNowPage());
		modelAndView.addObject("totalPage", serviceResult.getTotalPage());
		modelAndView.setViewName(device.isMobile() ? MOBILE + "work" : "work");
		return modelAndView;
	}

	@Override
	@RequestMapping(value = "/work/ajax", method = RequestMethod.POST)
	@ResponseBody
	// 查询所有作品，此方法可用于作品管理，也可用于查看旧货
	public Map<String, Object> queryAllWorkControllerAjax(@RequestBody WorkForAll work) {
		ServiceResult serviceResult = workService.queryAllWorkService(work.getCondition(), work.toDTO());
		Map<String, Object> theResult = new HashMap<>();
		List<Work> result = serviceResult.getResult(WOKR_LIST);
		List<top.starrysea.object.view.out.WorkForAll> voResult = result.stream().map(Work::toVoForAll)
				.collect(Collectors.toList());
		theResult.put("workName", work.getWorkName());
		theResult.put("result", voResult);
		theResult.put("nowPage", serviceResult.getNowPage());
		theResult.put("totalPage", serviceResult.getTotalPage());
		return theResult;
	}

	@Override
	// 查询一个作品的详情页，此方法可用于作品管理，也可用于查看旧货
	@RequestMapping(value = "/work/{workId}", method = RequestMethod.GET)
	public ModelAndView queryWorkController(@Valid WorkForOne work, BindingResult bindingResult, Device device) {
		ModelAndView modelAndView = new ModelAndView();
		ServiceResult serviceResult = workService.queryWorkService(work.toDTO());
		Work w = serviceResult.getResult(WORK_DETAIL);
		modelAndView.addObject("work", w.toVoForOne());
		modelAndView.addObject("workId", work.getWorkId());
		modelAndView.addObject("workImages", serviceResult.getResult(WORK_DETAIL_IMAGE));
		modelAndView.addObject("workTypes", serviceResult.getResult(WORK_DETAIL_TYPE));
		modelAndView.setViewName(device.isMobile() ? MOBILE + "work_detail" : "work_detail");
		return modelAndView;
	}

	@Override
	// 查询一个作品的详情页，此方法可用于作品管理，也可用于查看旧货
	@RequestMapping(value = "/work/detail/ajax", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryWorkControllerAjax(@RequestBody @Valid WorkForOne work,
			BindingResult bindingResult) {
		Map<String, Object> theResult = new HashMap<>();
		ServiceResult serviceResult = workService.queryWorkService(work.toDTO());
		Work w = serviceResult.getResult(WORK_DETAIL);
		theResult.put("work", w.toVoForOne());
		theResult.put("workId", work.getWorkId());
		theResult.put("workImages", serviceResult.getResult(WORK_DETAIL_IMAGE));
		theResult.put("workTypes", serviceResult.getResult(WORK_DETAIL_TYPE));
		return theResult;
	}

	// 添加一个作品
	@RequestMapping(value = "/work/add", method = RequestMethod.POST)
	public ModelAndView addWorkController(@RequestParam("coverFile") MultipartFile coverFile,
			@RequestParam("imageFiles") MultipartFile[] imageFiles, @Valid WorkForAdd work, BindingResult bindingResult,
			Device device) {
		ModelAndView modelAndView = new ModelAndView();
		workService.addWorkService(coverFile, imageFiles, work.toDTO(), work.toDTOWorkType());
		modelAndView.addObject(INFO, "添加成功!");
		modelAndView.setViewName(device.isMobile() ? MOBILE + SUCCESS_VIEW : SUCCESS_VIEW);
		return modelAndView;
	}

	@Override
	// 删除一个作品
	@RequestMapping(value = "/work/remove", method = RequestMethod.POST)
	public ModelAndView removeWorkController(@Valid WorkForOne work, BindingResult bindingResult, Device device) {
		ModelAndView modelAndView = new ModelAndView();
		workService.removeWorkService(work.toDTO());
		modelAndView.addObject(INFO, "删除成功！");
		modelAndView.setViewName(device.isMobile() ? MOBILE + SUCCESS_VIEW : SUCCESS_VIEW);
		return modelAndView;
	}

	@Override
	@RequestMapping(value = "/worktype/remove", method = RequestMethod.POST)
	public ModelAndView removeWorkTypeController(WorkTypeForRemove workType, BindingResult bindingResult,
			Device device) {
		ModelAndView modelAndView = new ModelAndView();
		workService.removeWorkTypeService(workType.toDTO());
		modelAndView.addObject(INFO, "删除作品类型成功！");
		modelAndView.setViewName(device.isMobile() ? MOBILE + SUCCESS_VIEW : SUCCESS_VIEW);
		return modelAndView;
	}

	@Override
	@RequestMapping(value = "/worktype/modifystock", method = RequestMethod.POST)
	public ModelAndView modifyWorkTypeController(@Valid WorkTypeForModify workType, BindingResult bindingResult,
			Device device) {
		ModelAndView modelAndView = new ModelAndView();
		workService.modifyWorkTypeService(workType.toDTO());
		modelAndView.addObject(INFO, "修改库存成功！");
		modelAndView.setViewName(device.isMobile() ? MOBILE + SUCCESS_VIEW : SUCCESS_VIEW);
		return modelAndView;
	}

}
