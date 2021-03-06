package servlet;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
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

import bean.Path;
import bean.SimilarPath;
import bean.TimePath;

import com.alibaba.fastjson.JSON;

import controller.PathController;
import dao.PathDao;

@WebServlet("/mulquery")
public class MulQueryServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		req.getRequestDispatcher("/WEB-INF/mulquery.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");

		DiskFileItemFactory dfif = new DiskFileItemFactory();
		ServletFileUpload sfu = new ServletFileUpload(dfif);
		sfu.setSizeMax(4 * 1024 * 1024);
		int type = Integer.parseInt(req.getParameter("is_time"));
		// 源路径和比较路径数组
		Path path = null;
		HashMap<String, Path> dest_paths = new HashMap<String, Path>();
		TimePath timePath = null;
		HashMap<String, TimePath> dest_timePaths = new HashMap<String, TimePath>();

		// 建立上传文件夹
		String dirPath = this.getServletContext().getRealPath("/");
		if (type == 0) {
			dirPath = dirPath + "upload\\mulquery\\notime\\";
		} else {
			dirPath = dirPath + "upload\\mulquery\\time\\";
		}
		File file = new File(dirPath);
		if (!file.exists()) {
			file.mkdirs();
		}
		String fileName = null;
		String fileName_t = null;
		// 读取源路径
		try {
			// 上传原路径
			List<FileItem> list = sfu.parseRequest(req);
			Iterator<FileItem> iterator = list.iterator();
			FileItem fileItem = null;
			fileItem = iterator.next();
			// 若为空则跳转
			if (fileItem.getName() == null || fileItem.getName().equals("")) {
				req.getRequestDispatcher("/WEB-INF/mulquery.jsp").forward(req,
						resp);
				return;
			}
			// 写入文件
			fileName = fileItem.getName();
			fileName_t = fileName;
			file = new File(dirPath + fileName);
			if (!file.exists())
				fileItem.write(file);
			if (type == 0)
				path = PathDao.createPath(dirPath + fileName);
			else
				timePath = PathDao.createTimePath(dirPath + fileName);
			// 上传所有路径
			while (iterator.hasNext()) {
				fileItem = (FileItem) iterator.next();
				fileName = fileItem.getName();
				// System.out.println(fileName);
				if (fileName == null || fileName.equals("")) {
					continue;
				}
				file = new File(dirPath + fileName);
				if (!file.exists()) {
					fileItem.write(file);
				}
				if (type == 0) {
					dest_paths.put(fileName,
							PathDao.createPath(dirPath + fileName));
				} else {
					dest_timePaths.put(fileName,
							PathDao.createTimePath(dirPath + fileName));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 比较相似度
		Path simPath = null;
		TimePath simTimePath = null;
		// 最大相似度路径
		SimilarPath mostPath = null;
		// 比较普通路径相似度
		if (type == 0) {
			// 计算时间
			long startTime = System.currentTimeMillis();
			mostPath = PathController.comparePaths(path, dest_paths);
			simPath = mostPath.getMostPath();
			long endTime = System.currentTimeMillis();
			Float excTime = new Float(endTime - startTime) / 1000;

			req.setAttribute("nodes1", JSON.toJSON(path.getNodes()).toString());
			req.setAttribute("nodes2", JSON.toJSON(simPath.getNodes())
					.toString());
			req.setAttribute("tracks", JSON.toJSON(mostPath.getNodes())
					.toString());
			req.setAttribute("excTime", excTime.toString());

		} else {
			// 计算时间
			long startTime = System.currentTimeMillis();
			mostPath = PathController
					.compareTimePaths(timePath, dest_timePaths);
			simTimePath = mostPath.getMostTimePath();
			long endTime = System.currentTimeMillis();
			Float excTime = new Float(endTime - startTime) / 1000;
			req.setAttribute("nodes1", JSON.toJSON(timePath.getTimeNodes())
					.toString());
			req.setAttribute("nodes2", JSON.toJSON(simTimePath.getTimeNodes())
					.toString());
			req.setAttribute("tracks", JSON.toJSON(mostPath.getTimeNodes())
					.toString());
			req.setAttribute("excTime", excTime.toString());
		}
		req.setAttribute("path_source_name", fileName_t);
		req.setAttribute("resultList", mostPath.getSimList());
		req.setAttribute("type", type);
		req.getRequestDispatcher("/WEB-INF/mulquery.jsp").forward(req, resp);
	}
}
