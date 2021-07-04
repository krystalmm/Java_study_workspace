package com.example.demo.app.inquiry;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/inquiry")
public class InquiryController {
	
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	// フラッシュスコープを受け取るため@ModelAttributeを引数に指定！
	public String form(InquiryForm inquiryForm, Model model, @ModelAttribute("complete") String complete) {
		model.addAttribute("title", "Inquiry Form");
		return "inquiry/form";
	}
	
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String formGoBack(InquiryForm inquiryForm, Model model) {
		model.addAttribute("title", "Inquiry Form");
		return "inquiry/form";
	}
	
	@RequestMapping(value = "/confirm", method = RequestMethod.POST)
	public String confirm(@Validated InquiryForm inquiryForm, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("title", "Inquiry Form");
			return "inquiry/form";
		}
		
		model.addAttribute("title", "Confirm Page");
		return "inquiry/confirm";
	}
	
	// 確認フォームの内容は裏からいじることもできるので、validationを入れておく！
	// RedirectAttributesはフラッシュスコープ（セッションを用いてできている）を使うためのもの！リダイレクトするとmodel.addAttributeが使えないので、これを使う！
	@RequestMapping(value = "/complete", method = RequestMethod.POST)
	public String complete(@Validated InquiryForm inquiryForm, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		if(result.hasErrors()) {
			model.addAttribute("title", "InquiryForm");
			return "inquiry/form";
		}
		// フラッシュスコープを使って一時的なメッセージを表示させる！
		redirectAttributes.addFlashAttribute("complete", "Registered!");
		return "redirect:/inquiry/form";
	}
}
