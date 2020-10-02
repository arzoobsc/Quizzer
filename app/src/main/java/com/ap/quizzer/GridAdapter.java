package com.ap.quizzer;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.List;

public class GridAdapter extends BaseAdapter {

    private List<String> sets;
    private String category;
    private InterstitialAd interstitialAd;

    public GridAdapter(List<String> sets, String category, InterstitialAd interstitialAd) {
        this.category = category;
        this.sets = sets;
        this.interstitialAd = interstitialAd;

    }

    @Override
    public int getCount() {
        return sets.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        View view;

        if (convertView == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.set_item, parent, false);

        }else {
            view = convertView;
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                interstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdClosed() {
                        super.onAdClosed();
                        interstitialAd.loadAd(new AdRequest.Builder().build());

                        Intent questionIntent = new Intent(parent.getContext(), QuestionsActivity.class);
                        questionIntent.putExtra("category", category);
                        questionIntent.putExtra("setId", sets.get(position));
                        parent.getContext().startActivity(questionIntent);
                    }
                });

                if (interstitialAd.isLoaded()){
                    interstitialAd.show();
                    return;
                }
                Intent questionIntent = new Intent(parent.getContext(), QuestionsActivity.class);
                questionIntent.putExtra("category", category);
                questionIntent.putExtra("setId", sets.get(position));
                parent.getContext().startActivity(questionIntent);
            }
        });

        ((TextView)view.findViewById(R.id.textView)).setText(String.valueOf(position+1));

        return view;
    }
}
