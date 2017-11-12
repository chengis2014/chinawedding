package com.hnqj.services;

import com.hnqj.core.PageData;

import com.hnqj.model.Roles;

import javax.annotation.Resource;
import com.hnqj.dao.DaoSupportImpl;
import org.springframework.stereotype.Service;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.util.List;
@Service("roles")
public class RolesServices {

protected final Log logger = LogFactory.getLog(getClass());

	@Resource(name = "daoSupportImpl")

	private DaoSupportImpl daoSupport; 

	public int addRoles(PageData pageData) {
	 logger.info("增加Roles");
	 int iFlag =0; 
	 try { 
		iFlag = (int) daoSupport.insert("RolesMapper.addRoles",pageData);
	 }catch (Exception e){ 
	 e.printStackTrace(); 
	 iFlag=0; 
	}
	 return iFlag; 
	}
	public int delRolesByFid(String fid) {
	 logger.info("删除Roles");
	 int iFlag =0; 
	 try { 
		iFlag = (int) daoSupport.delete("RolesMapper.deleteRolesByFid",fid);
	 }catch (Exception e){ 
	 e.printStackTrace(); 
	 iFlag=0; 
	}
	 return iFlag; 
	}
	public int updateRoles(PageData pageData) {
	 logger.info("修改Roles");
	 int iFlag =0; 
	 try { 
		iFlag = (int) daoSupport.update("RolesMapper.updateRoles",pageData);
	 }catch (Exception e){ 
	 e.printStackTrace(); 
	 iFlag=0; 
	}
	 return iFlag; 
	}
	public Roles getRolesforId(String fid) {
	 logger.info("通过ID查询Roles");
	Roles	roles=null;
	 try { 
		roles = (Roles) daoSupport.findForObject("RolesMapper.getRolesForId",fid);
	 }catch (Exception e){ 
	 e.printStackTrace(); 
	 roles=null; 
	}
	 return roles; 
	}
	public List<Roles> getAllRoles(PageData pageData) {
	 logger.info("分页查询Roles");
	List<Roles>	rolesList=null;
	 try { 
		rolesList = (List<Roles>) daoSupport.findForList("RolesMapper.getAllRoles",pageData);
	 }catch (Exception e){ 
	 e.printStackTrace(); 
	 rolesList=null; 
	}
	 return rolesList; 
	}
	public List<Roles> selectRolesList() {
	 logger.info("查询所有Roles");
	List<Roles>	rolesList=null;
	 try { 
		rolesList = (List<Roles>) daoSupport.findForList("RolesMapper.selectRolesList",null);
	 }catch (Exception e){ 
	 e.printStackTrace(); 
	 rolesList=null; 
	}
	 return rolesList; 
	}
}
