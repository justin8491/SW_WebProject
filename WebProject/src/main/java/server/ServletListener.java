package server;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import board.BoardDAO;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class ServletListener
 *
 */
public class ServletListener implements ServletContextListener {



    public ServletListener() {
    }

    public void contextInitialized(ServletContextEvent sce)  {
		Context context;
		try {
			context = new InitialContext();
			Context envContext = (Context) context.lookup("java:/comp/env");
			DataSource dataFactory = (DataSource) envContext.lookup("jdbc/pro05DB");
			BoardDAO.setDataFactory(dataFactory);
//			MemberDAO
		} catch (NamingException e) {
			e.printStackTrace();
		}
    }

    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }
	
}
