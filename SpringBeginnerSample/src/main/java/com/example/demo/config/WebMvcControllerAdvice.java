package com.example.demo.config;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;


/**
 * 全てのControllerで共通処理を定義
 */
@ControllerAdvice
public class WebMvcControllerAdvice {

	/*
	 * This method changes empty character to null
	 * こちらのメソッドを用意しておくと送信された空文字はnullに変換されます
	 */
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
   
    // jdbcTemplateのqueryForMapで一つも値が取得できなかったときに発生する例外（EmptyResultDataAccessException）に対する例外処理を実装
    @ExceptionHandler(EmptyResultDataAccessException.class)
    // 引数のEmptyResultAccessExceptionは、エラーのメッセージを受け取るために使う！Modelも引数に指定できる！
    public String handleException(EmptyResultDataAccessException e, Model model) {
    	model.addAttribute("message", e);
    	return "error/CustomPage";
    }
}