package info.lofei.android.torch.service;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import info.lofei.android.torch.R;
import info.lofei.android.torch.Util.TorchUtil;

/**
 * @author lofei@duotin.com
 * @version 1.0.0
 *          lofei 2014-10-21 上午11:04
 * @package info.lofei.android.torch.service
 * @copyright Copyright (C) 2012-2020 DuoTin Network Technology Co.,LTD
 */
public class TorchAppWidgetService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return null;
    }

    private class TorchAppWidgetFactory implements RemoteViewsFactory {

        private RemoteViews mRemoteViews;

        private Context mContext;

        public TorchAppWidgetFactory(Context context){
            mContext = context;
        }

        @Override
        public void onCreate() {

        }

        @Override
        public void onDataSetChanged() {
            Log.d(TorchAppWidgetService.class.getSimpleName(), " onDataSetChanged =" );
        }

        @Override
        public void onDestroy() {

        }

        @Override
        public int getCount() {
            return 1;
        }

        @Override
        public RemoteViews getViewAt(int position) {

            mRemoteViews = new RemoteViews(mContext.getPackageName(), R.layout.main_appwidget);

            boolean flag = TorchUtil.isTorchOn();

            Log.d(TorchAppWidgetService.class.getSimpleName(), " is TorchOn flag =" + flag);

            if(flag) {
                mRemoteViews.setImageViewResource(R.id.btn_torch_state, R.drawable.ic_lightbulb);
            } else {
                mRemoteViews.setImageViewResource(R.id.btn_torch_state, R.drawable.ic_lightbulb_on);
            }

            return mRemoteViews;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }
    }
}
