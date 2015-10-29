package messagesend.seita.com.messagesend;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

public class AsyncR extends AsyncTask<Void, Void, String> {


    /*
     * -------------フィールド-------------
     * TextView v:文字表示テキスト表示用
     */
    TextView v;

    /*
     * DatagramSocket so:データグラムソケット
     */
    DatagramSocket so;

    /*
     * DatagramPacket pa:データグラムパケット
     */
    DatagramPacket pa;

	/*
	 *------------------------------------
	 */


    /*
     * コンストラクタ
     * 引数:TextView
     */
    public AsyncR(TextView v){

        this.v = v;

        Log.d("log","AsyncRコンストラクタ呼出");

    }



    /*
     * 非同期処理用
     * MainActivityの受信文字表示用の変数に格納する
     * 引数:なし
     */
    @Override
    protected String doInBackground(Void... arg0) {


        Log.d("log","受信ソケット生成開始");
        //データグラムソケットの監視ポート(6000番)
        try {

            so = new DatagramSocket(8000);
            Log.i("info","info = データグラムソケット生成:ポート番号(" + so.getPort() + ")");

        } catch (SocketException e1) {

            Log.e("error","error = " + e1.getMessage());

        }
        Log.d("log","受信ソケット生成完了");



        try {

            Log.i("info","受信パケット生成開始");

            //データバッファとUDPパケットの作成
            byte receviBf[] = new byte[1024];
            pa = new DatagramPacket(receviBf,receviBf.length);

            Log.i("info","受信パケット生成完了");

            while (true) {

                //受信開始
                Log.d("log", "メッセージ受信");
                so.receive(pa);

                //受信メッセージ表示
                publishProgress();

            }

        } catch (IOException e1) {

            Log.e("error","エラー = " + e1.getMessage());

        }

        return null;

    }


    /*
     * 処理結果表示用
     * 受信文字列表示する(受信した瞬間に)
     * 引数:なし
     */
    @Override
    protected void onProgressUpdate(Void... values) {

        String jmoji = new String(pa.getData());

        Log.d("log","文字表示準備完了");

        v.setText(v.getText() + jmoji + "\n");

        jmoji = "";

    }

}
