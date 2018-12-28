package dao;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import model.UserMyself;

public class UserDaoMyself {

	/*ログイン用*/
	/**主キーのidで検索ができるようにfindByメソッドを使う**/
    public UserMyself findByLoginInfo(String loginId, String password){
		Connection conn = null;

		try {
		/**データベースへ接続**/
			conn = DBmanagerMyself.getConnection();

		/**SELECT文を準備**/
		/**PreparedStatementで入力したものをSELECTを実行してrsにexecuteQueryメソッドでセット**/
		String sql = "SELECT * FROM user WHERE  login_id = ? and password = ?";

		String passwordCode = encryption(password);
		PreparedStatement pStmt = conn.prepareStatement(sql);
		pStmt.setString(1, loginId);
		pStmt.setString(2, passwordCode);
		ResultSet rs = pStmt.executeQuery();

		/**SQLでlogin_idはUNIQUEなのでrs.nextは1行でいい
		 →だからwhileを回す必要がないのでifでnullを返せばOK**/
		if (!rs.next()) {
            return null;
        }

		/**rsにセットしたlogin_idとnameをUserMyselfのコンストラクタの引数にセットする
		 そのために新しい変数を用意する**/
        String loginIdData = rs.getString("login_id");
        String nameData = rs.getString("name");
        return new UserMyself(loginIdData, nameData);


		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}finally {
			/**データベース切断する**/
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return null;
                }
            }
		}
    }

    /*全部用*/
		/**全部のユーザデータを取得する→スライド6-11**/

    public List<UserMyself> findall(){
    	Connection conn = null;
    	List<UserMyself> userMyselfList = new ArrayList<UserMyself>();

    	try {
    		/**Connection**/
    		/**データベースに接続**/
    		conn = DBmanagerMyself.getConnection();

    		/**SELECT文を準備**/
    		/**これuserテーブルを全部**/
    		String sql = "SELECT * FROM user WHERE id != 1";

    		/**SELECTを実行して、結果の表を取得する**/
    		/**取得してrsにexecuteQueryメソッドでセット**/
    		Statement stmt = conn.createStatement();
    		ResultSet rs = stmt.executeQuery(sql);

    		/**結果表に格納されたレコードの内容をwhileを回してそれぞれ変数に入れていく**/
    		/**getの中身はまだSQL語なのでテーブルのカラム名通りに入力していく**/
    		while(rs.next()){
    			int id = rs.getInt("id");
    			String loginId = rs.getString("login_Id");
    			String name = rs.getString("name");
    			Date birthDate = rs.getDate("birth_Date");
    			String password = rs.getString("password");
    			String createDate = rs.getString("create_Date");
    			String updateDate = rs.getString("update_Date");
    			/**UserMyselfのインスタンスを生成して設定してコンストラクタに引数を全部渡す
    			 上で作ったArrayListインスタンス(userMyselfList)に追加**/
    			UserMyself userMyself = new UserMyself(id,loginId,name,birthDate,password,createDate,updateDate);
    			userMyselfList.add(userMyself);
    		}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}finally{
			/**データベースを切断する**/
			/**finallyは例外をキャッチした場合もしてない場合も必ず実行する**/
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO: handle exception
					e.printStackTrace();
					return null;
				}
			}
		}

		return userMyselfList;

    }

    public List<UserMyself> Retrieval(String loginIdRetrieval, String userNameRetrieval, String birthDayStartRetrieval, String birthDayEndRetrieval){
    	Connection conn = null;
    	List<UserMyself> userMyselfList = new ArrayList<UserMyself>();

    	try {
    		/**Connection**/
    		/**データベースに接続**/
    		conn = DBmanagerMyself.getConnection();

    		/**SELECT文を準備**/
    		/**これuserテーブルを全部**/
    		String sql = "SELECT * FROM user WHERE id != 1";


    		//if文で入力によってSELECT文に追加するSQL文を分岐させる
//    		例えば
//    		if(!(loginId.isEmpty)) {
//    			sql += " And loginId = loginId''"
//    		}
    		//みたいな感じ


    /**もしログインIDがからじゃなかったら（ログインIDが入力されていたら）**/
   	/**この場合はログインIDは固有だから分岐しない**/

    		if(!(loginIdRetrieval.isEmpty())) {
    	/**ここ大事！！！！**/
    	/**sql文の文脈とJavaの文脈が混ざってるから**/
    	/**シングルクォーテーションとダブルクォーテーションが混在してるから注意**/
    			sql += " And login_Id = '"+loginIdRetrieval+"'";
    		}

    /**もしユーザ名が空じゃなかったら（ユーザ名が入力されていたら）**/
    		if(!(userNameRetrieval.isEmpty())){
    			sql += " And name LIKE '%"+userNameRetrieval+"%'";
    		}

//   	/**もし生年月日の前が空じゃなかったら（生年月日の前が入力されていたら）**/
//    		if(!(birthDayStartRetrieval.isEmpty())){
//    			sql += " And birth_Date >= '"+birthDayStartRetrieval+"'";
//    		}
//
//   	/**もし生年月日の後ろが空じゃなかったら（生年月日の後ろが入力されていたら）**/
//    		if(!(birthDayEndRetrieval.isEmpty())){
//    			sql += " And birth_Date <= '"+birthDayEndRetrieval+"'";
//    		}




    		/**SELECTを実行して、結果の表を取得する**/
    		/**取得してrsにexecuteQueryメソッドでセット**/
    		Statement stmt = conn.createStatement();
    		ResultSet rs = stmt.executeQuery(sql);

    		/**結果表に格納されたレコードの内容をwhileを回してそれぞれ変数に入れていく**/
    		/**getの中身はまだSQL語なのでテーブルのカラム名通りに入力していく**/
    		while(rs.next()){
    			int id = rs.getInt("id");
    			String loginId = rs.getString("login_Id");
    			String name = rs.getString("name");
    			Date birthDate = rs.getDate("birth_Date");
    			String password = rs.getString("password");
    			String createDate = rs.getString("create_Date");
    			String updateDate = rs.getString("update_Date");
    			/**UserMyselfのインスタンスを生成して設定してコンストラクタに引数を全部渡す
    			 上で作ったArrayListインスタンス(userMyselfList)に追加**/
    			UserMyself userMyself = new UserMyself(id,loginId,name,birthDate,password,createDate,updateDate);
    			userMyselfList.add(userMyself);
    		}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}finally{
			/**データベースを切断する**/
			/**finallyは例外をキャッチした場合もしてない場合も必ず実行する**/
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO: handle exception
					e.printStackTrace();
					return null;
				}
			}
		}

		return userMyselfList;

    }

//    INSERTするメソッドを作る
    public  UserMyself NewSighUpInfo(String loginId, String userName, String password, String birthDay)throws SQLException{
    	Connection conn = null;

    	try {
    		/**Connection**/
    		/**データベースに接続**/
    		conn = DBmanagerMyself.getConnection();

    		/**INSERT文を準備**/
    		/**これuserテーブルを全部**/
    		String sql = "INSERT INTO user (login_id, name, birth_date, password, create_date, update_date)VALUES ( ?, ?, ?, ?, now(), now())";

    		//ここから

    		/**INSERTを実行して、情報をテーブルに送信する**/
    		/**取得してrsにexecuteUpdateメソッドでセット→スライド6-9**/
    		String passwordCode = encryption(password);
    		PreparedStatement pStmt = conn.prepareStatement(sql);
    		pStmt.setString(1, loginId);
    		pStmt.setString(2, userName);
    		pStmt.setString(3, birthDay);
    		pStmt.setString(4, passwordCode);
    		pStmt.executeUpdate();

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
	//Dao内だけでなくNewSighUpServletでも例外を検知できるようにreturn = nullではなく
	//SQLExceptionをthrow e で投げる。
			throw e;
		}finally{
			/**データベースを切断する**/
			/**finallyは例外をキャッチした場合もしてない場合も必ず実行する**/
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO: handle exception
					e.printStackTrace();
					return null;
				}
			}
		}
		return null;
    }




/**取得したIDからSELECT文で一列丸ごとデータ持ってきてインスタンスに詰め込むメソッドを作る
 * @throws SQLException **/
public UserMyself getDetail(int id2){
	Connection conn = null;
	UserMyself userMyself = null;

	try {
		/**Connection**/
		/**データベースに接続**/
		conn = DBmanagerMyself.getConnection();

		/**SELECT文を準備**/
		/**これuserテーブルを全部**/
		String sql = "SELECT * FROM user WHERE id = ?";

		/**SELECTを実行して、結果の表を取得する**/
		/**取得してrsにexecuteQueryメソッドでセット**/
		PreparedStatement pStmt = conn.prepareStatement(sql);
		pStmt.setInt(1, id2);
		ResultSet rs = pStmt.executeQuery();

		/**結果表に格納されたレコードの内容をwhileを回してそれぞれ変数に入れていく**/
		/**getの中身はまだSQL語なのでテーブルのカラム名通りに入力していく**/
		//これは1人分なのでif文でもいい
		while(rs.next()){
			int id = rs.getInt("id");
			String loginId = rs.getString("login_Id");
			String name = rs.getString("name");
			Date birthDate = rs.getDate("birth_Date");
			String password = rs.getString("password");
			String createDate = rs.getString("create_Date");
			String updateDate = rs.getString("update_Date");
			/**UserMyselfのインスタンスを生成して設定してコンストラクタに引数を全部渡す
			 上で作ったArrayListインスタンス(userMyselfList)に追加**/
			userMyself = new UserMyself(id,loginId,name,birthDate,password,createDate,updateDate);
		}
	} catch (SQLException e) {
		// TODO: handle exception
		e.printStackTrace();
		return null;
	}finally{
		/**データベースを切断する**/
		/**finallyは例外をキャッチした場合もしてない場合も必ず実行する**/
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
				return null;
			}
		}
	}
	return userMyself;
	}


//ユーザ情報更新のためのメソッド
public UserMyself upDate(String password,String name,String birth_Date,int id){
	Connection conn = null;


	try {
		/**Connection**/
		/**データベースに接続**/
		conn = DBmanagerMyself.getConnection();

		/**SELECT文を準備**/
		/**これuserテーブルを全部**/
		String sql = "UPDATE user SET password =?,name = ?,birth_Date = ? WHERE id = ?";

		/**SELEを実行して、結果の表を取得する**/
		/**取得してrsにexecuteQueryメソッドでセット**/
		String passwordCode = encryption(password);
		PreparedStatement pStmt = conn.prepareStatement(sql);
		pStmt.setString(1, passwordCode);
		pStmt.setString(2, name);
		pStmt.setString(3, birth_Date);
		pStmt.setInt(4, id);
		pStmt.executeUpdate();


	} catch (SQLException e) {
		// TODO: handle exception
		e.printStackTrace();
		return null;
	}finally{
		/**データベースを切断する**/
		/**finallyは例外をキャッチした場合もしてない場合も必ず実行する**/
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
				return null;
			}
		}
	}
	return null;
	}

public  UserMyself delate(int id){
	Connection conn = null;

	try {
		/**Connection**/
		/**データベースに接続**/
		conn = DBmanagerMyself.getConnection();

		/**INSERT文を準備**/
		/**これuserテーブルを全部**/
		String sql = " DELETE FROM user WHERE id = ?";

		//ここから

		/**INSERTを実行して、情報をテーブルに送信する**/
		/**取得してrsにexecuteUpdateメソッドでセット→スライド6-9**/
		PreparedStatement pStmt = conn.prepareStatement(sql);
		pStmt.setInt(1, id);
		pStmt.executeUpdate();

	} catch (SQLException e) {
		// TODO: handle exception
		e.printStackTrace();
//Dao内だけでなくNewSighUpServletでも例外を検知できるようにreturn = nullではなく
//SQLExceptionをthrow e で投げる。
		return null;
	}finally{
		/**データベースを切断する**/
		/**finallyは例外をキャッチした場合もしてない場合も必ず実行する**/
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
				return null;
			}
		}
	}
	return null;
}

/**暗号化するメソッドを作る
 * ・String型の変数を暗号化して戻り値でString型の文字列で返す。
 * ・それを登録、更新、ログインの際に呼び出してからそれぞれのメソッドを呼び出す。
 * @throws NoSuchAlgorithmException
 *
 * **/
public String encryption(String password){
	//ハッシュを生成したい元の文字列
	String source = password;
	//ハッシュ生成前にバイト配列に置き換える際のCharset
	Charset charset = StandardCharsets.UTF_8;
	//ハッシュアルゴリズム
	String algorithm = "MD5";

	//ハッシュ生成処理
	byte[] bytes = null;
	try {
		bytes = MessageDigest.getInstance(algorithm).digest(source.getBytes(charset));
	} catch (NoSuchAlgorithmException e) {
		// TODO 自動生成された catch ブロック
		e.printStackTrace();
	}
	String result = DatatypeConverter.printHexBinary(bytes);
	System.out.println(result);

	return result;

}

}






