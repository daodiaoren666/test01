package org.example.lulu.controller;


import org.example.lulu.common.PageResult;
import org.example.lulu.common.QueryPageBean;
import org.example.lulu.common.R;
import org.example.lulu.entity.Logtext;
import org.example.lulu.service.ILogtextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2023-04-05
 */
@RestController
@RequestMapping("/logtext")
public class LogtextController {
    @Autowired
    ILogtextService logtextService;
    @PostMapping("/update")
  public R<String> updateLog( @RequestBody String str){
        Logtext logtext = new Logtext();
        logtext.setManagementId(1);
        logtext.setManagementName("admin");
        logtext.setManagementText(str);
        logtext.setManagementTime(LocalDateTime.now());
        logtextService.save(logtext);
        return R.success("添加成功");
    }

    /**
     * 添加日志
     */


    /**
     * 返回log数据
     * @return
     */
    @GetMapping("/getlogAll")
    @ResponseBody
    public PageResult getlogAll(QueryPageBean queryPageBean){
        PageResult pageResult=logtextService.pageQuery(queryPageBean);
        return pageResult;
    }


}

