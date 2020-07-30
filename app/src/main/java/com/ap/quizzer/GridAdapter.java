package com.ap.quizzer;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class GridAdapter extends BaseAdapter {

    private int sets = 0;
    private String category;

    public GridAdapter(int sets, String category) {
        this.category = category;
        this.sets = sets;
    }

    @Override
    public int getCount() {
        return sets;
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
                Intent questionIntent = new Intent(parent.getContext(), QuestionsActivity.class);
                questionIntent.putExtra("category", category);
                questionIntent.putExtra("setNo", position+1);
                parent.getContext().startActivity(questionIntent);
            }
        });

        ((TextView)view.findViewById(R.id.textView)).setText(String.valueOf(position+1));

        return view;
    }
}
