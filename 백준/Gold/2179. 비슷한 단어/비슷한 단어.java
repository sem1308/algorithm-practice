import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static class Word {
		Character alphabet; // 현재 알파벳
		Word[] childWords; // 연결된 알파벳
		int index; // 처음 등록된 단어 index

		public static Word newRoot(){
			return new Word(null, -1);
		}

		public Word(Character alphabet, int index){
			this.alphabet = alphabet;
			this.index = index;
			this.childWords = new Word[27];
		}

		public static int toIndex(char alphabet) {
			return alphabet - 'a';
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		/*
		 * N개의 영단어 => 가장 비슷한 두 단어 구하기
		 * 접두사 길이로 측정
		 *
		 * 단어 길이 <= 100
		 *
		 * 1. 트라이 구성
		 * 2. 가장 긴 부분문자열 찾기
		 * 
		 */

		int N = Integer.parseInt(br.readLine());
		Word root = Word.newRoot();

		String[] words = new String[N];

		int sIdx = N;
		int tIdx = -1;
		int maxLength = -1;

		for (int i = 0; i < N; i++) {
			String word = br.readLine();
			words[i] = word;
			char[] alphabets = word.toCharArray();

			Word cur = root;
			int overlapWordIndex = -1; // 부분 문자열 단어 index
			int overlapLength = 0; // 부분 문자열 길이
			for (char alphabet : alphabets){
				int idx = Word.toIndex(alphabet);
				if(cur.childWords[idx] == null){
					// 입력되지 않은 단어
					cur.childWords[idx] = new Word(alphabet,i);
				}else{
					// 이미 입력된 단어
					overlapLength++;
					overlapWordIndex = cur.childWords[idx].index;
				}
				cur = cur.childWords[idx];
			}

			if((overlapLength > maxLength) || (overlapLength == maxLength && overlapWordIndex < sIdx)){
				sIdx = overlapWordIndex;
				tIdx = i;
				maxLength = overlapLength;
			}
		}
		System.out.println(words[sIdx]);
		System.out.println(words[tIdx]);

	}
}
