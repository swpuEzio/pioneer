package com.yiling.pioneer.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.yiling.pioneer.model.Competition;
import com.yiling.pioneer.service.admin.AdminGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Seagull_gby
 * @date 2019/9/9 16:51
 * Description: 比赛操作控制层
 */

@RestController
@RequestMapping("/admin")
public class AdminGameControl {

    @Autowired
    private AdminGameService adminGameService;

    /**
     * 添加比赛记录
     * @param request 请求域（包含 比赛信息）
     * @return JSON
     */
    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping("/addGame")
    public JSONObject addGame(HttpServletRequest request) {
        String gameTitle = request.getParameter("gameTitle");
        String gameContent = request.getParameter("gameContent");
        String gameTime = request.getParameter("gameTime");
        String gamePlace = request.getParameter("gamePlace");
        Integer userNum = Integer.valueOf(request.getParameter("userNum"));

        Competition competition = new Competition();
        competition.setTitle(gameTitle);
        competition.setContent(gameContent);
        competition.setTime(gameTime);
        competition.setTotalNum(userNum);
        if(null == gamePlace) {
            gamePlace = "无";
        }
        competition.setPlace(gamePlace);

        JSONObject gameJSON = new JSONObject();
        gameJSON = adminGameService.addGame(competition);

        return gameJSON;
    }

    /**
     * 更新指定比赛信息
     * @param request 请求域（包含 比赛信息）
     * @return JSON
     */
    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping("/updateGame")
    public JSONObject updateGame(HttpServletRequest request) {
        String gameTitle = request.getParameter("gameTitle");
        String gameContent = request.getParameter("gameContent");
        String gameTime = request.getParameter("gameTime");
        String gamePlace = request.getParameter("gamePlace");
        Integer userNum = Integer.valueOf(request.getParameter("userNum"));
        Long gameId = Long.valueOf(request.getParameter("gameId"));

        Competition competition = new Competition();
        competition.setTitle(gameTitle);
        competition.setContent(gameContent);
        competition.setTime(gameTime);
        competition.setTotalNum(userNum);
        competition.setCID(gameId);
        competition.setPlace(gamePlace);

        JSONObject gameUpdateJSON = new JSONObject();
        gameUpdateJSON = adminGameService.updateGame(competition);

        return gameUpdateJSON;
    }

    /**
     * 删除指定比赛记录
     * @param request 请求域（包含 比赛编号）
     * @return JSON
     */
    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping("/deleteGame")
    public JSONObject deleteGame(HttpServletRequest request) {
        Long gameId = Long.valueOf(request.getParameter("gameId"));

        JSONObject gameDeleteJSON = new JSONObject();
        gameDeleteJSON = adminGameService.deleteGame(gameId);

        return gameDeleteJSON;
    }

    /**
     * 查询指定比赛信息
     * @param request 请求域（包含 比赛编号）
     * @return JSON
     */
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/getGameInformation")
    public JSONObject getGameInformation(HttpServletRequest request) {
        Long gameId = Long.valueOf(request.getParameter("gameId"));

        JSONObject gameInformationJSON = new JSONObject();
        gameInformationJSON = adminGameService.getGameInformation(gameId);

        return gameInformationJSON;
    }

    /**
     * 获得全部比赛（指引）
     * @return JSON
     */
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/getAllGame")
    public JSONObject getAllGame() {
        JSONObject gameJSON = new JSONObject();
        gameJSON = adminGameService.getAllGame();

        return gameJSON;
    }
}
