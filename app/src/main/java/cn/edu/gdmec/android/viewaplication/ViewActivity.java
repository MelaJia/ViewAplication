package cn.edu.gdmec.android.viewaplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class ViewActivity extends AppCompatActivity implements MediaPlayer.OnPreparedListener,SurfaceHolder.Callback {
    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;
    private MediaPlayer mediaPlayer;
    private SeekBar seekBar;
    private Timer timer;
    private TimerTask timerTask;
    private boolean isChange = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        surfaceView = (SurfaceView)findViewById(R.id.surfaceview);
        mediaPlayer = new MediaPlayer();

        try {
            mediaPlayer.setDataSource(Environment.getExternalStorageDirectory().getAbsolutePath()+"/beyond.mp4");
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        mediaPlayer.setOnPreparedListener(this);
        seekBar = (SeekBar)findViewById(R.id.seekbar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                isChange=true;

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
                isChange=false;

            }
        });
        timer = new Timer();







    }
    public void intent(View view){
        Uri uri = Uri.parse("http://android2017.duapp.com/beyond.mp4");
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(uri,"video/mp4");
        startActivity(intent);

    }
    public void videoview(View view){
        Uri uri = Uri.parse(Environment.getExternalStorageDirectory().getAbsolutePath()+"/beyond.mp4");
        VideoView videoView = (VideoView) findViewById(R.id.videoview);
        videoView.setMediaController(new MediaController(this));
        videoView.setVideoURI(uri);
        videoView.start();
        videoView.requestFocus();


    }


    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }
}
