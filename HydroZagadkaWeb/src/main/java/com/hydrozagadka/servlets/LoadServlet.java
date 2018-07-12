package com.hydrozagadka.servlets;

import com.hydrozagadka.servlets.Beans.UnzipDao;
import net.lingala.zip4j.core.ZipFile;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;

@WebServlet("/loadservlet")
@MultipartConfig
public class LoadServlet extends HttpServlet {
    @Inject
    private UnzipDao unzipDao;
    String directPath = "/home/orzel/Downloads";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
        InputStream is = filePart.getInputStream();
        unzipDao.unzip(is,directPath);



        PrintWriter pr = response.getWriter();
        pr.println(fileName);
  //      unzipDao.unzip(fileName,directPath);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
