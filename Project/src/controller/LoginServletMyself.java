package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDaoMyself;
import model.UserMyself;


@WebServlet("/LoginServletMyself")
public class LoginServletMyself extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public LoginServletMyself() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/**フォワードする**/
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/loginMyself.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	//フォームからリクエストパラメータを取得する　〜43まで
		/**リクエストパラメータの文字コードを指定**/
		request.setCharacterEncoding("UTF-8");

		/**リクエストパラメータの取得**/
		/**getParameter()メソッドにformで指定したリクエストパラメータの名前を
		引数にすることでパラメータが取得できる**/
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");


		/**この後のリクエストスコープ、セッションスコープを保存するための
		 * UserDaoMyselfのインスタンス（JavaBeansのインスタンス）を作って UserMyself型のuserMyselfに
		 * userMyselfのfindByLoginInfoメソッドに引数を渡す
		 * →Daoのメソッドが実行される**/

	//ここから〜64行目まで　リクエストスコープ→スライドの4-14
		UserDaoMyself userDaoMyself = new UserDaoMyself();
		UserMyself userMyself = userDaoMyself.findByLoginInfo(loginId, password);

	/**もしDaoのメソッドを実行して入力されたデータが見つからなかった場合**/
		if(userMyself == null) {
			request.setAttribute("errMsg", "ログインに失敗しました。");

		/**loginMyself.jspにフォワード**/
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/loginMyself.jsp");
			dispatcher.forward(request, response);
			return;
		}

	/**もしDaoのメソッドを実行して入力されたデータが見つかった場合 **/
	// ここからセッションスコープ　→スライド4-17

		/**HttpServletインスタンスのgetSession()メソッドでHttpSessionインスタンスを取得**/
		HttpSession session = request.getSession();

		/**属性名を指定してインスタンスを保存する
		 * すでに同じ属性名でインスタンスが保存されている場合は上書きされる**/
		session.setAttribute("userInfoMyself", userMyself);

		/**UserListServletMyselfのサーブレットにリダイレクト**/
		response.sendRedirect("UserListServletMyself");

	}

}
