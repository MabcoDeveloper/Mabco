package com.example.mabco.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.bumptech.glide.Glide;
import com.example.mabco.R;
import com.example.mabco.SingletonSession;
import com.smarteist.autoimageslider.SliderViewAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderAdapterVH> {

    private Context context;
    private JSONArray mSliderItems = null;
    private boolean zoomOut = false;

    public SliderAdapter(Context context, JSONArray mSliderItems) {
        this.context = context;
        this.mSliderItems = mSliderItems;

    }

    public void renewItems(JSONArray sliderItems) {
        this.mSliderItems = sliderItems;
        notifyDataSetChanged();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void deleteItem(int position) {
        this.mSliderItems.remove(position);
        notifyDataSetChanged();
    }

    public void addItem(String sliderItem) {
//        this.mSliderItems.add(sliderItem);
//        notifyDataSetChanged();
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slider_layout_item, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(final SliderAdapterVH viewHolder, final int position) {

//        SliderItem sliderItem = mSliderItems.get(position);
        JSONObject pp = null;
        try {
            pp = mSliderItems.getJSONObject(position);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String slide_image_link = "https://" + pp.optString("slide_image_link");
//                String slide_image_link = "https://mabcoonline.com/images/Slides/ShopOnline26CoverMABCOSite.jpg";
        String title = pp.optString("title");
        String slide_link = pp.optString("slide_link");

        viewHolder.textViewDescription.setText(title);
        viewHolder.textViewDescription.setTextSize(16);
        viewHolder.textViewDescription.setTextColor(Color.WHITE);
        Glide.with(viewHolder.itemView.getContext()).load(slide_image_link)
//                .fitCenter()
                .into(viewHolder.imageViewBackground);
//        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
////            public void onClick(View v) {
////                String s = String.valueOf(position-1);
////
//////                Toast.makeText(context, "This is item in position " + position, Toast.LENGTH_SHORT).show();
////                SingletonSession.Instance().setPositionslider(position);
////                Intent intent = new Intent(context,FullSlide.class);
////                intent.putExtra("position",String.valueOf(position));
////                context.startActivity(intent);
////            }
//        });
    }


    static class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imageViewBackground;
        ImageView imageGifContainer;
        TextView textViewDescription;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
            imageGifContainer = itemView.findViewById(R.id.iv_gif_container);
            textViewDescription = itemView.findViewById(R.id.tv_auto_image_slider);
            this.itemView = itemView;
        }
    }


    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return mSliderItems.length();
    }
}
