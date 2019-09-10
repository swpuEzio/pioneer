package com.yiling.pioneer.dto.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Seagull_gby
 * @date 2019/9/9 20:09
 * Description: 比赛指引实体包装，包含比赛部分信息
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameIndex {

    /**
     * 比赛编号
     */
    private Long gameId;

    /**
     * 比赛标题
     */
    private String gameTitle;
}
