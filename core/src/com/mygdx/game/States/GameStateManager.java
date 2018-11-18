package com.mygdx.game.States;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class GameStateManager {
    private Stack<State> states;

    public GameStateManager(Stack<State> states) {
        this.states = states;
    }

    //добавить элемент в вершину стека
    public void push(State state){
        states.push(state);
    }

    //извлекаем  верхний элемент и удаляем его
    public void pop(){
        states.pop().dispose();
    }

    //удаляем верхний экран и помещать след экран в вершину стека
    public  void set(State state){
        states.pop().dispose();
        states.push(state);
    }

    //через дельта возвращаем верхний элемент
    public void update(float delta){
        states.peek().update(delta);
    }

    //принимает верхнее состояние стека и отрисосывает его
    public void render(SpriteBatch batch){
        states.peek().render(batch);
    }
}
