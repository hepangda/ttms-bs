package com.hepangda.ttms.idao;

import com.hepangda.ttms.model.Studio;

import java.util.ArrayList;

public interface IStudioDAO {
    int Add_Studio(String name,int type,int row,int column);
    int Delete_Studio(String name);
    int Modify_Studio(String old_name,String new_name);
    ArrayList<Studio> GetStduios(int begin, int end);
}
