package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

		PreparedStatement pStmt = conn.prepareStatement(sql);
		pStmt.setString(1, loginId);
		pStmt.setString(2, password);
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
    		String sql = "SELECT * FROM user";

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
    		PreparedStatement pStmt = conn.prepareStatement(sql);
    		pStmt.setString(1, loginId);
    		pStmt.setString(2, userName);
    		pStmt.setString(3, birthDay);
    		pStmt.setString(4, password);
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
}






