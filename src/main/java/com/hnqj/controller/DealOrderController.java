package com.hnqj.controller;

import com.hnqj.core.PageData;
import com.hnqj.core.ResultUtils;
import com.hnqj.core.TableReturn;
import com.hnqj.model.Dealrecord;
import com.hnqj.services.DealrecordServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.hnqj.core.ResultUtils.toJson;

/**
 * Created by nyw on 2017/12/02.
 * 交易订单控制类
 */

@Controller
@RequestMapping("/dealOrderMgr")
public class DealOrderController  extends  BaseController{

    @Autowired
    DealrecordServices dealService;
    /*
         *跳转信息管理页面   /dealOrderMgr/toDealList.do
         * */
    @RequestMapping("/toDealList.do")
    public String toOrderList(){
        return "deal_manager/toOrderList";
    }

    //获取信息列表
    @RequestMapping("/getDealorderList.do")
    public String getDealorderList(HttpServletRequest request, HttpServletResponse response){
        logger.info("getDealorderList");
        int currentPage = request.getParameter("offset") == null ? 0 : Integer.parseInt(request.getParameter("offset"));
        int showCount = request.getParameter("limit") == null ? 50 : Integer.parseInt(request.getParameter("limit"));
        TableReturn tablereturn =new TableReturn();
        PageData pageData = new PageData();
        pageData.put("offset",currentPage);
        pageData.put("limit",showCount);
        List<Dealrecord> list=dealService.getAllDealrecord(pageData);
        List<Dealrecord> listCount=dealService.selectDealrecordList();
        tablereturn.setTotal(listCount.size());
        tablereturn.setRows(list);
        ResultUtils.write(response,toJson(tablereturn));
        return null;
    }
}
