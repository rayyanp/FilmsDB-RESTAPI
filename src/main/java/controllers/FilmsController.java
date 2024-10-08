package controllers;

import java.io.IOException;


import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.FilmDAO;
import models.Film;

@WebServlet("/films")
public class FilmsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		FilmDAO dao = new FilmDAO();
		ArrayList<Film> allfilms = dao.getAllFilms();
		
		for (Film f: allfilms) {
			System.out.println(f.getTitle());
		}
		request.setAttribute("films", allfilms);
		RequestDispatcher rd = request.getRequestDispatcher("films.jsp");
		rd.include(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		FilmDAO dao = new FilmDAO();
		Film f = new Film();

		f.setTitle(request.getParameter("title"));
		f.setDirector(request.getParameter("director"));
		f.setYear(Integer.valueOf(request.getParameter("year")));
		f.setStars(request.getParameter("stars"));
		f.setReview(request.getParameter("review"));

		dao.insertFilm(f);

		doGet(request, response);

	}
	
	
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FilmDAO dao = new FilmDAO();
		Film f = new Film();
		
		f.setId(Integer.valueOf(request.getParameter("id")));
		f.setTitle(request.getParameter("title"));
		f.setDirector(request.getParameter("director"));
		f.setYear(Integer.valueOf(request.getParameter("year")));
		f.setStars(request.getParameter("stars"));
		f.setReview(request.getParameter("review"));
		
		dao.updateFilm(f);
		
		doGet(request, response);
	    response.sendRedirect(request.getContextPath() + "/films");
	    
		int id = Integer.valueOf(request.getParameter("id"));
		
		Film film = dao.getFilmByID(id);
		request.setAttribute("film", film);
		request.getRequestDispatcher("edit.jsp").include(request, response);
	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		FilmDAO dao = new FilmDAO();
		Film f = new Film();
		
		f.setId(Integer.valueOf(request.getParameter("id")));
		
		dao.deleteFilm(f.getId());
	    response.sendRedirect(request.getContextPath() + "/films");
	}

}
