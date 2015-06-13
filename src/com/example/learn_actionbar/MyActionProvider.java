package com.example.learn_actionbar;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.ActionProvider;
import android.view.SubMenu;
import android.view.View;

@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH) @SuppressLint("NewApi") 
public class MyActionProvider extends ActionProvider{

	public MyActionProvider(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public View onCreateActionView(){
		return null;
	}

	@Override
	public void onPrepareSubMenu(SubMenu subMenu){
		subMenu.clear();
		Intent intent = new Intent();
		subMenu.add("sub item 1").setIcon(R.drawable.ic_launcher);
		subMenu.add("sbu item 2").setIcon(R.drawable.ic_launcher);

	}
	
	@Override
	public boolean hasSubMenu() {
		return true;
	}
}
