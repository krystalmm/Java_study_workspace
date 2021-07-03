package com.example.demo.app.inquiry;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/inquiry")
public class InquiryController {
	
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String form(InquiryForm inquiryForm, Model model) {
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
}
