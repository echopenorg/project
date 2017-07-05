package com.echopen.asso.echopen;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.Button;

// Probe imports
import com.echopen.asso.echopen.echography_image_streaming.EchographyImageStreamingService;
import com.echopen.asso.echopen.echography_image_streaming.modes.EchographyImageStreamingMode;
import com.echopen.asso.echopen.echography_image_streaming.modes.EchographyImageStreamingTCPMode;
import com.echopen.asso.echopen.echography_image_visualisation.EchographyImageVisualisationContract;
import com.echopen.asso.echopen.echography_image_visualisation.EchographyImageVisualisationPresenter;

/**
 * Created by yanis on 04/07/2017.
 */

public class HomeScreenActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);
        EchOpenApplication app = (EchOpenApplication) getApplication();
        EchographyImageStreamingService stream = app.getEchographyImageStreamingService();

        EchographyImageVisualisationPresenter presenter = new EchographyImageVisualisationPresenter(stream, new EchographyImageVisualisationContract.View(){
            @Override
            public void refreshImage(final Bitmap iBitmap){
                try{
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ImageView echoImage = (ImageView) findViewById(R.id.echography);

                            Display display = getWindowManager().getDefaultDisplay();
                            Point size = new Point();
                            display.getSize(size);
                            int width = size.x;
                            int height = size.y;


                            echoImage.setImageBitmap(iBitmap);
                            echoImage.setScaleType(ImageView.ScaleType.CENTER_CROP);

                            System.out.println("Bitmap received");
                            Log.d("Debug",iBitmap.getHeight() + "");
                            Log.d("Debug",iBitmap.getWidth() + "");
                        }
                    });
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void setPresenter(EchographyImageVisualisationContract.Presenter presenter){

            }
        });

        EchographyImageStreamingMode mode = new EchographyImageStreamingTCPMode("10.6.200.128", 7538);

        stream.connect(mode,this);
        presenter.start();
        startHome();
    }

    /**
     * Start home features
     */
    private void startHome()
    {

    }
}
