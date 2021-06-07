package member;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

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
 * Servlet implementation class Join
 */
@WebServlet("/member/join")
public class Join extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Join() {
        super();
        // TODO Auto-generated constructor stub
    }

	/** 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			MemberInfo memberJoinInfo = new MemberInfo(request, LocalDateTime.now());
			
			MemberService ms = new MemberService();
			boolean isJoin = ms.join(memberJoinInfo);
			if(isJoin) {
				// 회원가입이 성공적으로 됬다면
				response.setStatus(201);
			} else {
				// 회원가입이 되지 않았다면(아이디가 중복)
				response.setStatus(400);
			}
		} catch(EmptyMemberInfoException | OverflowMeberInfoException e) {
			response.setStatus(400);
		} catch(SQLException e) {
			// 서버 문제
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


