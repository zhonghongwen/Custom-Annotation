package utils.utilcode.blankj.com.annotation.utils;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import utils.utilcode.blankj.com.annotation.handler.ProxyHandler;
import utils.utilcode.blankj.com.annotation.inject.EventType;
import utils.utilcode.blankj.com.annotation.inject.Inject;
import utils.utilcode.blankj.com.annotation.inject.InjectView;
import utils.utilcode.blankj.com.annotation.inject.onClick;

/**
 * Created by Administrator on 2018-01-03.
 */

public class InjectUtils {
    public static void injectView(Activity activity) {
        if (null == activity) return;
        //获取activity的.class对象
        Class<? extends Activity> activityClass = activity.getClass();
//        Constructor<? extends Activity> declaredConstructor = activityClass.getDeclaredConstructor();
//        获取所有成员变量集合
        Field[] declaredFields = activityClass.getDeclaredFields();

        for (Field field : declaredFields) {

            //找到有InjectView注解的成员变量
            if (field.isAnnotationPresent(InjectView.class)) {
                //得到注解类的对象
                InjectView annotation = field.getAnnotation(InjectView.class);
                //找到button的id
                int value = annotation.value();
                try {
                    //找到findViewById方法
                    Method findViewByIdMethod = activityClass.getMethod("findViewById", int.class);
                    findViewByIdMethod.setAccessible(true);
                    View view = (View) findViewByIdMethod.invoke(activity, value);
                    //将结果赋值给成员变量
                    field.set(activity, view);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void injectEvent(Activity activity) {
        if (null == activity) {
            return;
        }
        //获取activity的.class对象
        Class<? extends Activity> activityClass = activity.getClass();
        //        获取所有成员方法集合
        Method[] declaredMethods = activityClass.getDeclaredMethods();

        for (Method method : declaredMethods) {
            //该方法上是否存在@onClick这个注解
            if (method.isAnnotationPresent(onClick.class)) {
                //得到注解类的对象
                onClick annotation = method.getAnnotation(onClick.class);
                //获取控件id的集合
                int[] value = annotation.value();
                //获取注解类中的注解
                EventType eventType = annotation.annotationType().getAnnotation(EventType.class);
                Class listenerType = eventType.listenerType();
                String listenerSetter = eventType.listenerSetter();
                String methodName = eventType.methodName();

                //创建InvocationHandler和动态代理(代理要实现listenerType，这个例子就是处理onClick点击事件)
                ProxyHandler proxyHandler = new ProxyHandler(activity);
                //得到实现类的接口对象（1:实现类的类加载器2：实现类的借口数组，proxyHandler对象：执行接口的方法时会先执行里面的invoke，再执行实际方法，实现动态代理）且只有接口中的方法
                Object listener = Proxy.newProxyInstance(listenerType.getClassLoader(), new Class[]{listenerType}, proxyHandler);
                //  onClick和InvokeBtnClick
                proxyHandler.mapMethod(methodName, method);
                try {
                    for (int id : value) {
                        //找到Button
                        Method findViewByIdMethod = activityClass.getMethod("findViewById", int.class);
                        findViewByIdMethod.setAccessible(true);
                        View btn = (View) findViewByIdMethod.invoke(activity, id);
                        //根据listenerSetter方法名和listenerType方法参数找到method
                        Method listenerSetMethod = btn.getClass().getMethod(listenerSetter, listenerType);
                        listenerSetMethod.setAccessible(true);
                        //反射执行setOnclickListener
                        listenerSetMethod.invoke(btn, listener);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void inject(Activity activity) {
        if (null == activity) {
            return;
        }
        //获取activity的.class对象
        Class<? extends Activity> activityClass = activity.getClass();
//        Constructor<? extends Activity> declaredConstructor = activityClass.getDeclaredConstructor();
        //        获取所有成员变量集合
        Field[] declaredFields = activityClass.getDeclaredFields();
        for (Field field : declaredFields) {
            //找到有InjectView注解的成员变量
            if (field.isAnnotationPresent(Inject.class)) {
                //得到成员变量的类型
                Class<?> clazz = field.getType();

                Constructor con = null;
                try {

                    con = clazz.getConstructor();
                    //获取其对象
                    Object instance = con.newInstance();
                    //赋值给field
                    field.set(activity,instance);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
