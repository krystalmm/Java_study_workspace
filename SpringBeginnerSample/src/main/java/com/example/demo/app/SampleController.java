package com.example.demo.app;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/sample")
public class SampleController {
	
	// JdbcTemplateはjdbcでDBを操作するためのクラス！
	private final JdbcTemplate jdbcTemplate;
	
	// jdbcTemplateはnewしないで、Dependency Injection（インスタンス化されたオブジェクトを自動で渡してくれる機能）を使う！
	@Autowired
	public SampleController(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test(Model model) {
		
		// DB操作の処理
		String sql = "SELECT id, name, email"
				+ " FROM inquiry WHERE id = 1";
		// Stringはカラム名、Objectはカラムの値の型がstringやintと分かれてくるのでそうしている！
		// queryForMapメソッドでデータ取得（引数に上記のsql文を指定！）
		Map<String, Object> map = jdbcTemplate.queryForMap(sql);
		// 取得したデータをHTML上で表示するためmodelに追加！
		// map.get()で返ってくる型はObjectだが、Stringに変換される！
		model.addAttribute("name", map.get("name"));
		model.addAttribute("email", map.get("email"));
		
		model.addAttribute("title", "Inqurity Form");
		return "test";
	}
}
