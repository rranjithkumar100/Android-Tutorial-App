package ranjih.kotlinandroid.controller.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import android.view.KeyEvent;

import ranjih.kotlinandroid.R;


public class VersionController {


	private Activity mActivity;
	private static final String TAG = VersionController.class.getSimpleName();
	public VersionController(Activity context) {
		mActivity = context;
	}


	public void checkVersions(int liveVersionCode, boolean forceUpdate) {

		PackageInfo pInfo = null;
		try {
			pInfo = mActivity.getPackageManager().getPackageInfo(mActivity.getPackageName(), 0);
			int currentAppVersion = pInfo.versionCode;

			if (currentAppVersion < liveVersionCode) {
				if (forceUpdate) {
					showDialogWithoutCancel();
				}
				else
					showDialogWithCancelButton();
			}
		} catch (Exception e) {
			Log.e(TAG, "checkVersions: ",e );
		}


	}

	private void showDialogWithoutCancel() {
		new AlertDialog.Builder(mActivity)
				.setTitle(mActivity.getString(R.string.new_update_available))
				.setMessage(mActivity.getString(R.string.pls_update))
				.setCancelable(false)
				.setPositiveButton(mActivity.getString(R.string.update), new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						Uri uri = Uri.parse("market://details?id=" + mActivity.getPackageName());
						Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
						try {
							mActivity.startActivity(goToMarket);
						} catch (ActivityNotFoundException e) {
							mActivity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + mActivity.getPackageName())));
						}
					}
				}).setOnKeyListener(new DialogInterface.OnKeyListener() {
			@Override
			public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_BACK) {
					mActivity.finish();
					dialog.dismiss();
				}
				return true;
			}
		}).setIcon(R.mipmap.ic_launcher)
				.show();
	}

	private void showDialogWithCancelButton() {
		new AlertDialog.Builder(mActivity)
				.setTitle(mActivity.getString(R.string.new_update_available))
				.setMessage(mActivity.getString(R.string.pls_update))
				.setPositiveButton(mActivity.getString(R.string.update), new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						Uri uri = Uri.parse("market://details?id=" + mActivity.getPackageName());
						Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
						try {
							mActivity.startActivity(goToMarket);
						} catch (ActivityNotFoundException e) {
							mActivity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + mActivity.getPackageName())));
						}
					}
				}).setOnKeyListener(new DialogInterface.OnKeyListener() {
			@Override
			public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_BACK) {
//                                    finish();
					dialog.dismiss();
				}
				return true;
			}
		}).setNegativeButton((mActivity.getString(R.string.cancel)), new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,
			                    int which) {
				dialog.cancel();
			}
		}).setIcon(R.mipmap.ic_launcher)
				.show();
	}
}
