package com.joey.android.bestprice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by Joey on 3/4/2017.
 */

public class SQLiteListAdapater extends BaseAdapter {
    Context mContext;
    ArrayList<String> id;
    ArrayList<String> itemName;
    ArrayList<String> price1;
    ArrayList<String> market1;
    ArrayList<String> price2;
    ArrayList<String> market2;

    public SQLiteListAdapater(Context context, ArrayList<String> id, ArrayList<String> itemName,
                              ArrayList<String> price1, ArrayList<String> market1, ArrayList<String> price2, ArrayList<String> market2 ){
        this.mContext = context;
        this.id = id;
        this.itemName = itemName;
        this.price1 = price1;
        this.market1 = market1;
        this.price2 = price2;
        this.market2 = market2;
    }

    public int getCount(){
        return id.size();
    }

    public Objects getItem(int position){
        return null;
    }

    public long getItemId(int position){
        return 0;
    }

    public View getView(int position, View Child, ViewGroup parent){
        Holder holder;

        LayoutInflater layoutInflater;

        if(Child == null){
            layoutInflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
            Child = layoutInflater.inflate(R.layout.listviewdatalayout, null);

            holder = new Holder();

            holder.mTextViewid = (TextView)Child.findViewById(R.id.textViewID);
            holder.mTextViewName = (TextView)Child.findViewById(R.id.textViewNAME);
            holder.mTextViewPrice1 = (TextView)Child.findViewById(R.id.textViewPrice1);
            holder.mTextViewMarket1 = (TextView)Child.findViewById(R.id.textViewMarket1);
            holder.mTextViewPrice2 = (TextView)Child.findViewById(R.id.textViewPrice2);
            holder.mTextViewMarket2 = (TextView)Child.findViewById(R.id.textViewMarket2);

            Child.setTag(holder);
        }
        else{
            holder = (Holder) Child.getTag();
        }
        holder.mTextViewid.setText(id.get(position));
        holder.mTextViewName.setText(itemName.get(position));
        holder.mTextViewPrice1.setText(price1.get(position));
        holder.mTextViewMarket1.setText(market1.get(position));
        holder.mTextViewPrice2.setText(price2.get(position));
        holder.mTextViewMarket2.setText(market2.get(position));

        return Child;
    }

    public class Holder{
        TextView mTextViewid;
        TextView mTextViewName;
        TextView mTextViewPrice1;
        TextView mTextViewMarket1;
        TextView mTextViewPrice2;
        TextView mTextViewMarket2;
    }

}
