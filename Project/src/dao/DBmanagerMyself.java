package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**「DBManager」DB接続への接続のコネクションを作成するメソッドを持ったクラス。
　　　　　　　　 Daoと同じパッケージに所属させて、コネクションを生成する際にメソッドを呼び出す。
　　　　　　　　　接続するDBや、パスワード等が変わった場合はこのクラスを編集する。
**/

public class DBmanagerMyself {
	private static String url = "jdbc:mysql://localhost/";
	private static String dbName = "usermanagement";
	private static String user = "root";
	private static String pass ="password";
	
	/**DBへ接続するコネクションを返す**/
	
	/**Connection型のgetConnectionメソッド
	 ⓵コネクション型の変数conをnullで初期化
	 ↓tryの内容
	 ⓶ClassのforNameメソッドでcom.mysql.jdbc.Driverクラスオブジェクトを取得
	 ⓷変数conにDriverManagerのgetConnectionメソッドでurl, user, passを取得
	 ↓catchの内容
	 クラスが見つからなかった場合スタックトレースを出力
	 ④tryの場合はConnection型のgetConnectionメソッドを呼び出した結果として
	 url, user, passを取得した変数conを返す**/
	
	
	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		/**Connection→スライド6-5参照**/
		/**データベースへ接続**/
			con = DriverManager.getConnection(url, user, pass);
		}catch (SQLException  | ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return con;
	}
	
}
