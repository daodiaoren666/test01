package org.example.lulu.service;

import org.example.lulu.common.PageResult;
import org.example.lulu.common.QueryPageBean;
import org.example.lulu.entity.Log;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 露露
 * @since 2023-03-22
 */
public interface LogService extends IService<Log> {
    public PageResult pageQuery(QueryPageBean queryPageBean);
}
