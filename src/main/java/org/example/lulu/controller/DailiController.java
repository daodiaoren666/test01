package org.example.lulu.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.lulu.common.PageResult;
import org.example.lulu.common.QueryPageBean;
import org.example.lulu.common.R;
import org.example.lulu.entity.Daili;
import org.example.lulu.mapper.DailiMapper;
import org.example.lulu.service.IDailiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2023-04-02
 */
@RestController
@RequestMapping("/daili")
public class DailiController {
    @Autowired
    IDailiService iDailiService;
    @Autowired
    DailiMapper dailiMapper;
    //删除
    @PostMapping("/deleteAgencyById")
    @PreAuthorize("hasAnyAuthority('deleteD')")
    public R<String> deleteAgencyById(@RequestBody Integer index){
        iDailiService.removeById(index);
        return R.success("删除成功");
    }
    //添加代理
@PostMapping("/insertAgency")
@PreAuthorize("hasAnyAuthority('addD')")
    public R<String> insertAgency(@RequestBody Daili daili){
        daili.setDailiUpdatetime(LocalDateTime.now());
        daili.setDailiXj("0");
        daili.setDailiUpdatetime(LocalDateTime.now());
        daili.setDailiNumber(0);
        iDailiService.save(daili);
    return R.success("修改成功");
    }
 //根据id修改信息
@PostMapping("/updateAgency")
@PreAuthorize("hasAnyAuthority('alterD')")
    public R<String> updateAgency(@RequestBody Daili daili,HttpServletResponse response) {
    daili.setDailiUpdatetime(LocalDateTime.now());
    iDailiService.updateById(daili);

    return R.success("修改成功");
}
    //根据id查询信息
  @GetMapping("/getAgencyById/{index}")
  public Daili getAgencyById(@PathVariable("index") Integer index, HttpServletResponse response) {
        R.success("查询成功");
        response.setStatus(200  );
        return iDailiService.getById(index);
  }
@GetMapping("/getdailiAll")
    public PageResult getdailiAll(QueryPageBean queryPageBean) {
        PageResult pageResult=iDailiService.getdailiAll(queryPageBean);
        R.error("查询成功");
        return pageResult;
    }
    //搜索功能
@GetMapping("/getdailiIf")
    public PageResult getdailiIf(String a, String b, Integer c, Integer d) {
    IPage<Daili> page = new Page<>(c, d);
    PageResult pageResult=new PageResult();
    LambdaQueryWrapper<Daili> wrapper = new LambdaQueryWrapper<>();
    if (a != null) {
        if(!a.equals("全部")){
            wrapper.eq(Daili::getDailiLv, a);
        }
    }
    if(b!=null) {
        wrapper.like(Daili::getDailiName, b);
    }
      dailiMapper.selectPage(page,wrapper);
    pageResult.setRows(page.getRecords());
    pageResult.setTotal(page.getTotal());
    R.success("操作成功");
    return pageResult;
}
}


