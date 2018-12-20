package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.UserMyself;

public class UserDaoMyself {

	public List<UserMyself>findall(){
		Connection conn = null;
		List<UserMyself> empList = new ArrayList<UserMyself>();

		try {
			//**データベースへ接続**/
			conn = DBmanagerMyself.
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return null;

	}

}
