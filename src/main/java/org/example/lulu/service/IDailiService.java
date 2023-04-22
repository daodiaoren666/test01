package org.example.lulu.service;

import org.example.lulu.common.PageResult;
import org.example.lulu.common.QueryPageBean;
import org.example.lulu.entity.Daili;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2023-04-02
 */
public interface IDailiService extends IService<Daili> {
    public PageResult getdailiAll(QueryPageBean queryPageBean);

}
