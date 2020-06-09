package home.petrus.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class Level1 extends AppCompatActivity {

    Dialog dialog;
    Dialog dialogEnd;

    public int numLeft; //Переменная для левой картинки + текст
    public int numRight; //Переменная  для правой картинки + текст
    Array array = new Array(); //Создали новый объект из класса Array
    Random random = new Random(); //Генерации случайных чисел
    public int count = 0; //Счетчик правельных ответов

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);

        //Создаем переменную text_levels
        TextView text_levels = findViewById(R.id.text_levels);
        text_levels.setText(R.string.level1); //Установили текст

        final ImageView img_left = (ImageView)findViewById(R.id.img_left);
        // код который скругляет углы левой картинки
        img_left.setClipToOutline(true);

        final ImageView img_right = (ImageView)findViewById(R.id.img_right);
        // код который скругляет углы правой картинки
        img_right.setClipToOutline(true);

        //Путь к левой TextView
        final TextView text_left = findViewById(R.id.text_left);
        //Путь к правой TextView
        final TextView text_right = findViewById(R.id.text_right);

        // Развернуть игру на весь экран - начало
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // Развернуть игру на весь экран - конец

        //Вызов диалогового окна в начале игры
        dialog = new Dialog(this); //создаем новое диалоговое окно
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //скрываем заголовок
        dialog.setContentView(R.layout.previewdialog); // путь к макету диалогового окна
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); //прозрачный фон диалогового окна
        dialog.setCancelable(false); //окно нельзя закрыть кнопкой "Назад"

        //кнопка которая закрывет диалоговое окно - "Начало"
        TextView btnclose = (TextView)dialog.findViewById(R.id.btnclose);
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //обрабвтываем нажатие кнопки - начало
                try {
                    //вернуться назад к выбору уровня - начало
                    Intent intent = new Intent(Level1.this, GameLevels.class); //создали намерение для перехода
                    startActivity(intent); //старт намерения
                    finish(); //закрыть этот класс
                    //вернуться назад к выбору уровня - конец
                }catch (Exception e) {
                    //здесь кода не будет
                }
                dialog.dismiss(); //закрываем диалоговое окно
                //обрабвтываем нажатие кнопки - конец
            }
        });
        //кнопка которая закрывет диалоговое окно - "Конец"

        //Кнопка "ПРОДОЛЖИТЬ" - начало
        Button btncontinue = (Button)dialog.findViewById(R.id.btncontinue);
        btncontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss(); //закрываем диалоговое окно
            }
        });
        //Кнопка "ПРОДОЛЖИТЬ" - конец

        dialog.show(); //показать диалоговое окно

        //__________________________________________________________________________________________
        //Вызов диалогового окна в конце игры
        dialogEnd = new Dialog(this); //создаем новое диалоговое окно
        dialogEnd.requestWindowFeature(Window.FEATURE_NO_TITLE); //скрываем заголовок
        dialogEnd.setContentView(R.layout.dialogend); // путь к макету диалогового окна
        dialogEnd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); //прозрачный фон диалогового окна
        dialogEnd.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialogEnd.setCancelable(false); //окно нельзя закрыть кнопкой "Назад"

        //кнопка которая закрывет диалоговое окно - "Начало"
        TextView btnclose2 = (TextView)dialogEnd.findViewById(R.id.btnclose);
        btnclose2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //обрабвтываем нажатие кнопки - начало
                try {
                    //вернуться назад к выбору уровня - начало
                    Intent intent = new Intent(Level1.this, GameLevels.class); //создали намерение для перехода
                    startActivity(intent); //старт намерения
                    finish(); //закрыть этот класс
                    //вернуться назад к выбору уровня - конец
                }catch (Exception e) {
                    //здесь кода не будет
                }
                dialogEnd.dismiss(); //закрываем диалоговое окно
                //обрабвтываем нажатие кнопки - конец
            }
        });
        //кнопка которая закрывет диалоговое окно - "Конец"

        //Кнопка "ПРОДОЛЖИТЬ" - начало
        Button btncontinue2 = (Button)dialogEnd.findViewById(R.id.btncontinue);
        btncontinue2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Level1.this, Level2.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e) {
                    //Здесь кода не будет
                }
                dialog.dismiss(); //закрываем диалоговое окно
            }
        });
        //Кнопка "ПРОДОЛЖИТЬ" - конец
        //__________________________________________________________________________________________

        //Кнопка "Назад" - наччало
        Button btn_back = (Button)findViewById(R.id.button_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Обрабатываем нажатие кнопки "Назад" - начало
                try {
                    //Вернуться назад к выбору уровня - начало
                    Intent intent = new Intent(Level1.this, GameLevels.class); //Создали намерение для перехода
                    startActivity(intent); //Старт намерения
                    finish(); //Закрыть этот класс
                    //Вернуться назад к выбору уровня - конец
                }catch (Exception e) {
                    //Здесь кода не будет
                }
                //Обрабатываем нажатие кнопки "Назад" - конец
            }
        });
        //Кнопка "Назад" - конец

        //Массив для прогресса игры - начало
        final int[] progress = {
                R.id.point1, R.id.point2, R.id.point3, R.id.point4, R.id.point5,
                R.id.point6, R.id.point7, R.id.point8, R.id.point9, R.id.point10,
                R.id.point11, R.id.point12, R.id.point13, R.id.point14, R.id.point15,
                R.id.point16, R.id.point17, R.id.point18, R.id.point19, R.id.point20,
        };
        //Массив для прогресса игры - конец

        //Подключаем анимацию - начало
        final Animation a = AnimationUtils.loadAnimation(Level1.this, R.anim.alpha);
        //Подключаем анимацию - конец

        numLeft = random.nextInt(10); //Генерируем случайное число от 0 до 9
        img_left.setImageResource(array.images1[numLeft]); //Достаем из массива картинку
        text_left.setText(array.texts1[numLeft]); //Достаем из массива текст

        numRight = random.nextInt(10); //Генерируем случайное число от 0 до 9

        //Цикл с предусловием, проверяющий равенство чисел - начало
        while (numLeft==numRight) {
            numRight = random.nextInt(10);
        }
        //Цикл с предусловием, проверяющий равенство чисел - конец
        img_right.setImageResource(array.images1[numRight]); //Достаем из массива картинку
        text_right.setText(array.texts1[numRight]); //Достаем из массива текст

        //Обрабатываем нажатие на левую картинку - начало
        img_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //Условия косания картинки - начало
                if (event.getAction()==MotionEvent.ACTION_DOWN) {
                    //Если коснулся картинки - начало
                    img_right.setEnabled(false); //Блокируем правую картинку
                    if (numLeft>numRight) {
                        img_left.setImageResource(R.drawable.img_true);
                    }else{
                        img_left.setImageResource(R.drawable.img_false);
                    }
                    //Если коснулся картинки - конец
                }else if (event.getAction()==MotionEvent.ACTION_UP) {
                    //Если отпустил палец - начало
                    if (numLeft > numRight) {
                        //Если левая картинка больше
                        if (count < 20) {
                            count = count + 1;
                        }
                        //Закрашиваем прогресс серым цветом - начало
                        for (int i = 0; i < 20; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //Закрашиваем прогресс серым цветом - конец

                        //Определяем правильные ответы и закрашиваем зеленым - начало
                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                        //Определяем правильные ответы и закрашиваем зеленым - конец
                    }else{
                        //Если левая картинка меньше
                        if (count > 0) {
                            if (count == 1) {
                                count = 0;
                            } else {
                                count = count - 2;
                            }
                            //Закрашиваем прогресс серым цветом - начало
                            for (int i = 0; i < 19; i++) {
                                TextView tv = findViewById(progress[i]);
                                tv.setBackgroundResource(R.drawable.style_points);
                            }
                            //Закрашиваем прогресс серым цветом - конец

                            //Определяем правильные ответы и закрашиваем зеленым - начало
                            for (int i = 0; i < count; i++) {
                                TextView tv = findViewById(progress[i]);
                                tv.setBackgroundResource(R.drawable.style_points_green);
                            }
                            //Определяем правильные ответы и закрашиваем зеленым - конец
                        }
                    }
                    //Если отпустил палец - конец
                    if (count == 20) {
                        //ВЫХОД ИЗ УРОВНЯ
                        dialogEnd.show();
                    } else {
                        numLeft = random.nextInt(10); //Генерируем случайное число от 0 до 9
                        img_left.setImageResource(array.images1[numLeft]); //Достаем из массива картинку
                        img_left.startAnimation(a);
                        text_left.setText(array.texts1[numLeft]); //Достаем из массива текст

                        numRight = random.nextInt(10); //Генерируем случайное число от 0 до 9

                        //Цикл с предусловием, проверяющий равенство чисел - начало
                        while (numLeft==numRight) {
                            numRight = random.nextInt(10);
                        }
                        //Цикл с предусловием, проверяющий равенство чисел - конец
                        img_right.setImageResource(array.images1[numRight]); //Достаем из массива картинку
                        img_right.startAnimation(a);
                        text_right.setText(array.texts1[numRight]); //Достаем из массива текст

                        img_right.setEnabled(true); //Включаем обратно правую картинку
                    }
                }
                //Условия косания картинки - конец
                return true;
            }
        });
        //Обрабатываем нажатие на левую картинку - конец

        //Обрабатываем нажатие на правую картинку - начало
        img_right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //Условия косания картинки - начало
                if (event.getAction()==MotionEvent.ACTION_DOWN) {
                    //Если коснулся картинки - начало
                    img_left.setEnabled(false); //Блокируем левую картинку
                    if (numLeft<numRight) {
                        img_right.setImageResource(R.drawable.img_true);
                    } else {
                        img_right.setImageResource(R.drawable.img_false);
                    }
                    //Если коснулся картинки - конец
                }else if (event.getAction()==MotionEvent.ACTION_UP) {
                    //Если отпустил палец - начало
                    if (numLeft < numRight) {
                        //Если правая картинка больше
                        if (count < 20) {
                            count = count + 1;
                        }
                        //Закрашиваем прогресс серым цветом - начало
                        for (int i = 0; i < 20; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //Закрашиваем прогресс серым цветом - конец

                        //Определяем правильные ответы и закрашиваем зеленым - начало
                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                        //Определяем правильные ответы и закрашиваем зеленым - конец
                    } else {
                        //Если правая картинка меньше
                        if (count > 0) {
                            if (count == 1) {
                                count = 0;
                            } else {
                                count = count - 2;
                            }
                            //Закрашиваем прогресс серым цветом - начало
                            for (int i = 0; i < 19; i++) {
                                TextView tv = findViewById(progress[i]);
                                tv.setBackgroundResource(R.drawable.style_points);
                            }
                            //Закрашиваем прогресс серым цветом - конец

                            //Определяем правильные ответы и закрашиваем зеленым - начало
                            for (int i = 0; i < count; i++) {
                                TextView tv = findViewById(progress[i]);
                                tv.setBackgroundResource(R.drawable.style_points_green);
                            }
                            //Определяем правильные ответы и закрашиваем зеленым - конец
                        }
                    }
                    //Если отпустил палец - конец
                    if (count == 20) {
                        //ВЫХОД ИЗ УРОВНЯ
                        dialogEnd.show();
                    } else {
                        numLeft = random.nextInt(10); //Генерируем случайное число от 0 до 9
                        img_left.setImageResource(array.images1[numLeft]); //Достаем из массива картинку
                        img_left.startAnimation(a);
                        text_left.setText(array.texts1[numLeft]); //Достаем из массива текст

                        numRight = random.nextInt(10); //Генерируем случайное число от 0 до 9

                        //Цикл с предусловием, проверяющий равенство чисел - начало
                        while (numLeft==numRight) {
                            numRight = random.nextInt(10);
                        }
                        //Цикл с предусловием, проверяющий равенство чисел - конец
                        img_right.setImageResource(array.images1[numRight]); //Достаем из массива картинку
                        img_right.startAnimation(a);
                        text_right.setText(array.texts1[numRight]); //Достаем из массива текст

                        img_left.setEnabled(true); //Включаем обратно левую картинку
                    }
                }
                //Условия косания картинки - конец
                return true;
            }
        });
        //Обрабатываем нажатие на правую картинку - конец
    }
    //Системная кнопка "Назад" - начало
    @Override
    public void onBackPressed() {
        //Обрабатываем нажатие кнопки "Назад" - начало
        try {
            //Вернуться назад к выбору уровня - начало
            Intent intent = new Intent(Level1.this, GameLevels.class); //Создали намерение для перехода
            startActivity(intent); //Старт намерения
            finish(); //Закрыть этот класс
            //Вернуться назад к выбору уровня - конец
        } catch (Exception e) {
            //Здесь кода не будет
        }
        //Обрабатываем нажатие кнопки "Назад" - конец
    }
    //Системная кнопка "Назад" - конец
}
























