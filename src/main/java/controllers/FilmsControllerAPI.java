package controllers;

import java.io.IOException;

import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import database.FilmDAO;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import models.Film;
import models.FilmList;

@WebServlet("/filmsapi")
public class FilmsControllerAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		FilmDAO dao = new FilmDAO();
		ArrayList<Film> allFilms = dao.getAllFilms();
		PrintWriter out = response.getWriter();		
		
		String format = request.getHeader("Accept");
		System.out.println(format);
		if ("application/json".equals(format)) {
			response.setContentType("application/json");
			Gson gson = new Gson();
			String json = gson.toJson(allFilms);
			out.write(json);
			out.close();
		} else if ("application/xml".equals(format)) {

			response.setContentType("application/xml");
			FilmList f1 = new FilmList(allFilms);
//			StringWriter sw = new StringWriter();
			PrintWriter out1 = response.getWriter();
			JAXBContext jaxbContext;
			try {
				jaxbContext = JAXBContext.newInstance(FilmList.class);
				Marshaller marshaller = jaxbContext.createMarshaller();
				marshaller.setProperty(marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
				marshaller.marshal(f1, out1);
//				out.write(sw.toString());
				out.close();
				System.out.println(out1.toString());

			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		} else {
			response.setContentType("text/plain");


		}

		// Default	
		Gson gson = new Gson();
		String json = gson.toJson(allFilms);
		out.write(json);
		out.close();
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String data = request.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);
		System.out.println(data);
		
		//set content type
		
		String dataFormat = request.getHeader("Content-Type");
		if ("application/xml".equals(dataFormat)) {
			JAXBContext jaxbContext1;
			FilmDAO dao = new FilmDAO();
			PrintWriter out = response.getWriter();
			try {
				jaxbContext1 = JAXBContext.newInstance(Film.class);
				Unmarshaller jaxbUnmarsheller = jaxbContext1.createUnmarshaller();
				Film f = (Film) jaxbUnmarsheller.unmarshal(new StringReader(data));
				dao.insertFilm(f);
				out.write("film inserted");
				
			} catch (JAXBException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			out.close();
			
		} else if ("application/json".equals(dataFormat)) {
			FilmDAO dao = new FilmDAO();
			PrintWriter out = response.getWriter();
			Gson gson = new Gson();
			Film f = gson.fromJson(data, Film.class);
			dao.insertFilm(f);
			out.write("film inserted");
		} else {
			response.setContentType("text/plain");
		
		}
		
		

		
		
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String data = request.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);
		System.out.println(data);
		
		//set content type
		
		String dataFormat = request.getHeader("Content-Type");
		if ("application/xml".equals(dataFormat)) {
			JAXBContext jaxbContext1;
			FilmDAO dao = new FilmDAO();
			PrintWriter out = response.getWriter();
			try {
				jaxbContext1 = JAXBContext.newInstance(Film.class);
				Unmarshaller jaxbUnmarsheller = jaxbContext1.createUnmarshaller();
				Film f = (Film) jaxbUnmarsheller.unmarshal(new StringReader(data));
				dao.updateFilm(f);
				out.write("film updated");
				
			} catch (JAXBException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			out.close();
			
		} else if ("application/json".equals(dataFormat)) {
			FilmDAO dao = new FilmDAO();
			PrintWriter out = response.getWriter();
			Gson gson = new Gson();
			Film f = gson.fromJson(data, Film.class);
			dao.updateFilm(f);
			out.write("film updated");
		} else {
			response.setContentType("text/plain");
		
		}
		

	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	
		String data = request.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);
		System.out.println(data);
		
		//set content type
		
		String dataFormat = request.getHeader("Content-Type");
		if ("application/xml".equals(dataFormat)) {
			JAXBContext jaxbContext1;
			FilmDAO dao = new FilmDAO();
			PrintWriter out = response.getWriter();
			try {
				jaxbContext1 = JAXBContext.newInstance(Film.class);
				Unmarshaller jaxbUnmarsheller = jaxbContext1.createUnmarshaller();
				Film f = (Film) jaxbUnmarsheller.unmarshal(new StringReader(data));
				dao.deleteFilm(f.getId());
				out.write("film deleted");
				
			} catch (JAXBException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			out.close();
			
		} else if ("application/json".equals(dataFormat)) {
			FilmDAO dao = new FilmDAO();
			PrintWriter out = response.getWriter();
			Gson gson = new Gson();
			Film f = gson.fromJson(data, Film.class);
			dao.deleteFilm(f.getId());
			out.write("film deleted");
		} else {
			response.setContentType("text/plain");
		
		}
	}
		

	
}
