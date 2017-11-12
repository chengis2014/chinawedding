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

	public int addUserinfo(PageData pageData) {
	 logger.info("增加Userinfo");
	 int iFlag =0; 
	 try { 
		iFlag = (int) daoSupport.insert("UserinfoMapper.addUserinfo",pageData);
	 }catch (Exception e){ 
	 e.printStackTrace(); 
	 iFlag=0; 
	}
	 return iFlag; 
	}
	public int delUserinfoByFid(String fid) {
	 logger.info("删除Userinfo");
	 int iFlag =0; 
	 try { 
		iFlag = (int) daoSupport.delete("UserinfoMapper.deleteUserinfoByFid",fid);
	 }catch (Exception e){ 
	 e.printStackTrace(); 
	 iFlag=0; 
	}
	 return iFlag; 
	}
	public int updateUserinfo(PageData pageData) {
	 logger.info("修改Userinfo");
	 int iFlag =0; 
	 try { 
		iFlag = (int) daoSupport.update("UserinfoMapper.updateUserinfo",pageData);
	 }catch (Exception e){ 
	 e.printStackTrace(); 
	 iFlag=0; 
	}
	 return iFlag; 
	}
	public Userinfo getUserinfoforId(String fid) {
	 logger.info("通过ID查询Userinfo");
	Userinfo	userinfo=null;
	 try { 
		userinfo = (Userinfo) daoSupport.findForObject("UserinfoMapper.getUserinfoForId",fid);
	 }catch (Exception e){ 
	 e.printStackTrace(); 
	 userinfo=null; 
	}
	 return userinfo; 
	}
	public List<Userinfo> getAllUserinfo(PageData pageData) {
	 logger.info("分页查询Userinfo");
	List<Userinfo>	userinfoList=null;
	 try { 
		userinfoList = (List<Userinfo>) daoSupport.findForList("UserinfoMapper.getAllUserinfo",pageData);
	 }catch (Exception e){ 
	 e.printStackTrace(); 
	 userinfoList=null; 
	}
	 return userinfoList; 
	}
	public List<Userinfo> selectUserinfoList() {
	 logger.info("查询所有Userinfo");
	List<Userinfo>	userinfoList=null;
	 try { 
		userinfoList = (List<Userinfo>) daoSupport.findForList("UserinfoMapper.selectUserinfoList",null);
	 }catch (Exception e){ 
	 e.printStackTrace(); 
	 userinfoList=null; 
	}
	 return userinfoList; 
	}
}
