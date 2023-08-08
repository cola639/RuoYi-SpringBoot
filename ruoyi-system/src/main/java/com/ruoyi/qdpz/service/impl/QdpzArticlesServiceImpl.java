package com.ruoyi.qdpz.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.qdpz.domain.QdpzArticles;
import com.ruoyi.qdpz.mapper.QdpzArticlesMapper;
import com.ruoyi.qdpz.service.IQdpzArticlesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * article sheetService业务层处理
 *
 * @author ruoyi
 * @date 2023-08-08
 */
@Service
public class QdpzArticlesServiceImpl implements IQdpzArticlesService {
    @Autowired
    private QdpzArticlesMapper qdpzArticlesMapper;

    /**
     * 查询article sheet
     *
     * @param id article sheet主键
     * @return article sheet
     */
    @Override
    public QdpzArticles selectQdpzArticlesById(Long id) {
        return qdpzArticlesMapper.selectQdpzArticlesById(id);
    }

    /**
     * 查询article sheet列表
     *
     * @param qdpzArticles article sheet
     * @return article sheet
     */
    @Override
    public List<QdpzArticles> selectQdpzArticlesList(QdpzArticles qdpzArticles) {
        return qdpzArticlesMapper.selectQdpzArticlesList(qdpzArticles);
    }

    /**
     * 新增article sheet
     *
     * @param qdpzArticles article sheet
     * @return 结果
     */
    @Override
    public int insertQdpzArticles(QdpzArticles qdpzArticles) {
        qdpzArticles.setCreateTime(DateUtils.getNowDate());
        return qdpzArticlesMapper.insertQdpzArticles(qdpzArticles);
    }

    /**
     * 修改article sheet
     *
     * @param qdpzArticles article sheet
     * @return 结果
     */
    @Override
    public int updateQdpzArticles(QdpzArticles qdpzArticles) {
        return qdpzArticlesMapper.updateQdpzArticles(qdpzArticles);
    }

    /**
     * 批量删除article sheet
     *
     * @param ids 需要删除的article sheet主键
     * @return 结果
     */
    @Override
    public int deleteQdpzArticlesByIds(Long[] ids) {
        return qdpzArticlesMapper.deleteQdpzArticlesByIds(ids);
    }

    /**
     * 删除article sheet信息
     *
     * @param id article sheet主键
     * @return 结果
     */
    @Override
    public int deleteQdpzArticlesById(Long id) {
        return qdpzArticlesMapper.deleteQdpzArticlesById(id);
    }
}
