package com.yiling.pioneer.service.admin;

import com.alibaba.fastjson.JSONObject;
import com.yiling.pioneer.model.Competition;
import org.springframework.stereotype.Service;

/**
 * @author Seagull_gby
 * @date 2019/9/9 16:40
 * Description: 管理端比赛操作接口
 */

@Service
public interface AdminGameService {

    /**
     * 添加比赛记录
     * @param competition 比赛记录实体
     * @return JSON
     */
    public JSONObject addGame(Competition competition);

    /**
     * 更新指定比赛记录
     * @param competition 比赛记录实体
     * @return JSON
     */
    public JSONObject updateGame(Competition competition);

    /**
     * 删除指定比赛记录
     * @param gameId 比赛记录编号
     * @return JSON
     */
    public JSONObject deleteGame(Long gameId);

    /**
     * 查询指定比赛记录
     * @param gameId 比赛编号
     * @return JSON
     */
    public JSONObject getGameInformation(Long gameId);

    /**
     * 查询所有比赛记录（指引）
     * @return JSON
     */
    public JSONObject getAllGame();
}
