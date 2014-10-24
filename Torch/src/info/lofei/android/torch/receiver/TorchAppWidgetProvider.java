package info.lofei.android.torch.receiver;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

import info.lofei.android.torch.MainActivity;
import info.lofei.android.torch.R;
import info.lofei.android.torch.service.TorchAppWidgetService;
import info.lofei.android.torch.service.TorchToggleService;

/**
 * Created by lofei on 14/10/20.
 */
public class TorchAppWidgetProvider extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.main_appwidget);

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
}
