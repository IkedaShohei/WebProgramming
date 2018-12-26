package controller;

import java.io.IOException;
import java.sql.SQLException;

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


		/**登録失敗時
		 * ・ユーザ新規登録に戻る。
		 * ・その時、ページ名の下に赤色で「入力された内容は正しくありません」と表示する。
		 * ・入力下内容は引き継がれるけど、パスワードとパスワード（確認）だけは空欄にする。**/

//		→パスワードとパスワード（確認）が合わなかった時
		if(!(password.equals(passwordConfirmation))) {
			request.setAttribute("errMsg", "入力された内容は正しくありません。");

			request.setAttribute("loginId",loginId);
			request.setAttribute("userName",userName);
			request.setAttribute("birthDay",birthDay);

			/**newSighUpServletMyself.jspにフォワード**/
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/newSighUpMyself.jsp");
			dispatcher.forward(request, response);
			return;
		}

//どれか１つでも入力欄が空だった場合
		if(loginId.equals("") || password.equals("") || passwordConfirmation.equals("") || userName.equals("") || birthDay.equals("")) {
			request.setAttribute("errMsg", "入力された内容は正しくありません。");

			/**newSighUpServletMyself.jspにフォワード**/
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/newSighUpMyself.jsp");
			dispatcher.forward(request, response);
			return;
		}


		/**
		 * UserDaoMyselfのインスタンスを作って UserMyself型のuserMyselfに
		 * userDaoMyselfのNewSighUpInfoメソッドに引数を渡す
		 * →Daoのメソッドが実行される**/

		/**作ったインサート文をテーブルに追加するメソッドを実行**/
		UserDaoMyself userDaoMyself = new UserDaoMyself();
	//すでに登録している時の弾きをcatchで表現
		try {
			userDaoMyself.NewSighUpInfo(loginId, userName, password, birthDay);
		} catch(SQLException e) {

			//UserDaoMyselfのNewSighUpInfoでスロー宣言されていて
			//catchの中でthrowされている例外をこっちでも検知できるように
			//trycatchで囲む

			request.setAttribute("errMsg", "入力された内容は正しくありません。");

			request.setAttribute("loginId",loginId);
			request.setAttribute("userName",userName);
			request.setAttribute("birthDay",birthDay);

			/**newSighUpServletMyself.jspにフォワード**/
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/newSighUpMyself.jsp");
			dispatcher.forward(request, response);
			return;
		}


	/**登録成功時：ユーザー一覧画面に遷移する**/
		/**UserListServletMyselfのサーブレットにリダイレクト**/
//		UserListServletMyselfにリダイレクト
//		データはUserListServletMyselfが表示させる
		response.sendRedirect("UserListServletMyself");

		}
	}


