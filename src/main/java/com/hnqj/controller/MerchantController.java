package com.hnqj.controller;

import com.hnqj.core.PageData;
import com.hnqj.core.ResultUtils;
import com.hnqj.services.MerchServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * Created by Administrator on 2017/8/8.
 */
@Controller
@RequestMapping("/merchant")
public class MerchantController extends  BaseController{
    @Autowired
    MerchServices merchServices;
    /**
     * 跳转到商户管理页面
     * @return
     */
    @RequestMapping("/toMerchantList.do")
    public String toMerchantList(){
        return "merchant/merchantlist";
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

    }
