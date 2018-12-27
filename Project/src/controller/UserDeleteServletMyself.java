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
 * Servlet implementation class UserDeleteServletMyself
 */
@WebServlet("/UserDeleteServletMyself")
public class UserDeleteServletMyself extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDeleteServletMyself() {
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

		// URLからGETパラメータとしてIDを受け取る
		String id1 = request.getParameter("id");
		int id = Integer.parseInt(id1);

		// idを引数にして、idに紐づくユーザ情報を出力する
		UserDaoMyself userDaoMyself = new UserDaoMyself();
		UserMyself userMyself = userDaoMyself.getDetail(id);

		request.setAttribute("userMyself", userMyself);


		/**フォワードでjsp表示**/
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userDeleteMyself.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/**文字化け防止**/
		request.setCharacterEncoding("UTF-8");

		String id1 = request.getParameter("id");
		int id = Integer.parseInt(id1);

		UserDaoMyself userDaoMyself = new UserDaoMyself();
		userDaoMyself.delate(id);

		/**削除成功時：ユーザー一覧画面に遷移する**/
		/**UserListServletMyselfのサーブレットにリダイレクト**/
//		UserListServletMyselfにリダイレクト
//		データはUserListServletMyselfが表示させる
		response.sendRedirect("UserListServletMyself");

	}

}
