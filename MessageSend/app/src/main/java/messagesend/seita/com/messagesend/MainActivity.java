package messagesend.seita.com.messagesend;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends Activity implements  OnCheckedChangeListener, View.OnClickListener{

    //��M�Ǘ��pAsyncTasc
    AsyncR syncr;

    //���M�Ǘ��pAsyncTask
    AsyncS syncs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //openswitch��ϐ��Ɋi�[
        Switch s = (Switch) findViewById(R.id.Openswitch);

        s.setOnCheckedChangeListener(this);

        //sendButton��ϐ��Ɋi�[
        Button b = (Button)findViewById(R.id.sendButton);

        b.setOnClickListener(this);

        //��M�J�n
        syncr = new AsyncR((TextView)findViewById(R.id.textView3));
        syncr.execute();





    }



    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if(isChecked == true){

            //���M����
            syncs = new AsyncS((EditText)findViewById(R.id.Ip),(EditText)findViewById(R.id.Port), (EditText)findViewById(R.id.editText3),(TextView)findViewById(R.id.textView3));
        }

    }



    @Override
    public void onClick(View v) {

        //文字列送信処理
        syncs.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

    }



}



