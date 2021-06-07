package member;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberService;
import dto.MemberInfo;
import exception.EmptyMemberInfoException;
import exception.OverflowMeberInfoException;

/**
 * Servlet implementation class Login
 */
@WebServlet("/member/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// request에서 회원 정보를 꺼내서 객체로 만듬
			MemberInfo memberLoginInfo = new MemberInfo(request);
			
			MemberService ms = new MemberService();
			// 회원 정보로 로그인 시도
			boolean isLogin = ms.login(memberLoginInfo);
			if(isLogin) {
				// 로그인 성공
				response.setStatus(200);
			} else {
				// 로그인 실패
				response.setStatus(404);
			}
		} catch(EmptyMemberInfoException | OverflowMeberInfoException e) {
			response.setStatus(400);
		} catch(SQLException e) {
			response.setStatus(500);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
