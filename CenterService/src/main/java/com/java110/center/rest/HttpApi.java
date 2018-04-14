package com.java110.center.rest;

import com.java110.center.smo.ICenterServiceSMO;
import com.java110.common.constant.ResponseConstant;
import com.java110.common.util.ResponseTemplateUtil;
import com.java110.core.base.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 中心http服务 统一服务类
 *  1、只提供service方法
 * Created by wuxw on 2018/4/13.
 */
@RestController
public class HttpApi extends BaseController {

    @Autowired
    private ICenterServiceSMO centerServiceSMOImpl;

    @RequestMapping(path = "/httpApi/service",method= RequestMethod.GET)
    public String serviceGet(HttpServletRequest request) {
        return ResponseTemplateUtil.createOrderResponseJson(ResponseConstant.NO_TRANSACTION_ID,
                ResponseConstant.NO_NEED_SIGN,ResponseConstant.RESULT_CODE_ERROR,"不支持Get方法请求");
    }

    @RequestMapping(path = "/httpApi/service",method= RequestMethod.POST)
    public String servicePost(@RequestBody String orderInfo, HttpServletRequest request) {
        try {
            Map<String, String> headers = new HashMap<String, String>();
            getRequestInfo(request, headers);
            return centerServiceSMOImpl.service(orderInfo, headers);
        }catch (Exception e){
            return ResponseTemplateUtil.createOrderResponseJson(ResponseConstant.NO_TRANSACTION_ID,
                    ResponseConstant.NO_NEED_SIGN,ResponseConstant.RESULT_CODE_ERROR,e.getMessage()+e);
        }
    }

    /**
     * 获取请求信息
     * @param request
     * @param headers
     * @throws RuntimeException
     */
    private void getRequestInfo(HttpServletRequest request,Map headers) throws RuntimeException{
        try{
            super.initHeadParam(request,headers);
            super.initUrlParam(request,headers);
        }catch (Exception e){
            logger.error("加载头信息失败",e);
            throw e;
        }
    }


    public ICenterServiceSMO getCenterServiceSMOImpl() {
        return centerServiceSMOImpl;
    }

    public void setCenterServiceSMOImpl(ICenterServiceSMO centerServiceSMOImpl) {
        this.centerServiceSMOImpl = centerServiceSMOImpl;
    }
}
