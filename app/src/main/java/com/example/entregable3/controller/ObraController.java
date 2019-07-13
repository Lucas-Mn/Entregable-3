package com.example.entregable3.controller;

import com.example.entregable3.model.dao.ObraDAO;
import com.example.entregable3.model.pojo.ObraContainer;
import com.example.entregable3.retrofit.ResultListener;

public class ObraController {

    public static void getObras(final ResultListener<ObraContainer> listener)
    {
        ObraDAO dao = new ObraDAO();
        dao.getObras(listener);
    }

}
