package com.hnqj.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hnqj.core.PageData;
import com.hnqj.core.ResultUtils;
import com.hnqj.core.TableReturn;
import com.hnqj.model.Releaseadvert;
import com.hnqj.services.ReleaseadvertServices;
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
 * Created by nyw on 2017/11/29.
 */
@Controller
@RequestMapping("/advertInfoMgr")
public class ReleaseadvertController extends BaseController{
    @Autowired
    ReleaseadvertServices adServices;
    /*
  *跳转广告信息管理页面  /advertInfoMgr/toAdvertInfoList.do
  * */
    @RequestMapping("/toAdvertInfoList.do")
    public String toAdvertInfoList(){
        return "advert_manager/toAdvertInfoList";
    }

    //获取广告信息列表 advertInfoMgr/getAdvertInfoList.do
    @RequestMapping("/getAdvertInfoList.do")
    public String getAdvertInfoList(HttpServletRequest request, HttpServletResponse response){
        logger.info("getAdvertInfoList");
        int currentPage = request.getParameter("offset") == null ? 0 : Integer.parseInt(request.getParameter("offset"));
        int showCount = request.getParameter("limit") == null ? 50 : Integer.parseInt(request.getParameter("limit"));
        TableReturn tablereturn =new TableReturn();
        PageData pageData = new PageData();
        pageData.put("offset",currentPage);
        pageData.put("limit",showCount);
        List<Releaseadvert> list=adServices.getAllReleaseadvert(pageData);
        List<Releaseadvert> listCount=adServices.selectReleaseadvertList();
        tablereturn.setTotal(listCount.size());
        tablereturn.setRows(list);
        ResultUtils.write(response,toJson(tablereturn));
        return null;
    }
    //添加一条广告信息记录
    /*
    **/
    @RequestMapping("/addAdvertInfo.do")
    public String addAdvertInfoList(HttpServletRequest request, HttpServletResponse response, Model model){
        //获取提交参数
        logger.info("addAdvertInfoList");
        String jsonTxt = request.getParameter("jsontxt") == null ? "" : request.getParameter("jsontxt");
        if(jsonTxt.equals("")){
            ResultUtils.writeFailed(response);
        }
        JSONObject jsonObj = JSON.parseObject(jsonTxt );
        //转换为广告信息Model
        PageData adPageData = new PageData();
        adPageData.put("aduid", UUID.randomUUID().toString());
        adPageData.put("adpositionid",jsonObj.getString("adpositionid"));//所属广告位ID
        adPageData.put("adprice",jsonObj.getString("adprice"));//广告价格
        adPageData.put("imgurl",jsonObj.getString("imgurl"));//广告位展示资源URL
        adPageData.put("adurl",jsonObj.getString("adurl"));//广告位导航URL
        adPageData.put("clickcount",jsonObj.getString("clickcount"));//点击量
        adPageData.put("clientuid",jsonObj.getString("clientuid"));//客户信息ID
        adPageData.put("adstarttime",jsonObj.getString("adstarttime"));//开始时间
        adPageData.put("adendtime",jsonObj.getString("adendtime"));//结束时间
        adPageData.put("creator",jsonObj.getString("creator"));//创建人
        adPageData.put("createtime",jsonObj.getString("createtime"));//创建时间
        //adPageData.put("delflag",0);//删除标志 默认0
        //插入数据库
        try{
            adServices.addReleaseadvert(adPageData);
            ResultUtils.writeSuccess(response);
        } catch (Exception e) {
            logger.error("addAdvertInfoList e="+e.getMessage());
            ResultUtils.writeFailed(response);
        }
        return "";
    }
    @RequestMapping("/delAdvertInfoList.do")
    public String delAdvertInfoList(HttpServletRequest request, HttpServletResponse response){
        logger.info("delAdvertInfoList");
        String jsonTxt = request.getParameter("ids") == null ? "" : request.getParameter("ids");
        if(jsonTxt.equals("")){
            ResultUtils.writeFailed(response);
        }
        String[] idStrs = jsonTxt.split(",");
        try{
            for (String fid:idStrs){
                adServices.delReleaseadvertByFid(fid);
            }
            ResultUtils.writeSuccess(response);
        } catch (Exception e) {
            logger.error("delAdvertInfoList e="+e.getMessage());
            ResultUtils.writeFailed(response);
        }
        return "";
    }
    //修改更新一条记录
    @RequestMapping("/updateAdvertInfo.do")
    public String updateAdvertInfo(HttpServletRequest request, HttpServletResponse response){
        //获取提交参数
        logger.info("updateAdvertInfo");
        String jsonTxt = request.getParameter("jsontxt") == null ? "" : request.getParameter("jsontxt");
        if(jsonTxt.equals("")){
            ResultUtils.writeFailed(response);
        }
        JSONObject jsonObj = JSON.parseObject(jsonTxt );

        //转换为作品Model

        PageData adPageData = new PageData();
        adPageData.put("aduid", jsonObj.getString("aduid"));
        adPageData.put("adpositionid",jsonObj.getString("adpositionid"));//所属广告位ID
        adPageData.put("adprice",jsonObj.getString("adprice"));//广告价格
        adPageData.put("imgurl",jsonObj.getString("imgurl"));//广告位展示资源URL
        adPageData.put("adurl",jsonObj.getString("adurl"));//广告位导航URL
        adPageData.put("clickcount",jsonObj.getString("clickcount"));//点击量
        adPageData.put("clientuid",jsonObj.getString("clientuid"));//客户信息ID
        adPageData.put("adstarttime",jsonObj.getString("adstarttime"));//开始时间
        adPageData.put("adendtime",jsonObj.getString("adendtime"));//结束时间
        adPageData.put("creator",jsonObj.getString("creator"));//创建人
        adPageData.put("createtime",jsonObj.getString("createtime"));//创建时间
        //adPageData.put("delflag",0);//删除标志 默认0
        //插入数据库
        try{
            adServices.updateReleaseadvert(adPageData);
            ResultUtils.writeSuccess(response);
        } catch (Exception e) {
            logger.error("updateAdvertInfo e="+e.getMessage());
            ResultUtils.writeFailed(response);
        }
        return "";
    }


}
