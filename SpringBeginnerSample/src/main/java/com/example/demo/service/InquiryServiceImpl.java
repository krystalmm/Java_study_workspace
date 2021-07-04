package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.InquiryDao;
import com.example.demo.entity.Inquiry;

@Service
public class InquiryServiceImpl implements InquiryService {
	
	// Daoクラスを読み込む！
	private final InquiryDao dao;
	// 上記Daoのインスタンスを使えるようにする！
	@Autowired
	InquiryServiceImpl(InquiryDao dao) {
		this.dao = dao;
	}
	
	@Override
	public void save(Inquiry inquiry) {
		// InquiryDaoImplで実装したinsertInquiryメソッドを使用！
		dao.insertInquiry(inquiry);
	}
	
	@Override
	public List<Inquiry> getAll() {
		// InquiryDaoImplで実装したgetAllメソッドを返す！
		return dao.getAll();
	}
}
