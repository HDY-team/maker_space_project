package work.controller;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class InitListener implements ServletContextListener {
   public ServletContext application;
   
   /* (non-Javadoc)
    * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
    */
   @Override
   public void contextInitialized(ServletContextEvent event) {
   
      application = event.getServletContext();
      String CONTEXT_PATH = application.getContextPath();
      application.setAttribute("CONTEXT_PATH", CONTEXT_PATH);
      System.out.println("\n## �������� ���ø����̼� �ʱ�ȭ ���� ���ؽ�Ʈ ��θ� : " + CONTEXT_PATH);
      
      StringBuilder information = new StringBuilder();
      information.append("\n=========================================");
      information.append("\n## �������� ���ø����̼� �ε� �ʱ�ȭ ����");
      information.append("\n" + CONTEXT_PATH);
      information.append("\n" + new Date());
      information.append("\n=========================================");
      information.append("\n");
      application.log(information.toString());
   }
   
   /* (non-Javadoc)
    * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
    */
   @Override
   public void contextDestroyed(ServletContextEvent event) {
      application = event.getServletContext();
      String CONTEXT_PATH = application.getContextPath();
      if (application.getAttribute("CONTEXT_PATH") != null) {
         application.removeAttribute("CONTEXT_PATH");
      }
      System.out.println("\n## �������� ���ø����̼� �ڿ����� ���� ���ؽ�Ʈ ��θ� : " + CONTEXT_PATH);
      
      StringBuilder information = new StringBuilder();
      information.append("\n=========================================");
      information.append("\n## �������� ���ø����̼� �ڿ����� ����");
      information.append("\n" + CONTEXT_PATH);
      information.append("\n" + new Date());
      information.append("\n=========================================");
      information.append("\n");
      application.log(information.toString());
   }

}