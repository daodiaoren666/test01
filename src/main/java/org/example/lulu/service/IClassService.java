package org.example.lulu.service;

import org.example.lulu.common.PageResult;
import org.example.lulu.common.QueryPageBean;
import org.example.lulu.entity.Class;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2023-03-28
 */
public interface IClassService extends IService<Class> {
    public PageResult pageQuery(QueryPageBean queryPageBean);

}
