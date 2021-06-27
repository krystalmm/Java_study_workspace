package demo;

import java.util.ArrayList;

import entity.Member;
import service.MemberServiceImpl;

// 実行するクラス
public class Main {

	public static void main(String[] args) {
		
//		MemberServiceImplをserviceという名前で初期化
		MemberServiceImpl service = new MemberServiceImpl();
		
//		greetメソッドを出力
		System.out.println(service.greet(2));
		
//		getAllメソッドを出力
		System.out.println(service.getAll());
		
//		このままでは、[entity.Member@3d012ddd, entity.Member@6f2b958e]のように表示される（toStringメソッドがオーバーライドされていない）ので、値を表示させるようにする！
		ArrayList<Member> list = service.getAll();
//		拡張for文を使って値を表示させる！
		for (Member mem : list) {
			System.out.println(mem.getId() + "," + mem.getName() + "," + mem.getEmail());
		}
		
//		課題のsumOfメソッドを出力
		System.out.println(service.sumOf(3, 5));
	}
}
