package com.example.nikitashykovtechnoparkdz;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.NumbersViewHolder> {

    private Context context;

    // Список с отображаемыми числами.
    private static ArrayList<Integer> numbers = new ArrayList<>();

    // Интерфейс для обработки клика на элемент списка.
    private OnEntryClickListener mOnEntryClickListener;

    public interface OnEntryClickListener {
        void onEntryClick(View view, int position);
    }

    public void setOnEntryClickListener(OnEntryClickListener onEntryClickListener) {
        mOnEntryClickListener = onEntryClickListener;
    }


    class NumbersViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // TextView, в который мы будем устанавливать число.
        private TextView number;

        NumbersViewHolder(View itemView) {
            super(itemView);
            // Получаем TextView для числа из разметки.
            number = itemView.findViewById(R.id.recycler_view_item___text_view___number);
            // Устанавливаем обработчик нажатия на элемент списка.
            itemView.setOnClickListener(this);
        }

        /**
         * Обрабатывает клик на элемент списка.
         *
         * @param v представляет из себя view, которая ялвяется корневой в элементе списка.
         */
        @Override
        public void onClick(View v) {
            // Проверяем наш слушатель нажатия на null, т.к. он мог быть не установлен в Activity
            // или Fragment, который реализует интерфейс.
            if (mOnEntryClickListener != null) {
                mOnEntryClickListener.onEntryClick(v, getLayoutPosition());
            }
        }
    }

    public MyRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return numbers.size();
    }

    @NonNull
    @Override
    public NumbersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item, parent, false);
        return new NumbersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyRecyclerViewAdapter.NumbersViewHolder holder, final int position) {
        // Получаем число и TextView, которая будет его отображать.
        final Integer currentNumber = numbers.get(position);
        TextView numberTextView = holder.number;

        // Находим необходимый цвет для числа и устанавливаем его для TextView.
        int color;
        if (currentNumber % 2 == 0)
            color = R.color.red;
        else
            color = R.color.blue;
        numberTextView.setTextColor(context.getResources().getColor(color));

        // Устанавливаем текст (число) в TextView.
        numberTextView.setText(String.valueOf(currentNumber));
    }

    /**
     * Устанавливает числа в наш список чисел.
     *
     * @param numbers представляет список чисел.
     */
    public void setNumbers(ArrayList<Integer> numbers) {
        // Устанавливаем переданный список чисел.
        this.numbers = numbers;
        // Уведомляем о том, что данные поменялись.
        notifyDataSetChanged();
    }

    public static ArrayList<Integer> getNumbers() {
        return numbers;
    }
}

