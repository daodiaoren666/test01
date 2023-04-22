package org.example.lulu.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.lulu.entity.Menu;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class RoutersVo {
    private List<Menu> menus;


}
