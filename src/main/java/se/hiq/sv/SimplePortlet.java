package se.hiq.sv;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletConfig;
import javax.portlet.PortletContext;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import senselogic.sitevision.api.Utils;
import senselogic.sitevision.api.log.LogUtil;

public class SimplePortlet extends GenericPortlet {
	private LogUtil log;
	
	Logger logger = LoggerFactory.getLogger(SimplePortlet.class);

	@Override
	protected void doDispatch(RenderRequest req, RenderResponse resp) throws PortletException, IOException {
		if(log == null) {
			Object attribute = req.getAttribute("sitevision.utils");
			if(attribute instanceof Utils) {
				LogUtil logUtil = ((Utils)attribute).getLogUtil();
				log = logUtil;
				log("doDispatch first " + req.getPortletMode());
			}
		} else {
			log("doDispatch " + req.getPortletMode());
		}
		
		log(req.getPortletMode().getClass().getName());
		
		if(req.getPortletMode().equals(PortletMode.VIEW)) {
			doView(req, resp);
		} else if(req.getPortletMode().toString().equals("config")) {
			
			try {
				throw new RuntimeException();
			} catch(Throwable e) {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				PrintStream ps = new PrintStream(baos);
				e.printStackTrace(ps);
				log(baos.toString());
			}
			doConfig(req, resp);
		} else {
			super.doDispatch(req, resp);
		}
	}

	private void log(String s) {
		if(log != null) {
			log.debug(s);
		}
		
		logger.debug(s);
		
		System.out.println("§§§ " + s);
	}
	
	@Override
	public void destroy() {
		super.destroy();
		
		log("§§§ Delaying Destroy");
		
		try {
			Thread.sleep(3000);
			log("Done");
		} catch (InterruptedException e) {
			log(e.getMessage());
		}
		
	}
	
	@Override
	public void init(PortletConfig config) throws PortletException {
		super.init(config);
		
		log("Init portlet " + config.getPortletName());
		
		try {
			Thread.sleep(3000);
			log("Done");
		} catch (InterruptedException e) {
			log(e.getMessage());
		}
	}
	
	@Override
	public void processAction(ActionRequest request, ActionResponse response) throws PortletException, IOException {
		log("processAction " + request.getServerPort() + "|" + request.getContextPath());
	}
	
	protected void doConfig(RenderRequest request, RenderResponse response) throws IOException, PortletException {
        response.setContentType("text/html");
        request.setAttribute("foo","bar");
        String jsp = "/WEB-INF/jsp/portal.jsp";               
        PortletContext ctx = getPortletContext();
        PortletRequestDispatcher dispatcher = ctx.getRequestDispatcher(jsp);            
        dispatcher.include(request, response);
	}
	
	@Override
	protected void doView(RenderRequest request, RenderResponse response) throws PortletException, IOException {
		PrintWriter writer = response.getWriter();
		
		Object attribute = request.getAttribute("sitevision.utils");
		writer.println("<p>Simple Portlet</p>");
		if(attribute instanceof Utils) {
			writer.println("<h1>YEAH</h1>");
			LogUtil logUtil = ((Utils)attribute).getLogUtil();
			logUtil.debug("Wooh");
		} 
	}
}
