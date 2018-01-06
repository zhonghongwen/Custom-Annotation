package utils.utilcode.blankj.com.annotation.inject;

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Administrator on 2018-01-03.
 */

//    Target指定了onClick注解作用对象是成员方法
//    Retention指定了onClick注解有效期直到运行时时期
//    value就是用来指定id，也就是findViewById的参数



@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@EventType(listenerType = View.OnClickListener.class, listenerSetter = "setOnClickListener", methodName = "onClick")
public @interface onClick {
    int[] value();
}
