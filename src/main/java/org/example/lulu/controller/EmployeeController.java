package org.example.lulu.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.example.lulu.common.PageResult;
import org.example.lulu.common.QueryPageBean;
import org.example.lulu.common.R;
import org.example.lulu.common.StateResult;
import org.example.lulu.entity.LoginUser;
import org.example.lulu.entity.Management;
import org.example.lulu.entity.Menu;
import org.example.lulu.mapper.EmployeeMapper;
import org.example.lulu.mapper.MenuMapper;
import org.example.lulu.mapper.RoleMapper;
import org.example.lulu.service.EmployeeService;
import org.example.lulu.service.IMenuService;
import org.example.lulu.service.MyInvocationSecurityMetadataSourceService;
import org.example.lulu.util.AutionUtis;
import org.example.lulu.vo.UserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/")
@RestController
public class EmployeeController {
    @Autowired
    private EmployeeMapper employeeMapper;
   @Autowired
   private org.example.lulu.service.toolService toolService;
     @Autowired
     private IMenuService iMenuService;

     @Autowired
     private MenuMapper menuMapper;
@Autowired
private AutionUtis autionUtis;
    @Autowired
  private RoleMapper roleMapper;
    @Autowired
    private MyInvocationSecurityMetadataSourceService cs;

    @Autowired
   private EmployeeService employeeService;





    //修改用户权限
    @PostMapping("/alterAution/{userid}")
    public R<String> alterAution(@RequestBody(required = false) String roleList ,@PathVariable Long userid){
        roleList=roleList.substring(1,roleList.length()-1);
         roleMapper.updateMenuListById(roleList,userid);
        //获取当前登录用户
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser  loginUser =(LoginUser) authentication.getPrincipal();
        Management user=loginUser.getManagement();
        //更新权限信息
        List<String> list = autionUtis.getMenuList(userid);
        return R.success("修改成功");
    }
    //获取用户对应的权限信息
    @GetMapping("/getAllRouters")
    public R<List<Menu>> getAllRouters(){
       List<Menu> menus=iMenuService.selectRouterMenuByUserId(1L);;
       return R.success(menus);
    }
    //获取当前用户权限所对应的ID
 @GetMapping("/getRoutersAllId")
    public  R<List>  getRoutersAllId(Long userid){
      String[] menulist;
     List<Integer> list1 =new ArrayList<>();
     String str=menuMapper.selectRoleMenuList(userid);
        menulist=str.split(",");
     for(String e:menulist){
         list1.add(Integer.valueOf(e));
     }
        return R.success(list1);
 }

    //获取当前用户权限
@GetMapping("/getRouters")
    public R<List<Menu>>  getRouters(){
        //获取当前登录用户
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser  loginUser =(LoginUser) authentication.getPrincipal();
        Management user=loginUser.getManagement();
        Long id=loginUser.getManagement().getManagementId();

        List<Menu> menus=iMenuService.selectRouterMenuByUserId(id);
        return R.success(menus);
    }
@GetMapping("/getinfo")
    private R<UserInfoVo> getInfo(){
        //获取当前登录用户
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      LoginUser  loginUser =(LoginUser) authentication.getPrincipal();
      Management user=loginUser.getManagement();
      Long id=loginUser.getManagement().getManagementId();
      //根据id查询用户权限
    List<String> perms=menuMapper.selectPermsByUserId(id);
    //根据id查询角色信息
    List<String> roleKeyList=roleMapper.selectRoleByUserId(id);
    //封装数据
    UserInfoVo userInfoVo = new UserInfoVo(perms,roleKeyList,user);
    return R.success(userInfoVo);
}


    @PostMapping("/login")
    public R login(HttpServletRequest request, @RequestBody Management management){
        return employeeService.login(management);
    }

    /**
     * 查询管理员列表
     * @return
     */
   //分页查询
    @PostMapping("/employee/postAll")
    public PageResult pageAll(@RequestBody QueryPageBean queryPageBean){
        PageResult   pageResult=toolService.pageQuery(queryPageBean);
        return  pageResult;
    }

    /**
     * 新建管理员
     * @param management
     * @return
     */
    @PostMapping("/employee/insertMt")
    public R<String> insertMt(@RequestBody  Management management){
        if(management==null) {
           return R.error("信息填写错误");
        }else {
            employeeService.save(management);
            return R.success("添加管理员成功");
        }
    }

    @PostMapping("/employee/getstate")
      public R<String> getState(@RequestBody StateResult stateResult){
        Integer id=stateResult.getId();
        Integer state=stateResult.getZ();
        UpdateWrapper<Management> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("management_Ki",state);
        updateWrapper.eq("management_id",id);
        employeeService.update(null,updateWrapper);
        return R.success("操作成功");
    }

    @GetMapping("/employee/getid")
    public List<Management> getById(Integer a){
        LambdaQueryWrapper<Management> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Management::getManagementId,a);
        return employeeService.list(queryWrapper);
    }
    //修改管理员数据
    @PostMapping("/employee/updateMt")
    public  R<String> updateMt(@RequestBody Management management){
        long id=management.getManagementId();
//        UpdateWrapper<Management> updateWrapper = new UpdateWrapper<>();
//        updateWrapper.eq()
        employeeService.updateById(management);
        return R.success("修改成功");
    }
    @DeleteMapping("/employee/updateMt")
    public R<String> deleteMt( Integer id){
        LambdaQueryWrapper<Management> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Management::getManagementId,id);
        employeeService.remove(queryWrapper);
        return R.success("删除成功");
    }

    //退出登录
    @GetMapping("/employee/logout")
    public R  logout(){
        return employeeService.logout();
    }
}
