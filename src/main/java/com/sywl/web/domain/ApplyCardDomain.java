package com.sywl.web.domain;

/**
 * @author pengxiao
 * @date 2017/7/19
 */

import com.sywl.utils.CommonUtils;
import org.apache.commons.lang.StringUtils;

import java.util.Date;

public class ApplyCardDomain {
    private String id;
    private String contactName;
    private String contactPhone;
    private String contactAddress;
    private String idCard;
    private Date applyTime;
    private Date updateTime;
    private Byte status;

    /**
     * 新增申请格式校验
     *
     * @return 无效返回true
     */
    public boolean invalidAdd() {
        if (CommonUtils.hasEmptyString(contactName, contactAddress, idCard, contactPhone)) {
            return true;
        }
        if (!CommonUtils.isPhone(contactPhone)) {
            return true;
        }
        return false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}
