package com.hnqj.services;

import com.hnqj.core.PageData;

import com.hnqj.model.Merch;

import javax.annotation.Resource;
import com.hnqj.dao.DaoSupportImpl;
import org.springframework.stereotype.Service;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.util.List;
@Service("merch")
public class MerchServices {

protected final Log logger = LogFactory.getLog(getClass());

	@Resource(name = "daoSupportImpl")

	private DaoSupportImpl daoSupport; 

	public int addMerch(PageData pageData) {
	 logger.info("增加Merch");
	 int iFlag =0; 
	 try { 
		iFlag = (int) daoSupport.insert("MerchMapper.addMerch",pageData);
	 }catch (Exception e){ 
	 e.printStackTrace(); 
	 iFlag=0; 
	}
	 return iFlag; 
	}
	public int delMerchByFid(String fid) {
	 logger.info("删除Merch");
	 int iFlag =0; 
	 try { 
		iFlag = (int) daoSupport.delete("MerchMapper.deleteMerchByFid",fid);
	 }catch (Exception e){ 
	 e.printStackTrace(); 
	 iFlag=0; 
	}
	 return iFlag; 
	}
	public int updateMerch(PageData pageData) {
	 logger.info("修改Merch");
	 int iFlag =0; 
	 try { 
		iFlag = (int) daoSupport.update("MerchMapper.updateMerch",pageData);
	 }catch (Exception e){ 
	 e.printStackTrace(); 
	 iFlag=0; 
	}
	 return iFlag; 
	}
	public Merch getMerchforId(String fid) {
	 logger.info("通过ID查询Merch");
	Merch	merch=null;
	 try { 
		merch = (Merch) daoSupport.findForObject("MerchMapper.getMerchForId",fid);
	 }catch (Exception e){ 
	 e.printStackTrace(); 
	 merch=null; 
	}
	 return merch; 
	}
	public List<Merch> getAllMerch(PageData pageData) {
	 logger.info("分页查询Merch");
	List<Merch>	merchList=null;
	 try { 
		merchList = (List<Merch>) daoSupport.findForList("MerchMapper.getAllMerch",pageData);
	 }catch (Exception e){ 
	 e.printStackTrace(); 
	 merchList=null; 
	}
	 return merchList; 
	}
	public List<Merch> selectMerchList() {
	 logger.info("查询所有Merch");
	List<Merch>	merchList=null;
	 try { 
		merchList = (List<Merch>) daoSupport.findForList("MerchMapper.selectMerchList",null);
	 }catch (Exception e){ 
	 e.printStackTrace(); 
	 merchList=null; 
	}
	 return merchList; 
	}
}
