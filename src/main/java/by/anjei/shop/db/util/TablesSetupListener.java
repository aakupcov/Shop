package by.anjei.shop.db.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;

public class TablesSetupListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
        ApplicationContext context = new ClassPathXmlApplicationContext("SpringConfig.xml");
        TablesSetupDaoImpl tablesSetupDao = (TablesSetupDaoImpl) context.getBean("tablesSetupDaoImpl");
        tablesSetupDao.createDefaultTables();
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!Tables are created.!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("!!!!!!!!!!!!!!Context is destroyed.!!!!!!!!!!!!!!!!!");
	}
}
