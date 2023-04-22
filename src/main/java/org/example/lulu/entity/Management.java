package org.example.lulu.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
@TableName(value = "user")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Management {
   private long managementId;
   private String managementGroup;
   private String managementName;
   private String managementMaibox;
   private String managementHome;
   private Boolean managementState;
   private String managementPassword;
   private Integer managementKi;
}
