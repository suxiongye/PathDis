package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Config;

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
		// 设置默认值
		req.setAttribute("long_acc", Config.LONG_SIM_ACCURACY);
		req.setAttribute("lat_acc", Config.LAT_SIM_ACCURACY);
		req.setAttribute("long_time_acc", Config.LONG_TIME_SIM_ACCURACY);
		req.setAttribute("lat_time_acc", Config.LAT_TIME_SIM_ACCURACY);
		req.setAttribute("time_acc", Config.TIME_SIM_ACCURACY);
		req.getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		String long_acc = req.getParameter("long_acc");
		String lat_acc = req.getParameter("lat_acc");
		String long_time_acc = req.getParameter("long_time_acc");
		String lat_time_acc = req.getParameter("lat_time_acc");
		String time_acc = req.getParameter("time_acc");

		if (!long_acc.trim().equals("")) {
			Config.LONG_SIM_ACCURACY = Integer.parseInt(long_acc);
		}
		if (!lat_acc.trim().equals("")) {
			Config.LAT_SIM_ACCURACY = Integer.parseInt(lat_acc);
		}
		if (!long_time_acc.trim().equals("")) {
			Config.LONG_TIME_SIM_ACCURACY = Integer.parseInt(long_time_acc);
		}
		if (!lat_time_acc.trim().equals("")) {
			Config.LAT_TIME_SIM_ACCURACY = Integer.parseInt(lat_time_acc);
		}
		if (!time_acc.trim().equals("")) {
			Config.TIME_SIM_ACCURACY = Integer.parseInt(time_acc);
		}
		// 设置默认值
		req.setAttribute("long_acc", Config.LONG_SIM_ACCURACY);
		req.setAttribute("lat_acc", Config.LAT_SIM_ACCURACY);
		req.setAttribute("long_time_acc", Config.LONG_TIME_SIM_ACCURACY);
		req.setAttribute("lat_time_acc", Config.LAT_TIME_SIM_ACCURACY);
		req.setAttribute("time_acc", Config.TIME_SIM_ACCURACY);
		System.out.println("Config:");
		System.out.println("long_acc=" + Config.LONG_SIM_ACCURACY);
		System.out.println("lat_acc=" + Config.LAT_SIM_ACCURACY);
		System.out.println("long_time_acc=" + Config.LONG_TIME_SIM_ACCURACY);
		System.out.println("lat_time_acc=" + Config.LAT_TIME_SIM_ACCURACY);
		System.out.println("time_acc=" + Config.TIME_SIM_ACCURACY);
		req.getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);

	}

}
