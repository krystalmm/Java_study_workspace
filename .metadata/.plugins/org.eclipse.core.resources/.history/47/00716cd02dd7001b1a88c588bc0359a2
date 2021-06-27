package service;

import java.util.ArrayList;

import entity.Member;

public class MemberServiceImpl implements MemberService {
	
//	オーバーライドとはクラスを継承する時に、スーパークラスのメソッドをサブクラスにおいて同じメソッド名で定義し直すこと！アノテーションをつけておくと、わかりやすくなる！
	@Override
	public String greet(int i) {
//		挨拶文の配列を作っておく！（iの範囲は0~2になる！）
		String[] greetings = new String[] {"Good morning", "Hello", "Good evening"};
		return greetings[i];
	}
	
	@Override
	public ArrayList<Member> getAll() {
//		Listの初期化
		ArrayList<Member> list = new ArrayList<>();
		
//		memberをインスタンス化し、listのなかに入れる！
		Member men1 = new Member(1, "Linda", "linda@example.com");
		Member men2 = new Member(2, "James", "james@example.com");
		list.add(men1);
		list.add(men2);
		
//		memberがlistに入ったので、それをreturnする！
		return list;
	}
	
}
