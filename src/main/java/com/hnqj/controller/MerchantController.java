package com.hnqj.controller;

import com.hnqj.core.PageData;
import com.hnqj.core.ResultUtils;
import com.hnqj.core.TableReturn;
import com.hnqj.model.Merch;
import com.hnqj.services.MerchServices;
import com.hnqj.services.UserinfoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

import static com.hnqj.core.ResultUtils.toDateJson;
import static com.hnqj.core.ResultUtils.toJson;

/**
 * 张威 2017/11
 * 商户管理
 */
@Controller
@RequestMapping("/merchant")
public class MerchantController extends  BaseController{
    @Autowired
    MerchServices merchServices;
    @Autowired
    UserinfoServices userinfoServices;
    /**
     * 跳转到商户管理页面
     * @return
     */
    @RequestMapping("/toMerchantList.do")
    public String toMerchantList(){
        return "merchant/merchantlist";
    }

    /**
     * 初始化数据
     */
    @RequestMapping("/getMerchantList.do")
    public String getMerchantList(HttpServletRequest request, HttpServletResponse response) {
        logger.info("getMerchantList");
        int currentPage = request.getParameter("offset") == null ? 0 : Integer.parseInt(request.getParameter("offset"));
        int showCount = request.getParameter("limit") == null ? 10 : Integer.parseInt(request.getParameter("limit"));
        TableReturn tablereturn =new TableReturn();
        PageData pageData = new PageData();
        pageData.put("offset",currentPage);
        pageData.put("limit",showCount);
        try{
            List<Merch> list=merchServices.getAllMerch(pageData);
            List<Map<String, Object>> hashMaps=new ArrayList<>();
            for(Merch merch:list){
                Map<String, Object> map = new HashMap<>();
                map.put("uid",merch.getUid());
                map.put("uids",merch.getUid());
                map.put("merchname",merch.getMerchname());
                map.put("userinfouid",userinfoServices.getUserInfoByUid(merch.getUserinfouid()).getFristname());
                map.put("bondvalue",merch.getBondvalue());
                map.put("builddatetime",merch.getBuilddatetime());
                map.put("merchscroe",merch.getMerchscroe());
                map.put("statu",merch.getStatu());
                if(merch.getStatu() == 0){
                    map.put("status","待审核");
                }else if(merch.getStatu() == 1){
                    map.put("status","审核通过");
                }else if(merch.getStatu() == 2){
                    map.put("status","审核不通过");
                }else{
                    map.put("status","已冻结");
                }
                hashMaps.add(map);
            }
            List<Merch> listCount=merchServices.selectMerchList();
            tablereturn.setTotal(listCount.size());
            tablereturn.setRows(hashMaps);
            ResultUtils.write(response,toDateJson(tablereturn));
        }catch(Exception e){
            logger.error("applyShop e="+e.getMessage());
            ResultUtils.writeFailed(response);
        }
        return null;
    }
    /**
     * 开店申请
     */
    @RequestMapping("/applyShop.do")
        public String applyShop(HttpServletRequest request, HttpServletResponse response) {
            logger.info("applyShop");
        String merchname = request.getParameter("merchname") == null ? "" : request.getParameter("merchname");
        String userinfouid = request.getParameter("userinfouid") == null ? "" : request.getParameter("userinfouid");
        String bondvalue = request.getParameter("bondvalue") == null ? "" : request.getParameter("bondvalue");
        String builddatetime = request.getParameter("builddatetime") == null ? "" : request.getParameter("builddatetime");
        String merchscroe = request.getParameter("merchscroe") == null ? "" : request.getParameter("merchscroe");
        PageData pageData=new PageData();
        pageData.put("uid", UUID.randomUUID().toString());
        pageData.put("merchname",merchname);
        pageData.put("userinfouid",userinfouid);
        pageData.put("bondvalue",bondvalue);
        pageData.put("builddatetime",builddatetime);
        pageData.put("merchscroe",merchscroe);
        pageData.put("statu",0);
        try{
            merchServices.addMerch(pageData);
            ResultUtils.writeSuccess(response);
        }catch(Exception e){
            logger.error("applyShop e="+e.getMessage());
            ResultUtils.writeFailed(response);
        }
        return null;
    }
    /**
     * 店铺状态修改--审核或者冻结
     */
    @RequestMapping("/examineOrFrozen.do")
    public String examineOrFrozen(HttpServletRequest request, HttpServletResponse response) {
        logger.info("examineOrFrozen");
        String uid = request.getParameter("uid") == null ? "" : request.getParameter("uid");
        String statu = request.getParameter("statu") == null ? "" : request.getParameter("statu");
        PageData pageData=new PageData();
        pageData.put("uid",uid);
        pageData.put("statu",statu);
        try{
            merchServices.updateMerchStatu(pageData);
            ResultUtils.writeSuccess(response);
        }catch(Exception e){
            logger.error("examineOrFrozen e="+e.getMessage());
            ResultUtils.writeFailed(response);
        }
        return null;
    }
    /**
     * 店铺信息修改
     */
    @RequestMapping("/updateShop.do")
    public String updateShop(HttpServletRequest request, HttpServletResponse response) {
        logger.info("updateShop");
        String uid = request.getParameter("uid") == null ? "" : request.getParameter("uid");
        String merchname = request.getParameter("merchname") == null ? "" : request.getParameter("merchname");
        PageData pageData=new PageData();
        pageData.put("uid",uid);
        pageData.put("merchname",merchname);
        Merch merch=merchServices.getMerchforId(uid);
        if(merch.getStatu() == 2){
            pageData.put("statu",0);
        }else{
            pageData.put("statu",merch.getStatu());
        }
        try{
            merchServices.updateMerch(pageData);
            ResultUtils.writeSuccess(response);
        }catch(Exception e){
            logger.error("applyShop e="+e.getMessage());
            ResultUtils.writeFailed(response);
        }
        return null;
    }

    /**
     * 降级
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/downgrade.do")
    public String downgrade(HttpServletRequest request, HttpServletResponse response) {
        logger.info("downgrade");
        String uid = request.getParameter("uid") == null ? "" : request.getParameter("uid");
        String merchscroe = request.getParameter("merchscroe") == null ? "" : request.getParameter("merchscroe");
        return null;
    }

    }
