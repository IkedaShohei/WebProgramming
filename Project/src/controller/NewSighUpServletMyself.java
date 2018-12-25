package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDaoMyself;

/**
 * Servlet implementation class NewSighUpServletMyself
 */
@WebServlet("/NewSighUpServletMyself")
public class NewSighUpServletMyself extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewSighUpServletMyself() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/**文字化け防止**/
		response.setContentType("text/html; charset=UTF-8");

		/**とりあえずフォワードでjsp表示**/
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/newSighUpMyself.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/**とりあえず文字化け防止**/
		request.setCharacterEncoding("UTF-8");

		/**リクエストパラメータの取得**/
		/**getParameter()メソッドにformで指定したリクエストパラメータの名前を
		引数にすることでパラメータが取得できる**/
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		String passwordConfirmation = request.getParameter("passwordConfirmation");
		String userName = request.getParameter("userName");
		String birthDay = request.getParameter("birthDay");


		/**この後のリクエストスコープ、セッションスコープを保存するための
		 * UserDaoMyselfのインスタンスを作って UserMyself型のuserMyselfに
		 * userDaoMyselfのfindByLoginInfoメソッドに引数を渡す
		 * →Daoのメソッドが実行される**/

		/**作ったインサート文を呼び出して**/
		UserDaoMyself userDaoMyself = new UserDaoMyself();
		userDaoMyself.NewSighUpInfo(loginId, userName, password, birthDay);

		/**登録成功時：ユーザー一覧画面に遷移する**/
		/**UserListServletMyselfのサーブレットにリダイレクト**/
//		UserListServletMyselfにリダイレクト
//		データはUserListServletMyselfが表示させる
		response.sendRedirect("UserListServletMyself");



		/**登録失敗時
		 * ・ユーザ新規登録に戻る。
		 * ・その時、ページ名の下に赤色で「入力された内容は正しくありません」と表示する。
		 * ・入力下内容は引き継がれるけど、パスワードとパスワード（確認）だけは空欄にする。**/

//		入力内容引き継がれる→セッションスコープ？
//		パスワード関連のところだけリクエストスコープにする？
//		分岐のさせかた
//		値を残したままだからフォワードで「ユーザ新規登録」遷移
//
//		失敗する場合
//		→すでに登録されてる時
//		→データ型が合わない時？
//		→sqlのインサートがうまくできない文字の場合？
//		からの場合
//


//		ログインサーブレットから引用
//		if(userMyself == null) {
//			request.setAttribute("errMsg", "入力された内容は正しくありません");
//
//		/**loginMyself.jspにフォワード**/
//			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/loginMyself.jsp");
//			dispatcher.forward(request, response);
//			return;
		}


	}


