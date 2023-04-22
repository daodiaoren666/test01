package org.example.lulu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.lulu.common.PageResult;
import org.example.lulu.common.QueryPageBean;
import org.example.lulu.common.R;
import org.example.lulu.entity.Baoming;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2023-03-28
 */
public interface IBaomingService extends IService<Baoming> {
    public PageResult pageQuery(QueryPageBean queryPageBean);
    public Baoming getBaomingById(Integer index);
    public R<String> updateApply(Baoming baoming);

}
