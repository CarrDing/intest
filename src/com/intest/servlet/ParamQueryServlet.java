package com.intest.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.intest.service.ParamQueryService;

/**
 * Servlet implementation class ParamSetServlet
 */
@WebServlet("/ParamQueryServlet")
public class ParamQueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ParamQueryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String type = request.getParameter("queryType");
		switch (type) {
		case "intReq":{ParamQueryService.querySingle("intReq", true);}break;
		case "link1Req":{ParamQueryService.querySingle("link1Req", true);}break;
		case "link2Req":{ParamQueryService.querySingle("link2Req", true);}break;
		case "link2EnReq":{ParamQueryService.querySingle("link2EnReq", true);}break;
		case "dbcNameReq":{ParamQueryService.querySingle("dbcNameReq", true);}break;
		case "sysReq":{ParamQueryService.querySingle("sysReq", true);}break;
		case "sdHzReq":{ParamQueryService.querySingle("sdHzReq", true);}break;
		case "sdCapReq":{ParamQueryService.querySingle("sdCapReq", true);}break;
		case "custNameReq":{ParamQueryService.queryCan(1, 10);}break;
		default:break;
		}
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
