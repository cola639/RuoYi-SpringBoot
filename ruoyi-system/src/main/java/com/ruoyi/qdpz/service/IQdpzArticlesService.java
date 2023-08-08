package com.ruoyi.qdpz.service;

import com.ruoyi.qdpz.domain.QdpzArticles;

import java.util.List;

/**
 * article sheetService接口
 *
 * @author ruoyi
 * @date 2023-08-08
 */
public interface IQdpzArticlesService {
    /**
     * 查询article sheet
     *
     * @param id article sheet主键
     * @return article sheet
     */
    public QdpzArticles selectQdpzArticlesById(Long id);

    /**
     * 查询article sheet列表
     *
     * @param qdpzArticles article sheet
     * @return article sheet集合
     */
    public List<QdpzArticles> selectQdpzArticlesList(QdpzArticles qdpzArticles);

    /**
     * 新增article sheet
     *
     * @param qdpzArticles article sheet
     * @return 结果
     */
    public int insertQdpzArticles(QdpzArticles qdpzArticles);

    /**
     * 修改article sheet
     *
     * @param qdpzArticles article sheet
     * @return 结果
     */
    public int updateQdpzArticles(QdpzArticles qdpzArticles);

    /**
     * 批量删除article sheet
     *
     * @param ids 需要删除的article sheet主键集合
     * @return 结果
     */
    public int deleteQdpzArticlesByIds(Long[] ids);

    /**
     * 删除article sheet信息
     *
     * @param id article sheet主键
     * @return 结果
     */
    public int deleteQdpzArticlesById(Long id);
}
