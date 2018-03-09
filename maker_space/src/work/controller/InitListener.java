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
      System.out.println("\n## 서버구동 어플리케이션 초기화 수행 컨텍스트 경로명 : " + CONTEXT_PATH);
      
      StringBuilder information = new StringBuilder();
      information.append("\n=========================================");
      information.append("\n## 서버구동 어플리케이션 로딩 초기화 수행");
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
      System.out.println("\n## 서버중지 어플리케이션 자원해재 수행 컨텍스트 경로명 : " + CONTEXT_PATH);
      
      StringBuilder information = new StringBuilder();
      information.append("\n=========================================");
      information.append("\n## 서버중지 어플리케이션 자원해제 수행");
      information.append("\n" + CONTEXT_PATH);
      information.append("\n" + new Date());
      information.append("\n=========================================");
      information.append("\n");
      application.log(information.toString());
   }

}