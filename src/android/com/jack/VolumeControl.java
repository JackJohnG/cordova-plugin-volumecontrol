package com.jack;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.content.Context;

import android.media.AudioManager;

import android.os.Build;
import android.util.Log;

public class VolumeControl extends CordovaPlugin {
	private Activity activity;
  	private Context context;

  	private int oldVolumeMusic = -1;

  	private AudioManager mgr = null;

  	public void initialize(CordovaInterface cordova, CordovaWebView webView) {
    	super.initialize(cordova, webView);

    	activity = cordova.getActivity();
    	context = webView.getContext();

    	this.mgr = (AudioManager)this.context.getSystemService(Context.AUDIO_SERVICE);
  	}

  	public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
    	if(action.equals("setVolume")) {
      		int volume = args.getInt(0);
			this.oldVolumeMusic = this.mgr.getStreamVolume(AudioManager.STREAM_MUSIC);
			this.mgr.setStreamVolume(AudioManager.STREAM_MUSIC, volume, AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
    	} else if(action.equals("getVolume")) {
      		final PluginResult result = new PluginResult(PluginResult.Status.OK, this.mgr.getStreamVolume(AudioManager.STREAM_MUSIC));
      		result.setKeepCallback(true);
			callbackContext.sendPluginResult(result);
    	} else if(action.equals("resetVolume")) {
			int currentVolume = this.mgr.getStreamVolume(AudioManager.STREAM_MUSIC);
      		if(this.oldVolumeMusic != -1 && currentVolume == 0) {
				this.mgr.setStreamVolume(AudioManager.STREAM_MUSIC, this.oldVolumeMusic, AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
				this.oldVolumeMusic = -1;
      		}
    	}
    	return true;
  	}

}
