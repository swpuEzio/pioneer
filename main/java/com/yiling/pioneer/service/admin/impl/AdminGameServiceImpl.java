package com.yiling.pioneer.service.admin.impl;

import com.alibaba.fastjson.JSONObject;
import com.yiling.pioneer.dto.entity.GameIndex;
import com.yiling.pioneer.dto.json.GameJsonResult;
import com.yiling.pioneer.dto.json.JsonContext;
import com.yiling.pioneer.mapper.AdminGameMapper;
import com.yiling.pioneer.model.Competition;
import com.yiling.pioneer.service.admin.AdminGameService;
import com.yiling.pioneer.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Seagull_gby
 * @date 2019/9/9 16:42
 * Description: 管理端比赛操作实现
 */

@Service
public class AdminGameServiceImpl implements AdminGameService {

    @Autowired
    private AdminGameMapper adminGameMapper;

    @Override
    public JSONObject addGame(Competition competition) {
        JSONObject gameJSON = new JSONObject();

        /* 用时间戳后8位作为比赛ID */
        Long gameId = Long.valueOf(TimeUtils.TimeCrateID());
        competition.setCID(gameId);

        /* 将比赛信息添加进数据库 */
        int insertNum = adminGameMapper.insertGame(competition);

        /* JSON封装 */
        JsonContext jsonContext = new JsonContext(new GameJsonResult<>());
        if(insertNum >= 1) {
            gameJSON = jsonContext.success(null);
        } else {
            gameJSON = jsonContext.fail("未知错误，插入失败！");
        }

        return gameJSON;
    }

    @Override
    public JSONObject updateGame(Competition competition) {
        JSONObject gameUpdateJSON = new JSONObject();

        /* 更新数据库中指定比赛信息 */
        int updateNum = adminGameMapper.updateGame(competition);

        /* JSON封装 */
        JsonContext jsonContext = new JsonContext(new GameJsonResult<>());
        if(updateNum >= 1) {
            gameUpdateJSON = jsonContext.success(null);
        } else {
            gameUpdateJSON = jsonContext.fail("数据库中无此比赛信息，更新失败！");
        }

        return gameUpdateJSON;
    }

    @Override
    public JSONObject deleteGame(Long gameId) {
        JSONObject gameDeleteJSON = new JSONObject();

        /* 删除数据库中指定比赛记录 */
        int deleteNum = adminGameMapper.deleteGame(gameId);

        /* JSON封装 */
        JsonContext jsonContext = new JsonContext(new GameJsonResult<>());
        if(deleteNum >= 1) {
            gameDeleteJSON = jsonContext.success(null);
        } else {
            gameDeleteJSON = jsonContext.fail("数据库中无此比赛信息，删除失败！");
        }

        return gameDeleteJSON;
    }

    @Override
    public JSONObject getGameInformation(Long gameId) {
        JSONObject gameInformationJSON = new JSONObject();

        /* 从数据库中查询指定比赛信息 */
        Competition competition = new Competition();
        competition = adminGameMapper.queryGameOfId(gameId);

        /* JSON封装 */
        JsonContext<Competition> jsonContext = new JsonContext(new GameJsonResult<>());
        if(null == competition) {
            gameInformationJSON = jsonContext.fail("查询失败，无此比赛信息！");
        } else {
            gameInformationJSON = jsonContext.success(competition);
        }

        return gameInformationJSON;
    }

    @Override
    public JSONObject getAllGame() {
        JSONObject gameJSON = new JSONObject();

        /* 从数据库中查询所有比赛记录（指引） */
        List<GameIndex> gameIndices = new ArrayList<>();
        gameIndices = adminGameMapper.queryAllGame();

        /* JSON封装 */
        JsonContext<List<GameIndex>> jsonContext = new JsonContext(new GameJsonResult<>());
        gameJSON = jsonContext.success(gameIndices);

        return gameJSON;
    }

}
