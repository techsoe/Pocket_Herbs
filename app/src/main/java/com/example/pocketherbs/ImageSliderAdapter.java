package com.example.pocketherbs;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class ImageSliderAdapter extends PagerAdapter {

    private Context context;
    private int[] images;
    private ViewPager viewPager;
    private Handler handler;
    private Runnable runnable;
    private int delay = 3000; // Delay in milliseconds
    private int currentItem = 0;
    private ImageView imageView;
    private TextView textView;

    public ImageSliderAdapter(Context context, int[] images, ViewPager viewPager) {
        this.context = context;
        this.images = images;
        this.viewPager = viewPager;
        handler = new Handler();
        runnable = new Runnable() {
            public void run() {
                if (currentItem == images.length - 1) {
                    currentItem = 0;
                } else {
                    currentItem++;
                }
                viewPager.setCurrentItem(currentItem, true);
                handler.postDelayed(this, delay);
            }
        };
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.slide_item, container, false);

        imageView = view.findViewById(R.id.imageSlideView);
        imageView.setImageResource(images[position]);
        textView = view.findViewById(R.id.textSlideView);
        textView.setText(getTextForPosition(position));

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    public void startSlider() {
        handler.postDelayed(runnable, delay);
    }

    public void stopSlider() {
        handler.removeCallbacks(runnable);
    }


    private String getTextForPosition(int position) {
        // Define the array of texts corresponding to each image position
        String[] texts = {
                "Acapulco",
                "Ampalaya",
                "Bayabas",
                "Kataka taka",
                "Lagundi",
                "Oregano",
                "Sambong"
        };

        // Make sure the position is within the array bounds
        if (position >= 0 && position < texts.length) {
            return texts[position];
        }

        return ""; // Return empty string for invalid positions
    }


}
