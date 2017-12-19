package com.hnqj.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hnqj.core.PageData;
import com.hnqj.core.ResultUtils;
import com.hnqj.core.TableReturn;
import com.hnqj.services.UserinfoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

import static com.hnqj.core.ResultUtils.toJson;

/**
 * Created by nyw on 2017/11/28.
 */
@Controller
@RequestMapping("/trainMgr")
public class TrainController  extends  BaseController{
    //@Autowired
    //TrainServices trainService;
    @Autowired
    UserinfoServices userinfoServices;

    /*
    *跳转名师信息管理页面trainMgr/toTrainList.do
    * */
    @RequestMapping("/toTrainList.do")
    public String toTrainList(){
        return "advert_manager/trainList";
    }
    //获取信息列表
    @RequestMapping("/getTrainList.do")
    public String getTrainList(HttpServletRequest request, HttpServletResponse response){
        logger.info("getTrainList");
        int currentPage = request.getParameter("offset") == null ? 0 : Integer.parseInt(request.getParameter("offset"));
        int showCount = request.getParameter("limit") == null ? 50 : Integer.parseInt(request.getParameter("limit"));
        TableReturn tablereturn =new TableReturn();
        PageData pageData = new PageData();
        pageData.put("offset",currentPage);
        pageData.put("limit",showCount);
        //List<Train> list=trainService.getAllTrain(pageData);
        //List<Train> listCount=trainService.selectTrainList();
        //tablereturn.setTotal(listCount.size());
        //tablereturn.setRows(list);
        ResultUtils.write(response,toJson(tablereturn));
        return null;
    }
    //添加一条名师记录
    /*
    * Fastjson API入口类是com.alibaba.fastjson.JSON，常用的序列化操作都可以在JSON类上的静态方法直接完成。

public static final Object parse(String text); // 把JSON文本parse为JSONObject或者JSONArray
*public static final JSONObject parseObject(String text)； // 把JSON文本parse成JSONObject
*public static final  T parseObject(String text, Class clazz); // 把JSON文本parse为JavaBean
*public static final JSONArray parseArray(String text); // 把JSON文本parse成JSONArray
*public static final  List parseArray(String text, Class clazz); //把JSON文本parse成JavaBean集合
*public static final String toJSONString(Object object); // 将JavaBean序列化为JSON文本
public static final String toJSONString(Object object, boolean prettyFormat); // 将JavaBean序列化为带格式的JSON文本
public static final Object toJSON(Object javaObject); 将JavaBean转换为JSONObject或者JSONArray。
    * */
    @RequestMapping("/addTrain.do")
    public String addTrainList(HttpServletRequest request, HttpServletResponse response, Model model){
        //获取提交参数
        logger.info("addTrainList");
        String jsonTxt = request.getParameter("jsontxt") == null ? "" : request.getParameter("jsontxt");
        if(jsonTxt.equals("")){
            ResultUtils.writeFailed(response);
        }
        JSONObject jsonObj = JSON.parseObject(jsonTxt );
        //转换为名师Model
        PageData trainPageData = new PageData();
        trainPageData.put("teruid", UUID.randomUUID().toString());
        trainPageData.put("trainname",jsonObj.getString("trainname"));//培训内容名称
        trainPageData.put("traintime",jsonObj.getString("traintime"));//培训时间
        trainPageData.put("trainaddress",jsonObj.getString("trainaddress"));//培训地址
        trainPageData.put("phone",jsonObj.getString("phone"));//联系电话
        trainPageData.put("logoimg",jsonObj.getString("logoimg"));//logo 图片地址
        trainPageData.put("topflag",jsonObj.getString("topflag"));//是否置顶  0 否 1 2 3置顶顺序 ，不可以有同一顺序位
        trainPageData.put("price",jsonObj.getString("price"));//投标价格
        trainPageData.put("legtime",jsonObj.getString("legtime"));//有效期
        trainPageData.put("delflag",0);//删除标志 默认0
        //插入数据库
        try{
            //trainService.addTrain(trainPageData);
            ResultUtils.writeSuccess(response);
        } catch (Exception e) {
            logger.error("addTrainList e="+e.getMessage());
            ResultUtils.writeFailed(response);
        }
        return "";
    }
    /*
    * 删除一条或多条记录
    * 多条记录ID用逗号分隔
    * */
    @RequestMapping("/delTrainList.do")
    public String delTrainList(HttpServletRequest request, HttpServletResponse response){
        logger.info("delTrainList");
        String jsonTxt = request.getParameter("ids") == null ? "" : request.getParameter("ids");
        if(jsonTxt.equals("")){
            ResultUtils.writeFailed(response);
        }
        String[] idStrs = jsonTxt.split(",");
        try{
            for (String fid:idStrs){
                //trainService.delTrainByFid(fid);
            }
            ResultUtils.writeSuccess(response);
        } catch (Exception e) {
            logger.error("delTrainList e="+e.getMessage());
            ResultUtils.writeFailed(response);
        }
        return "";
    }
    //修改更新一条记录
    @RequestMapping("/updateTrain.do")
    public String updateTrain(HttpServletRequest request, HttpServletResponse response){
        //获取提交参数
        logger.info("updateTrain");
        String jsonTxt = request.getParameter("jsontxt") == null ? "" : request.getParameter("jsontxt");
        if(jsonTxt.equals("")){
            ResultUtils.writeFailed(response);
        }
        JSONObject jsonObj = JSON.parseObject(jsonTxt );
        //转换为名师Model
        PageData trainPageData = new PageData();
        trainPageData.put("teruid", jsonObj.getString("teruid"));
        trainPageData.put("trainname",jsonObj.getString("trainname"));//培训内容名称
        trainPageData.put("traintime",jsonObj.getString("traintime"));//培训时间
        trainPageData.put("trainaddress",jsonObj.getString("trainaddress"));//培训地址
        trainPageData.put("phone",jsonObj.getString("phone"));//联系电话
        trainPageData.put("logoimg",jsonObj.getString("logoimg"));//logo 图片地址
        trainPageData.put("topflag",jsonObj.getString("topflag"));//是否置顶  0 否 1 2 3置顶顺序 ，不可以有同一顺序位
        trainPageData.put("price",jsonObj.getString("price"));//投标价格
        trainPageData.put("legtime",jsonObj.getString("legtime"));//有效期
        trainPageData.put("delflag",0);//删除标志 默认0
        //插入数据库
        try{
            //trainService.updateTrain(trainPageData);
            ResultUtils.writeSuccess(response);
        } catch (Exception e) {
            logger.error("updateTrain e="+e.getMessage());
            ResultUtils.writeFailed(response);
        }
        return "";
    }
}
