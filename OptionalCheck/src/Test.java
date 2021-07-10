import java.util.Optional;

public class Test {

	public static void main(String[] args) {
		// Optional型について学ぶ！
		
		// よくない例
		String str = null;
		// 下の場合、コンパイルエラーにはならず、実行時にエラーになる！
//		System.out.println(str.length());
		
		// Optionalを使う！
		// Optional.ofNullable()で中身がnullかもしれないと合図する！
		Optional<String> strOpt = Optional.ofNullable(str);
		
		if (strOpt.isPresent()) {
			// もしstrOptが存在したらmessageというString型の変数を定義し、そのなかにstrOptの中身を代入！
			String message = strOpt.get();
			System.out.println(message.length());
		}
		// 上記のように条件分岐してあげることで、中身がnullかどうか判断し、実行エラーが起こらない！
	
		// 上記記述を短くしたい場合、以下のようにする！（ラムダ式）
		strOpt.ifPresent(v -> System.out.println(v.length()));
	}

}
