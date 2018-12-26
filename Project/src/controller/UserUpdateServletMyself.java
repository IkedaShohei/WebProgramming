package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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


		/**とりあえずフォワード**/
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
		String password = request.getParameter("password");
		String passwordConfirmation = request.getParameter("passwordConfirmation");
		String userName = request.getParameter("userName");
		String birthDay = request.getParameter("birthDay");








		if(!(password.equals(passwordConfirmation))) {
			request.setAttribute("errMsg", "入力された内容は正しくありません。");

			request.setAttribute("userName",userName);
			request.setAttribute("birthDay",birthDay);


		/**userDeteilMyself.jspにフォワード**/
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userUpdateMyself.jsp");
		dispatcher.forward(request, response);
		}

		/**登録成功時：ユーザー一覧画面に遷移する**/
		/**UserListServletMyselfのサーブレットにリダイレクト**/
//		UserListServletMyselfにリダイレクト
//		データはUserListServletMyselfが表示させる
		response.sendRedirect("UserListServletMyself");

	}

}
