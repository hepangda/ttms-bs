package com.hepangda.ttms.idao;

import com.hepangda.ttms.model.Studio;

import java.util.ArrayList;

public interface IStudioDAO {
    int add(String name,int type,int row,int column);
    int delete(String name);
    int modify(String old_name,String new_name);
    ArrayList<Studio> get(int begin, int end);
}
