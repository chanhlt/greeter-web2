package org.bhn.training;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;

@Component(name = "Greeter Servlet", immediate = true)
@Service(value = javax.servlet.Servlet.class)
@Properties({ @Property(name = "servlet-name", value = "Greeter Servlet"),
		@Property(name = "alias", value = "/greeter") })
public class GreeterServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3330870013919653842L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().write("Servlet Is Bound!");
	}
}
