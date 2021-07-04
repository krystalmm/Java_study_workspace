package com.example.demo.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Inquiry;

// @RepositoryでDBを操作するクラスであると宣言
@Repository
public class InquiryDaoImpl implements InquiryDao {

	// DB操作用のクラスJdbcTemplateをprivateで定数で定義！
	private final JdbcTemplate jdbcTemplate;
	
	// @Autowiredでnewせずに上記のjdbcTemplateを使う！
	@Autowired
	public InquiryDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public void insertInquiry(Inquiry inquiry) {
		jdbcTemplate.update("INSERT INTO inquiry(name, email, contents, created) VALUES (?, ?, ?, ?)",
				inquiry.getName(), inquiry.getEmail(), inquiry.getContents(), inquiry.getCreated());
	}

	@Override
	public List<Inquiry> getAll() {
		String sql = "SELECT id, name, email, contents, created FROM inquiry";
		// ListのなかにMap！
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql);
		// とってきたデータを入れるList（Inquiry型）
		List<Inquiry> list = new ArrayList<Inquiry>();
		// データはfor文で繰り返して取り出す
		for (Map<String, Object> result : resultList) {
			// Inquiryをインスタンス化してMapの中身を詰めていく！
			Inquiry inquiry = new Inquiry();
			inquiry.setId((int) result.get("id"));
			inquiry.setName((String) result.get("name"));
			inquiry.setEmail((String) result.get("email"));
			inquiry.setContents((String) result.get("contents"));
			// createdはDBから抜き出したタイミングでTimestamp型になる！inquiryの中ではLocalDateTimeになるので、toLocalDateTime()で変換する！
			inquiry.setCreated(((Timestamp) result.get("created")).toLocalDateTime());
			// listのなかに上記inquiryを詰める！
			list.add(inquiry);
		}
		// データを詰めた後のlistをreturnする！
		return list;
	}

}
