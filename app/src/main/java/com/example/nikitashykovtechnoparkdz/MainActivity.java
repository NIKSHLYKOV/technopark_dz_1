package com.example.nikitashykovtechnoparkdz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements NumbersFragment.ReportListener{

    // Тег для логирования.
    private static final String LOG_TAG = "MainActivity";

    // Тэги для фрагментов.
    private static final String TAG_NUMBERS_FRAGMENT = "NumbersFragment";
    private static final String TAG_NUMBER_DETAIL_FRAGMENT = "NumberDetailFragment";

    // id layout'а, в который будет помещаться тот или иной фрагмент.
    private int contentLayoutId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(LOG_TAG, "onCreate");
        setContentView(R.layout.activity_main);

        // Получение id layout'а для контента.
        LinearLayoutCompat contentLayout = findViewById(R.id.activity_main___linear_layout_compat___content_layout);
        contentLayoutId = contentLayout.getId();

        // Проверяем на пустой Bundle, чтобы создавать фрагмент только при первоначальном запуске Activity.
        if(savedInstanceState == null){
            // Получаем менеджер фрагментов и совершаем транзакцию добавления фрагмента с RecyclerView.
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(contentLayoutId, new NumbersFragment(), TAG_NUMBERS_FRAGMENT)
                    .commit();
        }
    }

    /**
     * Обрабатывает отправку числа фрагментом со списком чисел.
     * @param number представляет из себя число, на которое мы нажали.
     */
    @Override
    public void reportNumber(int number, int resColorId) {
        Log.i(LOG_TAG, "reportNumber");
        // Создаём Bundle для передачи числа в детализирующий фрагмент.
        Bundle arguments = new Bundle();
        // Создаём детализирующий фрагмент.
        NumberDetailFragment numberDetailFragment = new NumberDetailFragment();
        // Записываем в Bundle передаваемое число, и устанавливаем его в детализирующий фрагмент.
        arguments.putInt(NumberDetailFragment.EXTRA_NUMBER, number);
        arguments.putInt(NumberDetailFragment.EXTRA_TEXT_COLOR, resColorId);
        numberDetailFragment.setArguments(arguments);
        // Заменяем существующий фрагмент детализируемым, добавляя второй в стек, чтобы удалять его
        // по нажатию кнопки "Назад".
        getSupportFragmentManager()
                .beginTransaction()
                .replace(contentLayoutId, numberDetailFragment, TAG_NUMBER_DETAIL_FRAGMENT)
                .addToBackStack(null)
                .commit();
    }


    // Далее идут методы для просмотра взаимодействия между Activity и Fragment.

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(LOG_TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(LOG_TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(LOG_TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(LOG_TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(LOG_TAG, "onDestroy");
    }
}
