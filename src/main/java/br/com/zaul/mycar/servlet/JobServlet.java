package br.com.zaul.mycar.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.zaul.mycar.service.CarService;

@WebServlet("/job")
public class JobServlet extends HttpServlet {
	
	@EJB
	private CarService carsService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		carsService.synchronizeCars();
		
		PrintWriter out = resp.getWriter();
		out.println("JOB Triggered");
	}
	
}
