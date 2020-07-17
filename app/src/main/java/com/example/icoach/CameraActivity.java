package com.example.icoach;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.SurfaceView;
import android.widget.Toast;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.JavaCameraView;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

public class CameraActivity extends AppCompatActivity implements CameraBridgeViewBase.CvCameraViewListener2 {

    CameraBridgeViewBase cb;
    Mat mat1,mat2,mat3;
    BaseLoaderCallback bc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        cb = (JavaCameraView) findViewById(R.id.myCV);
        cb.setVisibility(SurfaceView.VISIBLE);
        cb.setCvCameraViewListener(this);
        cb.enableView();

        bc = new BaseLoaderCallback(this) {
            @Override
            public void onManagerConnected(int status) {
                super.onManagerConnected(status);
                switch(status){
                    case BaseLoaderCallback.SUCCESS:
                        cb.enableView();
                        break;
                    default:
                        super.onManagerConnected(status);
                        break;
                }
            }
        };

    }

    @Override
    public void onCameraViewStarted(int width, int height) {
        mat1 = new Mat(width,height, CvType.CV_8SC4);
        mat2 = new Mat(width,height, CvType.CV_8SC4);
        mat3 = new Mat(width,height, CvType.CV_8SC4);
    }

    @Override
    public void onCameraViewStopped() {
        mat1.release();
        mat2.release();
        mat3.release();

    }

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        mat1 = inputFrame.rgba();
        return mat1;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(cb != null){
            cb.disableView();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!OpenCVLoader.initDebug()){
            Toast.makeText(getApplicationContext(),"There is a problem in openCV",Toast.LENGTH_SHORT).show();
        }
        else{
            bc.onManagerConnected(bc.SUCCESS);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(cb!=null){
            cb.disableView();
        }
    }
}