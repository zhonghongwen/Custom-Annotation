package utils.utilcode.blankj.com.annotation.inject;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Administrator on 2018-01-03.
 */
//Target指定了EventType注解作用对象是注解，也就是注解的注解
//    Retention指定了EventType注解有效期直到运行时时期
//    listenerType用来指定点击监听类型，比如OnClickListener
//listenerSetter用来指定设置点击事件方法，比如setOnClickListener
//    methodName用来指定点击事件发生后会回调的方法，比如onClick
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)

public @interface EventType {
    Class listenerType();

    String listenerSetter();

    String methodName();
}

