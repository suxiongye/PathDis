package servlet;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
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
import bean.TimeNode;

import com.alibaba.fastjson.JSON;

import controller.HistoryController;
import dao.PathDao;

@WebServlet("/show")
public class ShowServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");

		req.getRequestDispatcher("/WEB-INF/showPath.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
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
				req.getRequestDispatcher("/WEB-INF/showPath.jsp").forward(req,
						resp);
				return;
			}
			String fileName = fileItem.getName();
			String dirPath = this.getServletContext().getRealPath("/");
			dirPath = dirPath + "upload\\show\\";
			File file = new File(dirPath);
			if (!file.exists()) {
				file.mkdirs();
			}
			// 写入文件
			file = new File(dirPath + fileName);
			fileItem.write(file);

			List<Node> nodes = null;
			List<TimeNode> timeNodes = null;
			// 返回对应类型的节点
			if (type == 0) {
				nodes = PathDao.createPath(dirPath + fileName).getNodes();
				req.setAttribute("nodes", JSON.toJSON(nodes).toString());
			} else if (type == 1) {
				timeNodes = PathDao.createTimePath(dirPath + fileName)
						.getTimeNodes();
				req.setAttribute("nodes", JSON.toJSON(timeNodes).toString());
			}

			req.setAttribute("type", type);
			HistoryController.addPath(fileName, new Timestamp(System.currentTimeMillis()));
			//System.out.println(JSON.toJSON(nodes).toString());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		req.getRequestDispatcher("/WEB-INF/showPath.jsp").forward(req, resp);
	}
}
