package market;
//backup
import java.util.ArrayList;

public class MarketMgmSystem {
	//Field
	MarketDAO dao ;
	
	//Constructor
	public MarketMgmSystem() {
		dao = new MarketDAO();
	}
	
	public boolean join(MemberVO vo) {
		return dao.join(vo);
	}
	
	public boolean register(ProductVO vo) {
		return dao.insert(vo);
	}
	
	/**전체 리스트 - 기림**/
	public ArrayList <ProductVO> list() {
		return dao.select();
	}
	
	/**삭제 리스트 - 민석**/
	public ArrayList <ProductVO> delete_list(MemberVO mvo) {
		return dao.delete_select(mvo);
	}

	/**검색 - 기림**/
	public ProductVO search(String pname) {
		return dao.select(pname);
	}
	
	/** 물품 정보 삭제 검색 - 민석 **/
	public boolean delselect(String pname) {
		return dao.delselect(pname);
	}
	
	/** 물품 정보 삭제 - 민석 **/
	public boolean delete(String pname) {
		return dao.delete(pname);
	}
	
	/** 물품 정보 출력  -영화씨 select select1로 수정**/
	public ProductVO selectProduct(String pid) {
		return dao.select1(pid);
	}
	
	/** 물품번호 검색 -영화씨*/
	public int SearchPid(String pid) {
		return dao.search(pid);
	}
	
	/** 물품정보 수정 -영화*/
	public boolean update_pr(ProductVO pvo) {
		return dao.update_pr(pvo);
	}
	
	/** 아이디 중복체크 -민석 */
	public boolean idCheck(String mid) {
		return dao.idCheck(mid);
	}
	
	/** 이메일 중복체크 -민석*/
	public boolean emailCheck(String memail) {
		return dao.emailCheck(memail);
	}
	
	/** 로그인 체크 -민석 */
	public boolean loginCheck(String mid, String mpass) {
		return dao.loginCheck(mid, mpass);
	}
	
	/** 현재 로그인 아이디 */
	public boolean loginIng(String mid) {
		return dao.loginIng(mid);
	}
	
	/** 회원 정보 찾기 */
	public int searchMember(String mid) {
		return dao.searchMember(mid);
	}
	
	/** 회원 조회 */
	public MemberVO selectMember(String mpass) {
		return dao.selectMember(mpass);
	}
	
	public boolean update_info(MemberVO mvo) {
		return dao.update_info(mvo);
	}
} // class
