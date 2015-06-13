package com.example.learn_actionbar;


import java.lang.reflect.Field;
import java.lang.reflect.Method;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewConfiguration;
import android.view.Window;
import android.widget.Toast;

@SuppressLint("NewApi") public class MainActivity extends Activity {

	@SuppressLint("NewApi") @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		setOverflowShowingAlways();
	}
	
	private void setOverflowShowingAlways(){
		try{
			ViewConfiguration config = ViewConfiguration.get(this);
			Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermaentMenuKey");
			menuKeyField.setAccessible(true);
			menuKeyField.setBoolean(config, false);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		
		/*** shareMenu平版無法用
		MenuItem shareItem = menu.findItem(R.id.action_share);
		ShareActionProvider provider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);
		provider.setShareIntent(getDefaultIntent());
		*/
		return super.onCreateOptionsMenu(menu);
	}
	
	private Intent getDefaultIntent(){
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("image/*");
		return intent;
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()){
		case R.id.action_edit:
			Toast.makeText(this, "EDIT", Toast.LENGTH_LONG).show();
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, Activity1.class);
			startActivity(intent);
			return true;
			
		case R.id.action_delete:
			Toast.makeText(this, "DELETE", Toast.LENGTH_LONG).show();
			return true;
			
		case android.R.id.home:
			finish();
			return true;
		}
		return true;
	}
	
	@Override
	public boolean onMenuOpened(int featureId, Menu menu){
		if(featureId == Window.FEATURE_ACTION_BAR && menu != null){
			if(menu.getClass().getSimpleName().equals("MenuBuilder")){
				try{
					Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
					m.setAccessible(true);
					m.invoke(menu, true);
				}catch(Exception e){
					
				}
			}
		}
		return super.onMenuOpened(featureId, menu);
		
	}

}
