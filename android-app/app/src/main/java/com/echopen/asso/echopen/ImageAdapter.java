package com.echopen.asso.echopen;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.File;

public class ImageAdapter extends BaseAdapter {

    private File galleryDirectory;
    private int clientId;
    private Context mContext;
    // references to our images
    private Drawable[] mThumbIds;

    public ImageAdapter(Context c, int clientId, File galleryDirectory) {
        mContext = c;
        this.clientId = clientId;
        this.galleryDirectory = galleryDirectory;
        setmThumbIds();
    }

    public Drawable[] getImages() {
        File[] allImages = (new File(this.galleryDirectory.toString() + "/" + clientId + "/")).listFiles();
        Drawable[] validImages = new Drawable[6];
        int i = 0;
        if (allImages != null) {
            for (File as : allImages) {
                //Convert bitmap to drawable
                Drawable drawable = new BitmapDrawable(mContext.getResources(), BitmapFactory.decodeFile(as.getPath()));
                validImages[i] = drawable;
                i++;
            }
        }
        return validImages;
    }

    public void setmThumbIds() {
        this.mThumbIds = getImages();
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    @Override
    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(mContext);


            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(0, 0, 0, 0);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageDrawable(mThumbIds[position]);
        return imageView;
    }
}