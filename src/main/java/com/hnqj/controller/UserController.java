package com.hnqj.controller;

import com.hnqj.model.Userinfo;
import com.hnqj.services.AccountServices;
import com.hnqj.services.RolesuserServices;
import com.hnqj.services.UserinfoServices;
import com.hnqj.core.PageData;
import com.hnqj.core.ResultUtils;
import com.hnqj.core.TableReturn;
import net.sourceforge.pinyin4j.PinyinHelper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

import static com.hnqj.core.EncodeUtil.encodeMD5;
import static com.hnqj.core.ResultUtils.toJson;

/**
 * Created by Administrator on 2017/8/8.
 */
@Controller
@RequestMapping("/user")
public class UserController extends  BaseController{
    @Autowired
    UserinfoServices userinfoServices;
    @Autowired
    AccountServices accountServices;
    @Autowired
    RolesuserServices rolesuserServices;

    @RequestMapping("/toUserList.do")
    public String toUserList(){
        return "sys/userlist";
    }
    //初始化数据
    @RequestMapping("/getUserList.do")
    public String getUserList(HttpServletRequest request, HttpServletResponse response, Model model){
        logger.info("日志打印");
        String unitid = request.getParameter("unId") == null ? "" : request.getParameter("unId");//部门ID
        int currentPage = request.getParameter("offset") == null ? 0 : Integer.parseInt(request.getParameter("offset"));
        // 每页行数
        int showCount = request.getParameter("limit") == null ? 50 : Integer.parseInt(request.getParameter("limit"));
        TableReturn tablereturn =new TableReturn();
        PageData pageData = new PageData();
        pageData.put("offset",currentPage);
        pageData.put("limit",showCount);
     //   tablereturn.setTotal(userService.getUserCount());
        List<Userinfo> list=null;
        if(unitid != "" && unitid != null && unitid != "undefined"){
            list=userinfoServices.getAllUserByUid(unitid);
        }else{
            list=userinfoServices.getAllUser(pageData);
        }
        tablereturn.setTotal(list.size());
        tablereturn.setRows(list);
        ResultUtils.write(response,toJson(tablereturn));
        return null;
    }
    /**
     * 增加用户
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/addUser.do")
    public String addUser(HttpServletRequest request, HttpServletResponse response){
        logger.info("addUser");
        String fname = request.getParameter("fname") == null ? "" : request.getParameter("fname");
        String mobilephone=request.getParameter("mobilephone") == null ? "" : request.getParameter("mobilephone");
        String acount=request.getParameter("account") == null ? "" : request.getParameter("account");
        String password=request.getParameter("password") == null ? "" : request.getParameter("password");
        String unitId=request.getParameter("unitId") == null ? "" : request.getParameter("unitId");//部门ID
        String numbers=request.getParameter("numbers") == null ? "" : request.getParameter("numbers");//部门ID

        PageData userPageData = new PageData();
        PageData accountPageData = new PageData();
        PageData unitUserPageData = new PageData();
        //向用户表插入数据
        String uuid= UUID.randomUUID().toString();
        userPageData.put("fid",uuid);
        userPageData.put("fname",fname);
        userPageData.put("mobilephone",mobilephone);
        userPageData.put("isdel",0);
        userPageData.put("numbers",numbers);
        userPageData.put("pinyin",getChineseToPinYin(fname));
        if(acount != ""){//如果前台没传过来账号，使用拼音作为帐号
            accountPageData.put("account",acount);
            accountPageData.put("passwd",encodeMD5(password));//帐号密码拼一块加密作为密码
        }else{
            accountPageData.put("account",getChineseToPinYin(fname));
            accountPageData.put("passwd",encodeMD5(password));//帐号密码拼一块加密作为密码
        }
        //向帐号表插入数据
        accountPageData.put("fid",uuid);
        accountPageData.put("userid",uuid);
        accountPageData.put("state",1);
        accountPageData.put("usemobile",1);
        //向用户部门表插入数据
        unitUserPageData.put("fid",UUID.randomUUID().toString());
        unitUserPageData.put("unitid",unitId);
        unitUserPageData.put("userid",uuid);
        unitUserPageData.put("isdel",1);
        try{
            accountServices.addAccount(accountPageData);//向account表插入一条记录，为当前添加用户的帐号密码等信息
            userinfoServices.addUser(userPageData);
            ResultUtils.writeSuccess(response);
        } catch (Exception e) {
            logger.error("addUser e="+e.getMessage());
            ResultUtils.writeFailed(response);
        }
        return null;
    }
    /**
     * 编辑用户
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/updateUser.do")
    public String updateUser(HttpServletRequest request, HttpServletResponse response){
        logger.debug("updateUser");
        String fid = request.getParameter("fid") == null ? "" : request.getParameter("fid");
        String fname = request.getParameter("fname") == null ? "" : request.getParameter("fname");
        String mobilephone=request.getParameter("mobilephone") == null ? "" : request.getParameter("mobilephone");
        String unitId=request.getParameter("unitId") == null ? "" : request.getParameter("unitId");//部门ID
        String numbers=request.getParameter("numbers") == null ? "" : request.getParameter("numbers");//部门ID
        //修改用户
        PageData userPageData = new PageData();
        userPageData.put("fid",fid);
        userPageData.put("fname",fname);
        userPageData.put("mobilephone",mobilephone);
        userPageData.put("numbers",numbers);
        userPageData.put("isdel",0);
        //修改此用户的部门、职位信息
       // UnitUser unitUser=unitUserService.getUnitUserByUserId(fid);
        PageData unitUserPageData = new PageData();
        unitUserPageData.put("userid",fid);
        unitUserPageData.put("unitid",unitId);
        try{
            userinfoServices.updateUser(userPageData);
            ResultUtils.writeSuccess(response);
        } catch (Exception e) {
            logger.error("updateUser e="+e.getMessage());
            ResultUtils.writeFailed(response);
        }
        return null;
    }
    /**
     * 删除
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/delUserIds.do")
    @Transactional
    public String delUserIds(HttpServletRequest request, HttpServletResponse response, Model model){
        logger.debug("delUserIds");
        try{
            String ids = request.getParameter("ids") == null ? "" : request.getParameter("ids");
            String[] idStrs=ids.split(",");
            for (String fid:idStrs){
                userinfoServices.deleteUserByFid(fid);
                accountServices.deleteAccountByFid(fid);
                rolesuserServices.deleteRolesUserByFid(fid);
            }
            ResultUtils.writeSuccess(response);
        } catch (Exception e) {
            logger.error("delUserIds e="+e.getMessage());
            ResultUtils.writeFailed(response);
        }
        return null;
    }
    /**
     * 通过用户ID获取一条记录
     */
    @RequestMapping("/getUserById.do")
    public void getUserById(HttpServletRequest request, HttpServletResponse response){
        String fids = request.getParameter("ids") == null ? "" : request.getParameter("ids");
        String[] idStrs;
        idStrs=fids.split(",");
        Userinfo user=null;
        for (String fid:idStrs){
            user=userinfoServices.selectUserByFid(fid);
        }
        ResultUtils.write(response,toJson(user));
    }
    /**
     * 初始化密码
     */
    @RequestMapping("/resetPasswd.do")
    public String resetPasswd(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("resetPasswd");
        try{
            String fid = request.getParameter("ids");
            String passwd=encodeMD5("000000");//重置密码为000000，和帐号管理页面保持一致
            PageData pageData = new PageData();
            pageData.put("fid",fid);
            pageData.put("passwd",passwd);
            accountServices.resetPasswd(pageData);
            ResultUtils.writeSuccess(response);
        }catch(Exception e){
            logger.error("resetPasswd e="+e.getMessage());
            ResultUtils.writeFailed(response);
        }
        return null;
    }
    /**
     *获取部门树
     * @param request
     * @param response
     * @return
     */
    /*
    @RequestMapping("/getUnitUser.do")
    public String getUnitUser(HttpServletRequest request, HttpServletResponse response){
        String unitId=request.getParameter("unitId");
        Boolean showAll=request.getParameter("showAll")==""?false:new Boolean(request.getParameter("showAll"));
        //showAll=false;
        System.out.println(showAll);

        List<Map<String, String>> hashMaps=new ArrayList<>();

        if (showAll){//包含子部门
            List<Unit> units=unitService.selectUnitList();
            getChildUnitUser(units,unitId,hashMaps);
        } else{
            List<User> users=new ArrayList<User>();
            users=userService.getAllUserByUid(unitId);
            for (User user:users){
                //简单处理减少JSON大小
                Map<String, String> map = new HashMap<>();
                map.put("fid",user.getFid());
                map.put("fname",user.getFname());
                hashMaps.add(map);
            }
        }
        System.out.println(toJson(hashMaps));
        ResultUtils.write(response,hashMaps);
        return null;
    }
    */
    /**
     * 查看所有子部门的人员
     * @param units
     * @param unitId
     * @param hashMaps
     */
    /*
    private void getChildUnitUser(List<Unit> units, String unitId,List<Map<String, String>> hashMaps) {
        for (Unit unit : units) {
            if (unit.getParentUnitid().equalsIgnoreCase(unitId)){
                getChildUnitUser(units,unit.getFid(),hashMaps);
            }
        }
        List<User> users=userService.getAllUserByUid(unitId);
        for (User user:users){
            //简单处理减少JSON大小
            Map<String, String> map = new HashMap<>();
            map.put("fid",user.getFid());
            map.put("fname",user.getFname());
            hashMaps.add(map);
        }

    }
*/
    /**
     * 汉字转换拼音
     */
    public static String getChineseToPinYin(String str) {
        // ==== 全拼音 ====
        StringBuffer full_pinyin = new StringBuffer();
        try {
            if (StringUtils.isNotEmpty(str) && StringUtils.isNotBlank(str)) {// 拆分字符串中的每个【文字】
                char[] charArray = str.toCharArray();
                for (char c : charArray) {
                    String[] pinyins = PinyinHelper.toHanyuPinyinStringArray(c);
                    if (pinyins != null && pinyins.length > 0) {
						/*
						 *
						 * 由于当【文字】为多音字时返回的数组元素为多个，如"间",既可读为jian1,又可以读为jian4,
						 * 而我们在匹配搜索时，只会用到jian作匹配，那么这里直接舍去音调。
						 */
                        String pinyinStr = pinyins[0];
                        pinyinStr = pinyinStr.replaceAll("\\d*", "");
                        full_pinyin.append(pinyinStr);
                    }
                }
            }
            return full_pinyin.toString();
        } catch (Exception e) {
            return "";
        }
    }
    /**
     * 添加人员时获取所有的部门名称和ID
     */
    /*
    @RequestMapping("/getUnit.do")
    public String getUnit(HttpServletRequest request, HttpServletResponse response) {
        logger.info("getUnit");
        String userids=request.getParameter("ids");
        String userid=userids.substring(0,userids.length()-1);
        logger.debug("$$$$$$$$$$$$$$$$$"+userid);
        UnitUser unitUser=unitUserService.getUnitUserByUserId(userid);
        logger.debug("##############"+unitUser.getUnitid());
        Unit unit=unitService.selectUnitByFid(unitUser.getUnitid());
        List<Map<String, String>> hashMaps=new ArrayList<>();
        //简单处理减少JSON大小
        Map<String, String> map = new HashMap<>();
        map.put("fid",unit.getFid());
        map.put("fname",unit.getFname());
        hashMaps.add(map);
        ResultUtils.write(response,toJson(hashMaps));
        return null;
    }

    */
}
