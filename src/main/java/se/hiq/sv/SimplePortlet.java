package se.hiq.sv;

import java.io.IOException;
import java.io.PrintWriter;

import javax.portlet.GenericPortlet;
import javax.portlet.PortletContext;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import senselogic.sitevision.api.Utils;
import senselogic.sitevision.api.log.LogUtil;

public class SimplePortlet extends GenericPortlet {
	@Override
	protected void doDispatch(RenderRequest req, RenderResponse resp) throws PortletException, IOException {
		Object attribute = req.getAttribute("sitevision.utils");
		if(attribute instanceof Utils) {
			LogUtil logUtil = ((Utils)attribute).getLogUtil();
			logUtil.debug("doDispatch " + req.getPortletMode());
		}
		
		if(req.getPortletMode().equals(PortletMode.VIEW)) {
			doView(req, resp);
		} else if(req.getPortletMode().toString().equals("config")) {
			doConfig(req, resp);
		} else {
			super.doDispatch(req, resp);
		}
	}
	
	protected void doConfig(RenderRequest request, RenderResponse response) throws IOException, PortletException {
		PrintWriter writer = response.getWriter();
		
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
