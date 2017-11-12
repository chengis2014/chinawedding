package com.hnqj.services;

import com.hnqj.core.PageData;

import com.hnqj.model.Modules;

import javax.annotation.Resource;
import com.hnqj.dao.DaoSupportImpl;
import org.springframework.stereotype.Service;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.util.List;
@Service("modules")
public class ModulesServices {

protected final Log logger = LogFactory.getLog(getClass());

	@Resource(name = "daoSupportImpl")

	private DaoSupportImpl daoSupport; 

	public int addModules(PageData pageData) {
	 logger.info("增加Modules");
	 int iFlag =0; 
	 try { 
		iFlag = (int) daoSupport.insert("ModulesMapper.addModules",pageData);
	 }catch (Exception e){ 
	 e.printStackTrace(); 
	 iFlag=0; 
	}
	 return iFlag; 
	}
	public int delModulesByFid(String fid) {
	 logger.info("删除Modules");
	 int iFlag =0; 
	 try { 
		iFlag = (int) daoSupport.delete("ModulesMapper.deleteModulesByFid",fid);
	 }catch (Exception e){ 
	 e.printStackTrace(); 
	 iFlag=0; 
	}
	 return iFlag; 
	}
	public int updateModules(PageData pageData) {
	 logger.info("修改Modules");
	 int iFlag =0; 
	 try { 
		iFlag = (int) daoSupport.update("ModulesMapper.updateModules",pageData);
	 }catch (Exception e){ 
	 e.printStackTrace(); 
	 iFlag=0; 
	}
	 return iFlag; 
	}
	public Modules getModulesforId(String fid) {
	 logger.info("通过ID查询Modules");
	Modules	modules=null;
	 try { 
		modules = (Modules) daoSupport.findForObject("ModulesMapper.getModulesForId",fid);
	 }catch (Exception e){ 
	 e.printStackTrace(); 
	 modules=null; 
	}
	 return modules; 
	}
	public List<Modules> getAllModules(PageData pageData) {
	 logger.info("分页查询Modules");
	List<Modules>	modulesList=null;
	 try { 
		modulesList = (List<Modules>) daoSupport.findForList("ModulesMapper.getAllModules",pageData);
	 }catch (Exception e){ 
	 e.printStackTrace(); 
	 modulesList=null; 
	}
	 return modulesList; 
	}
	public List<Modules> selectModulesList() {
	 logger.info("查询所有Modules");
	List<Modules>	modulesList=null;
	 try { 
		modulesList = (List<Modules>) daoSupport.findForList("ModulesMapper.selectModulesList",null);
	 }catch (Exception e){ 
	 e.printStackTrace(); 
	 modulesList=null; 
	}
	 return modulesList; 
	}
}
