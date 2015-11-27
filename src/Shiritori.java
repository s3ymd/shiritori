

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Shiritori {

	// キーボード入力用
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	// 候補の言葉のリスト
	private static List<String> candidateWords = new ArrayList<>();
	// 使われた言葉のリスト
	private static List<String> usedWords = new ArrayList<>();

	/**
	 * 起動ポイントです
	 * @param args 使用しません
	 * @throws IOException 入出力例外発生時に送出されます
	 */
	public static void main(String[] args) throws IOException {
		// メインループ
		while (true) {
			// しりとりを行う
			game();
		}
	}
	
	/**
	 * しりとりゲームをします。
	 * @throws IOException 入出力例外発生時に送出されます
	 */
	public static void game() throws IOException {

		System.out.println("しりとりをしましょう。しりとり");

		while (true) {
			// プレイヤーのターン
			String playerWord = br.readLine();
			
			// 「使われた言葉のリスト」に追加
			usedWords.add(playerWord);
			// 「知っているの言葉のリスト」から、その言葉を削除
			candidateWords.remove(playerWord);
			
			// COMのターン
			String end = playerWord.substring(playerWord.length() - 1);
			String comWord = find(candidateWords, end);

			if (comWord != null) { // 見つかった場合
				// その言葉を表示
				System.out.println(comWord);
				// 「使われた言葉のリスト」に追加
				usedWords.add(comWord);
				// 「候補の言葉のリスト」から、その言葉を削除
				candidateWords.remove(comWord);
			} else { // 見つからない場合
				// COMの負け
				System.out.println("降参です！");
				break;
			}
		}
		
		// 「候補の言葉のリスト」に、「使われた言葉のリスト」を追加
		candidateWords.addAll(usedWords);
		// 「使われた言葉のリスト」をクリア
		usedWords.clear();
	}

	/**
	 * 言葉のリストから、指定した文字で始まる言葉を1つ探す
	 * 
	 * @param list 言葉のリスト
	 * @param start 検索する言葉の最初の文字（列）
	 * @return 見つかった場合はその言葉、見つからない場合はnull
	 */
	public static String find(List<String> list, String start) {
		for (String w : list) {
			if (w.startsWith(start)) {
				return w;
			}
		}
		return null;
	}

}
