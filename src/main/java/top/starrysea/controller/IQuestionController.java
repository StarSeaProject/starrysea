package top.starrysea.controller;

import java.util.Map;

import org.springframework.validation.BindingResult;

import top.starrysea.object.view.in.QuestionForAnswer;
import top.starrysea.object.view.in.QuestionForAsk;

public interface IQuestionController {

	Map<String, Object> askQuestionController(QuestionForAsk question, BindingResult bindingResult);

	Map<String, Object> answerQuestionController(QuestionForAnswer question, BindingResult bindingResult);
}
