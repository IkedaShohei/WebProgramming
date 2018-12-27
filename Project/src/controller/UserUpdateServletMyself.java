package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDaoMyself;
import model.UserMyself;

/**
 * Servlet implementation class UserUpdateServletMyself
 */
@WebServlet("/UserUpdateServletMyself")
public class UserUpdateServletMyself extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserUpdateServletMyself() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		// URLからGETパラメータとしてIDを受け取る
		String id1 = request.getParameter("id");
		int id = Integer.parseInt(id1);

		// idを引数にして、idに紐づくユーザ情報を出力する
		UserDaoMyself userDaoMyself = new UserDaoMyself();
		UserMyself userMyself = userDaoMyself.getDetail(id);

		request.setAttribute("userMyself", userMyself);

		/**フォワード**/
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userUpdateMyself.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/**文字化け防止**/
		request.setCharacterEncoding("UTF-8");


		/**リクエストパラメータの取得**/
		/**getParameter()メソッドにformで指定したリクエストパラメータの名前を
		引数にすることでパラメータが取得できる**/
		String id1 = request.getParameter("id");
		int id = Integer.parseInt(id1);
		String password = request.getParameter("passWord");
		String passwordConfirmation = request.getParameter("passwordConfirmation");
		String name = request.getParameter("userName");
		String birthDay = request.getParameter("birthday");


		//パスワードとパスワード（確認）が一致しなかった時
		if(!(password.equals(passwordConfirmation))) {
			request.setAttribute("errMsg", "入力された内容は正しくありません。");

			/**UserUpdateServletMyselfにフォワード**/
			doGet(request, response);
			return;
		}

		//ユーザ名または生年月日が空だった場合
		if(name.equals("") || birthDay.equals("")) {
				request.setAttribute("errMsg", "入力された内容は正しくありません。");

			/**UserUpdateServletMyselfにフォワード**/
			doGet(request, response);
			return;
		}

		UserDaoMyself userDaoMyself = new UserDaoMyself();
		userDaoMyself.upDate(password, name, birthDay, id);


		/**登録成功時：ユーザー一覧画面に遷移する**/
		/**UserListServletMyselfのサーブレットにリダイレクト**/
//		UserListServletMyselfにリダイレクト
//		データはUserListServletMyselfが表示させる
		response.sendRedirect("UserListServletMyself");

	}

}
