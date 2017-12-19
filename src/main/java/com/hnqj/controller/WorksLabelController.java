package com.hnqj.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hnqj.core.PageData;
import com.hnqj.core.ResultUtils;
import com.hnqj.core.TableReturn;
import com.hnqj.model.Worklabel;
import com.hnqj.services.WorklabelServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

import static com.hnqj.core.ResultUtils.toJson;

@Controller
@RequestMapping("/worksLabelMgr")
public class WorksLabelController extends  BaseController{
    @Autowired
    WorklabelServices labelService;

    /*
  *跳转信息管理页面 /worksLabelMgr/toWorkLabelList.do
  * */
    @RequestMapping("/toWorkLabelList.do")
    public String toWorkLabelList(){
        return "works_manager/toWorkLabelList";
    }

    //获取信息列表  /worksLabelMgr/getLabelList.do
    @RequestMapping("/getLabelList.do")
    public String getLabelList(HttpServletRequest request, HttpServletResponse response){
        logger.info("getLabelList");
        int currentPage = request.getParameter("offset") == null ? 0 : Integer.parseInt(request.getParameter("offset"));
        int showCount = request.getParameter("limit") == null ? 50 : Integer.parseInt(request.getParameter("limit"));
        TableReturn tablereturn =new TableReturn();
        PageData pageData = new PageData();
        pageData.put("offset",currentPage);
        pageData.put("limit",showCount);
        List<Worklabel> list=labelService.getAllWorklabel(pageData);
        List<Worklabel> listCount=labelService.selectWorklabelList();
        tablereturn.setTotal(listCount.size());
        tablereturn.setRows(list);
        ResultUtils.write(response,toJson(tablereturn));
        return null;
    }
    //添加一条作品标签
    /*
    **/
    @RequestMapping("/addLabel.do")
    public String addLabel(HttpServletRequest request, HttpServletResponse response, Model model){
        //获取提交参数
        logger.info("addLabel");
        String jsonTxt = request.getParameter("jsontxt") == null ? "" : request.getParameter("jsontxt");
        if(jsonTxt.equals("")){
            ResultUtils.writeFailed(response);
        }
        JSONObject jsonObj = JSON.parseObject(jsonTxt );
        //转换为Model
        PageData trainPageData = new PageData();
        trainPageData.put("uid", UUID.randomUUID().toString());
        trainPageData.put("labelname",jsonObj.getString("labelname"));//标签名称
        trainPageData.put("labelnum",jsonObj.getString("labelnum"));//标签被使用次数
        trainPageData.put("codeid",jsonObj.getString("codeid"));//所属作品分类ID
        //插入数据库
        try{
            labelService.addWorklabel(trainPageData);
            ResultUtils.writeSuccess(response);
        } catch (Exception e) {
            logger.error("addLabel e="+e.getMessage());
            ResultUtils.writeFailed(response);
        }
        return "";
    }
    @RequestMapping("/delLabelList.do")
    public String delLabelList(HttpServletRequest request, HttpServletResponse response){
        logger.info("delLabelList");
        String jsonTxt = request.getParameter("ids") == null ? "" : request.getParameter("ids");
        if(jsonTxt.equals("")){
            ResultUtils.writeFailed(response);
        }
        String[] idStrs = jsonTxt.split(",");
        try{
            for (String fid:idStrs){
                labelService.delWorklabelByFid(fid);
            }
            ResultUtils.writeSuccess(response);
        } catch (Exception e) {
            logger.error("delLabelList e="+e.getMessage());
            ResultUtils.writeFailed(response);
        }
        return "";
    }
    //修改更新一条记录
    @RequestMapping("/updateLabel.do")
    public String updateLabel(HttpServletRequest request, HttpServletResponse response){
        //获取提交参数
        logger.info("updateLabel");
        String jsonTxt = request.getParameter("jsontxt") == null ? "" : request.getParameter("jsontxt");
        if(jsonTxt.equals("")){
            ResultUtils.writeFailed(response);
        }
        JSONObject jsonObj = JSON.parseObject(jsonTxt );

        //转换为作品Model
        PageData trainPageData = new PageData();
        trainPageData.put("uid", UUID.randomUUID().toString());
        trainPageData.put("labelname",jsonObj.getString("labelname"));//标签名称
        trainPageData.put("labelnum",jsonObj.getString("labelnum"));//标签被使用次数
        trainPageData.put("codeid",jsonObj.getString("codeid"));//所属作品分类ID
        //插入数据库
        try{
            labelService.updateWorklabel(trainPageData);
            ResultUtils.writeSuccess(response);
        } catch (Exception e) {
            logger.error("updateLabel e="+e.getMessage());
            ResultUtils.writeFailed(response);
        }
        return "";
    }
}
