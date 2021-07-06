package com.example.demo.service;

/**
 * update後に対して反応するのが0件だった場合にこの例外をスローする！
 */
public class InquiryNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	// 中身はデフォルトコンストラクタだけにして、親クラスを呼び出す！
	public InquiryNotFoundException(String message) {
		super(message);
	}
}
