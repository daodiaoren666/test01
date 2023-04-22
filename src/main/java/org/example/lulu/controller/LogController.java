package org.example.lulu.controller;


import org.example.lulu.common.LogMtResult;
import org.example.lulu.common.PageResult;
import org.example.lulu.common.QueryPageBean;
import org.example.lulu.common.R;
import org.example.lulu.entity.Log;
import org.example.lulu.service.LogService;
import org.example.lulu.util.getIp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.UnknownHostException;
import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 露露
 * @since 2023-03-22
 */
   @ResponseBody
@Controller
@RequestMapping("/log")
public class LogController {

   /**
    * 添加日志
    */
   @Autowired
   private LogService logService;
   @PostMapping("/logSave")
   public R<String> logSave(@RequestBody LogMtResult logMtResult) throws UnknownHostException {
      Log log = new Log();
      getIp getIp = new getIp();
      log.setLogName(logMtResult.getManagementName());
      log.setManagId(logMtResult.getManagementId());
      log.setLogState(logMtResult.getCode());
      log.setLogIp(getIp.getTraditionIp());
      log.setLogTime(LocalDateTime.now());
      logService.save(log);
      return R.success("添加日志成功");
   }

   /**
    * 返回log数据
    * @return
    */
   @GetMapping("/getlogAll")
@ResponseBody
   public PageResult getlogAll(QueryPageBean queryPageBean){
         PageResult pageResult=logService.pageQuery(queryPageBean);
         return pageResult;
   }
   @DeleteMapping("/deleteLog/{logId}")
   public R<String> deleteLog(@PathVariable(name = "logId") Integer id){
      logService.removeById(id);
      return R.success("删除成功");
   }

}

