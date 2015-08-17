package br.com.zaul.mycar.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.zaul.mycar.service.PingService;

@WebServlet("/ping")
public class PingServlet extends HttpServlet {
	
	@EJB
	private PingService pingService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		pingService.pingServer();;
		
		PrintWriter out = resp.getWriter();
		out.println("pong");
	}
	
}
