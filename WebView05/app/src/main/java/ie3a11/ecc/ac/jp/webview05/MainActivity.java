package ie3a11.ecc.ac.jp.webview05;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //表示しているWebView
    private WebView mWedView;

    //画面回転数に応じて表示サイトを切り替えるための数値
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //WebViewのインスタンス取得
        mWedView = (WebView)findViewById(R.id.webView);

        //WebSettingsオブジェクトを取得
        WebSettings settings = mWedView.getSettings();

        //JaveScriptを有効にする
        settings.setJavaScriptEnabled(true);

        //WebViewのリンクをクリックするたびにIntentが発行される処理を無効にする
        mWedView.setWebViewClient(new WebViewClient());

//        //Activity起動時に初期URL(Yahooサイト)を読み込む
//        if(savedInstanceState == null){
//            mWedView.loadUrl("http://www.yahoo.co.jp/");
//        }else{
//            //WebViewの状態を復元
//            mWedView.restoreState(savedInstanceState);
//        }

    }



    @Override
    public void onBackPressed() {
        //履歴があれば、Backキーで前のページへ戻る
        if(mWedView.canGoBack()){
            mWedView.goBack();
        }else{
            super.onBackPressed();
        }

        Toast.makeText(this,"「onBackPressed」が呼ばれました",Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //WebViewの状態を保存する(画面開店前の状態を保存する処理)
        mWedView.saveState(outState);

        //サイト変更のための変数保存
        outState.putInt("サイト変更値", i);

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //WebViewで実行中の処理を停止
        mWedView.onPause();

        Toast.makeText(this,"「onPause」が呼ばれました",Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        i = savedInstanceState.getInt("サイト変更値");
    }

    @Override
    protected void onResume() {
        //WebViewの処理を再び開始
        mWedView.onResume();
        super.onResume();

        i = i % 3;
        //画面が切り替わるごとに表示サイトを変える
        switch (i){
            case 0 :
                mWedView.loadUrl("https://www.google.co.jp/");
                break;

            case 1 :
                mWedView.loadUrl("http://www.yahoo.co.jp/");
                break;

            case 2 :
                mWedView.loadUrl("http://www.goo.ne.jp/");
                break;
        }
        i++;

        Toast.makeText(this,"「onResume」が呼ばれました",Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onDestroy() {
        if(mWedView != null){
            mWedView.stopLoading();
            mWedView.setWebChromeClient(null);
            mWedView.setWebViewClient(null);
            mWedView.destroy();
            mWedView = null;
        }

        Toast.makeText(this,"「onDestroy」が呼ばれました",Toast.LENGTH_SHORT).show();

        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
