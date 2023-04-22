package org.example.lulu.controller;


import cn.afterturn.easypoi.entity.ImageEntity;
import cn.afterturn.easypoi.word.WordExportUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.example.lulu.common.PageResult;
import org.example.lulu.common.QueryPageBean;
import org.example.lulu.common.R;
import org.example.lulu.entity.Baoming;
import org.example.lulu.mapper.BaomingMapper;
import org.example.lulu.service.IBaomingService;
import org.example.lulu.util.Base64Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2023-03-28
 */
@Slf4j
@RestController
@RequestMapping("/baoming")
public class BaomingController {
    @Autowired
    private IBaomingService iBaomingService;
    @Autowired
    private BaomingMapper baomingMapper;

    @GetMapping("/getApplyById")
    public Baoming getApplyById(Integer index){
          Baoming baoming=iBaomingService.getBaomingById(index);
          return baoming;
    }


    @GetMapping("/getbaoming")
    public PageResult getclass(QueryPageBean queryPageBean) {
        PageResult pageResult = iBaomingService.pageQuery(queryPageBean);
        return pageResult;
    }

    @GetMapping("/getApply")
    public PageResult getApply(String a, String b, String c, String d, Integer currentPage, Integer pageSize) {
        PageResult pageResult = new PageResult();
        IPage<Baoming> page = new Page<>(currentPage, pageSize);

        LambdaQueryWrapper<Baoming> queryWrapper = new LambdaQueryWrapper<>();
        if (a != null) {
            queryWrapper.eq(Baoming::getSignupState, a);
        }
        if (b != null) {
            queryWrapper.eq(Baoming::getSignupSj, b);
        }
        if (c != null) {
            queryWrapper.eq(Baoming::getClassName, c);
        }
        if (d != null) {
            queryWrapper.like(Baoming::getSignupName, d).or().like(Baoming::getSignupNumber, d).or()
                    .like(Baoming::getSignupHome, d);
        }
        baomingMapper.selectPage(page, queryWrapper);
//          List<Baoming> baomings=baomingMapper.selectList(queryWrapper);
        pageResult.setRows(page.getRecords());
        pageResult.setTotal(page.getTotal());
        R.success("查询成功");
        return pageResult;
    }


    @PreAuthorize("hasAnyAuthority('addB')")
    @PostMapping("/addApply")
    public R<String> addApply(@RequestBody Baoming baoming) {

        if (baoming == null) {
            return R.error("添加失败");
        } else {
            baoming.setCreateTime(String.valueOf(LocalDateTime.now()));
            iBaomingService.save(baoming);
            return R.success("添加成功");
        }

    }
    /**
     * @return
     * @name 导出数据exls
     */
    @GetMapping("exportHttp")
    @PreAuthorize("hasAnyAuthority('exB')")
    public R<String> daoChu(HttpServletResponse response) throws IOException {

                List<Baoming> list = iBaomingService.list();
              // 创建excel
                HSSFWorkbook wk = new HSSFWorkbook();
                // 创建一张工作表
                HSSFSheet sheet = wk.createSheet("报名表");
        //创建第一行
                 HSSFRow row1 = sheet.createRow(0);
                 //为第一行设置每列名字
                  row1.createCell(0).setCellValue("班级");
                  row1.createCell(1).setCellValue("姓名");
                  row1.createCell(2).setCellValue("身份证号");
                  row1.createCell(3).setCellValue("手机号");
                  row1.createCell(4).setCellValue("上级代理");
                  row1.createCell(5).setCellValue("人员类型");
                  row1.createCell(6).setCellValue("学历");
                  row1.createCell(7).setCellValue("性别");
                  row1.createCell(8).setCellValue("民族");
                  row1.createCell(9).setCellValue("户口所在地");
                  row1.createCell(10).setCellValue("家庭住址");
                  row1.createCell(11).setCellValue("报名时间");
                  //插入数据  一条数据一行
             for(int i=0;i<list.size();i++) {
                  row1 = sheet.createRow(i + 1);
                 row1.createCell(0).setCellValue(list.get(i).getClassName());
                 row1.createCell(1).setCellValue(list.get(i).getSignupName());
                 row1.createCell(2).setCellValue(list.get(i).getSignupNumber());
                 row1.createCell(3).setCellValue(list.get(i).getSignupHome());
                 row1.createCell(4).setCellValue(list.get(i).getSignupSj());
                 row1.createCell(5).setCellValue(list.get(i).getSignupGz());
                 row1.createCell(6).setCellValue(list.get(i).getXueli());
                 row1.createCell(7).setCellValue(list.get(i).getSex());
                 row1.createCell(8).setCellValue(list.get(i).getNation());
                 row1.createCell(9).setCellValue(list.get(i).getHukou());
                 row1.createCell(10).setCellValue(list.get(i).getHome());
                 row1.createCell(11).setCellValue(list.get(i).getCreateTime());
             }
//                            Date date = new Date();
//                           SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                            String str = sdf.format(date);
    try {
        //输出Excel文件

        OutputStream output = response.getOutputStream();
        response.reset();
        response.setContentType("application/msexcel");
        String str = "报名表";
        response.setHeader("Content-disposition", "attachment; filename=Baoming.xls");// 默认Excel名称
//        wk.write(new FileOutputStream(new File("D://oo.xls")));
        wk.write(output);
        output.close();

    }catch (IOException e){
        e.printStackTrace();
        }

        return R.success("导出成功");
    }
    //修改报名信息
    @PostMapping("/updateApply")
    @PreAuthorize("hasAnyAuthority('UB')")
    public R<String> updateApply(@RequestBody Baoming baoming){
      return   iBaomingService.updateApply(baoming);
    }
    //下载word
    @PostMapping("/downloadWord")
    public R<String> downloadWord( @RequestBody Integer index){
         Baoming baoming=iBaomingService.getBaomingById(index);
         String ID1=baoming.getLl();
         String ID2=baoming.getQq();
        Base64Image.GenerateImage(ID1,"D:/ID1.png");
        Base64Image.GenerateImage(ID2,"D:/ID2.png");
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Map<String, Object> map = new HashMap<>();
            ImageEntity imageEntity = new ImageEntity();
            imageEntity.setHeight(500);
            imageEntity.setWidth(500);
            if(i==1){
                imageEntity.setUrl("D:/ID2.png");
            }else{
                imageEntity.setUrl("D:/ID1.png");
            }
            imageEntity.setType(ImageEntity.URL);
            map.put("img",imageEntity);
            list.add(map);
        }
        try {
            XWPFDocument doc= WordExportUtil.exportWord07("D:/1.docx",list);
            FileOutputStream fos=new FileOutputStream("D:/个人报名表.docx");
              doc.write(fos);
                fos.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return R.success("导出成功");
        }
  //一键审核成功
    @PostMapping("/audit")
    @PreAuthorize("hasAnyAuthority('SHB')")
    public R<String> audit(@RequestBody Integer[] index){
        for (int i = 0; i <index.length; i++) {
            Baoming baoming = new Baoming();
            baoming.setSignupState(1);
                LambdaQueryWrapper<Baoming> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Baoming::getBaomingId,index[i]);
            iBaomingService.update(baoming,wrapper);
        }
        return R.success("审核成功");
    }
    //审核失败
    @PostMapping("noAudit")
    public R<String> noAudit(@RequestBody Integer[] index){
        for (int i = 0; i <index.length; i++) {
            Baoming baoming = new Baoming();
            baoming.setSignupState(-1);
            LambdaQueryWrapper<Baoming> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Baoming::getBaomingId,index[i]);
            iBaomingService.update(baoming,wrapper);
        }
        return R.success("审核失败");
    }




    }
