package com.example.christopher.lab3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.hardware.SensorEventListener;
import android.util.Log;
import android .widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    SensorManager _sensorManager;
    TextView _txtview;
    Sensor _giroscope;
    SensorEvent _sensorEvent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _txtview = (TextView) findViewById(R.id.txtview);
        _txtview.setBackgroundColor(Color.RED);

        _sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        _giroscope = _sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        if(_giroscope == null)
        {
            Log.d("Sensor:", "No está activo");
        }

        List<Sensor> _sensorList = _sensorManager.getSensorList(Sensor.TYPE_ALL);
        StringBuilder _sensorText = new StringBuilder();

        for(Sensor _currentSensor: _sensorList){
            _sensorText.append(_currentSensor.getName()).append(System.getProperty("line.separator"));
        }

        _txtview.setText(_sensorText);
    }

    @Override
    protected void onResume(){
        super.onResume();

        SensorEventListener _giroscopeListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent _sensorEvent) {

                    Log.d("Sensor", String.valueOf(_sensorEvent.sensor.getType()));
                    if(_sensorEvent.values[2] > 0.5f){
                        Log.d("Movimiento", String.valueOf(_sensorEvent.values[2]));
                        _txtview.setBackgroundColor(Color.BLUE);
                    }
                    else if(_sensorEvent.values[2] < -0.5f){
                        Log.d("Movimiento", "Nulo");
                        _txtview.setBackgroundColor(Color.RED);
                    }

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };

        _sensorManager.registerListener(_giroscopeListener, _giroscope, SensorManager.SENSOR_DELAY_NORMAL);

    }
}
