package com.example.demo.app.inquiry;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Inquiry;
import com.example.demo.service.InquiryNotFoundException;
import com.example.demo.service.InquiryService;

@Controller
@RequestMapping("/inquiry")
public class InquiryController {
	
	// Serviceクラスを読み込む
	private final InquiryService inquiryService;
	@Autowired
	public InquiryController(InquiryService inquiryService) {
		this.inquiryService = inquiryService;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model) {
		// 一覧に表示するリストをinquiryServiceのgetAllメソッドでとってくる!
		List<Inquiry> list = inquiryService.getAll();
		
		// 例外処理の確認のための記述
//		Inquiry inquiry = new Inquiry();
//		inquiry.setId(4);  // idが4のinquiryはない！！
//		inquiry.setName("Jamie");
//		inquiry.setEmail("sample4@example.com");
//		inquiry.setContents("Hello.");
//		
//		inquiryService.update(inquiry);
		
//		メソッドの呼び出しに対して個別の処理を組む場合に使われる、try-catch文を使う！
//		try {
//			inquiryService.update(inquiry);
//		} catch (InquiryNotFoundException e) {
//			model.addAttribute("message", e);
//			return "error/CustomPage";
//		}
		
		// listをinquiryListとしてViewで使えるようにする！
		model.addAttribute("inquiryList", list);
		model.addAttribute("title", "Inquiry Index");
		
		return "inquiry/index_boot";
	}
	
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	// フラッシュスコープを受け取るため@ModelAttributeを引数に指定！
	public String form(InquiryForm inquiryForm, Model model, @ModelAttribute("complete") String complete) {
		model.addAttribute("title", "Inquiry Form");
		return "inquiry/form_boot";
	}
	
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String formGoBack(InquiryForm inquiryForm, Model model) {
		model.addAttribute("title", "Inquiry Form");
		return "inquiry/form_boot";
	}
	
	@RequestMapping(value = "/confirm", method = RequestMethod.POST)
	public String confirm(@Validated InquiryForm inquiryForm, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("title", "Inquiry Form");
			return "inquiry/form_boot";
		}
		
		model.addAttribute("title", "Confirm Page");
		return "inquiry/confirm_boot";
	}
	
	// 確認フォームの内容は裏からいじることもできるので、validationを入れておく！
	// RedirectAttributesはフラッシュスコープ（セッションを用いてできている）を使うためのもの！リダイレクトするとmodel.addAttributeが使えないので、これを使う！
	@RequestMapping(value = "/complete", method = RequestMethod.POST)
	public String complete(@Validated InquiryForm inquiryForm, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		if(result.hasErrors()) {
			model.addAttribute("title", "InquiryForm");
			return "inquiry/form_boot";
		}
		
		// Inquiry（Entity）クラスにデータを詰め替える必要があるため初期化！
		Inquiry inquiry = new Inquiry();
		inquiry.setName(inquiryForm.getName());
		inquiry.setEmail(inquiryForm.getEmail());
		inquiry.setContents(inquiryForm.getContents());
		// createdはLocalDateTime.nowを使って自動生成する
		inquiry.setCreated(LocalDateTime.now());
		// serviceのsaveメソッドを使って、フォームに入力された値をDBへ登録！
		inquiryService.save(inquiry);
		
		// フラッシュスコープを使って一時的なメッセージを表示させる！
		redirectAttributes.addFlashAttribute("complete", "Registered!");
		return "redirect:/inquiry/form";
	}
	
	// 今のメソッドが例外をスローしたときにそれを補足するようなメソッドを用意！（InquiryNotFoundExceptionがスローされたらこのメソッド内の処理が実行される！）
	// このメソッドをそのままControllerAdviceの方に書くと、全てのコントローラに対応する！！
	@ExceptionHandler(InquiryNotFoundException.class)
	public String handleException(InquiryNotFoundException e, Model model) {
		model.addAttribute("message", e);
		return "error/CustomPage";
	}
}
