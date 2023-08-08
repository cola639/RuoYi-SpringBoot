package com.ruoyi.web.controller.qdpz;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.qdpz.domain.QdpzArticles;
import com.ruoyi.qdpz.service.IQdpzArticlesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * article sheetController
 *
 * @author ruoyi
 * @date 2023-08-08
 */
@RestController
@RequestMapping("/qdpz/articles")
public class QdpzArticlesController extends BaseController {
    @Autowired
    private IQdpzArticlesService qdpzArticlesService;

    /**
     * 查询article sheet列表
     */
    @PreAuthorize("@ss.hasPermi('system:articles:list')")
    @GetMapping("/list")
    public TableDataInfo list(QdpzArticles qdpzArticles) {
        startPage();
        List<QdpzArticles> list = qdpzArticlesService.selectQdpzArticlesList(qdpzArticles);
        return getDataTable(list);
    }

    /**
     * 导出article sheet列表
     */
    @PreAuthorize("@ss.hasPermi('system:articles:export')")
    @Log(title = "article sheet", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, QdpzArticles qdpzArticles) {
        List<QdpzArticles> list = qdpzArticlesService.selectQdpzArticlesList(qdpzArticles);
        ExcelUtil<QdpzArticles> util = new ExcelUtil<QdpzArticles>(QdpzArticles.class);
        util.exportExcel(response, list, "article sheet数据");
    }

    /**
     * 获取article sheet详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:articles:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(qdpzArticlesService.selectQdpzArticlesById(id));
    }

    /**
     * 新增article sheet
     */
    @PreAuthorize("@ss.hasPermi('system:articles:add')")
    @Log(title = "article sheet", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody QdpzArticles qdpzArticles) {
        return toAjax(qdpzArticlesService.insertQdpzArticles(qdpzArticles));
    }

    /**
     * 修改article sheet
     */
    @PreAuthorize("@ss.hasPermi('system:articles:edit')")
    @Log(title = "article sheet", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody QdpzArticles qdpzArticles) {
        return toAjax(qdpzArticlesService.updateQdpzArticles(qdpzArticles));
    }

    /**
     * 删除article sheet
     */
    @PreAuthorize("@ss.hasPermi('system:articles:remove')")
    @Log(title = "article sheet", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(qdpzArticlesService.deleteQdpzArticlesByIds(ids));
    }
}
