package com.hnqj.services;

import com.hnqj.core.PageData;

import com.hnqj.model.UserinfoWithBLOBs;

import javax.annotation.Resource;
import com.hnqj.dao.DaoSupportImpl;
import org.springframework.stereotype.Service;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.util.List;
@Service("userinfowithblobs")
public class UserinfoWithBLOBsServices {

protected final Log logger = LogFactory.getLog(getClass());

	@Resource(name = "daoSupportImpl")

	private DaoSupportImpl daoSupport; 

	public int addUserinfoWithBLOBs(PageData pageData) {
	 logger.info("增加UserinfoWithBLOBs");
	 int iFlag =0; 
	 try { 
		iFlag = (int) daoSupport.insert("UserinfoWithBLOBsMapper.addUserinfoWithBLOBs",pageData);
	 }catch (Exception e){ 
	 e.printStackTrace(); 
	 iFlag=0; 
	}
	 return iFlag; 
	}
	public int delUserinfoWithBLOBsByFid(String fid) {
	 logger.info("删除UserinfoWithBLOBs");
	 int iFlag =0; 
	 try { 
		iFlag = (int) daoSupport.delete("UserinfoWithBLOBsMapper.deleteUserinfoWithBLOBsByFid",fid);
	 }catch (Exception e){ 
	 e.printStackTrace(); 
	 iFlag=0; 
	}
	 return iFlag; 
	}
	public int updateUserinfoWithBLOBs(PageData pageData) {
	 logger.info("修改UserinfoWithBLOBs");
	 int iFlag =0; 
	 try { 
		iFlag = (int) daoSupport.update("UserinfoWithBLOBsMapper.updateUserinfoWithBLOBs",pageData);
	 }catch (Exception e){ 
	 e.printStackTrace(); 
	 iFlag=0; 
	}
	 return iFlag; 
	}
	public UserinfoWithBLOBs getUserinfoWithBLOBsforId(String fid) {
	 logger.info("通过ID查询UserinfoWithBLOBs");
	UserinfoWithBLOBs	userinfowithblobs=null;
	 try { 
		userinfowithblobs = (UserinfoWithBLOBs) daoSupport.findForObject("UserinfoWithBLOBsMapper.getUserinfoWithBLOBsForId",fid);
	 }catch (Exception e){ 
	 e.printStackTrace(); 
	 userinfowithblobs=null; 
	}
	 return userinfowithblobs; 
	}
	public List<UserinfoWithBLOBs> getAllUserinfoWithBLOBs(PageData pageData) {
	 logger.info("分页查询UserinfoWithBLOBs");
	List<UserinfoWithBLOBs>	userinfowithblobsList=null;
	 try { 
		userinfowithblobsList = (List<UserinfoWithBLOBs>) daoSupport.findForList("UserinfoWithBLOBsMapper.getAllUserinfoWithBLOBs",pageData);
	 }catch (Exception e){ 
	 e.printStackTrace(); 
	 userinfowithblobsList=null; 
	}
	 return userinfowithblobsList; 
	}
	public List<UserinfoWithBLOBs> selectUserinfoWithBLOBsList() {
	 logger.info("查询所有UserinfoWithBLOBs");
	List<UserinfoWithBLOBs>	userinfowithblobsList=null;
	 try { 
		userinfowithblobsList = (List<UserinfoWithBLOBs>) daoSupport.findForList("UserinfoWithBLOBsMapper.selectUserinfoWithBLOBsList",null);
	 }catch (Exception e){ 
	 e.printStackTrace(); 
	 userinfowithblobsList=null; 
	}
	 return userinfowithblobsList; 
	}
}
