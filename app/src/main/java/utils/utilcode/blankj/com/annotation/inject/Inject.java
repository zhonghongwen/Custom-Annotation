package utils.utilcode.blankj.com.annotation.inject;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Administrator on 2018-01-04.
 */

//Target指定了InjectView注解作用对象是成员变量
//Retention指定了注解有效期直到运行时时期
//value就是用来指定id，也就是findViewById的参数
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Inject {

}