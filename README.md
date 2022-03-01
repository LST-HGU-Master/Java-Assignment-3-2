# 課題 3-2
Switch文

### 課題の説明
キーボードから繰り返し整数を受けとり、数値に応じて下記を表示するプログラムを作成しなさい。
ただし、繰り返しは3回で終了させること。

|数値| 表示        |
|---|-----------|
|1| グー        |
|2| チョキ       |
|3| パー        |
|それ以外| 不適切な入力です  |

### プログラムの雛形
```java
public class Prog32 {

	public static void main(String[] args) {
		int input;
		java.util.Scanner sc = new java.util.Scanner(System.in);
		// ここからプログラムを追加する

		System.out.println("プログラムを終了します");
	}

}
```

### 実行例
```
数値を入力してください: 1
グー
数値を入力してください: 4
不適切な入力です
数値を入力してください: 2
パー
プログラムを終了します
```