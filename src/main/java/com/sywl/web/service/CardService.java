package com.sywl.web.service;

import com.sywl.common.enums.Constants;
import com.sywl.utils.UUIDUtil;
import com.sywl.web.dao.ApplyCardMapper;
import com.sywl.web.dao.CardMapper;
import com.sywl.web.domain.ApplyCardDomain;
import com.sywl.web.domain.CardDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.smartcardio.Card;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/17.
 */
@Service
@Transactional
public class CardService {

    @Autowired
    private CardMapper cardMapper;

    @Autowired
    private ApplyCardMapper applyCardMapper;

    public CardDomain queryCardById(String id) {
        return cardMapper.queryCardById(id);
    }


    public List<CardDomain> queryCardByCondition(Map<String, Object> params) {
        return cardMapper.queryCardByCondition(params);
    }

    public void applyCard(ApplyCardDomain applyCardDomain) {
        Date nowTime = new Date();
        applyCardDomain.setApplyTime(nowTime);
        applyCardDomain.setStatus(Constants.ApplyCardStatus.INIT.getValue());
        applyCardDomain.setId(UUIDUtil.getUUId());
        applyCardMapper.save(applyCardDomain);
    }
}
