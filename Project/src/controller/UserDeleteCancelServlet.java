package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UserDeleteCancelServlet
 */
@WebServlet("/UserDeleteCancelServlet")
public class UserDeleteCancelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDeleteCancelServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//インスタンスを取得してセッションがあるか比べる準備
		HttpSession session = request.getSession();
		//もしセッションがなかったらログイン画面にリダイレクト
		if(session.getAttribute("userInfoMyself") == null) {
			/**LoginServletMyselfのサーブレットにリダイレクト**/
			response.sendRedirect("LoginServletMyself");
			return;
		}

//		UserListServletMyselfにリダイレクト
//		データはUserListServletMyselfが表示させる
		response.sendRedirect("UserListServletMyself");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
