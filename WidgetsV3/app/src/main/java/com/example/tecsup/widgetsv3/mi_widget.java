package com.example.tecsup.widgetsv3;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Implementation of App Widget functionality.
 */
public class mi_widget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.mi_widget);
        views.setTextViewText(R.id.appwidget_text, widgetText);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
            actualizarWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    public static void actualizarWidget(Context context, AppWidgetManager appWidgetManager,
                                int widgetId) {

        SharedPreferences datos = context.getSharedPreferences("DatosWidget", Context.MODE_PRIVATE);

        String mensaje = datos.getString("mensaje", "Mensaje Recibio");

        CharSequence widgetText = context.getString(R.string.appwidget_text);

        RemoteViews controles = new RemoteViews(context.getPackageName(), R.layout.mi_widget);
        controles.setTextViewText(R.id.lblMensaje, mensaje);

        SimpleDateFormat sdfDate = new SimpleDateFormat("HH:mm");
        SimpleDateFormat mmmmmmm = new SimpleDateFormat("a");
        SimpleDateFormat sdfFecha = new SimpleDateFormat("EEE MMM dd");

        Date now = new Date();
        String hora = sdfDate.format(now);
        String mmmm = mmmmmmm.format(now);
        String fecha = sdfFecha.format(now);
        controles.setTextViewText(R.id.lblHora,hora);
        controles.setTextViewText(R.id.lblm,mmmm);
        controles.setTextViewText(R.id.lblFecha,fecha);

        Intent clicenwidget = new Intent(context, MainActivity.class);
        PendingIntent widgetesperando = PendingIntent.getActivity(context,widgetId,clicenwidget,PendingIntent.FLAG_UPDATE_CURRENT);

        controles.setOnClickPendingIntent(R.id.frmWidget,widgetesperando);

        appWidgetManager.updateAppWidget(widgetId, controles);

        Intent botonwidget = new Intent("com.example.tecsup.widgetsv3.ACTUALIZAR_WIDGET");
        botonwidget.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,widgetId);
        PendingIntent botonespera = PendingIntent.getBroadcast(context,widgetId,botonwidget,PendingIntent.FLAG_UPDATE_CURRENT);
        controles.setOnClickPendingIntent(R.id.btnActualizar,botonespera);

        appWidgetManager.updateAppWidget(widgetId, controles);
    }

    public void onReceive(Context context, Intent intent){
        super.onReceive(context, intent);
        if(intent.getAction().equals("com.example.tecsup.widgetsv3.ACTUALIZAR_WIDGET")){
            int widgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);

            AppWidgetManager actualizadorwidget = AppWidgetManager.getInstance(context);

            if(widgetId != AppWidgetManager.INVALID_APPWIDGET_ID){
                actualizarWidget(context,actualizadorwidget,widgetId);
            }
        }
    }
}

