package com.zgss.grib.contour.util;

/**
 * @program: grib-collect-sys
 * @description: 数字操作工具类
 * @author: ffye
 * @create: 2019-08-29 11:31
 */
public class ArrayUtil {

    /**
     * 反转
     * @param arr
     */
    public static void reverseArray(int[] arr){
        for(int i=0;i<arr.length/2;i++){
            int temp=arr[i];
            arr[i]=arr[(arr.length)-1-i];
            arr[(arr.length)-1-i]=temp;
        }
    }

    /**
     * 反转
     * @param arr
     */
    public static void reverseArray(int[][] arr){
        for(int i=0;i<arr.length/2;i++){
            int[] temp=arr[i];
            arr[i]=arr[(arr.length)-1-i];
            arr[(arr.length)-1-i]=temp;
        }
    }

    /**
     * 反转
     * @param arr
     */
    public static void reverseArray(float[] arr){
        for(int i=0;i<arr.length/2;i++){
            float temp=arr[i];
            arr[i]=arr[(arr.length)-1-i];
            arr[(arr.length)-1-i]=temp;
        }
    }

    /**
     * 反转
     * @param arr
     */
    public static void reverseArray(float[][] arr){
        for(int i=0;i<arr.length/2;i++){
            float[] temp=arr[i];
            arr[i]=arr[(arr.length)-1-i];
            arr[(arr.length)-1-i]=temp;
        }
    }

    /**
     * 反转
     * @param arr
     */
    public static void reverseArray(double[] arr){
        for(int i=0;i<arr.length/2;i++){
            double temp=arr[i];
            arr[i]=arr[(arr.length)-1-i];
            arr[(arr.length)-1-i]=temp;
        }
    }

    /**
     * 反转
     * @param arr
     */
    public static void reverseArray(double[][] arr){
        for(int i=0;i<arr.length/2;i++){
            double[] temp=arr[i];
            arr[i]=arr[(arr.length)-1-i];
            arr[(arr.length)-1-i]=temp;
        }
    }
}
