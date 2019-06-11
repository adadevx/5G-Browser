package acr.browser.speedbrowser7g.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.util.Log;

import javax.inject.Inject;
import javax.inject.Singleton;

import acr.browser.speedbrowser7g.BrowserApp;
import acr.browser.speedbrowser7g.R;
import acr.browser.speedbrowser7g.constant.Constants;
import acr.browser.speedbrowser7g.constant.Proxy;
import acr.browser.speedbrowser7g.dialog.BrowserDialog;
import acr.browser.speedbrowser7g.extensions.ActivityExtensions;
import acr.browser.speedbrowser7g.preference.DeveloperPreferences;
import acr.browser.speedbrowser7g.preference.UserPreferences;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import info.guardianproject.netcipher.proxy.OrbotHelper;
import info.guardianproject.netcipher.webkit.WebkitProxy;

@Singleton
public final class ProxyUtils {

    private static final String TAG = "ProxyUtils";

    @Inject UserPreferences mUserPreferences;
    @Inject DeveloperPreferences mDeveloperPreferences;

    @Inject
    public ProxyUtils() {
        BrowserApp.getAppComponent().inject(this);
    }

    /*
     * If Orbot/Tor or I2P is installed, prompt the user if they want to enable
     * proxying for this session
     */
    public void checkForProxy(@NonNull final Activity activity) {
        int proxyChoice = mUserPreferences.getProxyChoice();

        final boolean orbotInstalled = OrbotHelper.isOrbotInstalled(activity);
        boolean orbotChecked = mDeveloperPreferences.getCheckedForTor();
        boolean orbot = orbotInstalled && !orbotChecked;

        // TODO Is the idea to show this per-session, or only once?
        if (proxyChoice != Constants.NO_PROXY && (orbot)) {
            if (orbot) {
                mDeveloperPreferences.setCheckedForTor(true);
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);

            if (orbotInstalled) {
                String[] proxyChoices = activity.getResources().getStringArray(R.array.proxy_choices_array);
                builder.setTitle(activity.getResources().getString(R.string.http_proxy))
                    .setSingleChoiceItems(proxyChoices, mUserPreferences.getProxyChoice(),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mUserPreferences.setProxyChoice(which);
                            }
                        })
                    .setPositiveButton(activity.getResources().getString(R.string.action_ok),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (mUserPreferences.getProxyChoice() != Constants.NO_PROXY) {
                                    initializeProxy(activity);
                                }
                            }
                        });
            } else {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                mUserPreferences.setProxyChoice(orbotInstalled ?
                                    Constants.PROXY_ORBOT : Constants.PROXY_I2P);
                                initializeProxy(activity);
                                break;
                            case DialogInterface.BUTTON_NEGATIVE:
                                mUserPreferences.setProxyChoice(Constants.NO_PROXY);
                                break;
                        }
                    }
                };

                builder.setMessage(orbotInstalled ? R.string.use_tor_prompt : R.string.use_i2p_prompt)
                    .setPositiveButton(R.string.yes, dialogClickListener)
                    .setNegativeButton(R.string.no, dialogClickListener);
            }
            Dialog dialog = builder.show();
            BrowserDialog.setDialogSize(activity, dialog);
        }
    }

    /*
     * Initialize WebKit Proxying
     */
    private void initializeProxy(@NonNull Activity activity) {
        String host;
        int port;

        switch (mUserPreferences.getProxyChoice()) {
            case Constants.NO_PROXY:
                // We shouldn't be here
                return;
            case Constants.PROXY_ORBOT:
                if (!OrbotHelper.isOrbotRunning(activity)) {
                    OrbotHelper.requestStartTor(activity);
                }
                host = "localhost";
                port = 8118;
                break;
            default:
                host = mUserPreferences.getProxyHost();
                port = mUserPreferences.getProxyPort();
                break;
            case Constants.PROXY_MANUAL:
                host = mUserPreferences.getProxyHost();
                port = mUserPreferences.getProxyPort();
                break;
        }

        try {
            WebkitProxy.setProxy(BrowserApp.class.getName(), activity.getApplicationContext(), null, host, port);
        } catch (Exception e) {
            Log.d(TAG, "error enabling web proxying", e);
        }

    }

    public boolean isProxyReady(@NonNull Activity activity) {
        return true;
    }

    public void updateProxySettings(@NonNull Activity activity) {
        if (mUserPreferences.getProxyChoice() != Constants.NO_PROXY) {
            initializeProxy(activity);
        } else {
            try {
                WebkitProxy.resetProxy(BrowserApp.class.getName(), activity.getApplicationContext());
            } catch (Exception e) {
                Log.e(TAG, "Unable to reset proxy", e);
            }
        }
    }

    public void onStop() {
    }

    public void onStart(final Activity activity) {
    }

    @Proxy
    public static int sanitizeProxyChoice(int choice, @NonNull Activity activity) {
        switch (choice) {
            case Constants.PROXY_ORBOT:
                if (!OrbotHelper.isOrbotInstalled(activity)) {
                    choice = Constants.NO_PROXY;
                    ActivityExtensions.snackbar(activity, R.string.install_orbot);
                }
                break;
            case Constants.PROXY_MANUAL:
                break;
        }
        return choice;
    }
}
