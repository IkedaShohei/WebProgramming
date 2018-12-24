package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		doGet(request, response);

		/**登録成功時：ユーザー一覧画面に遷移する**/


		/**登録失敗時
		 * ・ユーザ新規登録に戻る。
		 * ・その時、ページ名の下に赤色で「入力された内容は正しくありません」と表示する。
		 * ・入力下内容は引き継がれるけど、パスワードとパスワード（確認）だけは空欄にする。**/


	}

}
