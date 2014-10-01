package se.hiq.sv;

import java.io.IOException;

import javax.portlet.GenericPortlet;
import javax.portlet.PortletConfig;
import javax.portlet.PortletContext;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

public class SimplePortlet extends GenericPortlet {
	@Override
	protected void doDispatch(RenderRequest req, RenderResponse resp) throws PortletException, IOException {
		
		if(req.getPortletMode().equals(PortletMode.VIEW)) {
			doView(req, resp);
		} else if(req.getPortletMode().toString().equals("config")) {
			doConfig(req, resp);
		} else {
			super.doDispatch(req, resp);
		}
	}

	private void log(String s) {
		System.out.println("§§§ " + s);
	}
	
	@Override
	public void destroy() {
		super.destroy();
		
		log("Destroy");
	}
	
	@Override
	public void init(PortletConfig config) throws PortletException {
		super.init(config);
		
		log("Init portlet " + config.getPortletName());
	}
	
	protected void doConfig(RenderRequest request, RenderResponse response) throws IOException, PortletException {
        response.setContentType("text/html");
        String jsp = "/WEB-INF/jsp/admin.jsp";               
        PortletContext ctx = getPortletContext();
        PortletRequestDispatcher dispatcher = ctx.getRequestDispatcher(jsp);            
        dispatcher.include(request, response);
	}
	
	@Override
	protected void doView(RenderRequest request, RenderResponse response) throws PortletException, IOException {
        response.setContentType("text/html");
        String jsp = "/WEB-INF/jsp/view.jsp";               
        PortletContext ctx = getPortletContext();
        PortletRequestDispatcher dispatcher = ctx.getRequestDispatcher(jsp);            
        dispatcher.include(request, response);
	}
}
