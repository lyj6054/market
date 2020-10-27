package market;
//backup
import java.util.ArrayList;

class MarketDAO extends DBConn{
	
	
	/**
	 *	join 
	 */
	public boolean join(MemberVO vo) {
		boolean result = false;
		
		try {
			String sql = "insert into market_member values(?,?,?,?,?,?,sysdate)";
			getPreparedStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPass());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getAddr());
			pstmt.setString(5, vo.getPhone());
			pstmt.setString(6, vo.getEmail());
			int count = pstmt.executeUpdate();
			if(count != 0) result = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 *  insert
	 */
	public boolean insert(ProductVO vo) {
		boolean result = false;
		try {
			String sql="insert into product values(SEQ_PID.NEXTVAL,?,?,?,?,?,?,?,?,sysdate)";
			getPreparedStatement(sql);
//			pstmt.setString(1, vo.getMid());
			pstmt.setString(2, vo.getPname());
			pstmt.setInt(3, vo.getPrice());
			pstmt.setString(4, vo.getPphone());
			pstmt.setString(5, vo.getState());
			pstmt.setString(6, vo.getMethod());
			pstmt.setString(7, vo.getArea());
			pstmt.setString(8, vo.getExplain());
			
			int count=pstmt.executeUpdate();
			if(count!=0) 	result =true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * select - 기림
	 */
	public ArrayList<ProductVO> select(){
		ArrayList<ProductVO> list = new ArrayList<ProductVO>();
		
		try {
			String sql = " select pid, pname, price, pphone, state, method, area, explain, pdate  " + 
						" from (select pid, pname, price, pphone, state, method, area, explain, pdate from product " + 
						"      order by pid desc)";
			getPreparedStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ProductVO vo = new ProductVO();
				vo.setPid(rs.getString(1));
				vo.setPname(rs.getString(2));
				vo.setPrice(rs.getInt(3));
//				vo.setAddress(rs.getString(4));
				vo.setExplain(rs.getString(5));
				vo.setPdate(rs.getString(6));
				vo.setPphone(rs.getString(4));
				vo.setState(rs.getString(5));
				vo.setMethod(rs.getString(6));
				vo.setArea(rs.getString(7));
				vo.setExplain(rs.getString(8));
				vo.setPdate(rs.getString(9));
					
				list.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * select(String name) - 기림
	 */
	public ProductVO select(String pname) {
		ProductVO vo = new ProductVO();
		
		try {
			String sql = "select pid, pname, price, pphone, state, method, area, explain, pdate from product where pname like '%' || ? || '%'";
			getPreparedStatement(sql);
			pstmt.setString(1,pname);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo.setPid(rs.getString(1));
				vo.setPname(rs.getString(2));
				vo.setPrice(rs.getInt(3));
				vo.setPphone(rs.getString(4));
				vo.setState(rs.getString(5));
				vo.setMethod(rs.getString(6));
				vo.setArea(rs.getString(7));
				vo.setExplain(rs.getString(8));
				vo.setPdate(rs.getString(9));
			}			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return vo;
	}
	
	
	
	/** 
	 * delete select -민석
	 */
	public boolean delselect(String pname) {
		boolean result = false;

		try {
			String sql = "select count(*) from product where pid=?";
			getPreparedStatement(sql);
			pstmt.setString(1, pname);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getInt(1) != 0) result = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/** 
	 * delete -민석
	 */
	public boolean delete(String pname) {
		boolean result = false;
			
		try {
			String sql = "delete from product where pid=?";
			getPreparedStatement(sql);
			pstmt.setString(1, pname);
			int count = pstmt.executeUpdate();
			if(count != 0) result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/** 하나의 상품 조회  -영화씨 select select1로 수정*/
	public ProductVO select1(String pid) {
		ProductVO pvo = new ProductVO();
		
		try {
			String sql = "select pname, price, pphone, state, method, area, explain from product where pid=?";
			getPreparedStatement(sql);
			
			pstmt.setString(1, pid);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				pvo.setPname(rs.getString(1));
				pvo.setPrice(rs.getInt(2));
				pvo.setPphone(rs.getString(3));
				pvo.setState(rs.getString(4));
				pvo.setMethod(rs.getString(5));
				pvo.setArea(rs.getString(6));
				pvo.setExplain(rs.getString(7));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pvo;
	}
	/** 물품번호 검색 */
	public int search(String pid) {
		int result =0;
		
		try {
			String sql = "select count(*) from product where pid=?";
			getPreparedStatement(sql);
			
			pstmt.setString(1, pid);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/** 물품정보 수정*/
	public boolean update_pr(BoardVO bvo) {
		boolean result = false;
		
		try {
			String sql = "update board set pname=?, price=?, phone=?, address=?, explain=? where pid=?";
			getPreparedStatement(sql);
			pstmt.setString(1, bvo.getPname());
			pstmt.setString(2, bvo.getAddress());
			pstmt.setString(3, bvo.getExplain());
			pstmt.setInt(4, bvo.getPrice());
			pstmt.setString(5, bvo.getPid());
			
			int count = pstmt.executeUpdate();
			if(count != 0) result = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 아이디 중복 확인
	 */
	public boolean idCheck(String mid) {
		boolean result = false;
		
		try {
			String sql = "select count(mid) from market_member where mid=?";
			getPreparedStatement(sql);
			pstmt.setString(1, mid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getInt(1) != 0) result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 이메일 중복 확인
	 */
	public boolean emailCheck(String memail) {
		boolean result = false;
		
		try {
			String sql ="select count(memail) from market_member where memail=?";
			getPreparedStatement(sql);
			pstmt.setString(1, memail);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getInt(1) !=0 ) result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 로그인 체크
	 */
	public boolean loginCheck(String mid, String mpass) {
		boolean result = false;
		
		try {
			String sql = "select count(*) from market_member where mid=? and mpass=?";
			getPreparedStatement(sql);
			pstmt.setString(1, mid);
			pstmt.setString(2, mpass);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getInt(1) != 0) result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
