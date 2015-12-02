package oceansfive.footymanager;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.lang.reflect.Field;

public class LogoAdapter extends BaseAdapter {
    private Context mContext;

    public LogoAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return SelectLogo.logos.length;
        //return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(200, 200));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(10, 10, 10, 10);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(getLogoId(SelectLogo.logos[position]));
        return imageView;
    }


    public int getLogoId(String name){
        //Resources r = getResources();
        //int drawableId = r.getIdentifier("drawableName", "drawable", "oceansfive.footymanager");

        int drawableId=0;
        try {
            Class res = R.drawable.class;
            Field field = res.getField(name);
            drawableId = field.getInt(null);
        }
        catch (Exception e) {
            Log.e("MyTag", "Failure to get drawable id.", e);
        }
        return drawableId;

    }

    // references to our images
    /*public Integer[] mThumbIds = {
            R.drawable.sample_7,R.drawable.sample_7,
            R.drawable.sample_7,R.drawable.sample_7,
            R.drawable.sample_7,R.drawable.sample_7,
            R.drawable.sample_7,R.drawable.sample_7,
            R.drawable.sample_7,R.drawable.sample_7,
            R.drawable.sample_7,R.drawable.sample_7,
            R.drawable.sample_7,R.drawable.sample_7,
            R.drawable.sample_7,R.drawable.sample_7,
            R.drawable.sample_7,R.drawable.sample_7,
            R.drawable.sample_7,R.drawable.sample_7,
            R.drawable.sample_7,R.drawable.sample_7,
            R.drawable.sample_7,R.drawable.sample_7,
            R.drawable.sample_7,R.drawable.sample_7,
            R.drawable.sample_7,R.drawable.sample_7,
            R.drawable.logo0,R.drawable.logo1,
            R.drawable.logo2,R.drawable.logo3,
            R.drawable.logo4
    };*/
}