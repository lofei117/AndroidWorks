package info.lofei.android.torch.receiver;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;

import info.lofei.android.torch.MainActivity;
import info.lofei.android.torch.R;
import info.lofei.android.torch.Util.TorchUtil;
import info.lofei.android.torch.service.TorchAppWidgetService;
import info.lofei.android.torch.service.TorchToggleService;

/**
 * Created by lofei on 14/10/20.
 */
public class TorchAppWidgetProvider extends AppWidgetProvider {


    public static void updateWidgets(Context context){
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context, TorchAppWidgetProvider.class));
        for ( int id : appWidgetIds ) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.main_appwidget);
            appWidgetManager.updateAppWidget(id, views);
        }

        //获取widget的布局
        RemoteViews remoteViews=new RemoteViews(context.getPackageName(), R.layout.main_appwidget);
        remoteViews.setImageViewResource(R.id.btn_torch_state,
        TorchUtil.isTorchOn() ? R.drawable.ic_lightbulb_on : R.drawable.ic_lightbulb);

        final ComponentName activityName = new ComponentName(context, TorchToggleService.class);
        Intent action = new Intent(TorchToggleService.TOGGLE_ACTION);
        action.setComponent(activityName);
        PendingIntent pendingIntent = PendingIntent.getService(context, 0, action, 0);
        remoteViews.setOnClickPendingIntent(R.id.btn_torch_state, pendingIntent);

        Intent intent = new Intent(context, TorchAppWidgetService.class);
        intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
        remoteViews.setRemoteAdapter(R.id.btn_torch_state, intent);

        appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        updateWidgets(context);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager,
            int appWidgetId, Bundle newOptions) {
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TorchAppWidgetService.class.getSimpleName(), "  onReceive intent= " + intent);
        super.onReceive(context, intent);
    }

    @Override
    public void onEnabled(Context context) {
        context.startService(new Intent(context, TorchToggleService.class));
        super.onEnabled(context);
    }

    @Override
    public void onDisabled(Context context) {
        context.stopService(new Intent(context, TorchToggleService.class));//停止更新widget的service
        super.onDisabled(context);
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        context.stopService(new Intent(context, TorchToggleService.class));//停止更新widget的service
        super.onDeleted(context, appWidgetIds);
    }
}
