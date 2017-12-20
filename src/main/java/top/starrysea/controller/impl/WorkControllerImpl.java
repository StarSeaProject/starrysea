package top.starrysea.controller.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import top.starrysea.common.Common;
import top.starrysea.common.ServiceResult;
import top.starrysea.controller.IWorkController;
import top.starrysea.object.dto.Work;
import top.starrysea.object.view.in.WorkForAdd;
import top.starrysea.object.view.in.WorkForAll;
import top.starrysea.object.view.in.WorkForOne;
import top.starrysea.service.IWorkService;

import static top.starrysea.common.Const.*;

@Controller
@RequestMapping(value = "/work")
public class WorkControllerImpl implements IWorkController {

	@Autowired
	private IWorkService workService;

	@Override
	@RequestMapping(value = "/ajax", method = RequestMethod.POST)
	@ResponseBody
	// 查询所有作品，此方法可用于作品管理，也可用于查看旧货
	public Map<String, Object> queryAllWorkControllerAjax(@RequestBody WorkForAll work) {
		ServiceResult serviceResult = workService.queryAllWorkService(work.getCondition(), work.toDTO());
		Map<String, Object> theResult = new HashMap<>();
		if (!serviceResult.isSuccessed()) {
			theResult.put(ERRINFO, serviceResult.getErrInfo());
			return theResult;
		}
		List<Work> result = serviceResult.getResult(List.class);
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
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ModelAndView queryWorkController(@Valid WorkForOne work, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return Common.handleVaildError(bindingResult);
		}
		ModelAndView modelAndView = new ModelAndView();
		ServiceResult serviceResult = workService.queryWorkService(work.toDTO());
		if (!serviceResult.isSuccessed()) {
			modelAndView.addObject(ERRINFO, serviceResult.getErrInfo());
			modelAndView.setViewName(ERROR_VIEW);
			return modelAndView;
		}
		Work w = serviceResult.getResult(Work.class);
		modelAndView.addObject("work", w.toVoForOne());
		modelAndView.addObject("workImages", serviceResult.getResult(List.class));
		modelAndView.setViewName("work_detail");
		return modelAndView;
	}

	@Override
	// 查询一个作品的详情页，此方法可用于作品管理，也可用于查看旧货
	@RequestMapping(value = "/detail/ajax", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryWorkControllerAjax(@RequestBody @Valid WorkForOne work,
			BindingResult bindingResult) {
		Map<String, Object> theResult = new HashMap<>();
		if (bindingResult.hasErrors()) {
			List<String> errInfo = bindingResult.getAllErrors().stream()
					.map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
			theResult.put(ERRINFO, errInfo);
			return theResult;
		}
		ServiceResult serviceResult = workService.queryWorkService(work.toDTO());
		if (!serviceResult.isSuccessed()) {
			theResult.put(ERRINFO, serviceResult.getErrInfo());
			return theResult;
		}
		Work w = serviceResult.getResult(Work.class);
		theResult.put("work", w.toVoForOne());
		theResult.put("workImages", serviceResult.getResult(List.class));
		return theResult;
	}

	// 添加一个作品
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addWorkController(@RequestParam("coverFile") MultipartFile coverFile,
			@RequestParam("imageFiles") MultipartFile[] imageFiles, @Valid WorkForAdd work,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return Common.handleVaildError(bindingResult);
		}
		ModelAndView modelAndView = new ModelAndView();
		ServiceResult serviceResult = workService.addWorkService(coverFile, imageFiles, work.toDTO());
		if (!serviceResult.isSuccessed()) {
			modelAndView.addObject(ERRINFO, serviceResult.getErrInfo());
			modelAndView.setViewName(ERROR_VIEW);
			return modelAndView;
		}
		modelAndView.addObject("info", "添加成功!");
		modelAndView.setViewName(SUCCESS_VIEW);
		return modelAndView;
	}

	@Override
	// 删除一个作品
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public ModelAndView removeWorkController(@Valid WorkForOne work, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return Common.handleVaildError(bindingResult);
		}
		ModelAndView modelAndView = new ModelAndView();
		ServiceResult serviceResult = workService.removeWorkService(work.toDTO());
		if (!serviceResult.isSuccessed()) {
			modelAndView.addObject(ERRINFO, serviceResult.getErrInfo());
			modelAndView.setViewName(ERROR_VIEW);
			return modelAndView;
		}
		modelAndView.addObject("info", "删除成功！");
		modelAndView.setViewName(SUCCESS_VIEW);
		return modelAndView;
	}

}
