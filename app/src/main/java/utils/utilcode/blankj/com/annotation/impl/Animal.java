package utils.utilcode.blankj.com.annotation.impl;

import utils.utilcode.blankj.com.annotation.inter.Fly;
import utils.utilcode.blankj.com.annotation.inter.Run;

/**
 * Created by Administrator on 2018-01-03.
 */

public class Animal implements Fly, Run {

    public static final String TAG = "ProxyTest";

    public Animal() {
    }

    @Override
    public String fly() {
       return "fly";
    }

    @Override
    public void run() {
        System.out.println("Animal run");

    }
}
