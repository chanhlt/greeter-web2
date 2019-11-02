package org.bhn.training.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.ReferenceCardinality;
import org.apache.felix.scr.annotations.ReferencePolicy;
import org.apache.felix.scr.annotations.Service;
import org.bhn.training.api.Greeter;

@Component(name = "Greeter Web2", immediate = true)
@Service(value = javax.servlet.Servlet.class)
@Properties({ @Property(name = "servlet-name", value = "Greeter Web2"),
		@Property(name = "alias", value = "/greeter-web2") })
public class GreeterServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3330870013919653842L;

	@Reference(referenceInterface = Greeter.class, cardinality = ReferenceCardinality.OPTIONAL_MULTIPLE, policy = ReferencePolicy.DYNAMIC)
	private List<Greeter> greeters;

	public void bindGreeter(Greeter greeter) {
		if (this.greeters == null) {
			this.greeters = new ArrayList<Greeter>();
		}
		this.greeters.add(greeter);
	}

	public void unbindGreeter(Greeter greeter) {
		if (this.greeters != null && greeter != null) {
			this.greeters.remove(greeter);
		}
	}

	public List<String> getGreetingMessages() {
		List<String> messages = new ArrayList<String>();
		if (this.greeters != null) {
			for (Greeter greeter : this.greeters) {
				messages.add(greeter.greet());
			}
		}
		return messages;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<String> messages = this.getGreetingMessages();
		String html = "";
		for (String message : messages) {
			html += "<h1>" + message + "</h1>";
		}
		if (html.isEmpty())
			resp.getWriter().write("No message for you!");
		else
			resp.getWriter().write(html);
	}
}
