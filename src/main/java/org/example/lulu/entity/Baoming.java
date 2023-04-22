package org.example.lulu.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2023-03-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("baoming")
@ApiModel(value="Baoming对象", description="")
public class Baoming implements Serializable {

    private static final long serialVersionUID = 1L;


    private Integer classId;


    private String className;


    private String signupName;


    private String signupSj;


    private String signupNumber;


    private String signupHome;


    private Integer signupState;


    private String signupGz;

    private String xueli;

    private String sex;

    private String birth;

    private String nation;

    private String hukou;

    private String home;
   @TableField(value = "ID1")
    private String ll;
    @TableField(value = "ID2")
    private String qq;
    @TableField(value = "createTime")
    private String createTime;
    @TableId
    private Integer baomingId;
}
