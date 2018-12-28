package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDaoMyself;
import model.UserMyself;


@WebServlet("/UserListServletMyself")
public class UserListServletMyself extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UserListServletMyself() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");

		//インスタンスを取得してセッションがあるか比べる準備
		HttpSession session = request.getSession();
		//もしセッションがなかったらログイン画面にリダイレクト
		if(session.getAttribute("userInfoMyself") == null) {
			/**LoginServletMyselfのサーブレットにリダイレクト**/
			response.sendRedirect("LoginServletMyself");
			return;
		}

		/**ユーザの一覧情報を取得してBeansのリストにfindallメソッドで
		 * リストに情報を持ったインスタンスを入れていく**/
		UserDaoMyself userDaoMyself = new UserDaoMyself();
		List<UserMyself> userMyselfList = userDaoMyself.findall();

		/**リクエストスコープで情報インスタンスを格納したリストを合言葉と一緒にセット**/
		request.setAttribute("userMyselfList", userMyselfList);

		/**ユーザ一覧のjsp（userListMyself.jsp"）にフォワード**/
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userListMyself.jsp");
		dispatcher.forward(request, response);

	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/**フォームの中の情報で検索する機能をつける**/

		/**とりあえず文字化け防止**/
		request.setCharacterEncoding("UTF-8");

		/**リクエストパラメータの取得**/
		/**getParameter()メソッドにformで指定したリクエストパラメータの名前を
		引数にすることでパラメータが取得できる**/
		String loginIdRetrieval = request.getParameter("loginId");
		String userNameRetrieval = request.getParameter("userName");
		String birthDayStartRetrieval = request.getParameter("birthDayStart");
		String birthDayEndRetrieval = request.getParameter("birthDayEnd");


		/**ユーザの一覧情報を取得してBeansのリストにfindallメソッドで
		 * リストに情報を持ったインスタンスを入れていく**/
		UserDaoMyself userDaoMyself = new UserDaoMyself();
		List<UserMyself> userMyselfList = userDaoMyself.Retrieval(loginIdRetrieval,userNameRetrieval,birthDayStartRetrieval,birthDayEndRetrieval);

		/**リクエストスコープで情報インスタンスを格納したリストを合言葉と一緒にセット**/
		request.setAttribute("userMyselfList", userMyselfList);

		/**ユーザ一覧のjsp（userListMyself.jsp"）にフォワード**/
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userListMyself.jsp");
		dispatcher.forward(request, response);

	}

}
