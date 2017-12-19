package com.hnqj.controller;

import com.hnqj.core.PageData;
import com.hnqj.core.ResultUtils;
import com.hnqj.core.TableReturn;
import com.hnqj.model.Cashrecord;
import com.hnqj.services.CashrecordServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.hnqj.core.ResultUtils.toJson;

/**
 * Created by nyw on 2017/12/02.
 * 提现审核控制类
 */

@Controller
@RequestMapping("/cashMgr")
public class CashrecordController extends  BaseController{

    @Autowired
    CashrecordServices dealService;
    /*
         *跳转信息管理页面   /cashMgr/toCashrecordList.do
         * */
    @RequestMapping("/toCashrecordList.do")
    public String toOrderList(){
        return "deal_manager/toWithdrawalList";
    }

    //获取信息列表
    @RequestMapping("/getCashList.do")
    public String getCashList(HttpServletRequest request, HttpServletResponse response){
        logger.info("getCashList");
        int currentPage = request.getParameter("offset") == null ? 0 : Integer.parseInt(request.getParameter("offset"));
        int showCount = request.getParameter("limit") == null ? 50 : Integer.parseInt(request.getParameter("limit"));
        TableReturn tablereturn =new TableReturn();
        PageData pageData = new PageData();
        pageData.put("offset",currentPage);
        pageData.put("limit",showCount);
        List<Cashrecord> list=dealService.getAllCashrecord(pageData);
        List<Cashrecord> listCount=dealService.selectCashrecordList();
        tablereturn.setTotal(listCount.size());
        tablereturn.setRows(list);
        ResultUtils.write(response,toJson(tablereturn));
        return null;
    }
}
