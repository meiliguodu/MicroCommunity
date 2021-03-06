package com.java110.api.listener.org;

import com.alibaba.fastjson.JSONObject;
import com.java110.api.bmo.org.IOrgBMO;
import com.java110.api.listener.AbstractServiceApiPlusListener;
import com.java110.utils.util.Assert;
import com.java110.core.context.DataFlowContext;
import com.java110.core.event.service.api.ServiceDataFlowEvent;

import com.java110.core.annotation.Java110Listener;
import com.java110.utils.constant.ServiceCodeOrgConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;

/**
 * 保存小区侦听
 * add by wuxw 2019-06-30
 */
@Java110Listener("deleteOrgListener")
public class DeleteOrgListener extends AbstractServiceApiPlusListener {

    @Autowired
    private IOrgBMO orgBMOImpl;
    @Override
    protected void validate(ServiceDataFlowEvent event, JSONObject reqJson) {
        //Assert.hasKeyAndValue(reqJson, "xxx", "xxx");

        Assert.hasKeyAndValue(reqJson, "orgId", "组织ID不能为空");
        Assert.hasKeyAndValue(reqJson, "storeId", "必填，请填写商户ID");

    }

    @Override
    protected void doSoService(ServiceDataFlowEvent event, DataFlowContext context, JSONObject reqJson) {

        orgBMOImpl.deleteOrg(reqJson, context);


    }

    @Override
    public String getServiceCode() {
        return ServiceCodeOrgConstant.DELETE_ORG;
    }

    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.POST;
    }

    @Override
    public int getOrder() {
        return DEFAULT_ORDER;
    }

}
