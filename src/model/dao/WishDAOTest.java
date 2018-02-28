package model.dao;

import java.util.ArrayList;

import org.junit.Test;

import model.vo.WishVO;

public class WishDAOTest {

	@Test
	public void test() {
		WishDAO dao = new WishDAO();
		WishVO vo = new WishVO();
		
		System.out.println("뭐냐");
		/*vo.setCategory("게임");
		
		vo.setTitle("몬스터헌터");
		vo.setContent("아 제발 ps4 pro 가격 내려라ㅡㅡ");
		vo.setReason("Twitch까지 보고 있음 ㅡㅡ 정말 사고 싶다.");		
		//dao.insert(vo);
		vo.setId(14);
		vo.setReason("이유 바뀜");*/
		//dao.delete(15);
/*		ArrayList<WishVO> list = dao.loadAll();
		System.out.println(list.size());
		for(WishVO index : list)
			System.out.println(index.toString());*/
		/*
		vo = dao.loadOne(9);		
		System.out.println(vo.toString());*/
		
		ArrayList<Integer> cnt = dao.cnt();
	}

}
