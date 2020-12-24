package com.spring.springform;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HomeController {
	
	
	
	@RequestMapping(value = "inputForm.me")
	public String inputForm() {
		//뷰로 연결하는 메소드

		return "inputForm";
	}
	
	@RequestMapping(value="inputProcess.me")
	public String inputProcess(EmpVO vo) {
		Connection con =null;
		PreparedStatement pstmt=null;
		
		try {
			String driver ="oracle.jdbc.driver.OracleDriver";
			String url ="jdbc:oracle:thin:@127.0.0.1:1521:orcl";
			String sql ="insert into emp_copy values " +"(?,?,?,?, sysdate,?,?,?)";
			
			Class.forName(driver);
			con = DriverManager.getConnection(url,"scott", "123456");
			pstmt= con.prepareStatement(sql);
			pstmt.setInt(1,  vo.getEmpno());
			pstmt.setString(2, vo.getEname());
			pstmt.setString(3, vo.getJob());
			pstmt.setInt(4,  vo.getMgr());
			pstmt.setDouble(5, vo.getSal());
			pstmt.setDouble(6, vo.getComm());
			pstmt.setInt(7, vo.getDeptno());
			pstmt.executeUpdate();
		}
		catch(Exception e) {
		}
		return "index";
	}//inputProcess
	
	@RequestMapping(value ="selectProcess.me")
	public String selectProcess(Model model) {
		Connection con =null;
		PreparedStatement pstmt=null;
		ResultSet rs= null;
		ArrayList<EmpVO>list = new ArrayList<EmpVO>();
		
		try {
			String driver ="oracle.jdbc.driver.OracleDriver";
			String url ="jdbc:oracle:thin:@127.0.0.1:1521:orcl";
			
			Class.forName(driver);
			con= DriverManager.getConnection(url,"scott", "123456" );
			pstmt= con.prepareStatement("select *from emp_copy order by ename asc");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				EmpVO empvo = new EmpVO();
				empvo.setEmpno(rs.getInt("empno"));
				empvo.setEname(rs.getString("ename"));
				empvo.setJob(rs.getString("job"));
				empvo.setMgr(rs.getInt("mgr"));
				empvo.setHiredate(rs.getDate("hiredate"));
				empvo.setSal(rs.getDouble("sal"));
				empvo.setComm(rs.getDouble("comm"));
				empvo.setDeptno(rs.getInt("deptno"));
				list.add(empvo);
			}//while
			model.addAttribute("list", list);
		}
		catch(Exception e) {
			
		}
		return "list";
		
		
	}//selectProcess
	
	
	@RequestMapping (value="selectDept.me")
	public String selectDept(Model model,
			@RequestParam(value="deptno", required=false, defaultValue="1")int deptno) {
		Connection con =null;
		PreparedStatement pstmt=null;
		ResultSet rs= null;
		DeptVO deptvo = null;
		
		try {
			String driver ="oracle.jdbc.driver.OracleDriver";
			String url ="jdbc:oracle:thin:@127.0.0.1:1521:orcl";
			
			Class.forName(driver);
			con= DriverManager.getConnection(url,"scott", "123456" );
			pstmt= con.prepareStatement(" select * from dept_copy where deptno=? ");
			pstmt.setInt(1, deptno);
			rs = pstmt.executeQuery();
			rs.next();
			deptvo = new DeptVO();
			deptvo.setDeptno(rs.getInt("deptno"));
			deptvo.setDname(rs.getString("dname"));
			deptvo.setLoc(rs.getString("loc"));
			
			model.addAttribute("deptvo",deptvo);
		}
		catch(Exception e) {
			
		}
		return "deptview";
	}//selectDept
	
	@RequestMapping(value="selectEmpDempt.me")
	public String selectEmpDempt(Model model) {
		Connection con =null;
		PreparedStatement pstmt=null;
		ResultSet rs= null;
		
		ArrayList<EmpDeptVO> edlist = new ArrayList<EmpDeptVO>();
		
		try {
			String driver ="oracle.jdbc.driver.OracleDriver";
			String url ="jdbc:oracle:thin:@127.0.0.1:1521:orcl";
			
			Class.forName(driver);
			con= DriverManager.getConnection(url,"scott", "123456" );
			pstmt= con.prepareStatement("select e.empno, e.ename, e.job ,e.deptno,d.dname, d.loc from emp_copy e join dept_copy d on e.deptno =d.deptno"); 
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				EmpDeptVO edvo = new EmpDeptVO();
				edvo.setEmpno(rs.getInt("empno"));
				edvo.setEname(rs.getString("ename"));
				edvo.setJob(rs.getString("job"));
				edvo.setDeptno(rs.getInt("deptno"));
				edvo.setDname(rs.getString("dname"));
				edvo.setLoc(rs.getString("loc"));
				
				edlist.add(edvo);
			}//while
			model.addAttribute("edlist", edlist);
		}
		catch(Exception e) {
			
		}
		return "empdept_list";
	}
	
	
}
