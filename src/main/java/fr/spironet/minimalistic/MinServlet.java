package fr.spironet.minimalistic;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

import java.net.InetAddress;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class MinServlet extends HttpServlet {

	private static final long serialVersionUID = -1074473813454807497L;

	@Override
	public void service(HttpServletRequest req,
			HttpServletResponse res) throws IOException {

		res.setHeader("Content-Type", "text/plain");

		OutputStream os = res.getOutputStream();

		os.write("-- servletContextName: ".getBytes());
		os.write(req.getServletContext().getServletContextName().getBytes());
		os.write("\n".getBytes());

		// dumping received http headers
		os.write("-- HTTP Headers received:\n".getBytes());
		Enumeration<String> hns = req.getHeaderNames();
		while (hns.hasMoreElements()) {
			String currentHeader = hns.nextElement();

			if (currentHeader.equalsIgnoreCase("X-Set-Name")) {
				HttpSession sess = req.getSession();
				sess.setAttribute("name", req.getHeader(currentHeader));
			}

			Enumeration<String> currentValue = req.getHeaders(currentHeader);
			List<String> values = new ArrayList<String>();
			while (currentValue.hasMoreElements()) {
				values.add(currentValue.nextElement());
			}
			os.write(String.format("%s:\t%s\n", currentHeader,
					values.stream().collect(Collectors.joining(","))).getBytes());
		}

		if (req.getSession().getAttribute("name") != null) {
			os.write(String.format("Name in session: %s\n",
		  	req.getSession().getAttribute("name")).getBytes());
		}

		try {
			  InetAddress lhost = InetAddress.getLocalHost();
				os.write(String.format("Hostname: %s\n",
				lhost.getHostName()).getBytes());
		} catch (Exception e) {
			// who cares
		}



	}

}
