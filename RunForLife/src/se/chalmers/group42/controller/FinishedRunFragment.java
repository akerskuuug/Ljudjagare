package se.chalmers.group42.controller;

import com.google.android.gms.internal.bc;

import se.chalmers.group42.runforlife.R;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * A fragment including the info related to the current run.
 */
public class FinishedRunFragment extends Fragment {

	private int progressStatus;
	private ProgressBar progressBar;
	private Button deleteButton;

	public FinishedRunFragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.i("Fragment", "Run Finished-Fragment created");
		View rootView = inflater.inflate(R.layout.fragment_finished_run,
				container, false);
		setValues(rootView);
		
		return rootView;
	}

	public void setValues(View view) {
		Bundle args = getArguments();
		if (args != null) {
			// ************SET TIME!!**************
			Time t = new Time();
			t.set(args.getLong("time") * 1000);
			t.switchTimezone("GMT");

			TextView txTime = (TextView) view.findViewById(R.id.textViewTime2);
			txTime.setText(t.format("%H:%M:%S"));

			// *************SET Distance*************
			int distance = args.getInt("distance");
			
			double d = distance;
			if(d >= 1000){
				d = Math.round((d/1000)*100)/100.0d;
				TextView txd = (TextView) view.findViewById(R.id.textViewDist2);
				txd.setText("" + d);
				TextView txkm = (TextView) view.findViewById(R.id.textViewDist3);
				txkm.setText("km");
			}else{
				TextView txDist = (TextView) view.findViewById(R.id.textViewDist2);
				txDist.setText("" + distance);
			}
			
			// *************SET SPEED/pace***************
			double speed = args.getDouble("speed");
			speed = Math.round(speed * 100) / 100.0d;
			TextView txSpeed = (TextView) view
					.findViewById(R.id.textViewSpeedAverage2);
			txSpeed.setText("" + speed);
			
			double pace;
			if(speed != 0){
				pace = Math.round((60 / speed) * 100) / 100.0d;
			}else{
				pace = 0;
			}
			TextView txPace = (TextView) view
					.findViewById(R.id.textViewPaceAverage2);
			txPace.setText("" + pace);
			
			//*******************SET PROGRESS********************
			int coins = args.getInt("nrCoins");
			progress(coins, view);
		}
	}

	public void progress(int coins, View view){
		if(coins != progressStatus){
			progressBar = (ProgressBar) view.findViewById(R.id.progressBar1);
			progressStatus = coins;
			if(progressStatus <= progressBar.getMax()){
				TextView txProg = (TextView) view.findViewById(R.id.progressText2);
				txProg.setText(progressStatus+"/"+progressBar.getMax()+" Coins");
				progressBar.setProgress(progressStatus);
			}
		}
	}
	
}