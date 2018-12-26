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
 * Servlet implementation class UserDetailServletMyself
 */
@WebServlet("/UserDetailServletMyself")
public class UserDetailServletMyself extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDetailServletMyself() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**文字化け防止**/
		request.setCharacterEncoding("UTF-8");

		// URLからGETパラメータとしてIDを受け取る
		String id1 = request.getParameter("id");
		int id = Integer.parseInt(id1);

		// 確認用：idをコンソールに出力
//		System.out.println(id);


		// TODO  未実装：idを引数にして、idに紐づくユーザ情報を出力する
		UserDaoMyself userDaoMyself = new UserDaoMyself();
		UserMyself userMyself = userDaoMyself.getDetail(id);


		// TODO  未実装：ユーザ情報をリクエストスコープにセットしてjspにフォワード
		request.setAttribute("userMyself", userMyself);

		/**userDeteilMyself.jspにフォワード**/
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userDetailMyself.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


	}

}
