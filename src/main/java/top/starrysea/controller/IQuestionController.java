package top.starrysea.controller;

import java.util.Map;

import org.springframework.mobile.device.Device;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import top.starrysea.common.Condition;
import top.starrysea.object.view.in.QuestionForAll;
import top.starrysea.object.view.in.QuestionForAnswer;
import top.starrysea.object.view.in.QuestionForAsk;

public interface IQuestionController {

	ModelAndView queryQuestionController(Condition condition, QuestionForAll question, Device device);

	Map<String, Object> queryQuestionControllerAjax(QuestionForAll question);

	ModelAndView askQuestionController(QuestionForAsk question, BindingResult bindingResult, Device device);

	Map<String, Object> answerQuestionController(QuestionForAnswer question, BindingResult bindingResult);

}
