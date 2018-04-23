package com.example.hyy.work1;
        import android.os.Handler;
        import android.os.Message;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;

public class MainActivity extends AppCompatActivity { //handler

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button);
        final TextView Test1 = (TextView) findViewById(R.id.textView);
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Test1.setText(msg.arg1 + "");
            }
        };
        final Runnable test = new Runnable() {
            @Override
            public void run() {
                int progress = 0;
                while (progress <= 15) {
                    Message msg = new Message();
                    msg.arg1 = progress;
                    handler.sendMessage(msg);
                    progress += 1;
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread workThread = new Thread(null,test,"Run");
                workThread.start();
            }
        });
    }
}
