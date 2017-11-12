package com.hnqj.services;

import com.hnqj.core.PageData;

import com.hnqj.model.Userinfo;

import javax.annotation.Resource;
import com.hnqj.dao.DaoSupportImpl;
import org.springframework.stereotype.Service;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.util.List;
@Service("userinfo")
public class UserinfoServices {

protected final Log logger = LogFactory.getLog(getClass());

	@Resource(name = "daoSupportImpl")

	private DaoSupportImpl daoSupport;

	/**
	 * 添加代理用户信息
	 * @param pageData
	 * @return
	 */
	public int addUser(PageData pageData){
		int iFlag =0;
		try {
			iFlag = (int)daoSupport.insert("UserinfoMapper.addUser",pageData);
		}catch (Exception e){
			e.printStackTrace();
			iFlag=0;
		}
		return iFlag;
	}

	/**
	 * 更新代理用户信息
	 * @param pageData
	 * @return
	 */
	public int updateUser(PageData pageData){
		int iFlag =0;
		try {
			iFlag = (int)daoSupport.update("UserinfoMapper.updateUser",pageData);
		}catch (Exception e){
			e.printStackTrace();
			iFlag=0;
		}
		return iFlag;
	}

	/**
	 * 删除指定的代理用户信息
	 * @param fid
	 * @return
	 */
	public int deleteUserByFid(String fid){
		int iFlag =0;
		try {
			iFlag = (int)daoSupport.delete("UserinfoMapper.deleteUserByFid",fid);
		}catch (Exception e){
			e.printStackTrace();
			iFlag=0;
		}
		return iFlag;
	}

	/**
	 * 根据产品ID查询用户信息
	 * @param fid
	 * @return
	 */
	public Userinfo selectUserByFid(String fid){
		Userinfo user =null;
		try {
			user = (Userinfo) daoSupport.findForObject("UserinfoMapper.selectUserByFid",fid);
		}catch (Exception e){
			e.printStackTrace();
			user =null;
		}
		return user;
	}

	/**
	 * 查询所有的用户信息
	 * @return
	 */
	public List<Userinfo> selectUserList(){
		List<Userinfo> userList =null;
		try {
			userList = (List<Userinfo>) daoSupport.findForList("UserinfoMapper.selectUserList",null);
		}catch (Exception e){
			e.printStackTrace();
			userList =null;
		}
		return userList;
	}

	public Userinfo getUser(PageData pageData) {
		Userinfo user = null;
		try {
			user = (Userinfo)  daoSupport.findForObject("UserinfoMapper.selectUser",pageData);
		}catch (Exception e){
			e.printStackTrace();
			user =null;
		}
		return user;
	}
	//根据部门获取用户
	public List<Userinfo> getAllUserByUid(String unitId) {
		List<Userinfo> userList =null;
		try {
			userList = (List<Userinfo>) daoSupport.findForList("UserinfoMapper.getUnitUser",unitId);
		}catch (Exception e){
			e.printStackTrace();
			userList =null;
		}
		return userList;
	}
	//获取所有用户数据
	public List<Userinfo> getAllUser(PageData pageData) {
		List<Userinfo> userList =null;
		try {
			userList = (List<Userinfo>) daoSupport.findForList("UserinfoMapper.getAllUser",pageData);
		}catch (Exception e){
			e.printStackTrace();
			userList =null;
		}
		return userList;
	}
}
