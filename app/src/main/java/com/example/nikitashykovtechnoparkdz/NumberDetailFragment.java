package com.example.nikitashykovtechnoparkdz;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class NumberDetailFragment extends Fragment {

    // Тег для логирования.
    private String LOG_TAG = "NumberDetailFragment";

    // Теги для Extra.
    public static final String EXTRA_NUMBER = "Number";
    public static final String EXTRA_TEXT_COLOR = "TextColor";

    // Отображаемое число, переданное от Activity.
    private int number;
    private int color;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(LOG_TAG, "onCreate");

        // Получаем число.
        try {
            number = getArguments().getInt(EXTRA_NUMBER);
        } catch (NullPointerException e){
            number = 0;
        }

        // Получаем цвет текста.
        try {
            color = getArguments().getInt(EXTRA_TEXT_COLOR);
        }catch (NullPointerException e){
            color = R.color.colorPrimary;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(LOG_TAG, "onCreateView");

        // Получаем разметку для фрагмента.
        View view = inflater.inflate(R.layout.fragment_number_detail, null);

        // Находим TextView и устанавливаем в него цвет и текст (число).
        TextView tvNumber = view.findViewById(R.id.fragment_number_detail___text_view___number);
        tvNumber.setTextColor(getResources().getColor(color));
        tvNumber.setText(String.valueOf(number));

        // Возвращаем View для отрисовки.
        return view;
    }


    // Далее идут методы для просмотра взаимодействия между Activity и Fragment.

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(LOG_TAG, "onDetach");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(LOG_TAG, "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(LOG_TAG, "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(LOG_TAG, "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(LOG_TAG, "onDestroy");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(LOG_TAG, "onResume");
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.i(LOG_TAG, "onAttach");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(LOG_TAG, "onStart");
    }
}
