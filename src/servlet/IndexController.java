package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/index")
public class IndexController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IndexController() {
		// TODO Auto-generated constructor stub
		super();
		// contextPath = getServletContext().getRealPath("/");

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		req.setCharacterEncoding("UTF-8");

		req.getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

}
