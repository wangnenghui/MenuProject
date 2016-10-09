package cn.wnh.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import cn.wnh.dao.IMemberDAO;
import cn.wnh.dao.impl.MemberDAOImpl;
import cn.wnh.exception.MemberInvalidatePasswordException;
import cn.wnh.exception.MemberLoginException;
import cn.wnh.exception.MemberNoExistsException;
import cn.wnh.service.IMemberService;
import cn.wnh.util.factory.DAOFactory;
import cn.wnh.vo.Member;

public class MemberServiceImpl implements IMemberService {

	@Override
	public Map<String, Object> login(String mid, String password) throws MemberLoginException {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean flag = false ;	// 保存的是用户的登录信息
		IMemberDAO memberDAO = DAOFactory.getInstance(MemberDAOImpl.class);
		Member member = null;
		try {
			member = memberDAO.findById(mid); // 根据用户名查询出用户的完整数据
			if (member == null) {	// 用户不存在
				throw new MemberNoExistsException("用户不存在");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MemberNoExistsException("用户不存在");
		}	// 后面的代码表示该用户名存在后进行的处理
		if (member.getPassword().equals(password)) {	// 密码是否相同
			flag = true ;	// 修改登录结果标记
		} else {
			throw new MemberInvalidatePasswordException("密码错误！");
		}
		map.put("flag", flag) ;
		map.put("member", member) ; 
		return map;
	}

}
