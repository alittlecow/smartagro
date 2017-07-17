package com.sywl.web.service;

import com.sywl.web.dao.CardMapper;
import com.sywl.web.domain.CardDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.smartcardio.Card;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/17.
 */
@Service
@Transactional
public class CardService {

    @Autowired
    CardMapper cardMapper;

    public CardDomain queryCardById(String id) {
        return cardMapper.queryCardById(id);
    }


    public List<CardDomain> queryListCard(Map<String, Object> map) {
        return cardMapper.queryListCard(map);
    }
}
