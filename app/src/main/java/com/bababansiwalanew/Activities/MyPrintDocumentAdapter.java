package com.bababansiwalanew.Activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentInfo;
import android.print.PrintManager;
import android.print.pdf.PrintedPdfDocument;

import com.bababansiwalanew.R;
import com.bababansiwalanew.Util.ApplicationConstant;

import java.io.FileOutputStream;
import java.io.IOException;

public class MyPrintDocumentAdapter extends PrintDocumentAdapter {

    Context context;
    private int pageHeight;
    private int pageWidth;
    public PdfDocument myPdfDocument;
    public int totalpages = 1;
    int n = 0;
    // AppUserListResponse appUserListResponse;
    //CompanyProfile companyProfile;
    int fontSize;
    double relation;
    String Rechargedetail;
    public MyPrintDocumentAdapter(Context context, String Rechargedetail) {
        this.context = context;
        this.Rechargedetail=Rechargedetail;
    }

    public void printDocument(String detail) {
        //   n = tv_item.getAdapter().getCount();
        PrintManager printManager = (PrintManager) context. getSystemService(Context.PRINT_SERVICE);
        Rechargedetail=detail;
        String jobNameD = "Receipt Preview";
        printManager.print(jobNameD, new MyPrintDocumentAdapter(context,Rechargedetail),
                null);
    }

    @Override
    public void onLayout(PrintAttributes oldAttributes,
                         PrintAttributes newAttributes,
                         CancellationSignal cancellationSignal,
                         LayoutResultCallback callback,
                         Bundle metadata) {

        myPdfDocument = new PrintedPdfDocument(context, newAttributes);

        pageHeight = newAttributes.getMediaSize().getHeightMils() / 1000 * 72;
        pageWidth = newAttributes.getMediaSize().getWidthMils() / 1000 * 72;

        if (cancellationSignal.isCanceled()) {
            callback.onLayoutCancelled();
            return;
        }

        if (totalpages > 0) {
            PrintDocumentInfo.Builder builder = new PrintDocumentInfo
                    .Builder("print_Preview.pdf")
                    .setContentType(PrintDocumentInfo.CONTENT_TYPE_DOCUMENT)
                    .setPageCount(totalpages);

            PrintDocumentInfo info = builder.build();
            callback.onLayoutFinished(info, true);
        } else {
            callback.onLayoutFailed("Page count is zero.");
        }
    }

    @Override
    public void onWrite(final PageRange[] pageRanges,
                        final ParcelFileDescriptor destination,
                        final CancellationSignal cancellationSignal,
                        final WriteResultCallback callback) {

        for (int i = 0; i < totalpages; i++) {
            if (pageInRange(pageRanges, i)) {
                // page builder
                PdfDocument.PageInfo newPage = new PdfDocument.PageInfo.Builder(pageWidth,
                        pageHeight, i).create();

                PdfDocument.Page page =
                        myPdfDocument.startPage(newPage);

                if (cancellationSignal.isCanceled()) {
                    callback.onWriteCancelled();
                    myPdfDocument.close();
                    myPdfDocument = null;
                    return;
                }
                drawPage(page, i, pageWidth, pageHeight);
                myPdfDocument.finishPage(page);
            }
        }

        try {
            myPdfDocument.writeTo(new FileOutputStream(
                    destination.getFileDescriptor()));
        } catch (IOException e) {
            callback.onWriteFailed(e.toString());
            return;
        } finally {
            myPdfDocument.close();
            myPdfDocument = null;
        }
        callback.onWriteFinished(pageRanges);
    }

    private boolean pageInRange(PageRange[] pageRanges, int page) {
        for (int i = 0; i < pageRanges.length; i++) {
            if ((page >= pageRanges[i].getStart()) &&
                    (page <= pageRanges[i].getEnd()))
                return true;
        }
        return false;
    }

    public PdfDocument.Page drawPage(PdfDocument.Page page, int pagenumber, int pageWidth1, int pageHeight1) {

        pageWidth = pageWidth1;
        pageHeight = pageHeight1;
        String jobNameD = "Receipt Preview";
        Canvas canvas = page.getCanvas();
        pagenumber++;
        Paint paint = new Paint();
        paint.setTextScaleX(1.0f);
        relation = Math.sqrt(canvas.getWidth() * canvas.getHeight());
        relation = relation / 250;
        fontSize = context.getResources().getDimensionPixelSize(R.dimen.scoreFontSize);
        paint.setTextSize((float) (fontSize * relation));
        drawHeader(canvas, paint);
        return page;
    }

    private void drawHeader(Canvas canvas, Paint paint) {

        paint.setTextAlign(Paint.Align.LEFT);
        int y=15;
        Bitmap icon = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.logo);
        Bitmap b = Bitmap.createScaledBitmap(icon, (10 * pageWidth) / 100, (7 * pageWidth) / 100, false);
        canvas.drawBitmap(b, (7 * pageWidth) / 100, (y * pageHeight) / 100, null);

        String[] str=Rechargedetail.split("\n");






        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String emailPref = myPrefs.getString(ApplicationConstant.INSTANCE.supportEmail, null);
        String numberPref = myPrefs.getString(ApplicationConstant.INSTANCE.supportNumber, null);



        String company=""+ context.getResources().getString(R.string.app_name)+",\n" +

                "Mob: "+numberPref  +
                ", Email: "+ emailPref;
        String[] str2 = company.split("\n");
        //String[] str2=address.split("\n");
        for (int i = 0; i < str2.length; i++) {
            canvas.drawText(
                    str2[i] + "",
                    (19 * pageWidth) / 100,
                    (y * pageHeight) / 100,
                    paint);
            y = y + 5;
        }
        canvas.drawLine(10, ((y - 3) * pageHeight) / 100, pageWidth - 10, ((y - 3) * pageHeight) / 100, paint);
        for (int i = 0; i < str.length; i++) {
            canvas.drawText(
                    str[i] + "",
                    (19 * pageWidth) / 100,
                    (y * pageHeight) / 100,
                    paint);
            y = y + 5;
        }




        ///

    }
}


