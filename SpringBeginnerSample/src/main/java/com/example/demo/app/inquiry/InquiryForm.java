package com.example.demo.app.inquiry;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

// HTMLと値をやりとりするFormクラス
public class InquiryForm {
	
	// それぞれ@でvalidation追加
	@Size(min = 1, max = 20, message = "Please input 20characters or less.")
	private String name;
	
	@NotNull
	@Email(message = "Invalid Email Format.")
	private String email;
	
	@NotNull
	private String contents;
	
	// デフォルトのコンストラクタ（newしたときにjavaのデフォルト値がそれぞれのフィールドに設定される形になる！）
	public InquiryForm() {
	}
	
	// getterとsetterを用意！
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getContents() {
		return contents;
	}
	
	public void  setContents(String contents) {
		this.contents = contents;
	}
}
