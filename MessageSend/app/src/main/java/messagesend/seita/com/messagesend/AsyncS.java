package messagesend.seita.com.messagesend;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.DatagramSocketImpl;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

public class AsyncS extends AsyncTask<Void,Void,Void>{


    /*
     * --------------フィールド--------------
     * メッセージ表示用
     * TextView
     */
    TextView v;

    /*
     * IPアドレス、ポート番号用
     * EditText
     */
    EditText ipe,pre;

    /*
     *IPアドレス格納用
     *InetAddress
     */
    InetAddress ip;

    /*
     * Port番号格納用
     */
    int port;

    /*
    Ipアドレス変換用String型変数:ips
    送信テキスト:sends
     */
    String ips,sends;


    /*
     * DatagramSocket so:データグラムソケット
     */
    private DatagramSocket so;

    /*
     * DatagramPacket pa:データグラムパケット
     */
    DatagramPacket pa;

    /*
	 * --------------------------------------
	 */

    /*
     * コンストラクタ
     * IPアドレスの格納、ポート番号の格納、送信用ソケットの生成まで行う
     * 引数:ipアドレス入力用EditText,Port番号入力用,メッセージ表示用TextView
     */
    public AsyncS(EditText ipe,EditText pre,EditText sends,TextView v) {

        Log.d("log","AsyncSコンストラクタ");

        this.ipe = ipe;
        this.pre = pre;
        this.sends = sends.getText().toString();
        this.v = v;

        //IPアドレス変換用byte配列準備
        ips = this.ipe.getText().toString();

        try {

            //ipアドレスとポート番号の取得
            ip = InetAddress.getByName(ips);
            port = Integer.parseInt(pre.getText().toString());


            //---確認用Log---
            byte testbyte[] = ip.getAddress();
            Log.d("debug","byte = " + testbyte + "InetAddress = " + ip.toString());
            //--------------

        } catch (UnknownHostException e) {

            Log.e("error","エラー = " + e.getMessage());

        }

        Log.d("log","送信ソケット生成開始");
        try {



            so = new DatagramSocket();
            Log.i("info","info = データグラムソケット生成:ポート番号(" + so.getPort() + ")");

        } catch (SocketException e1) {

            Log.e("error","エラー = " + e1.getMessage());

        }
        Log.d("log","送信ソケット生成完了");


    }


    /*
     *非同期処理用メソッド
     *送信文字列をパケットに格納し送信
     *引数:なし
     */
    @Override
    protected Void doInBackground(Void... params) {


        Log.d("log","送信パケット生成開始");

        //ソケットにポートをバインド
        so.connect(ip,port);

        //データバッファとUDPパケットの作成
        byte receviBf[] = new byte[2048];
        receviBf = sends.getBytes();
        pa = new DatagramPacket(receviBf,receviBf.length);

        Log.d("log","送信パケット生成完了");

        //受信開始
        Log.d("log", "メッセージ送信開始");

        //データの送信
        try {

            so.send(pa);

            //送信メッセージ表示
            publishProgress();

        } catch (IOException e) {

            //データが送信できなかった場合エラー表示
            Log.e("error","エラー = " + e.getMessage());

        }


        return null;
    }


    /*
    * 送信文字列表示用
    * 送信文字列を表示する
    * 引数:なし
    */
    @Override
    protected void onProgressUpdate(Void... values) {

        Log.d("log", "文字表示準備完了");

        v.setText( v.getText() + "Send : " + sends + "\n");
    }


//    public void executeOnExecutor() {
//    }
}
