package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDaoMyself;
import model.UserMyself;


@WebServlet("/LoginListServletMyself")
public class UserListServletMyself extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UserListServletMyself() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());

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
		doGet(request, response);
		
		/**フォームの中の情報で検索する機能をつける**/
		
	}

}
