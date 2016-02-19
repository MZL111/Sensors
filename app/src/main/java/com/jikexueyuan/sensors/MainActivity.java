package com.jikexueyuan.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

/**
 *  传感器
 */

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    private SensorManager sensorManager;
    private Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获取系统服务里的传感服务
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        //获取所有传感器放进list中，
//        List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);

        //输出所有传感器的名字
//        for (Sensor s:sensorList){
//            System.out.println("传感器：" + s.getName());
//        }

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        switch(event.sensor.getType()){
            case Sensor.TYPE_PROXIMITY:
                //输出距离传感器的值
                System.out.println(event.values[0]);
                break;
            case Sensor.TYPE_ACCELEROMETER:
                //输出加速传感器坐标
                System.out.format("x=%f,y=%f,z=%f\n",event.values[0],event.values[1],event.values[2]);
                break;
            case Sensor.TYPE_ORIENTATION:
//                手机头指向北的时候数据是0
                System.out.format("value:%f\n",event.values[0]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();

        /**
         * 注册传感器
         */

        //获取距离传感器
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        //注册传感器监听事件，
        // SENSOR_DELAY_NORMAL:传输速度是普通模式
        sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL);

        //获取加速传感器
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);

        //获取方向传感器 orientation
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();

        /**
         * 注销传感器
         */
        sensorManager.unregisterListener(this);
    }
}
