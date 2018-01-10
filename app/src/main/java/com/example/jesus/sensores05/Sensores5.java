package com.example.jesus.sensores05;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Sensores5 extends AppCompatActivity implements SensorEventListener{
//SensorManager sensorManager;
Sensor orienta;
int contador;
double azimut =0;
double vertical =0;
double lateral =0;
String orientacion="posicion";
TextView tvAzimut;
TextView tvVertical;
TextView tvLateral;
TextView tvOrientacion;
TextView tvContador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensores5);
        tvAzimut = (TextView) findViewById(R.id.azimut);
        tvVertical = (TextView) findViewById(R.id.verticalidad);
        tvLateral = (TextView) findViewById(R.id.lateralidad);
        tvOrientacion = (TextView) findViewById(R.id.orientacion);
        tvContador = (TextView) findViewById(R.id.numeroLecturas);

        SensorManager SensorManager = (android.hardware.SensorManager)getSystemService(Context.SENSOR_SERVICE);
        orienta = SensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        SensorManager.registerListener(this,orienta,SensorManager.SENSOR_DELAY_NORMAL);

        runOnUiThread(new CambiaTexto());
    }

    @Override
    protected void onResume() {
        super.onResume();
        SensorManager SensorManager = (android.hardware.SensorManager)getSystemService(Context.SENSOR_SERVICE);
        SensorManager.registerListener(this, orienta,SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    protected void onPause() {
        super.onPause();
        SensorManager SensorManager = (android.hardware.SensorManager)getSystemService(Context.SENSOR_SERVICE);
        SensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        azimut = event.values[0];
        vertical = event.values[1];
        lateral = event.values[2];
        contador++;

        if (azimut<22) orientacion="NORTE";
        else if (azimut<67) orientacion="NORESTE";
        else if (azimut<112) orientacion="ESTE";
        else if (azimut<157) orientacion="SURESTE";
        else if (azimut<202) orientacion="SUR";
        else if (azimut<247) orientacion="SUROESTE";
        else if (azimut<292) orientacion="OESTE";
        else if (azimut<337) orientacion="NOROESTE";
        else orientacion="NORTE";

        if (vertical<-50) orientacion="VERTICAL ARRIBA";
        if (vertical>50) orientacion="VERTICAL ABAJO";

        if (lateral>50) orientacion="LATERAL IZQUIERDA";
        if (lateral<-50) orientacion="LATERAL DERECHA";
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    class CambiaTexto implements Runnable{
        @Override
        public void run() {
            tvAzimut.setText(""+azimut);
            tvVertical.setText(""+vertical);
            tvLateral.setText(""+lateral);
            tvOrientacion.setText(""+orientacion);
            tvContador.setText(""+contador);

        }
    }
}
