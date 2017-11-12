package com.hnqj.services;

import com.hnqj.core.PageData;

import com.hnqj.model.Rolesmodules;

import javax.annotation.Resource;
import com.hnqj.dao.DaoSupportImpl;
import org.springframework.stereotype.Service;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.util.List;
@Service("rolesmodules")
public class RolesmodulesServices {

protected final Log logger = LogFactory.getLog(getClass());

	@Resource(name = "daoSupportImpl")

	private DaoSupportImpl daoSupport; 

	public int addRolesmodules(PageData pageData) {
	 logger.info("增加Rolesmodules");
	 int iFlag =0; 
	 try { 
		iFlag = (int) daoSupport.insert("RolesmodulesMapper.addRolesmodules",pageData);
	 }catch (Exception e){ 
	 e.printStackTrace(); 
	 iFlag=0; 
	}
	 return iFlag; 
	}
	public int delRolesmodulesByFid(String fid) {
	 logger.info("删除Rolesmodules");
	 int iFlag =0; 
	 try { 
		iFlag = (int) daoSupport.delete("RolesmodulesMapper.deleteRolesmodulesByFid",fid);
	 }catch (Exception e){ 
	 e.printStackTrace(); 
	 iFlag=0; 
	}
	 return iFlag; 
	}
	public int updateRolesmodules(PageData pageData) {
	 logger.info("修改Rolesmodules");
	 int iFlag =0; 
	 try { 
		iFlag = (int) daoSupport.update("RolesmodulesMapper.updateRolesmodules",pageData);
	 }catch (Exception e){ 
	 e.printStackTrace(); 
	 iFlag=0; 
	}
	 return iFlag; 
	}
	public Rolesmodules getRolesmodulesforId(String fid) {
	 logger.info("通过ID查询Rolesmodules");
	Rolesmodules	rolesmodules=null;
	 try { 
		rolesmodules = (Rolesmodules) daoSupport.findForObject("RolesmodulesMapper.getRolesmodulesForId",fid);
	 }catch (Exception e){ 
	 e.printStackTrace(); 
	 rolesmodules=null; 
	}
	 return rolesmodules; 
	}
	public List<Rolesmodules> getAllRolesmodules(PageData pageData) {
	 logger.info("分页查询Rolesmodules");
	List<Rolesmodules>	rolesmodulesList=null;
	 try { 
		rolesmodulesList = (List<Rolesmodules>) daoSupport.findForList("RolesmodulesMapper.getAllRolesmodules",pageData);
	 }catch (Exception e){ 
	 e.printStackTrace(); 
	 rolesmodulesList=null; 
	}
	 return rolesmodulesList; 
	}
	public List<Rolesmodules> selectRolesmodulesList() {
	 logger.info("查询所有Rolesmodules");
	List<Rolesmodules>	rolesmodulesList=null;
	 try { 
		rolesmodulesList = (List<Rolesmodules>) daoSupport.findForList("RolesmodulesMapper.selectRolesmodulesList",null);
	 }catch (Exception e){ 
	 e.printStackTrace(); 
	 rolesmodulesList=null; 
	}
	 return rolesmodulesList; 
	}
}
