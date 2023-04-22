package org.example.lulu.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class R<T> {
    public R(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    private Integer code;
    private String msg;
    private T data;
    public static<T> R<T>  success(Integer code, String msg,T data){
        R<T> r=new R<>();
        r.code=code;
        r.msg=msg;
        r.data=data;
        return r;
    }


    public static<T> R<T>  success(Integer code, String msg){
          R<T> r=new R<>();
          r.code=code;
          r.msg=msg;
          return r;
    }
    public static<T> R<T>  success(T object){
        R<T> r=new R<>();
        r.data=object;
        r.code=1;  //1成功  0失败
        return r;
    }
     public static <T> R<T> error(String msg){
         R<T> r=new R<>();
         r.code=0;
         r.msg=msg;
         return r;
     }
}
