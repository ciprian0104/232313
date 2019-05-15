package com.example.ekgapp;



import android.app.Activity;
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Random;


import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphView.LegendAlign;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.GraphViewSeries.GraphViewStyle;
import com.jjoe64.graphview.LineGraphView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
	//FOR TESTING
	private static final Random RANDOM = new Random();

	@Override
	public void onBackPressed() {
		if (Bluetooth.connectedThread != null) {
			Bluetooth.connectedThread.write("Q");}//Stop streaming
		super.onBackPressed();
	}

	//toggle Button
	static boolean Lock;//whether lock the x-axis to 0-5
	static boolean AutoScrollX;//auto scroll to the last x value
	static boolean Stream;//Start or stop streaming
	//Button init
	Button bXminus;
	Button bXplus;
	ToggleButton tbLock;
	ToggleButton tbScroll;
	ToggleButton tbStream;
	//GraphView init
	static LinearLayout GraphView;
	static GraphView graphView;
	static GraphViewSeries Series;
	//graph value
	private static double graph2LastXValue = 0;
	private static int Xview=600;
	Button bConnect, bDisconnect;

	Handler mHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch(msg.what){
				case Bluetooth.SUCCESS_CONNECT:
					Bluetooth.connectedThread = new Bluetooth.ConnectedThread((BluetoothSocket)msg.obj);
					Toast.makeText(getApplicationContext(), "Connected!", Toast.LENGTH_SHORT).show();
					String s = "Successfully connected";
					Bluetooth.connectedThread.start();
					break;
				case Bluetooth.MESSAGE_READ:

					byte[] readBuf = (byte[]) msg.obj;
					String strIncom = new String(readBuf, 0, msg.arg1);                 // create string from bytes array
					String bpm = new String(readBuf, 0, msg.arg1);
					String tem = new String(readBuf, 0, msg.arg1);
					String hum = new String(readBuf, 0, msg.arg1);


					Log.d("strIncom", strIncom);

					int point1, point2;
					point1 = strIncom.indexOf("S");
					point2 = strIncom.indexOf(",");
					if(point1 >= 0 && point2 >= point1){
						strIncom = strIncom.substring(point1+1, point2);
						Series.appendData(new GraphViewData(graph2LastXValue,Integer.parseInt(strIncom)),AutoScrollX);

						//X-axis control
						if (graph2LastXValue >= Xview && Lock == true){
							Series.resetData(new GraphViewData[] {});
							graph2LastXValue = 0;
						}else graph2LastXValue += 0.4;

						if(Lock == true)
							graphView.setViewPort(0, Xview);
						else
							graphView.setViewPort(graph2LastXValue-Xview, Xview);

						//refresh
						GraphView.removeView(graphView);
						GraphView.addView(graphView);


					}
				/*
				if (strIncom.indexOf('.')==0 && strIncom.indexOf('s')==0){
					strIncom = strIncom.replace("s", "");
					if (isFloatNumber(strIncom)){
						Series.appendData(new GraphViewData(graph2LastXValue,Double.parseDouble(strIncom)),AutoScrollX);

						//X-axis control
						if (graph2LastXValue >= Xview && Lock == true){
							Series.resetData(new GraphViewData[] {});
							graph2LastXValue = 0;
						}else graph2LastXValue += 0.1;

						if(Lock == true)
							graphView.setViewPort(0, Xview);
						else
							graphView.setViewPort(graph2LastXValue-Xview, Xview);

						//refresh
						GraphView.removeView(graphView);
						GraphView.addView(graphView);

					}
				}*/
					//ELSE ADD RANDOM DATA


					else{
						/*
						//Series.appendData(new GraphViewData(graph2LastXValue, RANDOM.nextDouble() * 10d), AutoScrollX)
						for(int i = 0; i < 150; i++){
							Series.appendData(new GraphViewData(graph2LastXValue,(RANDOM.nextInt(600 - 150)+ 150) ),AutoScrollX);

							//X-axis control
							if (graph2LastXValue >= Xview && Lock == true){
								Series.resetData(new GraphViewData[] {});
								graph2LastXValue = 0;
							}else graph2LastXValue += 0.1;

							if(Lock == true)
								graphView.setViewPort(0, Xview);
							else
								graphView.setViewPort(graph2LastXValue-Xview, Xview);

							//refresh
							GraphView.removeView(graphView);
							GraphView.addView(graphView);

						}

						// Series.appendData(new GraphViewData(graph2LastXValue,RANDOM.nextDouble()),AutoScrollX);





						new Thread(new Runnable() {

							@Override
							public void run() {
								// we add 100 new entries
								for (int i = 0; i < 100; i++) {
									runOnUiThread(new Runnable() {

										@Override
										public void run() {
											addEntry();
										}
									});

									// sleep to slow down the add of entries
									try {
										Thread.sleep(600);
									} catch (InterruptedException e) {
										// manage error ...
									}
								}
							}
						}).start();
						//BRACKET

				*/

					}
					int temp1, temp2;
					temp1 = bpm.indexOf('B');
					temp2 = bpm.indexOf(',');
					if (temp1 >= 0 && temp2 >= temp1) {
						bpm = bpm.substring(temp1 + 1, temp2);
						TextView BPM = (TextView) findViewById(R.id.puls);
						BPM.setText(bpm);
					} else{
						/*
						bpm = (new Integer(RANDOM.nextInt(140 - 50) + 50)).toString();


						TextView BPM = (TextView) findViewById(R.id.puls);
						BPM.setText(bpm);
						*/
					}
					int temp3, temp4;
					temp3 = tem.indexOf('T');
					temp4 = tem.indexOf(',');
					if (temp3 >= 0 && temp4 >= temp3) {
						tem = tem.substring(temp3 + 1, temp4);
						TextView TEM = (TextView) findViewById(R.id.temperatura);
						TEM.setText(tem);
					} else{
						/*
						tem = (new Integer(RANDOM.nextInt(25 - 20 ) + 20)).toString();

						TextView TEM = (TextView) findViewById(R.id.temperatura);
						TEM.setText(tem);

						*/
					}
					int temp5, temp6;
					temp5 = hum.indexOf('H');
					temp6 = hum.indexOf(',');
					if (temp5 >= 0 && temp6 >= temp5) {
						hum = hum.substring(temp5 + 1, temp6);
						TextView HUM = (TextView) findViewById(R.id.umiditate);
						HUM.setText(hum);
					} else{
						/*
						hum = (new Integer(RANDOM.nextInt(90 - 30) + 30)).toString();

						TextView HUM = (TextView) findViewById(R.id.umiditate);
						HUM.setText(hum);
						*/
					}

					break;
			}
		}

		public boolean isFloatNumber(String num){
			try{
				Double.parseDouble(num);
			} catch(NumberFormatException nfe) {
				return false;
			}
			return true;
		}

	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);//Hide title
		//this.getWindow().setFlags(WindowManager.LayoutParams.
		//		FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);//Hide Status bar
		setContentView(R.layout.activity_main);
		//set background color
		LinearLayout background = (LinearLayout)findViewById(R.id.bg);
		background.setBackgroundColor(Color.BLACK);
		init();
		ButtonInit();

		// Find the toolbar view inside the activity layout
		//Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		// Sets the Toolbar to act as the ActionBar for this Activity window.
		// Make sure the toolbar exists in the activity and is not null
		//setSupportActionBar(toolbar);
	}

	void init(){
		Bluetooth.gethandler(mHandler);

		//init graphview
		GraphView = (LinearLayout) findViewById(R.id.Graph);
		// init example series data-------------------
		Series = new GraphViewSeries("Signal",
				new GraphViewStyle(Color.YELLOW, 2),//color and thickness of the line
				new GraphViewData[] {new GraphViewData(0, 0)});
		graphView = new LineGraphView(
				this // context
				, "Graph" // heading
		);
		graphView.setViewPort(0, Xview);
		graphView.setScrollable(true);
		graphView.setScalable(true);
		graphView.setShowLegend(true);
		graphView.setLegendAlign(LegendAlign.BOTTOM);
		graphView.setManualYAxis(true);
		graphView.setManualYAxisBounds(600, 0);
		graphView.addSeries(Series); // data
		GraphView.addView(graphView);
	}

	void ButtonInit(){
		bConnect = (Button)findViewById(R.id.bConnect);
		bConnect.setOnClickListener(this);
		bDisconnect = (Button)findViewById(R.id.bDisconnect);
		bDisconnect.setOnClickListener(this);
		//X-axis control button
		bXminus = (Button)findViewById(R.id.bXminus);
		bXminus.setOnClickListener(this);
		bXplus = (Button)findViewById(R.id.bXplus);
		bXplus.setOnClickListener(this);

		tbLock = (ToggleButton)findViewById(R.id.tbLock);
		tbLock.setOnClickListener(this);
		tbScroll = (ToggleButton)findViewById(R.id.tbScroll);
		tbScroll.setOnClickListener(this);
		tbStream = (ToggleButton)findViewById(R.id.tbStream);
		tbStream.setOnClickListener(this);
		//init toggleButton
		Lock=true;
		AutoScrollX=true;
		Stream=true;
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
			case R.id.bConnect:
				startActivity(new Intent("android.intent.action.BT1"));
				break;
			case R.id.bDisconnect:
				Bluetooth.disconnect();
				break;
			case R.id.bXminus:
				if (Xview>1) Xview -= 10;
				break;
			case R.id.bXplus:
				if (Xview<600) Xview += 10;
				break;
			case R.id.tbLock:
				if (tbLock.isChecked()){
					Lock = true;
				}else{
					Lock = false;
				}
				break;
			case R.id.tbScroll:
				if (tbScroll.isChecked()){
					AutoScrollX = true;
				}else{
					AutoScrollX = false;
				}
				break;
			case R.id.tbStream:
				if (tbStream.isChecked()){
					if (Bluetooth.connectedThread != null)
						Bluetooth.connectedThread.write("E");
				}else{
					if (Bluetooth.connectedThread != null)
						Bluetooth.connectedThread.write("Q");
				}
				break;
		}
	}

	// add random data to graph
	private void addEntry() {
		// here, we choose to display max 10 points on the viewport and we scroll to end

		Series.appendData(new GraphViewData(graph2LastXValue++, RANDOM.nextDouble()+10d), AutoScrollX);}





	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int id = item.getItemId();

		if(id == R.id.menu_measurements){
			Intent intent_1 = new Intent(this, MenuActivity.class);

			startActivity(intent_1);
		}
		if(id == R.id.menu_profile){
			Intent intent_2 = new Intent(this,UserProfile.class );

			startActivity(intent_2);
		}
		return super.onOptionsItemSelected(item);


	}









}


