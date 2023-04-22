package org.example.lulu.controller;


import org.example.lulu.common.PageResult;
import org.example.lulu.common.QueryPageBean;
import org.example.lulu.service.IClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2023-03-28
 */
@RestController
@RequestMapping("/class")
public class ClassController {
    @Autowired
    private IClassService iClassService;

    @GetMapping("/getclass")
    public PageResult getclass(QueryPageBean queryPageBean){
       PageResult pageResult=iClassService.pageQuery(queryPageBean);
       return pageResult;
    }

}

