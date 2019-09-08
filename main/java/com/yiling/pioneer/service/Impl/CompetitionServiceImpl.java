package com.yiling.pioneer.service.Impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yiling.pioneer.mapper.CompetitionMapper;
import com.yiling.pioneer.model.Competition;
import com.yiling.pioneer.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: xc
 * @Date: 2019/9/7 20:06
 * @Description:
 **/
@Service
public class CompetitionServiceImpl implements CompetitionService {
    @Autowired
    CompetitionMapper competitionMapper;
    @Override
    public JSONObject getAllCompetition(int rows,int pageNum) {
        PageHelper.startPage(pageNum,rows);
        List<Competition> competitions = competitionMapper.getAllCompetition();

        PageInfo<Competition> pageInfo = new PageInfo<>(competitions);
        JSONObject pageJson = new JSONObject();
        pageJson.put("pageNum", pageInfo.getPageNum());
        pageJson.put("pageSize", pageInfo.getPageSize());
        pageJson.put("total", pageInfo.getTotal());
        pageJson.put("pages", pageInfo.getPages());
        pageJson.put("isFirstPage", pageInfo.isIsFirstPage());
        pageJson.put("isLastPage", pageInfo.isIsLastPage());

        JSONArray result = new JSONArray();
        for (Competition competition:competitions){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("cID",competition.getCID());
            jsonObject.put("time",competition.getTime());
            jsonObject.put("place",competition.getPlace());
            jsonObject.put("content",competition.getContent());
            jsonObject.put("totalNum",competition.getTotalNum());
            jsonObject.put("joinNum",competition.getJoinNum());
            result.add(jsonObject);
        }
        JSONObject returnJsono = new JSONObject();
        returnJsono.put("pageJson",pageJson);
        returnJsono.put("result",result);
        return returnJsono;
    }

    @Override
    public JSONObject getCompetitionInfoByCID(String cID) {
        JSONObject jsonObject = new JSONObject();
        Competition competition = competitionMapper.getCompetitionInfoByCID(cID);
        jsonObject.put("cID",competition.getCID());
        jsonObject.put("time",competition.getTime());
        jsonObject.put("place",competition.getPlace());
        jsonObject.put("content",competition.getContent());
        jsonObject.put("expire",competition.getExpire());
        return jsonObject;
    }
}
