package com.hnqj.controller;

import com.hnqj.core.PageData;
import com.hnqj.core.ResultUtils;
import com.hnqj.core.TableReturn;
import com.hnqj.model.Account;
import com.hnqj.model.Userinfo;
import com.hnqj.services.AccountServices;
import com.hnqj.services.AccountServices;
import com.hnqj.services.UserinfoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

import static com.hnqj.core.EncodeUtil.encodeMD5;
import static com.hnqj.core.ResultUtils.toJson;

/**
 * Created by Administrator on 2017/8/8.
 */
@Controller
@RequestMapping("/account")
public class AccountController extends  BaseController{
    @Autowired
    AccountServices accountService;
    @Autowired
    UserinfoServices userinfoServices;

    @RequestMapping("/toAccountList.do")
    public String toAccountList(){
        return "sys/accountlist";
    }
    //初始化数据
    @RequestMapping("/getAccountList.do")
    public String getAccountList(HttpServletRequest request, HttpServletResponse response, Model model){
        logger.info("getAccountList");
        //偏移量
        int offset = request.getParameter("offset") == null ? 0 : Integer.parseInt(request.getParameter("offset"));
        // 每页行数
        int limit = request.getParameter("limit") == null ? 0 : Integer.parseInt(request.getParameter("limit"));
        TableReturn tablereturn =new TableReturn();
       // tablereturn.setTotal(accountService.getAccountCount());
        PageData pageData = new PageData();
        pageData.put("offset",offset);
        pageData.put("limit",limit);
        List<Account> list=accountService.getAllAccounts(pageData);
        tablereturn.setTotal(list.size());
        List<Map<String, String>> hashMaps=new ArrayList<>();
        for(Account account:list){
            Userinfo user=userinfoServices.selectUserByFid(account.getUserid());
            Map<String, String> map = new HashMap<>();
            map.put("fid",account.getUid());
            map.put("account",account.getAccount());
            map.put("fname",user.getFristname());
            if(account.getUsemobile()==(short) 1){
                map.put("usemobile","是");
            }else{
                map.put("usemobile","否");
            }
            hashMaps.add(map);
        }
        tablereturn.setRows(hashMaps);
        ResultUtils.write(response,toJson(tablereturn));
        return null;
    }
    /**
     * 增加帐号
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/addAccount.do")
    public String addAccount(HttpServletRequest request, HttpServletResponse response){
        logger.info("addAccount");
        String acount = request.getParameter("account") == null ? "" : request.getParameter("account");
        String passwd= request.getParameter("passwd") == null ? "" : request.getParameter("passwd");
        short usemobile= (request.getParameter("usemobile") == null) ? null : Short.parseShort(request.getParameter("usemobile"));
        String userIds= request.getParameter("userIds") == null ? "" : request.getParameter("userIds");
        //UUID.randomUUID().toString()生成一串随机数做唯一ID
        try{
            String[] idStrs=userIds.split(",");
            for(String userid:idStrs){
                Account account=new Account();
                PageData pageData = new PageData();
                pageData.put("fid",UUID.randomUUID().toString());
                pageData.put("usemobile",usemobile);
                pageData.put("acount",acount);
                pageData.put("passwd",encodeMD5(passwd));
                pageData.put("state",1);
                pageData.put("userid",userid);
                accountService.addAccount(pageData);
            }
            ResultUtils.writeSuccess(response);
        } catch (Exception e) {
            logger.error("addAccount e="+e.getMessage());
            ResultUtils.writeFailed(response);
        }
        return null;
    }
    /**
     * 删除账户
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/delAccountIds.do")
    @Transactional
    public String delAccountIds(HttpServletRequest request, HttpServletResponse response, Model model){
        logger.debug("delAccountIds");
        try{
            String ids = request.getParameter("ids") == null ? "" : request.getParameter("ids");
            String[] idStrs=ids.split(",");
            for (String fid:idStrs){
                accountService.deleteAccountByFid(fid);
            }
            ResultUtils.writeSuccess(response);
        } catch (Exception e) {
            logger.error("delAccountIds e="+e.getMessage());
            ResultUtils.writeFailed(response);
        }
        return null;
    }
    /**
     * 重置密码
     */
    @RequestMapping("/resetPasswd.do")
    public String resetPasswd(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("resetPasswd");
        try{
            String fid = request.getParameter("ids");
            String passwd=encodeMD5("000000");
            PageData pageData = new PageData();
            pageData.put("fid",fid);
            pageData.put("passwd",passwd);
            accountService.resetPasswd(pageData);
            ResultUtils.writeSuccess(response);
        }catch(Exception e){
            logger.error("resetPasswd e="+e.getMessage());
            ResultUtils.writeFailed(response);
        }
        return null;
    }
    /**
     * 修改个人密码
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/updatePersonPassword.do")
    public String updatePersonPassword(HttpServletRequest request, HttpServletResponse response) {
        logger.info("updatePersonPassword");
        String newPassword = request.getParameter("newPassword") == null ? "" : request.getParameter("newPassword");
        Account account=accountService.getAccountforUserId(getUser().getUid());
        PageData pageData = new PageData();
        pageData.put("fid",account.getUid());
        pageData.put("passwd",encodeMD5(newPassword));
        try {
            accountService.updateAccount(pageData);
            ResultUtils.writeSuccess(response);
        }catch(Exception e){
            logger.error("updatePersonPassword e="+e.toString());
            ResultUtils.writeFailed(response);
        }
        return null;
    }
}
