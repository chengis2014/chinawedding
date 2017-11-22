package com.hnqj.controller;

import com.hnqj.core.PageData;
import com.hnqj.core.ResultUtils;
import com.hnqj.core.TableReturn;
import com.hnqj.model.Sysusermgr;
import com.hnqj.services.SysusermgrServices;
import net.sourceforge.pinyin4j.PinyinHelper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

import static com.hnqj.core.EncodeUtil.encodeMD5;
import static com.hnqj.core.ResultUtils.toJson;

/**
 * Created by Administrator on 2017/8/8.
 */
@Controller
@RequestMapping("/dictionaries")
public class DictionariesController extends  BaseController{

    /**
     * 跳转到字典管理页面
     * @return
     */
    @RequestMapping("/toDictionariesList.do")
    public String toDictionariesList(){
        return "sys/dictionarieslist";
    }

}
