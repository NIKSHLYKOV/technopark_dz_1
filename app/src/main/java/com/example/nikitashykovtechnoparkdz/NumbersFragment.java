package com.example.nikitashykovtechnoparkdz;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class NumbersFragment extends Fragment {

    // Тег для логирования.
    private String LOG_TAG = "NumbersFragment";

    // Ключ для сохранения количества чисел.
    private static final String KEY_COUNT_NUMBER = "count";

    // Список с числами.
    private ArrayList<Integer> numbers;
    // RecyclerView и адаптер для него.
    private RecyclerView recyclerView;
    private MyRecyclerViewAdapter adapter;

    // Интерфейс для взаимодействия с Activity.
    private ReportListener reportListener;

    private Context context;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.i(LOG_TAG, "onAttach");

        // Получаем контекст и присваиваем слушателя.
        this.context = context;
        reportListener = (ReportListener) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(LOG_TAG, "onCreate");

        // Определяем, какое количество чисел будет отображаться.
        // Если фрагмент пересоздаётся, то берём последнее числов из Bundle.
        // Иначе берём базовое - 100.
        int countNumbers = 100;
        if (savedInstanceState != null)
            countNumbers = savedInstanceState.getInt(KEY_COUNT_NUMBER);

        // Заполняем список чисел для последующей передачи его адаптеру.
        numbers = new ArrayList<>();
        for (int i = 1; i <= countNumbers; i++) {
            numbers.add(i);
        }

        // Создаём adapter для RecyclerView.
        adapter = new MyRecyclerViewAdapter(context);

        // Закидываем список чисел в адаптер.
        adapter.setNumbers(numbers);

        // Устанавливаем обработчик нажатия на элемент RecyclerView.
        adapter.setOnEntryClickListener(new MyRecyclerViewAdapter.OnEntryClickListener() {
            @Override
            public void onEntryClick(View view, int position) {
                // Получаем число из элемента.
                int number = MyRecyclerViewAdapter.getNumbers().get(position);

                // Подбираем цвет текста.
                int color;
                if (number % 2 == 0)
                    color = R.color.red;
                else
                    color = R.color.blue;

                // Отправляем число с цветом в Activity.
                reportListener.reportNumber(MyRecyclerViewAdapter.getNumbers().get(position), color);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(LOG_TAG, "onCreateView");

        // Получаем нашу разметку для фрагмента.
        View view = inflater.inflate(R.layout.fragment_numbers, null);

        // Находим View элементы.
        recyclerView = view.findViewById(R.id.fragment_numbers___recycler_view___numbers);
        Button newNumberButton = view.findViewById(R.id.fragment_numbers___button___new_word);

        // Присваиваем обработчик нажатия на кнопку добавления нового числа.
        newNumberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numbers.add(numbers.get(numbers.size() - 1) + 1);
                adapter.setNumbers(numbers);
            }
        });

        // Возвращаем View для отрисовки.
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(LOG_TAG, "onStart");

        // Находим необходимое количество колонок для RecyclerView.
        // Для горизонтальной ориентации - 4 колонки.
        // Для других случаев (вертикальная/квадратная/неопределена)- 3 колонки.
        int spanCount = 3;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            spanCount = 4;

        // Устанавливаем RecyclerView GridLayoutManager с количеством колонок.
        recyclerView.setLayoutManager(new GridLayoutManager(context, spanCount));

        // Добавляем в RecyclerView разделители по вертикали и по горизонтали, чтобы
        // было лучше видно саму таблицу из чисел.
        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL));

        // Соединяем RecyclerView с адаптером для него.
        recyclerView.setAdapter(adapter);
    }

    public interface ReportListener {
        void reportNumber(int number, int resColorId);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(LOG_TAG, "onSaveInstanceState");

        // Сохраняем количество чисел.
        outState.putInt(KEY_COUNT_NUMBER, numbers.size());
    }


    // Далее идут методы для просмотра взаимодействия между Activity и Fragment.

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(LOG_TAG, "onDetach");
        reportListener = null;
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
}
