package com.github.teren4m.blockcanary;

import android.content.Context;

/**
 * Created by markzhai on 16/1/22
 *
 * @author markzhai
 */
public interface OnBlockEventInterceptor {
    void onBlockEvent(Context context, String timeStart);
}
