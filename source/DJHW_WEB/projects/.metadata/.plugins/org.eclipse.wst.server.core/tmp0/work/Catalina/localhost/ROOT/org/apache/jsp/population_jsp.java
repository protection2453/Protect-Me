/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.43
 * Generated at: 2019-08-22 06:47:56 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.io.PrintWriter;
import info.getPopulation;
import java.util.*;

public final class population_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("java.util");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("java.io.PrintWriter");
    _jspx_imports_classes.add("info.getPopulation");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("<meta charset=\"EUC-KR\">\n");
      out.write("<meta name=\"viewport\" content=\"width=device-width\">\n");
      out.write("<link rel=\"stylesheet\" href=\"css/bootstrap.css\">\n");
      out.write("<link rel=\"stylesheet\" href=\"css/custom.css\">\n");
      out.write("<title>나를지켜줘</title>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("\t");

	//==========성범죄인구수==========
		//세션이 유지되는지 검사
		String userID = null;
		if(session.getAttribute("userID") != null) {
			userID = (String) session.getAttribute("userID");
		}
	
      out.write("\n");
      out.write("\t<nav class=\"navbar navbar-default\">\n");
      out.write("\t\t<div class=\"navbar-header\">\n");
      out.write("\t\t\t<button type=\"button\" class=\"navbar-toggle collapsed\"\n");
      out.write("\t\t\t\tdata-toggle=\"collapse\" data-target=\"#bs-example-navbar-collapse-1\"\n");
      out.write("\t\t\t\taria-expanded=\"false\">\n");
      out.write("\t\t\t\t<span class=\"icon-bar\"></span>\n");
      out.write("\t\t\t\t<span class=\"icon-bar\"></span>\n");
      out.write("\t\t\t\t<span class=\"icon-bar\"></span>\n");
      out.write("\t\t\t</button>\n");
      out.write("\t\t\t<a class=\"navbar-brand\" href=\"main.jsp\">나를지켜줘</a>\n");
      out.write("\t\t</div>\n");
      out.write("\t\t<div class=\"collapse navbar-collapse\" id=\"bs-example-navbar-collapse-1\">\n");
      out.write("\t\t\t<ul class=\"nav navbar-nav\">\n");
      out.write("\t\t\t\t<li><a href=\"clinic.jsp\">성범죄 상담소</a></li>\n");
      out.write("\t\t\t\t<li class=\"active\"><a href=\"population.jsp\">성범죄 인구수</a></li>\n");
      out.write("\t\t\t\t<li><a href=\"bbs.jsp\">온라인 상담</a></li>\n");
      out.write("\t\t\t\t<li><a href=\"question.jsp\">자주묻는질문</a></li>\n");
      out.write("\t\t\t\t<li><a href=\"check.jsp\">자기점검코너</a></li>\n");
      out.write("\t\t\t\t<li><a href=\"law.jsp\">법률개정안내</a></li>\t\t\t\n");
      out.write("\t\t\t\t");

				if("admin".equals(userID)) {
				
      out.write("\n");
      out.write("\t\t\t\t<li><a href=\"admin.jsp\">관리자페이지</a></li>\t\n");
      out.write("\t\t\t\t");

					}
				
      out.write("\t\t\t\t\t\t\t\n");
      out.write("\t\t\t</ul>\n");
      out.write("\t\t\t");

				//세션이 없다면 -> 로그인, 회원가입 가능
				if(userID == null) {
			
      out.write("\n");
      out.write("\t\t\t<ul class=\"nav navbar-nav navbar-right\">\n");
      out.write("\t\t\t\t<li class=\"dropdown\">\n");
      out.write("\t\t\t\t\t<a href=\"#\" class=\"dropdown-toggle\"\n");
      out.write("\t\t\t\t\t\tdata-toggle=\"dropdown\" role=\"button\" aria-haspopup=\"true\"\n");
      out.write("\t\t\t\t\t\taria-expanded=\"false\">접속하기<span class=\"caret\"></span></a>\n");
      out.write("\t\t\t\t\t<ul class=\"dropdown-menu\">\n");
      out.write("\t\t\t\t\t\t<li><a href=\"login.jsp\">로그인</a></li>\n");
      out.write("\t\t\t\t\t\t<li><a href=\"join.jsp\">회원가입</a></li>\n");
      out.write("\t\t\t\t\t</ul>\n");
      out.write("\t\t\t\t</li>\n");
      out.write("\t\t\t</ul>\n");
      out.write("\t\t\t");
		
				//세션이 있다면 -> 로그아웃, 회원탈퇴 가능
				} else {
			
      out.write("\n");
      out.write("\t\t\t<ul class=\"nav navbar-nav navbar-right\">\n");
      out.write("\t\t\t\t<li class=\"dropdown\">\n");
      out.write("\t\t\t\t\t<a href=\"#\" class=\"dropdown-toggle\"\n");
      out.write("\t\t\t\t\t\tdata-toggle=\"dropdown\" role=\"button\" aria-haspopup=\"true\"\n");
      out.write("\t\t\t\t\t\taria-expanded=\"false\">회원관리<span class=\"caret\"></span></a>\n");
      out.write("\t\t\t\t\t<ul class=\"dropdown-menu\">\n");
      out.write("\t\t\t\t\t\t<li><a href=\"logoutAction.jsp\">로그아웃</a></li>\n");
      out.write("\t\t\t\t\t\t<li><a onclick=\"return confirm('정말로 탈퇴하시겠습니까?')\" href=\"removeAction.jsp\">회원탈퇴</a></li>\n");
      out.write("\t\t\t\t\t</ul>\n");
      out.write("\t\t\t\t</li>\n");
      out.write("\t\t\t</ul>\t\t\t\n");
      out.write("\t\t\t");

				}
			
      out.write("\n");
      out.write("\t\t</div>\n");
      out.write("\t</nav>\n");
      out.write("\t<div class=\"container\">\n");
      out.write("\t\t<div class=\"jumbotron\">\n");
      out.write("\t\t\t<div class=\"container\">\n");
      out.write("\t\t\t\t<h1>성범죄 인구수</h1>\n");
      out.write("\t\t\t\t<p>지역별 성범죄 인구수 <br />(제공 : 공공데이터포털)</p>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t</div>\n");
      out.write("\t</div>\n");
      out.write("\t<div class=\"container\">\n");
      out.write("\t\t");
 getPopulation gp = new getPopulation(); //공공데이터포털 XML에서 가공된 데이터 객체
      out.write('\n');
      out.write('	');
      out.write('	');
 gp.getTagValue(); 
      out.write("\n");
      out.write("\t\t<div class=\"row\">\n");
      out.write("\t\t\t<table class=\"table table-striped\" style=\"border: none;\">\n");
      out.write("\t\t\t\t<thead>\n");
      out.write("\t\t\t\t\t<tr>\n");
      out.write("\t\t\t\t\t\t<th>도시명</th>\n");
      out.write("\t\t\t\t\t\t<th>인구수</th>\n");
      out.write("\t\t\t\t\t\t<th>도시명</th>\n");
      out.write("\t\t\t\t\t\t<th>인구수</th>\n");
      out.write("\t\t\t\t\t</tr>\n");
      out.write("\t\t\t\t</thead>\n");
      out.write("\t\t\t\t<tbody>\n");
      out.write("\t\t\t\t<tr>\n");
      out.write("\t\t\t\t\t<td>");
      out.print(gp.cityname1);
      out.write("</td>\n");
      out.write("\t\t\t\t\t<td>");
      out.print(gp.citycnt1);
      out.write("</td>\n");
      out.write("\t\t\t\t\t<td>");
      out.print(gp.cityname10);
      out.write("</td>\n");
      out.write("\t\t\t\t\t<td>");
      out.print(gp.citycnt10);
      out.write("</td>\n");
      out.write("\t\t\t\t</tr>\n");
      out.write("\t\t\t\t<tr>\n");
      out.write("\t\t\t\t\t<td>");
      out.print(gp.cityname2);
      out.write("</td>\n");
      out.write("\t\t\t\t\t<td>");
      out.print(gp.citycnt2);
      out.write("</td>\n");
      out.write("\t\t\t\t\t<td>");
      out.print(gp.cityname11);
      out.write("</td>\n");
      out.write("\t\t\t\t\t<td>");
      out.print(gp.citycnt11);
      out.write("</td>\n");
      out.write("\t\t\t\t</tr>\n");
      out.write("\t\t\t\t<tr>\n");
      out.write("\t\t\t\t\t<td>");
      out.print(gp.cityname3);
      out.write("</td>\n");
      out.write("\t\t\t\t\t<td>");
      out.print(gp.citycnt3);
      out.write("</td>\n");
      out.write("\t\t\t\t\t<td>");
      out.print(gp.cityname12);
      out.write("</td>\n");
      out.write("\t\t\t\t\t<td>");
      out.print(gp.citycnt12);
      out.write("</td>\n");
      out.write("\t\t\t\t</tr>\n");
      out.write("\t\t\t\t<tr>\n");
      out.write("\t\t\t\t\t<td>");
      out.print(gp.cityname4);
      out.write("</td>\n");
      out.write("\t\t\t\t\t<td>");
      out.print(gp.citycnt4);
      out.write("</td>\n");
      out.write("\t\t\t\t\t<td>");
      out.print(gp.cityname13);
      out.write("</td>\n");
      out.write("\t\t\t\t\t<td>");
      out.print(gp.citycnt13);
      out.write("</td>\n");
      out.write("\t\t\t\t</tr>\n");
      out.write("\t\t\t\t<tr>\n");
      out.write("\t\t\t\t\t<td>");
      out.print(gp.cityname5);
      out.write("</td>\n");
      out.write("\t\t\t\t\t<td>");
      out.print(gp.citycnt5);
      out.write("</td>\n");
      out.write("\t\t\t\t\t<td>");
      out.print(gp.cityname14);
      out.write("</td>\n");
      out.write("\t\t\t\t\t<td>");
      out.print(gp.citycnt14);
      out.write("</td>\n");
      out.write("\t\t\t\t</tr>\n");
      out.write("\t\t\t\t<tr>\n");
      out.write("\t\t\t\t\t<td>");
      out.print(gp.cityname6);
      out.write("</td>\n");
      out.write("\t\t\t\t\t<td>");
      out.print(gp.citycnt6);
      out.write("</td>\n");
      out.write("\t\t\t\t\t<td>");
      out.print(gp.cityname15);
      out.write("</td>\n");
      out.write("\t\t\t\t\t<td>");
      out.print(gp.citycnt15);
      out.write("</td>\n");
      out.write("\t\t\t\t</tr>\n");
      out.write("\t\t\t\t<tr>\n");
      out.write("\t\t\t\t\t<td>");
      out.print(gp.cityname7);
      out.write("</td>\n");
      out.write("\t\t\t\t\t<td>");
      out.print(gp.citycnt7);
      out.write("</td>\n");
      out.write("\t\t\t\t\t<td>");
      out.print(gp.cityname16);
      out.write("</td>\n");
      out.write("\t\t\t\t\t<td>");
      out.print(gp.citycnt16);
      out.write("</td>\n");
      out.write("\t\t\t\t</tr>\n");
      out.write("\t\t\t\t<tr>\n");
      out.write("\t\t\t\t\t<td>");
      out.print(gp.cityname8);
      out.write("</td>\n");
      out.write("\t\t\t\t\t<td>");
      out.print(gp.citycnt8);
      out.write("</td>\n");
      out.write("\t\t\t\t\t<td>");
      out.print(gp.cityname17);
      out.write("</td>\n");
      out.write("\t\t\t\t\t<td>");
      out.print(gp.citycnt17);
      out.write("</td>\n");
      out.write("\t\t\t\t</tr>\n");
      out.write("\t\t\t\t<tr>\n");
      out.write("\t\t\t\t\t<td>");
      out.print(gp.cityname9);
      out.write("</td>\n");
      out.write("\t\t\t\t\t<td>");
      out.print(gp.citycnt9);
      out.write("</td>\n");
      out.write("\t\t\t\t\t<td>");
      out.print(gp.cityname18);
      out.write("</td>\n");
      out.write("\t\t\t\t\t<td>");
      out.print(gp.citycnt18);
      out.write("</td>\n");
      out.write("\t\t\t\t</tr>\n");
      out.write("\t\t\t\t</tbody>\n");
      out.write("\t\t\t</table>\n");
      out.write("\t\t</div>\n");
      out.write("\t</div>\n");
      out.write("\t<script src=\"https://code.jquery.com/jquery-3.1.1.min.js\"></script>\n");
      out.write("\t<script src=\"js/bootstrap.js\"></script>\n");
      out.write("</body>\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
