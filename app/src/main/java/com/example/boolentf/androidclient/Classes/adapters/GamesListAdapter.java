package com.example.boolentf.androidclient.Classes.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.boolentf.androidclient.Classes.DATA.GameForTransaction;
import com.example.boolentf.androidclient.R;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by BoolenTF on 21.08.2016.
 */
public class GamesListAdapter extends RecyclerView.Adapter<GamesListAdapter.ViewHolder> {

    private List<GameForTransaction> mGameForTransaction;

    // класс view holder-а с помощью которого мы получаем ссылку на каждый элемент
    // отдельного пункта списка
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // наш пункт состоит только из одного TextView
        public TextView mTextView;

        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.game_item_name);
        }
    }

    // Конструктор
    public GamesListAdapter(List<GameForTransaction> dataset) {
        mGameForTransaction = dataset;
    }

    // Создает новые views (вызывается layout manager-ом)
    @Override
    public GamesListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.games_list_item, parent, false);

        // тут можно программно менять атрибуты лэйаута (size, margins, paddings и др.)

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Заменяет контент отдельного view (вызывается layout manager-ом)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.mTextView.setText(mGameForTransaction.get(position).getName());

    }

    // Возвращает размер данных (вызывается layout manager-ом)
    @Override
    public int getItemCount() {
        return mGameForTransaction.size();
    }
}
