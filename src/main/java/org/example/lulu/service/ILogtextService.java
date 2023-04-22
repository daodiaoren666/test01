package org.example.lulu.service;

import org.example.lulu.common.PageResult;
import org.example.lulu.common.QueryPageBean;
import org.example.lulu.entity.Logtext;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2023-04-05
 */
public interface ILogtextService extends IService<Logtext> {
    public PageResult pageQuery(QueryPageBean queryPageBean);

}
