package com.jikexueyuan.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

/**
 *  距离传感器
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

        //获取距离传感器
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        //注册传感器监听事件，
        // SENSOR_DELAY_NORMAL:传输速度是普通模式
        sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        switch(event.sensor.getType()){
            case Sensor.TYPE_PROXIMITY:
                System.out.println(event.values[0]);
                break;

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
