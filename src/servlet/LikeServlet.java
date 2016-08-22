package servlet;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import bean.Node;
import bean.Path;
import bean.SimilarPath;
import bean.TimeNode;
import bean.TimePath;

import com.alibaba.fastjson.JSON;

import controller.PathController;
import dao.PathDao;

@WebServlet("/like")
public class LikeServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		req.getRequestDispatcher("/WEB-INF/like.jsp").forward(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");

		DiskFileItemFactory dfif = new DiskFileItemFactory();
		ServletFileUpload sfu = new ServletFileUpload(dfif);
		sfu.setSizeMax(4 * 1024 * 1024);
		int type = Integer.parseInt(req.getParameter("is_time"));
		try {
			// 上传文件
			// 获取路径名字
			List<FileItem> list = sfu.parseRequest(req);
			Iterator<FileItem> iterator = list.iterator();
			FileItem fileItem = null;
			fileItem = iterator.next();
			if (fileItem.getName() == null || fileItem.getName() == "") {
				req.getRequestDispatcher("/WEB-INF/like.jsp")
						.forward(req, resp);
				return;
			}
			String fileName = fileItem.getName();
			String dirPath = this.getServletContext().getRealPath("/");
			dirPath = dirPath + "upload\\like\\";
			File file = new File(dirPath);
			if (!file.exists()) {
				file.mkdirs();
			}
			// 写入文件
			file = new File(dirPath + fileName);
			fileItem.write(file);

			fileItem = iterator.next();
			if (fileItem.getName() == null || fileItem.getName() == "") {
				req.getRequestDispatcher("/WEB-INF/like.jsp")
						.forward(req, resp);
				return;
			}
			String fileName2 = fileItem.getName();
			file = new File(dirPath + fileName2);
			fileItem.write(file);

			List<Node> nodes1 = null;
			List<Node> nodes2 = null;
			List<Node> nodes_sim = null;

			List<TimeNode> timeNodes1 = null;
			List<TimeNode> timeNodes2 = null;
			List<TimeNode> time_nodes_sim = null;

			// 返回对应类型的节点
			if (type == 0) {
				Path path1 = PathDao.createPath(dirPath + fileName);
				Path path2 = PathDao.createPath(dirPath + fileName2);
				SimilarPath path_sim = PathController.comparePath(path1, path2);

				nodes1 = path1.getNodes();
				nodes2 = path2.getNodes();
				nodes_sim = path_sim.getNodes();

				req.setAttribute("nodes1", JSON.toJSON(nodes1).toString());
				req.setAttribute("nodes2", JSON.toJSON(nodes2).toString());
				req.setAttribute("tracks", JSON.toJSON(nodes_sim).toString());
				req.setAttribute("similar", new Double(
						path_sim.calculateSim()).toString());

			} else if (type == 1) {
				TimePath timePath1 = PathDao.createTimePath(dirPath + fileName);
				TimePath timePath2 = PathDao.createTimePath(dirPath + fileName2);
				SimilarPath path_sim = PathController.compareTimePath(timePath1, timePath2);
				
				timeNodes1 = timePath1.getTimeNodes();
				timeNodes2 = timePath2.getTimeNodes();
				time_nodes_sim = path_sim.getTimeNodes();
				
				req.setAttribute("nodes1", JSON.toJSON(timeNodes1).toString());
				req.setAttribute("nodes2", JSON.toJSON(timeNodes2).toString());
				req.setAttribute("tracks", JSON.toJSON(time_nodes_sim).toString());
				req.setAttribute("similar", new Double(
						path_sim.calculateSim()).toString());
			}

			req.setAttribute("type", type);

			// System.out.println(JSON.toJSON(nodes).toString());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		req.getRequestDispatcher("/WEB-INF/like.jsp").forward(req, resp);
	};

}
