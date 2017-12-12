package com.hnqj.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hnqj.core.PageData;
import com.hnqj.core.ResultUtils;
import com.hnqj.core.TableReturn;
import com.hnqj.model.Playimg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

import static com.hnqj.core.ResultUtils.toJson;

/**
 * Created by nyw on 2017/11/30.
 */
@Controller
@RequestMapping("/playImageMgr")
public class PlayImageController  extends  BaseController{
    //@Autowired
    //PlayimgServices playimgService;

    /*
     *跳转轮播信息管理页面
     * */
    @RequestMapping("/toPlayimgList.do")
    public String toPlayimgList(){
        return "business/toPlayimgList";
    }

    //获取信息列表
    @RequestMapping("/getPlayimgList.do")
    public String getPlayimgList(HttpServletRequest request, HttpServletResponse response){
        logger.info("getPlayimgList");
        int currentPage = request.getParameter("offset") == null ? 0 : Integer.parseInt(request.getParameter("offset"));
        int showCount = request.getParameter("limit") == null ? 50 : Integer.parseInt(request.getParameter("limit"));
        TableReturn tablereturn =new TableReturn();
        PageData pageData = new PageData();
        pageData.put("offset",currentPage);
        pageData.put("limit",showCount);
        //List<Playimg> list=playimgService.getAllPlayimg(pageData);
        //List<Playimg> listCount=playimgService.selectPlayimgList();
        //tablereturn.setTotal(listCount.size());
        //tablereturn.setRows(list);
        ResultUtils.write(response,toJson(tablereturn));
        return null;
    }
    //添加一条轮播图片记录
    /*
    **/
    @RequestMapping("/addPlayimg.do")
    public String addPlayimg(HttpServletRequest request, HttpServletResponse response, Model model){
        //获取提交参数
        logger.info("addPlayimg");
        String jsonTxt = request.getParameter("jsontxt") == null ? "" : request.getParameter("jsontxt");
        if(jsonTxt.equals("")){
            ResultUtils.writeFailed(response);
        }
        JSONObject jsonObj = JSON.parseObject(jsonTxt );
        //转换为作品Model
        PageData trainPageData = new PageData();
        trainPageData.put("playuid", UUID.randomUUID().toString());
        trainPageData.put("imgurl",jsonObj.getString("worksid"));//图片作品ID或URL，建议为URL
        trainPageData.put("playsort",jsonObj.getString("playsort"));//轮播图片顺序
        trainPageData.put("navurl",jsonObj.getString("navurl"));//图片导航URL
        trainPageData.put("playtype",jsonObj.getString("playtype"));//轮播图片类型
        trainPageData.put("typeremark",jsonObj.getString("typeremark"));//类型说明
        trainPageData.put("creator",jsonObj.getString("creator"));//创建人ID
        trainPageData.put("createtime",jsonObj.getString("createtime"));//创建时间
        trainPageData.put("delflag",0);//删除标志 默认0
        //插入数据库
        try{
            //playimgService.addPlayimg(trainPageData);
            ResultUtils.writeSuccess(response);
        } catch (Exception e) {
            logger.error("addPlayimg e="+e.getMessage());
            ResultUtils.writeFailed(response);
        }
        return "";
    }
    @RequestMapping("/delPlayimgList.do")
    public String delPlayimgList(HttpServletRequest request, HttpServletResponse response){
        logger.info("delPlayimgList");
        String jsonTxt = request.getParameter("ids") == null ? "" : request.getParameter("ids");
        if(jsonTxt.equals("")){
            ResultUtils.writeFailed(response);
        }
        String[] idStrs = jsonTxt.split(",");
        try{
            for (String fid:idStrs){
                //playimgService.delPlayimgByFid(fid);
            }
            ResultUtils.writeSuccess(response);
        } catch (Exception e) {
            logger.error("delPlayimgList e="+e.getMessage());
            ResultUtils.writeFailed(response);
        }
        return "";
    }
    //修改更新一条记录
    @RequestMapping("/updatePlayimg.do")
    public String updatePlayimg(HttpServletRequest request, HttpServletResponse response){
        //获取提交参数
        logger.info("updatePlayimg");
        String jsonTxt = request.getParameter("jsontxt") == null ? "" : request.getParameter("jsontxt");
        if(jsonTxt.equals("")){
            ResultUtils.writeFailed(response);
        }
        JSONObject jsonObj = JSON.parseObject(jsonTxt );

        //转换为Model
        PageData trainPageData = new PageData();
        trainPageData.put("playuid",jsonObj.getString("playuid"));
        trainPageData.put("imgurl",jsonObj.getString("worksid"));//图片作品ID或URL，建议为URL
        trainPageData.put("playsort",jsonObj.getString("playsort"));//轮播图片顺序
        trainPageData.put("navurl",jsonObj.getString("navurl"));//图片导航URL
        trainPageData.put("playtype",jsonObj.getString("playtype"));//轮播图片类型
        trainPageData.put("typeremark",jsonObj.getString("typeremark"));//类型说明
        trainPageData.put("creator",jsonObj.getString("creator"));//创建人ID
        trainPageData.put("createtime",jsonObj.getString("createtime"));//创建时间
        trainPageData.put("delflag",0);//删除标志 默认0
        //插入数据库
        try{
            //playimgService.updatePlayimg(trainPageData);
            ResultUtils.writeSuccess(response);
        } catch (Exception e) {
            logger.error("updatePlayimg e="+e.getMessage());
            ResultUtils.writeFailed(response);
        }
        return "";
    }

}
