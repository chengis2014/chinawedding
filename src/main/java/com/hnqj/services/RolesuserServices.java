package com.hnqj.services;

import com.hnqj.core.PageData;

import com.hnqj.model.Rolesuser;

import javax.annotation.Resource;
import com.hnqj.dao.DaoSupportImpl;
import org.springframework.stereotype.Service;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.util.List;
@Service("rolesuser")
public class RolesuserServices {

protected final Log logger = LogFactory.getLog(getClass());

	@Resource(name = "daoSupportImpl")

	private DaoSupportImpl daoSupport; 

	public int addRolesuser(PageData pageData) {
	 logger.info("增加Rolesuser");
	 int iFlag =0; 
	 try { 
		iFlag = (int) daoSupport.insert("RolesuserMapper.addRolesuser",pageData);
	 }catch (Exception e){ 
	 e.printStackTrace(); 
	 iFlag=0; 
	}
	 return iFlag; 
	}
	public int delRolesuserByFid(String fid) {
	 logger.info("删除Rolesuser");
	 int iFlag =0; 
	 try { 
		iFlag = (int) daoSupport.delete("RolesuserMapper.deleteRolesuserByFid",fid);
	 }catch (Exception e){ 
	 e.printStackTrace(); 
	 iFlag=0; 
	}
	 return iFlag; 
	}
	public int updateRolesuser(PageData pageData) {
	 logger.info("修改Rolesuser");
	 int iFlag =0; 
	 try { 
		iFlag = (int) daoSupport.update("RolesuserMapper.updateRolesuser",pageData);
	 }catch (Exception e){ 
	 e.printStackTrace(); 
	 iFlag=0; 
	}
	 return iFlag; 
	}
	public Rolesuser getRolesuserforId(String fid) {
	 logger.info("通过ID查询Rolesuser");
	Rolesuser	rolesuser=null;
	 try { 
		rolesuser = (Rolesuser) daoSupport.findForObject("RolesuserMapper.getRolesuserForId",fid);
	 }catch (Exception e){ 
	 e.printStackTrace(); 
	 rolesuser=null; 
	}
	 return rolesuser; 
	}
	public List<Rolesuser> getAllRolesuser(PageData pageData) {
	 logger.info("分页查询Rolesuser");
	List<Rolesuser>	rolesuserList=null;
	 try { 
		rolesuserList = (List<Rolesuser>) daoSupport.findForList("RolesuserMapper.getAllRolesuser",pageData);
	 }catch (Exception e){ 
	 e.printStackTrace(); 
	 rolesuserList=null; 
	}
	 return rolesuserList; 
	}
	public List<Rolesuser> selectRolesuserList() {
	 logger.info("查询所有Rolesuser");
	List<Rolesuser>	rolesuserList=null;
	 try { 
		rolesuserList = (List<Rolesuser>) daoSupport.findForList("RolesuserMapper.selectRolesuserList",null);
	 }catch (Exception e){ 
	 e.printStackTrace(); 
	 rolesuserList=null; 
	}
	 return rolesuserList; 
	}
}
