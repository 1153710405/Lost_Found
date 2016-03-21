package com.example.a481.lostfound;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import cn.bmob.v3.*;
import cn.bmob.v3.listener.FindListener;


import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.*;

public class MainActivity extends Activity {

    private List<Item> ItemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bmob.initialize(this, "63cb3a4e63bd82bb7b18ace75b24029e");
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        Button button1 = (Button) findViewById(R.id.User_Publish);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Start_publish = new Intent(MainActivity.this, PublishActivity.class);
                startActivity(Start_publish);
            }
        });


        initItems();
    }

    private void initItems() {
        BmobQuery<Item> item = new BmobQuery<>();
        item.findObjects(MainActivity.this, new FindListener<Item>() {

            @Override
            public void onSuccess(List<Item> list) {
                for (Item aitem : list) {
                    Log.d("MainActivity", aitem.getUserID().toString());
                    Log.d("MainActivity", aitem.getKind());
                    Log.d("MainActivity", aitem.getTime());
                    Log.d("MainActivity", aitem.getPlace());
                    Log.d("MainActivity", aitem.getDescription());
                    ItemList.add(aitem);
                    if (ItemList.isEmpty()){
                        Log.d("testItemList", "1"); }
                }
                if (ItemList.isEmpty()){
                    Log.d("testItemList", "2"); }
                ItemAdapter adapter = new ItemAdapter(MainActivity.this, R.layout.item_layout, ItemList);
                ListView listview = (ListView) findViewById(R.id.item_View);
                listview.setAdapter(adapter);
            }

            @Override
            public void onError(int i, String s) {

            }
        });
    }

    public class ItemAdapter extends ArrayAdapter<Item> {
        private int resourceId;
        public ItemAdapter(Context context, int textViewResourceId, List<Item> objects) {
            super (context, textViewResourceId, objects);
            Log.d("ItemAdapter", "1");
            resourceId = textViewResourceId;
        }

        @Override
        public int getCount() {
            Log.d("testgetCount", String.valueOf(ItemList.size()));
            return ItemList.size();
        }

        @Override
        public Item getItem(int position) {
            Log.d("testgetItem", String.valueOf(ItemList.get(position)));
            return ItemList.get(position);
        }

        @Override
        public long getItemId(int position) {
            Log.d("getItemIdtest", String.valueOf(position));
            return position;
        }



        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            class ViewHolder {
                TextView ItemKind;
                TextView ItemTime;
                TextView ItemPlace;
            }

            Item item = getItem(position);
            View view;
            ViewHolder viewHolder;
            Log.d("ItemAdapter", "2");

            if (convertView == null) {
                view = LayoutInflater.from(getContext()).inflate(resourceId, null);
                viewHolder = new ViewHolder();
                viewHolder.ItemKind = (TextView) view.findViewById(R.id.Item_Kind);
                viewHolder.ItemTime = (TextView) view.findViewById(R.id.Item_Time);
                viewHolder.ItemPlace = (TextView) view.findViewById(R.id.Item_Place);
                view.setTag(viewHolder);
            } else {
                view = convertView;
                viewHolder = (ViewHolder) view.getTag();
            }
            String aKind = "种类：" + item.getKind();
            String aTime = "时间：" + item.getTime();
            String aPlace = "地点：" + item.getPlace();
            Log.d("ItemAdapter", item.getKind());
            viewHolder.ItemKind.setText(aKind);
            viewHolder.ItemTime.setText(aTime);
            viewHolder.ItemPlace.setText(aPlace);
            return view;
        }
    }
}
